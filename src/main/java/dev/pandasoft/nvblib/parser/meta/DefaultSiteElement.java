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

package dev.pandasoft.nvblib.parser.meta;

import dev.pandasoft.nvblib.parser.meta.abs.Site;

public class DefaultSiteElement implements Site {
    private final String name;
    private final String protocol;
    private final String host;
    private final String type;
    private final String leyer;
    private final String soundLayer;
    private final String jsonLocation;
    private final Boolean categorySupport;

    public DefaultSiteElement(String name,
                              String protocol,
                              String host,
                              String type,
                              String leyer,
                              String soundLayer,
                              String jsonLocation,
                              boolean categorySupport) {
        this.name = name;
        this.protocol = protocol;
        this.host = host;
        this.type = type;
        this.leyer = leyer;
        this.soundLayer = soundLayer;
        this.jsonLocation = jsonLocation;
        this.categorySupport = categorySupport;
    }

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
        return leyer;
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
