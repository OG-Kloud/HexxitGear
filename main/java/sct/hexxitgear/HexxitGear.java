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

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import sct.hexxitgear.block.BlockHexbiscus;
import sct.hexxitgear.event.PlayerEventHandler;
import sct.hexxitgear.item.ItemHexicalDiamond;
import sct.hexxitgear.item.ItemHexicalEssence;
import sct.hexxitgear.item.ItemScaleArmor;
import sct.hexxitgear.item.ItemThiefArmor;
import sct.hexxitgear.item.ItemTribalArmor;
import sct.hexxitgear.setup.HexxitGearConfig;
import sct.hexxitgear.setup.HexxitGearRegistry;
import sct.hexxitgear.tick.PlayerTracker;
import sct.hexxitgear.world.HGWorldGen;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = HexxitGear.modid, name = "Hexxit Gear", useMetadata = true, version = HexxitGear.version)
/*
@NetworkMod(serverSideRequired = false, clientSideRequired = true,
        clientPacketHandlerSpec = @NetworkMod.SidedPacketHandler(channels = { HexxitGear.modNetworkChannel }, packetHandler = HGPacketHandler.class),
        serverPacketHandlerSpec = @NetworkMod.SidedPacketHandler(channels = { HexxitGear.modNetworkChannel }, packetHandler = HGPacketHandler.class))
*/
public class HexxitGear {

    public static final String modid = "hexxitgear";
    public static final String modNetworkChannel = "HexxitGear";
    public static final String version = "1.5.2R1.0";

    @Mod.Instance(modid)
    public static HexxitGear instance;
    
    public static String texturePre = modid + ":";

    @SidedProxy(clientSide="sct.hexxitgear.ClientProxy", serverSide="sct.hexxitgear.CommonProxy")
    public static CommonProxy proxy;

    public static Logger logger;
    public static PlayerEventHandler playerEventHandler;

    public static Block hexbiscus;

    public static Item hexicalEssence;
    public static Item hexicalDiamond;

    public static Item tribalHelmet;
    public static Item tribalChest;
    public static Item tribalLeggings;
    public static Item tribalShoes;

    public static Item thiefHelmet;
    public static Item thiefChest;
    public static Item thiefLeggings;
    public static Item thiefBoots;

    public static Item scaleHelmet;
    public static Item scaleChest;
    public static Item scaleLeggings;
    public static Item scaleBoots;

    public static List<Integer> dimensionalBlacklist = new ArrayList<Integer>();

    @EventHandler
    public void preInit(FMLPreInitializationEvent evt) {
    	

        
        HexxitGearConfig.setConfigFolderBase(evt.getModConfigurationDirectory());

        HexxitGearConfig.loadCommonConfig(evt);

        HexxitGearConfig.extractLang(new String[]{"en_US"});
        HexxitGearConfig.loadLang();
        HexxitGearConfig.registerDimBlacklist();

 //       logger = evt.getModLog();
        playerEventHandler = new PlayerEventHandler();
        MinecraftForge.EVENT_BUS.register(playerEventHandler);
    }

    @EventHandler
    public void init(FMLInitializationEvent evt) {
        
    	hexbiscus = new BlockHexbiscus(2400);
    	
        tribalHelmet = new ItemTribalArmor("hexxitgear.tribal.helmet", this.texturePre + this.tribalHelmet.getUnlocalizedName(), 0);
        tribalChest = new ItemTribalArmor("hexxitgear.tribal.chest", this.texturePre + this.tribalChest.getUnlocalizedName(), 1).setUnlocalizedName("hexxitgear.tribal.chest");
        tribalLeggings = new ItemTribalArmor("hexxitgear.tribal.leggings", this.texturePre + this.tribalLeggings.getUnlocalizedName(), 2).setUnlocalizedName("hexxitgear.tribal.leggings");
        tribalShoes = new ItemTribalArmor("hexxitgear.tribal.boots", this.texturePre + this.tribalShoes.getUnlocalizedName(), 3).setUnlocalizedName("hexxitgear.tribal.boots");
        scaleHelmet = new ItemScaleArmor("hexxitgear.scale.helmet", this.texturePre + this.scaleHelmet.getUnlocalizedName(), 0).setUnlocalizedName("hexxitgear.scale.helmet");
        scaleChest = new ItemScaleArmor("hexxitgear.scale.chest", this.texturePre + this.scaleChest.getUnlocalizedName(), 1).setUnlocalizedName("hexxitgear.scale.chest");
        scaleLeggings = new ItemScaleArmor("hexxitgear.scale.leggings", this.texturePre + this.scaleChest.getUnlocalizedName(), 2).setUnlocalizedName("hexxitgear.scale.leggings");
        scaleBoots = new ItemScaleArmor("hexxitgear.scale.boots", this.texturePre + this.scaleBoots.getUnlocalizedName(), 3).setUnlocalizedName("hexxitgear.scale.boots");
        thiefHelmet = new ItemThiefArmor("hexxitgear.thief.helmet", this.texturePre + this.thiefHelmet.getUnlocalizedName(), 0).setUnlocalizedName("hexxitgear.thief.helmet");
        thiefChest = new ItemThiefArmor("hexxitgear.thief.chest", this.texturePre + this.thiefChest.getUnlocalizedName(), 1).setUnlocalizedName("hexxitgear.thief.chest");
        thiefLeggings = new ItemThiefArmor("hexxitgear.thief.leggings", this.texturePre + this.thiefLeggings.getUnlocalizedName(), 2).setUnlocalizedName("hexxitgear.thief.leggings");
        thiefBoots = new ItemThiefArmor("hexxitgear.thief.boots", this.texturePre + this.thiefBoots.getUnlocalizedName(), 3).setUnlocalizedName("hexxitgear.thief.boots");

        hexicalEssence = new ItemHexicalEssence(HexxitGearConfig.hexicalEssence.getInt());
        hexicalDiamond = new ItemHexicalDiamond(HexxitGearConfig.hexicalDiamond.getInt());

        HexxitGearRegistry.init();

        GameRegistry.registerWorldGenerator(new HGWorldGen(), 1);

    //    proxy.init();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent evt) {
  //      GameRegistry.registerPlayerTracker(PlayerTracker.instance);
        
    }

    public static void addToDimBlacklist(int dimID) {
        if (!dimensionalBlacklist.contains(dimID))
            dimensionalBlacklist.add(dimID);
    }

    public static List<Integer> getDimBlacklist() {
        return dimensionalBlacklist;
    }
}
