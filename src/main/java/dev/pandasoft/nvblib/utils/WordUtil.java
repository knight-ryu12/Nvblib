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

package dev.pandasoft.nvblib.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class WordUtil {
    private static final Pattern CHECK_PATTERN = Pattern.compile("^[0-9!\\\"#$%&'()*+,-\\./:;<=>?@[]^_`{]+$]+$");
    private static final Pattern NUMBER_PATTERN = Pattern.compile("(.+?)([0-9]+$)");

    private WordUtil() {
        throw new UnsupportedOperationException();
    }

    /**
     * 指定した文字列が数字で終了している場合に末尾の数字を削除します。
     * 数字と記号のみで構成されている場合は処理を行いません。
     * 処理を行わなかった場合は{@code null}を返します。
     *
     * @param title 処理を行う文字列
     * @return 処理が行われた文字列
     */
    public static String generateWord(String title) {
        Matcher check = CHECK_PATTERN.matcher(title);
        if (check.find())
            return null;
        Matcher number = NUMBER_PATTERN.matcher(title);
        if (number.find())
            return number.group(1);
        return null;
    }
}
