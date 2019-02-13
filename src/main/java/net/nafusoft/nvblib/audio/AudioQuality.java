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

public class AudioQuality {

    private AudioQuality() {
    }

    /**
     * Sound quality of which frequency value of 7.4 kHz or more was cut
     */
    public static final long LOWEST = 16000L;
    public static final long LOW = 32000L;
    /**
     * Default Discord Quality
     */
    public static final long MEDIUM = 64000L;
    public static final long MIDIAMHIGH = 96000L;
    public static final long HIGH = 128000L;
    /**
     * Sound quality adopted on YouTube
     */
    public static final long HIGHEST = 160000L;
}
