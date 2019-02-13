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

package net.nafusoft.nvblib.voice;

import net.nafusoft.nvblib.parser.meta.abs.Site;

import java.nio.file.Path;

public interface IVoice {

    /**
     * このボイスが配置されているサイトを返します。
     *
     * @return このボイスが配置されているサイト
     */
    Site getSite();

    /**
     * ボイスタイトルを返します。
     *
     * @return ボイスタイトル
     */
    String getTitle();

    /**
     * ボイスが登録されているボタンのカテゴリー名を返します。
     * サイトがカテゴリーに対応していないか、取得できない場合{@code null}を返します。
     *
     * @return ボイスが登録されているボタンのカテゴリー名
     */
    String getCategory();

    /**
     * 連番になっているタイトルから番号以外を抽出します。
     * 該当しない場合は{@code null}を返します。
     *
     * @return 抽出されたボイスタイトル
     */
    String getPreformattedTitle();

    /**
     * ファイルが格納されている場所までのパスを返します。
     *
     * @return ファイルが格納されている場所までのパス
     */
    Path getFilePath();
}
