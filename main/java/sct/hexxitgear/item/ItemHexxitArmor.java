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

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.ISpecialArmor;
import sct.hexxitgear.HexxitGear;

public class ItemHexxitArmor extends ItemArmor implements ISpecialArmor {

	public String textureName;
	
    public ItemHexxitArmor(String unlocalizedName, ArmorMaterial material, String textureName, int type) {
        super(material, 0, type);
        this.textureName = textureName;
        this.setUnlocalizedName(unlocalizedName);
        this.setTextureName(HexxitGear.modid + ":" + unlocalizedName);
        this.setCreativeTab(CreativeTabs.tabCombat);
    }

 /*  @Override
    public ArmorProperties getProperties(EntityLiving player, ItemStack armor, DamageSource source, double damage, int slot) {
        return new ArmorProperties(1, damageReduceAmount / 22D, armor.getMaxDamage() + 1);
    }
*/
    @Override
    public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
        return damageReduceAmount;
    }
    
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
    {
        return HexxitGear.modid + ":armor/" + this.textureName + "_" + (this.armorType == 2 ? "2" : "1") + ".png";
    }
/*
    @Override
    public void damageArmor(EntityLiving entity, ItemStack stack, DamageSource source, int damage, int slot) {
        if (entity instanceof EntityPlayer && !(((EntityPlayer) entity).capabilities.isCreativeMode)) {
            if (stack.getItemDamage() < stack.getMaxDamage()) {
                stack.setItemDamage(stack.getItemDamage() + 1);
            } else {
                // Create broken item here
            }
        }
    }
*/
	@Override
	public void damageArmor(EntityLivingBase entity, ItemStack stack,
			DamageSource source, int damage, int slot) {
        if (entity instanceof EntityPlayer && !(((EntityPlayer) entity).capabilities.isCreativeMode)) {
            if (stack.getItemDamage() < stack.getMaxDamage()) {
                stack.setItemDamage(stack.getItemDamage() + 1);
            } else {
                // Create broken item here
            }
        }		
	}

	@Override
	public ArmorProperties getProperties(EntityLivingBase player,
			ItemStack armor, DamageSource source, double damage, int slot) {
		return new ArmorProperties(1, damageReduceAmount / 22D, armor.getMaxDamage() + 1);
	}
	
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, int layer) {

		return HexxitGear.modid + ":armor/" + this.textureName + "_" + (this.armorType == 2 ? "2" : "1") + ".png";
	}
}
