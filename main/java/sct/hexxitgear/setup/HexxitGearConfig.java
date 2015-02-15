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

package sct.hexxitgear.setup;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import sct.hexxitgear.HexxitGear;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class HexxitGearConfig {

    public static Property hexbiscus;

    public static Property tribalHelmetId;
    public static Property tribalChestId;
    public static Property tribalLeggingsId;
    public static Property tribalShoesId;
    public static Property scaleHelmetId;
    public static Property scaleChestId;
    public static Property scaleLeggingsId;
    public static Property scaleBootsId;
    public static Property thiefHelmetId;
    public static Property thiefChestId;
    public static Property thiefLeggingsId;
    public static Property thiefBootsId;
    public static Property hexicalEssence;
    public static Property hexicalDiamond;

    public static Property dimensionalBlacklist;

    public static File configFolder;

    public static void loadCommonConfig(FMLPreInitializationEvent evt)
    {
        Configuration c = new Configuration(evt.getSuggestedConfigurationFile());
        try {
            c.load();

      //      hexbiscus = c.getBlock("ID.HexbiscusFlower", 2400);

            tribalHelmetId = c.get(Configuration.CATEGORY_GENERAL, "ID.TribalHelmet", 26200);
            tribalChestId = c.get(Configuration.CATEGORY_GENERAL, "ID.TribalChest", 26201);
            tribalLeggingsId = c.get(Configuration.CATEGORY_GENERAL, "ID.TribalLeggings", 26202);
            tribalShoesId = c.get(Configuration.CATEGORY_GENERAL, "ID.TribalShoes", 26203);

            scaleHelmetId = c.get(Configuration.CATEGORY_GENERAL, "ID.ScaleHelmet", 26204);
            scaleChestId = c.get(Configuration.CATEGORY_GENERAL, "ID.ScaleChest", 26205);
            scaleLeggingsId = c.get(Configuration.CATEGORY_GENERAL, "ID.ScaleLeggings", 26206);
            scaleBootsId = c.get(Configuration.CATEGORY_GENERAL, "ID.ScaleBoots", 26207);

            thiefHelmetId = c.get(Configuration.CATEGORY_GENERAL, "ID.ThiefHelmet", 26208);
            thiefChestId = c.get(Configuration.CATEGORY_GENERAL, "ID.ThiefChest", 26209);
            thiefLeggingsId = c.get(Configuration.CATEGORY_GENERAL, "ID.ThiefLeggings", 26210);
            thiefBootsId = c.get(Configuration.CATEGORY_GENERAL, "ID.ThiefBoots", 26211);

            hexicalEssence = c.get(Configuration.CATEGORY_GENERAL, "ID.HexicalEssence", 26212);
            hexicalDiamond = c.get(Configuration.CATEGORY_GENERAL, "ID.HexicalDiamond", 26213);

            dimensionalBlacklist = c.get("World Generation", "Dimensional Blacklist", "");
            dimensionalBlacklist.comment = "Comma separated list of all blacklisted dimension IDs";

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            c.save();
        }
    }

    public static String getConfigBaseFolder()
    {
        return "sct";
    }

    public static void setConfigFolderBase(File folder)
    {
        configFolder = new File(folder.getAbsolutePath() + "/" + getConfigBaseFolder() + "/"
                + HexxitGear.modid + "/");
    }

    public static void extractLang(String[] languages)
    {
        String langResourceBase = "/sct/" + HexxitGear.modid + "/lang/";
        for (String lang : languages)
        {
            InputStream is = HexxitGear.instance.getClass().getResourceAsStream(langResourceBase + lang + ".lang");
            try
            {
                File f = new File(configFolder.getAbsolutePath() + "/lang/"
                        + lang + ".lang");
                if (!f.exists())
                    f.getParentFile().mkdirs();
                OutputStream os = new FileOutputStream(f);
                byte[] buffer = new byte[1024];
                int read = 0;
                while ((read = is.read(buffer)) != -1)
                {
                    os.write(buffer, 0, read);
                }
                is.close();
                os.flush();
                os.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public static void loadLang()
    {
        File f = new File(configFolder.getAbsolutePath() + "/lang/");
        for (File langFile : f.listFiles(new FilenameFilter()
        {
            @Override
            public boolean accept(File dir, String name)
            {
                return name.endsWith(".lang");
            }
        }))
        {
            try
            {
                Properties langPack = new Properties();
                langPack.load(new FileInputStream(langFile));
                String lang = langFile.getName().replace(".lang", "");
                LanguageRegistry.instance().addStringLocalization(langPack,
                        lang);
            }
            catch (FileNotFoundException x)
            {
                x.printStackTrace();
            }
            catch (IOException x)
            {
                x.printStackTrace();
            }
        }
    }

    public static void registerDimBlacklist() {
        String blacklist = dimensionalBlacklist.getString().trim();

        for (String dim : blacklist.split(",")) {
            try {
                Integer dimID = Integer.parseInt(dim);
                HexxitGear.addToDimBlacklist(dimID);
            } catch (Exception e) {
            }
        }
    }
}
