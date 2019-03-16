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

import dev.pandasoft.nvblib.listener.JobProgressListener;
import lombok.extern.slf4j.Slf4j;
import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;
import net.bramp.ffmpeg.job.FFmpegJob;

import java.io.File;
import java.io.IOException;

@Slf4j
public class AudioEncoder {
    private final FFmpeg ffmpeg;
    private final FFprobe ffprobe;

    private final MediaInfoManager infoManager;

    public AudioEncoder(FFmpeg ffmpeg, FFprobe ffprobe) {
        this.ffmpeg = ffmpeg;
        this.ffprobe = ffprobe;
        infoManager = new MediaInfoManager(ffprobe);
    }

    public boolean isEncodable(String input, String output) {
        File file = new File(output);
        if (!file.exists())
            return false;

        try {
            MediaInfo info = infoManager.getMediaInfo(input);
            if (info == null || info.getCodec_name().equals("opus"))
                return false;
        } catch (IOException e) {
            log.error("Could not retrieve the information of the specified file.", e);
            return false;
        }
        return true;
    }

    public void encode(String input, String output, long bitrate, boolean doTwoPass) throws IOException, InterruptedException {
        MediaInfo info = infoManager.getMediaInfo(input);

        FFmpegBuilder builder = new FFmpegBuilder()
                .setInput(info.getFilepath())
                .overrideOutputFiles(false)
                .addOutput(output)

                .setAudioCodec("opus")
                .setAudioChannels(info.stream.channels)
                .setAudioSampleRate(48000)
                .setAudioBitRate(bitrate)

                .setStrict(FFmpegBuilder.Strict.EXPERIMENTAL)
                .done();

        FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);
        FFmpegJob job;
        if (doTwoPass)
            job = executor.createTwoPassJob(builder);
        else
            job = executor.createJob(builder, new JobProgressListener(info));

        Thread jobThread = new Thread(job);
        jobThread.start();
        jobThread.join();
    }

    public void encode(String input, String output, long bitrate) throws IOException, InterruptedException {
        encode(input, output, bitrate, false);
    }

    public void encode(String input, String output) throws IOException, InterruptedException {
        encode(input, output, AudioQuality.MEDIUM);
    }
}
