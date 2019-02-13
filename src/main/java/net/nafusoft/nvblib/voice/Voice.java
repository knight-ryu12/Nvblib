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

public class Voice implements IVoice {
    public Site site;
    public String title;
    public String category;
    public String preformattedTitle;
    public String fileUrl;
    public Path filePath;

    public Voice(Site site, String title, String category, String preformattedTitle, String fileUrl, Path filePath) {
        this.site = site;
        this.title = title;
        this.category = category;
        this.preformattedTitle = preformattedTitle;
        this.fileUrl = fileUrl;
        this.filePath = filePath;
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
    public Path getFilePath() {
        return filePath;
    }

    @Override
    public String toString() {
        return "Voice{" +
                "site=" + site +
                ", title='" + title + '\'' +
                ", category='" + category + '\'' +
                ", preformattedTitle='" + preformattedTitle + '\'' +
                ", fileUrl='" + fileUrl + '\'' +
                ", filePath=" + filePath +
                '}';
    }
}
