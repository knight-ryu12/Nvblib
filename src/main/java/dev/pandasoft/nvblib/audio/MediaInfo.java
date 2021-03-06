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

package dev.pandasoft.nvblib.audio;

import net.bramp.ffmpeg.probe.FFmpegFormat;
import net.bramp.ffmpeg.probe.FFmpegStream;

public class MediaInfo {
    public final FFmpegFormat format;
    public final FFmpegStream stream;


    public MediaInfo(FFmpegFormat format, FFmpegStream stream) {
        this.format = format;
        this.stream = stream;
    }

    public String getFilename() {
        String[] namesplit = format.filename.split("/");
        return namesplit[namesplit.length - 1];
    }

    public String getFilepath() {
        return format.filename;
    }

    public String getFormat() {
        return format.format_name;
    }

    public String getCodecName() {
        return stream.codec_name;
    }

    public int getSampleRate() {
        return stream.sample_rate;
    }

    public int getChannels() {
        return stream.channels;
    }

    public ChannelLayout getLayout() {
        return ChannelLayout.valueOf(stream.channel_layout);
    }

    public long getBitRate() {
        if (stream.bit_rate == 0)
            return format.bit_rate;
        return stream.bit_rate;
    }

    public long getFileSize() {
        return format.size;
    }
}
