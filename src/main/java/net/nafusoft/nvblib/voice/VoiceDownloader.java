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

import lombok.extern.slf4j.Slf4j;
import net.nafusoft.nvblib.exception.DownloadFailureException;
import net.nafusoft.nvblib.parser.meta.abs.Site;
import net.nafusoft.nvblib.utils.URLUtil;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;

@Slf4j
public class VoiceDownloader {
    private final Site site;
    private final File directory;

    public VoiceDownloader(Site site, File directory) {
        this.site = site;
        this.directory = directory;
        if (!this.directory.exists() && this.directory.mkdirs())
            log.info("It was generated because the folder to save the audio file was not found.");
    }

    /**
     * 音声ファイルのダウンロードを行います。
     * ファイルのダウンロードに成功した場合もしくはすでにファイルが存在している場合はファイルまでのパスを返します。
     *
     * @param filename ダウンロードするファイル名
     * @return ダウンロードしたファイルまでのパス
     * @throws DownloadFailureException 一定回数以上連続でダウンロードに失敗した場合にスローされます。
     */
    public Path download(String filename) throws DownloadFailureException {
        File audio = new File(directory, filename);
        String urlStr = site.getStringURL() + site.getSoundLayer() + "/" + filename;

        if (!audio.exists()) {
            try {
                urlStr = URLUtil.encode(urlStr);
                URL url = new URL(urlStr);
                FileUtils.copyURLToFile(url, audio);
                return audio.toPath();
            } catch (EncoderException e) {
                throw new DownloadFailureException("Encoding of URL failed.¥n" + urlStr, e);
            } catch (MalformedURLException e) {
                throw new DownloadFailureException("Failed to generate the URL of the audio file.¥n" + urlStr, e);
            } catch (IOException e) {
                throw new DownloadFailureException("The file could not be downloaded.: " + filename);
            }
        } else {
            return audio.toPath();
        }
    }
}
