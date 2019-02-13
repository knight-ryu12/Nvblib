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

import net.nafusoft.nvblib.parser.meta.abs.Parser;
import net.nafusoft.nvblib.parser.meta.abs.Site;
import net.nafusoft.nvblib.utils.URLUtil;
import net.nafusoft.nvblib.utils.WordUtil;
import net.nafusoft.nvblib.voice.Voice;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HTMLParser extends Parser {

    public HTMLParser(Site site) {
        super(site);
    }

    @Override
    public List<Voice> getElements() {
        List<Voice> voices = new ArrayList<>();

        // HTML取得・読み込み処理
        Document document;
        try {
            String url = getSite().getStringURL();
            if (getSite().getLayer() != null)
                url += getSite().getLayer();
            document = Jsoup.connect(url).get();
        } catch (IOException e) {
            return voices;
        }

        Element body = document.body();
        String category = null;

        for (Element element : body.select("*")) {
            // カテゴリとして直前の太字を登録
            if (element.tag().equals(Tag.valueOf("b"))) {
                if (getSite().isCategorySupport())
                    category = element.text();
                // soundsクラスの場合はボイスとして読み込み
            } else if (element.classNames().contains("sounds")) {
                String title = element.text();
                String file = element.attr("data-file");
                file = URLUtil.format(file, "mp3");
                String urlStr = getSite().getStringURL() + getSite().getSoundLayer() + "/" + file;

                Voice voice = new Voice(getSite(), title, category, WordUtil.generateWord(title), urlStr, null);
                voices.add(voice);
            }
        }
        return voices;
    }
}