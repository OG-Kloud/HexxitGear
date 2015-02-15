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

package sct.hexxitgear.item;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import sct.hexxitgear.HexxitGear;
import sct.hexxitgear.model.ModelSkullHelmet;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemTribalArmor extends ItemHexxitArmor {

    public ItemTribalArmor(String unlocalizedName, String textureName, int type) {
        super(unlocalizedName, ArmorMaterial.DIAMOND, textureName, type);
    }

    @Override
    public void registerIcons(IIconRegister ir) {
        itemIcon = ir.registerIcon(getUnlocalizedName());
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, int layer) {
        if (slot == 0)
            return "/textures/maps/SkullHelmet.png";

        if (stack == new ItemStack(HexxitGear.tribalLeggings))
            return "/textures/armor/tribal2.png";

        return "/textures/armor/tribal.png";
    }

    @SideOnly(Side.CLIENT)
 //   @Override
    public ModelBiped getArmorModel(EntityLiving entityLiving, ItemStack itemStack, int armorSlot) {
        if (armorSlot == 0)
            return new ModelSkullHelmet();
        return null;
    }
}
