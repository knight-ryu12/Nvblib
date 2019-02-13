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

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import net.nafusoft.nvblib.parser.meta.abs.Site;
import net.nafusoft.nvblib.voice.Voice;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class VoiceRegistry {
    private final Multimap<String, Voice> voices;
    private final Site site;

    public VoiceRegistry(Site site) {
        this.site = site;
        voices = LinkedHashMultimap.create();
    }

    /**
     * 格納しているボイスの提供元サイトを取得します。
     *
     * @return ボイスの提供元サイト
     */
    public Site getSite() {
        return site;
    }

    /**
     * ボイスを登録します。
     *
     * @param voice 登録するVoice
     */
    public void registerVoice(Voice voice) {
        if (voice.getPreformattedTitle() == null)
            voices.put(voice.getTitle(), voice);
        else
            voices.put(voice.getPreformattedTitle(), voice);
    }

    /**
     * 登録されている全てのボイスをリストから削除します。
     * このメソッドを実行した場合自動的にリポジトリは準備未完了状態に設定されます。
     */
    public void clearVoices() {
        voices.clear();
    }

    /**
     * 登録されている全てのボイスの一覧を返します。
     *
     * @return 登録されている全てのボイスの一覧
     */
    public List<Voice> getVoices() {
        Collection<Voice> vCol = voices.values();
        return new ArrayList<>(vCol);
    }

    /**
     * 登録されているボイスの中から該当するボイスを取得します。
     *
     * @param name     検索するタイトル
     * @param category 絞り込むカテゴリ
     * @return 該当するボイス
     */
    public List<Voice> getVoice(String name, String category) {
        Collection<Voice> vCol = voices.get(name);
        List<Voice> result = new ArrayList<>(vCol);
        vCol.forEach(voice -> {
            if (voice.getPreformattedTitle() != null)
                result.remove(voice);
            if (category != null && !category.equals(voice.getCategory()))
                result.remove(voice);
        });
        return result;
    }

    /**
     * 登録されているボイスの中から指定したワードが含まれるボイスを取得します。
     *
     * @param word 検索するワード
     * @return 指定したワードが含まれるボイス
     */
    public List<Voice> getMatchVoice(String word) {
        List<Voice> find = new ArrayList<>();
        voices.entries().forEach(voice -> {
            if (voice.getKey().matches(".*" + word + ".*")) {
                find.add(voice.getValue());
            }
        });
        return find;
    }
}
