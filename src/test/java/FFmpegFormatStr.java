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

import net.bramp.ffmpeg.probe.FFmpegFormat;

import java.util.Map;

public class FFmpegFormatStr {
    public String filename;
    public int nb_streams;
    public int nb_programs;
    public String format_name;
    public String format_long_name;
    public double start_time;
    public double duration;
    public long size;
    public long bit_rate;
    public int probe_score;
    public Map<String, String> tags;

    public FFmpegFormatStr(FFmpegFormat format) {
        filename = format.filename;
        nb_streams = format.nb_streams;
        nb_programs = format.nb_programs;
        format_name = format.format_name;
        format_long_name = format.format_long_name;
        start_time = format.start_time;
        duration = format.duration;
        size = format.size;
        bit_rate = format.bit_rate;
        probe_score = format.probe_score;
        tags = format.tags;
    }

    @Override
    public String toString() {
        return "FFmpegFormatStr{" +
                "filename='" + filename + '\'' +
                ", nb_streams=" + nb_streams +
                ", nb_programs=" + nb_programs +
                ", format_name='" + format_name + '\'' +
                ", format_long_name='" + format_long_name + '\'' +
                ", start_time=" + start_time +
                ", duration=" + duration +
                ", size=" + size +
                ", bit_rate=" + bit_rate +
                ", probe_score=" + probe_score +
                ", tags=" + tags +
                '}';
    }
}
