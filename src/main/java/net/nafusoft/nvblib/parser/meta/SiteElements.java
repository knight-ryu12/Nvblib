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

package net.nafusoft.nvblib.parser.meta;

import net.nafusoft.voicebutton.core.parser.meta.abs.Site;

import java.util.*;

public class SiteElements {
    private final Map<String, Site> sites = new LinkedHashMap<>();

    /**
     * サイトを登録します。
     *
     * @param site 登録するサイト
     */
    public void registerSite(Site site) {
        sites.put(site.getName(), site);
    }

    /**
     * サイト識別名に関連付けられてたSiteを取得します。
     * 関連付けられたSiteが存在しない場合は{@code null}を返します。
     *
     * @param name 関連付けられたサイト識別名
     * @return 登録されているSite
     */
    public Site getSite(String name) {
        return sites.get(name);
    }

    /**
     * 登録されているサイトのホスト名から該当するSiteを取得します。
     * Siteが見つからない場合は{@code null}を返します。
     *
     * @param host 取得するサイトのホスト名
     * @return 登録されているSite
     */
    public Site getSiteFromHost(String host) {
        try {
            return Objects.requireNonNull(sites.entrySet().stream()
                    .filter(data -> host.equals(data.getValue().getHost()))
                    .findFirst()
                    .orElse(null)).getValue();
        } catch (NullPointerException e) {
            return null;
        }
    }

    /**
     * 登録されている全てのサイトの一覧を返します。
     *
     * @return 登録されている全てのサイト一覧
     */
    public List<Site> getSites() {
        return new ArrayList<>(sites.values());
    }
}
