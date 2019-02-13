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

package net.nafusoft.nvblib.registry;

import net.nafusoft.nvblib.parser.meta.abs.IParser;
import net.nafusoft.nvblib.parser.meta.abs.Parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParserRegistry {
    private final Map<String, Parser> parsers = new HashMap<>();

    /**
     * パーサーを登録します。
     *
     * @param parser 登録するIParser
     */
    public void registerParser(Parser parser) {
        parsers.put(parser.getSite().getName(), parser);
    }

    /**
     * サイト識別名に関連付けられたパーサーを取得します。
     * 関連付けられたパーサーが存在しない場合は{@code null}を返します。
     *
     * @param site パーサーを取得するサイト識別名
     * @return 登録されているパーサー
     */
    public Parser getParser(String site) {
        return parsers.get(site);
    }

    /**
     * 登録されている全てのパーサーの一覧を返します。
     *
     * @return 登録されている全てのパーサーの一覧
     */
    public List<IParser> getParsers() {
        return new ArrayList<>(parsers.values());
    }
}
