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

package net.nafusoft.nvblib.listener;

import net.bramp.ffmpeg.FFmpegUtils;
import net.bramp.ffmpeg.progress.Progress;
import net.bramp.ffmpeg.progress.ProgressListener;
import net.nafusoft.nvblib.audio.MediaInfo;

import java.util.concurrent.TimeUnit;

public class JobProgressListener implements ProgressListener {
    private final MediaInfo info;
    final double duration_ns;

    public JobProgressListener(MediaInfo info) {
        this.info = info;
        duration_ns = info.format.duration * TimeUnit.SECONDS.toNanos(1);
    }

    @Override
    public void progress(Progress progress) {
        double percentage = progress.out_time_ns / duration_ns;

        System.out.println(String.format("[%.0f%%] status:%s frame:%d time:%s ms fps:%.0f speed:%.2fx",
                percentage * 100,
                progress.status,
                progress.frame,
                FFmpegUtils.toTimecode(progress.out_time_ns, TimeUnit.NANOSECONDS),
                progress.fps.doubleValue(),
                progress.speed
        ));
    }
}
