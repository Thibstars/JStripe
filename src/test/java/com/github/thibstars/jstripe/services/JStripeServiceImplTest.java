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

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.github.thibstars.jstripe.executors.ScriptExecutor;
import com.github.thibstars.jstripe.io.WebScriptReader;
import com.github.thibstars.jstripe.model.BattleField;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;

/**
 * @author Thibault Helsmoortel
 */
class JStripeServiceImplTest {

    private WebScriptReader scriptReader;

    private ScriptExecutor scriptExecutor;

    private WebDriver webDriver;

    private BattleField battleField;

    private JStripeServiceImpl service;

    @BeforeEach
    void setUp() throws MalformedURLException {
        this.scriptReader = mock(WebScriptReader.class);
        this.scriptExecutor = mock(ScriptExecutor.class);
        this.webDriver = mock(WebDriver.class);
        this.battleField = BattleField.builder()
            .webDriver(webDriver)
            .url(new URL("http://www.google.com"))
            .build();
        this.service = new JStripeServiceImpl(scriptReader, scriptExecutor);
    }

    @Test
    void shouldPrepareBattle() throws IOException {
        Navigation navigation = mock(Navigation.class);
        when(webDriver.navigate()).thenReturn(navigation);

        String initialisationScript = RandomStringUtils.random(10);
        when(scriptReader.read(anyString())).thenReturn(initialisationScript);

        service.prepareBattle(battleField);

        verify(webDriver).navigate();
        verify(navigation).to(battleField.getUrl());
        verify(scriptExecutor).execute(webDriver, initialisationScript);
    }

    @Test
    void shouldNotPrepareBattleWithNullBattleField() {
        IllegalArgumentException thrown = Assertions
            .assertThrows(IllegalArgumentException.class, () -> service.prepareBattle(null), "Exception must be thrown.");

        Assertions.assertEquals("BattleField must not be null.", thrown.getMessage(), "Message must match the expected.");
    }

    @Test
    void shouldNotPrepareBattleWithNullWebDriver() {
        battleField.setWebDriver(null);
        IllegalArgumentException thrown = Assertions
            .assertThrows(IllegalArgumentException.class, () -> service.prepareBattle(battleField), "Exception must be thrown.");

        Assertions.assertEquals("Missing WebDriver.", thrown.getMessage(), "Message must match the expected.");
    }

    @Test
    void shouldNotPrepareBattleWithNullUrl() {
        battleField.setUrl(null);
        IllegalArgumentException thrown = Assertions
            .assertThrows(IllegalArgumentException.class, () -> service.prepareBattle(battleField), "Exception must be thrown.");

        Assertions.assertEquals("Missing url.", thrown.getMessage(), "Message must match the expected.");
    }
}