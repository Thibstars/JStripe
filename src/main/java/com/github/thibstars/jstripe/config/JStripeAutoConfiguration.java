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

package com.github.thibstars.jstripe.config;

import com.github.thibstars.jstripe.controllers.JStripeController;
import com.github.thibstars.jstripe.executors.ScriptExecutor;
import com.github.thibstars.jstripe.executors.SimpleScriptExecutor;
import com.github.thibstars.jstripe.io.ResourceScriptReader;
import com.github.thibstars.jstripe.io.WebScriptReader;
import com.github.thibstars.jstripe.services.JStripeService;
import com.github.thibstars.jstripe.services.JStripeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;

/**
 * Auto configuration providing the essential beans.
 *
 * @author Thibault Helsmoortel
 */
@Configuration
@RequiredArgsConstructor
public class JStripeAutoConfiguration {

    private final ResourceLoader resourceLoader;

    @Bean
    @ConditionalOnMissingBean(JStripeController.class)
    public JStripeController jStripeController() {
        return new JStripeController(jStripeService());
    }

    @Bean
    @ConditionalOnMissingBean(JStripeService.class)
    public JStripeService jStripeService() {
        return new JStripeServiceImpl(webScriptReader(), scriptExecutor());
    }

    @Bean
    @ConditionalOnMissingBean(ScriptExecutor.class)
    public ScriptExecutor scriptExecutor() {
        return new SimpleScriptExecutor();
    }

    @Bean
    @ConditionalOnMissingBean(WebScriptReader.class)
    public WebScriptReader webScriptReader() {
        return new WebScriptReader();
    }

    @Bean
    @ConditionalOnMissingBean(ResourceScriptReader.class)
    public ResourceScriptReader resourceScriptReader() {
        return new ResourceScriptReader(resourceLoader);
    }

}
