package io.github.srvenient.elegantoptions.plugin.utils;

import dev.srvenient.freya.abstraction.configuration.Configuration;
import dev.srvenient.freya.api.xseries.XMaterial;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.List;

import static io.github.srvenient.elegantoptions.plugin.translation.Translation.colorize;
import static io.github.srvenient.elegantoptions.plugin.translation.Translation.colorizeList;

public class InventoryUtils {

    public static int getItemSlot(Configuration configuration, String menuKey, String id) {
        return configuration.getInt(menuKey + ".items." + id + ".slot");
    }

    public static Material getItemMaterial(Configuration configuration, String menuKey, String id) {
        return XMaterial.valueOf(configuration.getString(menuKey + ".items." + id + ".material")).parseMaterial();
    }

    public static int getItemAmount(Configuration configuration, String menuKey, String id) {
        return configuration.getInt(menuKey + ".items." + id + ".amount");
    }

    public static byte getItemData(Configuration configuration, String menuKey, String id) {
        return XMaterial.valueOf(configuration.getString(menuKey + ".items." + id + ".material")).getData();
    }

    public static String getItemName(Configuration configuration, Player player, String menuKey, String id) {
        return colorize(player, configuration.getString(menuKey + ".items." + id + ".name"));
    }

    public static List<String> getItemLore(Configuration configuration, Player player, String menuKey, String id) {
        return colorizeList(player, configuration.getStringList(menuKey + ".items." + id + ".lore"));
    }
}
