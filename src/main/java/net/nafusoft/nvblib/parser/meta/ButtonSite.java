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

package net.nafusoft.nvblib.parser.meta;

import net.nafusoft.nvblib.parser.meta.abs.Site;

public class ButtonSite implements Site {
    public String name;
    public String protocol;
    public String host;
    public String type;
    public String layer = "";
    public String soundLayer = "";
    public String jsonLocation = "";
    public boolean categorySupport;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getProtocol() {
        return protocol;
    }

    @Override
    public String getHost() {
        return host;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getLayer() {
        return layer;
    }

    @Override
    public String getSoundLayer() {
        return soundLayer;
    }

    @Override
    public String getJsonLocation() {
        return jsonLocation;
    }

    @Override
    public boolean isCategorySupport() {
        return categorySupport;
    }

    @Override
    public String getStringURL() {
        return protocol + "://" + host;
    }
}
