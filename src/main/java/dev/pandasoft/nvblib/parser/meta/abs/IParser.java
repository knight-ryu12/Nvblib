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

import dev.pandasoft.nvblib.voice.IVoice;

import java.util.List;

public interface IParser {

    /**
     * 登録されているサイト情報を返します。
     * 何も登録されていない場合は{@code null}を返します。
     *
     * @return 登録されているサイト情報
     */
    Site getSite();

    /**
     * サイトからボイス要素一覧を取得します。
     */
    List<IVoice> getElements();
}
