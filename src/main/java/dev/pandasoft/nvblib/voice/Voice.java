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

package dev.pandasoft.nvblib.voice;

import dev.pandasoft.nvblib.parser.meta.abs.Site;
import dev.pandasoft.nvblib.registry.VoiceRegistry;

public class Voice implements IVoice {
    private final VoiceRegistry registry;
    private final Site site;
    private final String title;
    private final String category;
    private final String preformattedTitle;
    private final String fileName;
    private final String fileUrl;

    public Voice(VoiceRegistry registry, Site site, String title, String category, String preformattedTitle, String fileName, String fileUrl) {
        this.registry = registry;
        this.site = site;
        this.title = title;
        this.category = category;
        this.preformattedTitle = preformattedTitle;
        this.fileName = fileName;
        this.fileUrl = fileUrl;
    }

    @Override
    public VoiceRegistry getRegistry() {
        return registry;
    }

    @Override
    public Site getSite() {
        return site;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getCategory() {
        return category;
    }

    @Override
    public String getPreformattedTitle() {
        return preformattedTitle;
    }

    @Override
    public String getFileName() {
        return fileName;
    }

    @Override
    public String getFileUrl() {
        return fileUrl;
    }

    @Override
    public String toString() {
        return "Voice{" +
                "site=" + site +
                ", title='" + title + '\'' +
                ", category='" + category + '\'' +
                ", preformattedTitle='" + preformattedTitle + '\'' +
                ", fileName='" + fileName + '\'' +
                ", fileUrl='" + fileUrl +
                '}';
    }
}
