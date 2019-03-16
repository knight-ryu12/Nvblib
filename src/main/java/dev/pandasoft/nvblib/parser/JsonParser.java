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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class JsonParser extends Parser {

    public JsonParser(Site site) {
        super(site);
    }

    @Override
    public List<IVoice> getElements() {
        List<IVoice> voicesList = new ArrayList<>();

        // Json取得処理
        JsonObject jsonObj;
        try (BufferedReader stream = new BufferedReader(new InputStreamReader(
                new URL(getSite().getStringURL() + getSite().getJsonLocation()).openStream(), StandardCharsets.UTF_8))) {
            jsonObj = new Gson().fromJson(stream, JsonObject.class);
        } catch (IOException e) {
            return voicesList;
        }

        String title;
        String category;

        //Json読み込み処理
        JsonArray categories = jsonObj.get("category").getAsJsonArray();
        for (JsonElement categoryElm : categories) {
            // カテゴリーの処理
            JsonObject categoryObj = categoryElm.getAsJsonObject();
            category = categoryObj.get("name").getAsString();

            // ボイス一覧の取得
            JsonArray voices = categoryObj.get("voices").getAsJsonArray();
            for (JsonElement voiceElm : voices) {
                JsonObject voiceObj = voiceElm.getAsJsonObject();
                title = voiceObj.get("title").getAsString();
                String file = voiceObj.get("file").getAsString();
                file = URLUtil.format(file, "mp3");
                String urlStr = getSite().getStringURL() + getSite().getSoundLayer() + "/" + file;

                Voice voice = new Voice(null, getSite(), title, category, WordUtil.generateWord(title), file, urlStr);
                voicesList.add(voice);
            }
        }
        return voicesList;
    }
}
