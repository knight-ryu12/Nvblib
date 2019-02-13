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

package net.nafusoft.nvblib.audio;

import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.probe.FFmpegFormat;
import net.bramp.ffmpeg.probe.FFmpegProbeResult;
import net.bramp.ffmpeg.probe.FFmpegStream;

import java.io.IOException;
import java.util.List;

public class MediaInfoManager {
    private final FFprobe ffprobe;

    public MediaInfoManager(FFprobe ffprobe) {
        this.ffprobe = ffprobe;
    }

    public MediaInfo getMediaInfo(String path) throws IOException {
        FFmpegProbeResult result = ffprobe.probe(path);
        FFmpegFormat format = result.getFormat();
        List<FFmpegStream> streams = result.getStreams();

        for (FFmpegStream stream : streams) {
            if (stream.codec_type.equals(FFmpegStream.CodecType.AUDIO))
                return new MediaInfo(format, stream);
        }

        return null;
    }
}
