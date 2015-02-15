/*
 * HexxitGear
 * Copyright (C) 2013  Ryan Cohen
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package sct.hexxitgear;

import net.minecraft.entity.player.EntityPlayer;
import sct.hexxitgear.tick.PlayerTickHandler;
import cpw.mods.fml.relauncher.Side;

public class CommonProxy {

    public void init () {
        registerHandlers();
    }

    public int addArmor(String armorName) {
        return 0;
    }

    public EntityPlayer findPlayer(String playerName) {
        return null;
    }

    public void registerHandlers() {
 //       TickRegistry.registerTickHandler(new PlayerTickHandler(), Side.SERVER);
    }
}
