package io.github.srvenient.elegantoptions.plugin.listener.vanilla;

import dev.srvenient.freya.abstraction.configuration.Configuration;

import io.github.srvenient.elegantoptions.api.Enums;
import io.github.srvenient.elegantoptions.api.user.User;
import io.github.srvenient.elegantoptions.api.user.UserMatcher;
import io.github.srvenient.elegantoptions.plugin.utils.Utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import javax.inject.Inject;
import javax.inject.Named;

import static io.github.srvenient.elegantoptions.plugin.translation.Translation.colorize;

public class AsyncPlayerChatListener implements Listener {

    @Inject private UserMatcher userMatcher;

    @Inject @Named("config") private Configuration config;
    @Inject @Named("language") private Configuration language;

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        User user = userMatcher.getUserId(player.getUniqueId());

        if (user == null) {
            return;
        }

        if (Utils.playerValidWorld(player, config)) {
            if (user.getChat() == Enums.TypeStatus.OFF) {
                event.setCancelled(true);

                player.sendMessage(colorize(player, language.getString("chat.error")));

                return;
            }

            Bukkit.getOnlinePlayers()
                    .forEach(players -> {
                        User users = userMatcher.getUserId(players.getUniqueId());

                        if (users.getChat() == Enums.TypeStatus.OFF) {
                            event.getRecipients().remove(players);
                        }
                    });
        }
    }
}
