package io.github.srvenient.elegantoptions.plugin.utils;

import dev.srvenient.freya.abstraction.configuration.Configuration;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Objects;
import java.util.logging.Level;

public class Utils {

    public static boolean playerValidWorld(Player player,
                                           Configuration config) {

        List<String> world = config.getStringList("world.worlds");

        if (Objects.equals(config.getString("world.type"), "white_list")) {
            return world.contains(player.getWorld().getName());
        } else if (Objects.equals(config.getString("world.type"), "black_list")) {
            return !world.contains(player.getWorld().getName());
        } else {
            Bukkit.getLogger().log(Level.INFO, "A problem has occurred in the config.yml file at line: 32");

            return false;
        }
    }
}
