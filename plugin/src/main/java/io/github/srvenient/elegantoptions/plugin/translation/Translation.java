package io.github.srvenient.elegantoptions.plugin.translation;

import me.clip.placeholderapi.PlaceholderAPI;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;

public class Translation {

    public static String colorize(Player player, String text) {
        return ChatColor.translateAlternateColorCodes('&', PlaceholderAPI.setPlaceholders(player, text));
    }

    public static List<String> colorizeList(Player player, List<String> texts) {
        texts.replaceAll(lines -> ChatColor.translateAlternateColorCodes('&', PlaceholderAPI.setPlaceholders(player, lines)));

        return texts;
    }
}
