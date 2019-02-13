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

import net.bramp.ffmpeg.probe.FFmpegDisposition;
import net.bramp.ffmpeg.probe.FFmpegStream;
import org.apache.commons.lang3.math.Fraction;

import java.util.Map;

public class FFmpegStreamStr extends FFmpegStream {
    public int index;
    public String codec_name;
    public String codec_long_name;
    public String profile;
    public FFmpegStream.CodecType codec_type;
    public Fraction codec_time_base;
    public String codec_tag_string;
    public String codec_tag;
    public int width;
    public int height;
    public int has_b_frames;
    public String sample_aspect_ratio;
    public String display_aspect_ratio;
    public String pix_fmt;
    public int level;
    public String chroma_location;
    public int refs;
    public String is_avc;
    public String nal_length_size;
    public Fraction r_frame_rate;
    public Fraction avg_frame_rate;
    public Fraction time_base;
    public long start_pts;
    public double start_time;
    public long duration_ts;
    public double duration;
    public long bit_rate;
    public long max_bit_rate;
    public int bits_per_raw_sample;
    public int bits_per_sample;
    public long nb_frames;
    public String sample_fmt;
    public int sample_rate;
    public int channels;
    public String channel_layout;
    public FFmpegDisposition disposition;
    public Map<String, String> tags;

    public FFmpegStreamStr(FFmpegStream stream) {
        index = stream.index;
        codec_name = stream.codec_name;
        codec_long_name = stream.codec_long_name;
        profile = stream.profile;
        codec_type = stream.codec_type;
        codec_time_base = stream.codec_time_base;
        codec_tag_string = stream.codec_tag_string;
        codec_tag = stream.codec_tag;
        width = stream.width;
        height = stream.height;
        has_b_frames = stream.has_b_frames;
        sample_aspect_ratio = stream.sample_aspect_ratio;
        display_aspect_ratio = stream.display_aspect_ratio;
        pix_fmt = stream.pix_fmt;
        level = stream.level;
        chroma_location = stream.chroma_location;
        refs = stream.refs;
        is_avc = stream.is_avc;
        nal_length_size = stream.nal_length_size;
        r_frame_rate = stream.r_frame_rate;
        avg_frame_rate = stream.avg_frame_rate;
        time_base = stream.time_base;
        start_pts = stream.start_pts;
        start_time = stream.start_time;
        duration_ts = stream.duration_ts;
        duration = stream.duration;
        bit_rate = stream.bit_rate;
        max_bit_rate = stream.max_bit_rate;
        bits_per_raw_sample = stream.bits_per_raw_sample;
        bits_per_sample = stream.bits_per_sample;
        nb_frames = stream.nb_frames;
        sample_fmt = stream.sample_fmt;
        sample_rate = stream.sample_rate;
        channels = stream.channels;
        channel_layout = stream.channel_layout;
        disposition = stream.disposition;
        tags = stream.tags;
    }

    @Override
    public String toString() {
        return "FFmpegStreamStr{" +
                "index=" + index +
                ", codec_name='" + codec_name + '\'' +
                ", codec_long_name='" + codec_long_name + '\'' +
                ", profile='" + profile + '\'' +
                ", codec_type=" + codec_type +
                ", codec_time_base=" + codec_time_base +
                ", codec_tag_string='" + codec_tag_string + '\'' +
                ", codec_tag='" + codec_tag + '\'' +
                ", width=" + width +
                ", height=" + height +
                ", has_b_frames=" + has_b_frames +
                ", sample_aspect_ratio='" + sample_aspect_ratio + '\'' +
                ", display_aspect_ratio='" + display_aspect_ratio + '\'' +
                ", pix_fmt='" + pix_fmt + '\'' +
                ", level=" + level +
                ", chroma_location='" + chroma_location + '\'' +
                ", refs=" + refs +
                ", is_avc='" + is_avc + '\'' +
                ", nal_length_size='" + nal_length_size + '\'' +
                ", r_frame_rate=" + r_frame_rate +
                ", avg_frame_rate=" + avg_frame_rate +
                ", time_base=" + time_base +
                ", start_pts=" + start_pts +
                ", start_time=" + start_time +
                ", duration_ts=" + duration_ts +
                ", duration=" + duration +
                ", bit_rate=" + bit_rate +
                ", max_bit_rate=" + max_bit_rate +
                ", bits_per_raw_sample=" + bits_per_raw_sample +
                ", bits_per_sample=" + bits_per_sample +
                ", nb_frames=" + nb_frames +
                ", sample_fmt='" + sample_fmt + '\'' +
                ", sample_rate=" + sample_rate +
                ", channels=" + channels +
                ", channel_layout='" + channel_layout + '\'' +
                ", disposition=" + disposition +
                ", tags=" + tags +
                '}';
    }
}
