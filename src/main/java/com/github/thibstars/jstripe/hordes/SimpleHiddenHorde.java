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

import com.github.thibstars.jstripe.executors.ScriptExecutor;
import com.github.thibstars.jstripe.io.ResourceScriptReader;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.WebDriver;

/**
 * @author Thibault Helsmoortel
 */
@Slf4j
@RequiredArgsConstructor
public class SimpleHiddenHorde implements Horde {

    private static final String UNLEASH_SCRIPT = "scripts/unleash_simple_hidden_horde.js";
    private static final String RETREAT_SCRIPT = "if (typeof horde !== 'undefined') horde.stop();";

    private final ScriptExecutor scriptExecutor;

    private final ResourceScriptReader scriptReader;

    @Override
    public void unleash(WebDriver webDriver) {
        if (webDriver == null) {
            throw new IllegalArgumentException("WebDriver not provided.");
        }

        log.info("Unleashing horde!");

        try {
            scriptExecutor.execute(webDriver, scriptReader.read(UNLEASH_SCRIPT));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    @Override
    public void retreat(WebDriver webDriver) {
        if (webDriver == null) {
            throw new IllegalArgumentException("WebDriver not provided.");
        }

        log.info("Retreating horde!");

        try {
            scriptExecutor.execute(webDriver, RETREAT_SCRIPT);
        } catch (JavascriptException e) {
            log.error(e.getMessage(), e);
        }
    }
}
