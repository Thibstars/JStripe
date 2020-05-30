/*
 * Copyright (c) 2020 Thibault Helsmoortel.
 *
 * This file is part of JStripe.
 *
 * JStripe is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 * JStripe is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with JStripe.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.github.thibstars.jstripe.io;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import org.apache.commons.io.FileUtils;
import org.apache.maven.shared.utils.StringUtils;

/**
 * Script reader for web resources.
 *
 * @author Thibault Helsmoortel
 */
public class WebScriptReader implements ScriptReader {

    @Override
    public String read(String resource) throws IOException {
        if (StringUtils.isBlank(resource)) {
            throw new IllegalArgumentException("Resource invalid.");
        }

        URL url = new URL(resource);
        File target = new File(
            System.getProperty("java.io.tmpdir") + "/jstripe/" + LocalTime.now().format(DateTimeFormatter.ofPattern("HH_mm_ss")) + "script.js");
        target.deleteOnExit();

        FileUtils.copyURLToFile(url, target);

        return FileUtils.readFileToString(target, StandardCharsets.UTF_8);
    }
}
