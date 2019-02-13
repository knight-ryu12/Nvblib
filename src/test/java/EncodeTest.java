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
import net.nafusoft.nvblib.audio.AudioEncoder;
import net.nafusoft.nvblib.audio.AudioQuality;

import java.io.IOException;

public class EncodeTest {

    public static void main(String[] args) {
        FFmpeg ffmpeg = null;
        FFprobe ffprobe = null;

        if (args.length < 4)
            return;

        try {
            ffmpeg = new FFmpeg(args[0]);
            ffprobe = new FFprobe(args[1]);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String target = args[2];
        String to = args[3];
        AudioEncoder audioEncoder = new AudioEncoder(ffmpeg, ffprobe);
        System.out.println(audioEncoder.isEncodable(target));
        try {
            audioEncoder.encode(target, to, AudioQuality.MIDIAMHIGH, false);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
