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


const customClicker = gremlins.species.clicker({
  // which mouse event types will be triggered
  clickTypes: ['click', 'dblcick', 'mouseup', 'mouseover', 'mousemove', 'mouseout'],
  // by default, the clicker gremlin shows its action by a red circle
  // overriding showAction() with an empty function makes the gremlin action invisible
  showAction: (x, y) => {},
});

const customToucher = gremlins.species.toucher({showAction: (x, y) => {},})
const customFormFiller = gremlins.species.formFiller({showAction: (x, y) => {},})
const customScroller = gremlins.species.formFiller({showAction: (x, y) => {},})
const customTyper = gremlins.species.formFiller({showAction: (x, y) => {},})

const horde = gremlins
.createHorde({
  species: [customClicker, customToucher, customFormFiller, customScroller, customTyper],
});

horde.unleash();