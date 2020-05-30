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

import java.io.IOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.core.io.DefaultResourceLoader;

/**
 * @author Thibault Helsmoortel
 */
class ResourceScriptReaderTest {

    private ResourceScriptReader reader;

    @BeforeEach
    void setUp() {
        this.reader = new ResourceScriptReader(new DefaultResourceLoader());
    }

    @Test
    void shouldReadContentsOfResource() throws IOException {
        final String resource = "scripts/unleash_simple_horde.js";

        final String result = reader.read(resource);

        Assertions.assertNotNull(result, "Result must not be null.");
        Assertions.assertFalse(StringUtils.isBlank(result), "Result must not be blank.");
    }
}