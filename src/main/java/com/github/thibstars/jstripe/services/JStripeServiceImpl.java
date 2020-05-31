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

package com.github.thibstars.jstripe.services;

import com.github.thibstars.jstripe.executors.ScriptExecutor;
import com.github.thibstars.jstripe.io.WebScriptReader;
import com.github.thibstars.jstripe.model.BattleField;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;

/**
 * JStripe's service for its core functionality.
 *
 * @author Thibault Helsmoortel
 */
@Slf4j
@RequiredArgsConstructor
public class JStripeServiceImpl implements JStripeService {

    private static final String GREMLINS_SCRIPT_URL = "https://unpkg.com/gremlins.js";

    private final WebScriptReader webScriptReader;

    private final ScriptExecutor scriptExecutor;

    @Override
    public void prepareBattle(BattleField battleField) {
        if (battleField == null) {
            throw new IllegalArgumentException("BattleField must not be null.");
        }

        if (battleField.getWebDriver() == null) {
            throw new IllegalArgumentException("Missing WebDriver.");
        }

        if (battleField.getUrl() == null) {
            throw new IllegalArgumentException("Missing url.");
        }

        log.info("Preparing the battle...");

        WebDriver webDriver = battleField.getWebDriver();
        webDriver.navigate().to(battleField.getUrl());

        try {
            String gremlinsScript = webScriptReader.read(GREMLINS_SCRIPT_URL);

            scriptExecutor.execute(webDriver, gremlinsScript);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}
