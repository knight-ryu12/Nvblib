/*
 * Copyright 2019 NAFU_at
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

package net.nafusoft.nvblib.parser;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.nafusoft.nvblib.parser.meta.abs.Parser;
import net.nafusoft.nvblib.parser.meta.abs.Site;
import net.nafusoft.nvblib.utils.URLUtil;
import net.nafusoft.nvblib.utils.WordUtil;
import net.nafusoft.nvblib.voice.Voice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class IchikaArrayParser extends Parser {

    public IchikaArrayParser(Site site) {
        super(site);
    }

    @Override
    public List<Voice> getElements() {
        List<Voice> voices = new ArrayList<>();

        // Json取得処理
        JsonArray jsonAry;
        try (BufferedReader stream = new BufferedReader(new InputStreamReader(
                new URL(getSite().getStringURL() + getSite().getJsonLocation()).openStream(), StandardCharsets.UTF_8))) {
            jsonAry = new Gson().fromJson(stream, JsonArray.class);
        } catch (IOException e) {
            return voices;
        }

        String title;

        // Json読み込み処理
        for (JsonElement voiceElm : jsonAry) {
            JsonObject voiceObj = voiceElm.getAsJsonObject();
            title = voiceObj.get("serif").getAsString();
            String file = voiceObj.get("path").getAsString();
            file = URLUtil.format(file, "mp3");
            String urlStr = getSite().getStringURL() + getSite().getSoundLayer() + "/" + file;

            Voice voice = new Voice(getSite(), title, null, WordUtil.generateWord(title), urlStr, null);
            voices.add(voice);
        }
        return voices;
    }
}