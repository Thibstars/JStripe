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
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

/**
 * Script reader using Spring's {@link ResourceLoader}.
 *
 * @author Thibault Helsmoortel
 */
public class ResourceScriptReader implements ScriptReader {

    private final ResourceLoader resourceLoader;

    public ResourceScriptReader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public String read(String resource) throws IOException {
        Resource loaded = resourceLoader.getResource(resource);
        InputStream inputStream = loaded.getInputStream();
        File target = new File(
            System.getProperty("java.io.tmpdir") + "/jstripe/" + LocalTime.now().format(DateTimeFormatter.ofPattern("HH_mm_ss")) + resource);
        FileUtils.copyInputStreamToFile(inputStream, target);

        return FileUtils.readFileToString(target, StandardCharsets.UTF_8);
    }
}
