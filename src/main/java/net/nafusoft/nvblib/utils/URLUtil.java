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

package net.nafusoft.nvblib.utils;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.net.URLCodec;

public final class URLUtil {
    private static final URLCodec codec = new URLCodec("UTF-8");

    private URLUtil() {
        throw new UnsupportedOperationException();
    }

    /**
     * 日本語が含まれるURLをエンコードします。その際に記号の一部を使用できる形式に変換します。
     *
     * @param url エンコードを行うURLの文字列
     * @return エンコードが行われたURLの文字列
     * @throws EncoderException URLのエンコードに失敗した場合にスローされます。
     */
    public static String encode(String url) throws EncoderException {
        return codec.encode(url)
                .replaceAll("\\+", "%20")
                .replaceAll("%3A", ":")
                .replaceAll("%2F", "/")
                .replaceAll("%3F", "?")
                .replaceAll("%E2%80%8B", "");
    }

    /**
     * 取得したデータファイルのURLを処理しやすい形に整形します。
     *
     * @param filename 整形するデータファイルのURL
     * @return 整形したデータファイルのURL
     */
    public static String format(String filename) {
        if (filename.startsWith("./"))
            return filename.substring(2);
        if (filename.startsWith("/"))
            return filename.substring(1);
        return filename;
    }

    /**
     * 取得したデータファイルのURLを処理しやすい形に整形します。
     * 同時に指定した拡張子を付与します。
     *
     * @param filename  整形するデータファイルのURL
     * @param extension 付与する拡張子
     * @return 整形したデータファイルのURL
     */
    public static String format(String filename, String extension) {
        filename = format(filename);
        if (!extension.startsWith("."))
            extension = "." + extension;
        if (!filename.endsWith(extension))
            filename += extension;
        return filename;
    }
}
