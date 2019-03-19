/*
 * Copyright 2019 くまねこそふと
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.pandasoft.nvblib.parser;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.pandasoft.nvblib.parser.meta.abs.Parser;
import dev.pandasoft.nvblib.parser.meta.abs.Site;
import dev.pandasoft.nvblib.utils.URLUtil;
import dev.pandasoft.nvblib.utils.WordUtil;
import dev.pandasoft.nvblib.voice.IVoice;
import dev.pandasoft.nvblib.voice.Voice;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class TomariJsonParser extends Parser {

    public TomariJsonParser(Site site) {
        super(site);
        log.trace("This parser was called by {}.", site.getName());
    }

    @Override
    public List<IVoice> getElements() {
        List<IVoice> voices = new ArrayList<>();

        // Json取得処理
        JsonObject jsonObj;
        try (BufferedReader stream = new BufferedReader(new InputStreamReader(
                new URL(getSite().getStringURL() + getSite().getJsonLocation()).openStream(), StandardCharsets.UTF_8))) {
            jsonObj = new Gson().fromJson(stream, JsonObject.class);
        } catch (IOException e) {
            log.trace("Failed to communicate data with the site.", e);
            return voices;
        }

        String title;
        String category;

        //Json読み込み処理
        JsonArray tracks = jsonObj.get("tracks").getAsJsonArray();
        for (JsonElement trackElm : tracks) {
            JsonObject voiceObj = trackElm.getAsJsonObject();
            title = voiceObj.get("title").getAsString();
            category = voiceObj.get("tags").getAsJsonArray().get(0).getAsString();
            String file = voiceObj.get("path").getAsString();
            file = URLUtil.format(file);
            String urlStr = getSite().getStringURL() + getSite().getSoundLayer() + "/" + file;

            Voice voice = new Voice(null, getSite(), title, category, WordUtil.generateWord(title), file, urlStr);
            voices.add(voice);
        }
        log.trace("Content loaded. {}", voices.toString());
        return voices;
    }
}
