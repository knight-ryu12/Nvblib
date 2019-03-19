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

package dev.pandasoft.nvblib.parser.meta;

import dev.pandasoft.nvblib.exception.InvalidParserException;
import dev.pandasoft.nvblib.parser.*;
import dev.pandasoft.nvblib.parser.meta.abs.Parser;
import dev.pandasoft.nvblib.parser.meta.abs.Site;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class ParserLoader {
    private final Map<String, Class<? extends Parser>> parsers = new HashMap<>();

    public ParserLoader() {
        parsers.put("DEFAULT", HTMLParser.class);
        parsers.put("JSON", JsonParser.class);
        parsers.put("JSON_ARRAY", JsonArrayParser.class);
        parsers.put("TOMARI_JSON", TomariJsonParser.class);
        parsers.put("ICHIKA_ARRAY", IchikaArrayParser.class);
    }

    /**
     * サイトの読み込みに使用するパーサークラスを登録します。
     * すでに同じパーサークラスが登録されている場合は上書きされます。
     *
     * @param typeName パーサーのタイプ名
     * @param parser   パーサークラス
     */
    public void registerParser(String typeName, Class<? extends Parser> parser) {
        if (parsers.containsValue(parser))
            parsers.remove(parsers.entrySet()
                    .stream()
                    .filter(entry -> entry.getValue().equals(parser))
                    .findFirst()
                    .map(Map.Entry::getKey));
        parsers.put(typeName, parser);
    }

    /**
     * サイトから適切なパーサーを取得します。
     *
     * @param site パーサーを使用するサイト
     * @return 取得されたパーサーのインスタンス
     * @throws InvalidParserException 呼び出されたパーサークラスが正しい形式ではない場合にスローされます。
     */
    public Parser getParser(Site site) throws InvalidParserException {
        Class parserClass = parsers.get(site.getType());
        if (parserClass == null)
            return null;

        Constructor<?> cons;
        try {
            cons = parserClass.getConstructor(Site.class);
        } catch (NoSuchMethodException e) {
            throw new InvalidParserException(e);
        }

        Parser instance;
        try {
            instance = (Parser) cons.newInstance(site);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new InvalidParserException(e);
        }

        return instance;
    }
}
