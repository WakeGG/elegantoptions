package io.github.srvenient.elegantoptions.plugin.command;

import dev.srvenient.freya.abstraction.configuration.Configuration;

import io.github.srvenient.elegantoptions.api.menu.MenuCreator;
import io.github.srvenient.elegantoptions.api.user.UserMatcher;

import io.github.srvenient.elegantoptions.plugin.utils.Utils;

import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.bukkit.annotation.Sender;

import org.bukkit.entity.Player;

import javax.inject.Inject;
import javax.inject.Named;

import static io.github.srvenient.elegantoptions.plugin.translation.Translation.colorize;

@Command(names = "elegantoptions")
public class MainCommand implements CommandClass {

    @Inject private UserMatcher userMatcher;

    @Inject @Named("main-menu") private MenuCreator mainMenu;

    @Inject @Named("config") private Configuration config;
    @Inject @Named("language") private Configuration language;

    @Command(names = {"", "?", "help"}, desc = "Help command.")
    public boolean helpCommand(@Sender Player player) {
        language.getStringList("help.message")
                .forEach(message -> player.sendMessage(colorize(player, message)));

        return true;
    }

    @Command(names = "showoptions", desc = "Open menu options")
    public boolean openCommand(@Sender Player player) {
        if (!player.hasPermission("elegantoptions.menu.mainmenu")) {
            player.sendMessage(colorize(player, language.getString("showoptions.no-permission")));

            return true;
        }

        if (!Utils.playerValidWorld(player, config)) {
            player.sendMessage(colorize(player, language.getString("showoptions.error")));

            return true;
        }

        player.openInventory(mainMenu.create(player));

        return true;
    }
}
