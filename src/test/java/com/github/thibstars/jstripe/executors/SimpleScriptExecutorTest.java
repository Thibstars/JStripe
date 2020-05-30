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

package com.github.thibstars.jstripe.executors;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

/**
 * @author Thibault Helsmoortel
 */
class SimpleScriptExecutorTest {

    private static final String VALID_SCRIPT = "console.log('hello');";

    private SimpleScriptExecutor executor;

    @BeforeEach
    void setUp() {
        this.executor = new SimpleScriptExecutor();
    }

    @Test
    void shouldExecuteScript() {
        JSEnabledWebDriver webDriver = mock(JSEnabledWebDriver.class);

        executor.execute(webDriver, VALID_SCRIPT);

        verify(webDriver).executeScript(VALID_SCRIPT);
    }

    @Test
    void shouldThrowExceptionOnNullWebDriver() {
        IllegalArgumentException thrown = Assertions
            .assertThrows(IllegalArgumentException.class, () -> executor.execute(null, VALID_SCRIPT));

        Assertions.assertEquals("WebDriver not provided.", thrown.getMessage(), "Exception message must match the expected.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "     "})
    void shouldThrowExceptionOnInvalidScript(String script) {
        assertExceptionOnInvalidScript(script);
    }

    @Test
    void shouldThrowExceptionOnNullScript() {
        assertExceptionOnInvalidScript(null);
    }

    private void assertExceptionOnInvalidScript(String script) {
        IllegalArgumentException thrownException = Assertions
            .assertThrows(IllegalArgumentException.class, () -> executor.execute(mock(WebDriver.class), script),
                "Expected exception must be thrown for input: " + script);

        Assertions.assertEquals("Script invalid.", thrownException.getMessage(), "Exception message must match the expected.");
    }

    /**
     * Class which's sole purpose is to be used in mocks representing a combination of {@link WebDriver} and {@link JavascriptExecutor)}.
     */
    private abstract static class JSEnabledWebDriver implements WebDriver, JavascriptExecutor {

    }
}