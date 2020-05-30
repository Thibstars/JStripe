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

package com.github.thibstars.jstripe.hordes;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.github.thibstars.jstripe.executors.ScriptExecutor;
import com.github.thibstars.jstripe.io.ResourceScriptReader;
import java.io.IOException;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

/**
 * @author Thibault Helsmoortel
 */
class SimpleHordeTest {

    private ScriptExecutor scriptExecutor;
    private ResourceScriptReader scriptReader;
    private SimpleHorde horde;
    private WebDriver webDriver;

    @BeforeEach
    void setUp() {
        this.scriptExecutor = mock(ScriptExecutor.class);
        this.scriptReader = mock(ResourceScriptReader.class);
        this.horde = new SimpleHorde(scriptExecutor, scriptReader);
        this.webDriver = mock(WebDriver.class);
    }

    @Test
    void shouldNotUnleashWithNullWebDriver() {
        IllegalArgumentException thrown = Assertions
            .assertThrows(IllegalArgumentException.class, () -> horde.unleash(null), "Expected exception must be thrown.");

        Assertions.assertEquals("WebDriver not provided.", thrown.getMessage(), "Exception message must match the expected.");
    }

    @Test
    void shouldUnleash() throws IOException {
        String script = RandomStringUtils.random(10);
        when(scriptReader.read(anyString())).thenReturn(script);

        horde.unleash(webDriver);

        verify(scriptExecutor).execute(webDriver, script);
    }

    @Test
    void shouldNotRetreatWithNullWebDriver() {
        IllegalArgumentException thrown = Assertions
            .assertThrows(IllegalArgumentException.class, () -> horde.retreat(null), "Expected exception must be thrown.");

        Assertions.assertEquals("WebDriver not provided.", thrown.getMessage(), "Exception message must match the expected.");
    }

    @Test
    void shouldRetreat() {
        horde.retreat(webDriver);

        verify(scriptExecutor).execute(eq(webDriver), anyString());
    }
}