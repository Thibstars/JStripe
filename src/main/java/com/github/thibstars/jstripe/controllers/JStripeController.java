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

package com.github.thibstars.jstripe.controllers;

import com.github.thibstars.jstripe.model.BattleField;
import com.github.thibstars.jstripe.services.JStripeService;
import lombok.RequiredArgsConstructor;

/**
 * JStripe's main controller.
 *
 * @author Thibault Helsmoortel
 */
@SuppressWarnings("unused")
@RequiredArgsConstructor
public class JStripeController {

    private final JStripeService jStripeService;

    public void prepareBattle(BattleField battleField) {
        jStripeService.prepareBattle(battleField);
    }

}
