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

package dev.pandasoft.nvblib.registry;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import dev.pandasoft.nvblib.parser.meta.abs.Site;
import dev.pandasoft.nvblib.voice.IVoice;
import dev.pandasoft.nvblib.voice.Voice;
import dev.pandasoft.nvblib.voice.VoiceDownloader;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class VoiceRegistry {
    private final Multimap<String, IVoice> voices;
    private final Site site;
    private final VoiceDownloader downloader;

    public VoiceRegistry(Site site, File saveDir) {
        this.site = site;
        downloader = new VoiceDownloader(new File(saveDir, site.getHost()));
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
     */
    public void registerVoice(Site site, String title, String category, String preformattedTitle, String fileName, String fileUrl) {
        Voice voice = new Voice(this, site, title, category, preformattedTitle, fileName, fileUrl);
        if (voice.getPreformattedTitle() == null)
            voices.put(voice.getTitle(), voice);
        else
            voices.put(voice.getPreformattedTitle(), voice);
    }

    /**
     * 不完全なボイス要素をレジストリに登録します。
     */
    public void registerVoice(IVoice voice) {
        registerVoice(voice.getSite(), voice.getTitle(), voice.getCategory(), voice.getPreformattedTitle(), voice.getFileName(), voice.getFileUrl());
    }

    public void deleteVoice(IVoice voice) {
        if (voice.getPreformattedTitle() == null)
            voices.remove(voice.getTitle(), voice);
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
    public List<IVoice> getVoices() {
        Collection<IVoice> vCol = voices.values();
        return new ArrayList<>(vCol);
    }

    /**
     * 登録されているボイスの中から該当するボイスを取得します。
     *
     * @param name     検索するタイトル
     * @param category 絞り込むカテゴリ
     * @return 該当するボイス
     */
    public List<IVoice> getVoice(String name, String category) {
        Collection<IVoice> vCol = voices.get(name);
        List<IVoice> result = new ArrayList<>(vCol);
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
    public List<IVoice> getMatchVoice(String word) {
        List<IVoice> find = new ArrayList<>();
        voices.entries().forEach(voice -> {
            if (voice.getKey().matches(".*" + word + ".*")) {
                find.add(voice.getValue());
            }
        });
        return find;
    }

    public VoiceDownloader getDownloader() {
        return downloader;
    }
}
