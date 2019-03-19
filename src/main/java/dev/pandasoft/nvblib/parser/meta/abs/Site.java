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

package dev.pandasoft.nvblib.parser.meta.abs;

public interface Site {

    /**
     * サイトの識別名を返します。
     *
     * @return サイトの識別名
     */
    String getName();

    /**
     * サイトのプロトコルを返します。
     *
     * @return サイトのプロトコル
     */
    String getProtocol();

    /**
     * サイトのホスト名を返します。
     *
     * @return サイトのホスト名
     */
    String getHost();

    /**
     * サイトの要素取得に使用するパーサーの種類を返します。
     *
     * @return サイトの要素取得に使用するParserの種類
     */
    String getType();

    /**
     * ファイル名を含めたボタンページまでの階層を返します。
     * 階層が存在しない場合は{@code null}を返します。
     *
     * @return ファイル名を含めたボタンページまでの階層
     */
    String getLayer();

    /**
     * サイトに記載されているよりも前の音声ファイルまでの階層を返します。
     * 階層が存在しない場合は{@code null}を返します。
     *
     * @return サイトに記載されているよりも前の音声ファイルまでの階層
     */
    String getSoundLayer();

    /**
     * 要素が定義されたjsonファイルまでの階層を返します。
     *
     * @return 要素が定義されたjsonファイルまでの階層
     */
    String getJsonLocation();

    /**
     * カテゴリーのサポート状況を返します。
     *
     * @return カテゴリーをサポートしている場合true
     */
    boolean isCategorySupport();

    /**
     * プロトコルとホスト名を結合したURLの文字列を返します。
     *
     * @return プロトコルとホスト名を結合したURLの文字列
     */
    String getStringURL();
}
