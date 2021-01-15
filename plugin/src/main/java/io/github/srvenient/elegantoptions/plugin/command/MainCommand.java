package io.github.srvenient.elegantoptions.plugin.command;

import dev.srvenient.freya.abstraction.configuration.Configuration;

import io.github.srvenient.elegantoptions.api.user.User;
import io.github.srvenient.elegantoptions.api.user.UserMatcher;

import io.github.srvenient.elegantoptions.plugin.menu.option.MainMenu;
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

    @Inject private MainMenu mainMenu;

    @Inject @Named("config") private Configuration config;
    @Inject @Named("language") private Configuration language;

    @Command(names = "help")
    public boolean helpCommand(@Sender Player player) {
        player.sendMessage("hola");

        return true;
    }

    @Command(names = "showoptions")
    public boolean openCommand(@Sender Player player) {
        User user = userMatcher.getUserId(player.getUniqueId());

        if (user == null) return true;

        if (!Utils.playerValidWorld(player, config)) {
            player.sendMessage(colorize(player, language.getString("showoptions.error")));

            return true;
        }

        player.openInventory(mainMenu.create(player));

        return true;
    }
}
