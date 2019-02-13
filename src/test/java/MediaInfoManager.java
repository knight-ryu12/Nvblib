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

import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.probe.FFmpegFormat;
import net.bramp.ffmpeg.probe.FFmpegProbeResult;
import net.bramp.ffmpeg.probe.FFmpegStream;

import java.io.IOException;
import java.util.List;

public class MediaInfoManager {

    public static void main(String[] args) {
        FFmpeg ffmpeg = null;
        FFprobe ffprobe = null;
        try {
            ffmpeg = new FFmpeg("/Users/chococha/Downloads/ffmpeg");
            ffprobe = new FFprobe("/Users/chococha/Downloads/ffprobe");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FFmpegProbeResult result = ffprobe.probe("/Users/chococha/opustest.opus");
            FFmpegFormat format = result.getFormat();
            List<FFmpegStream> streams = result.getStreams();

            System.out.println(new FFmpegFormatStr(format).toString());

            for (FFmpegStream stream : streams) {
                System.out.println(new FFmpegStreamStr(stream).toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
