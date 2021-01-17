package io.github.srvenient.elegantoptions.plugin.listener;

import dev.srvenient.freya.abstraction.configuration.Configuration;

import io.github.srvenient.elegantoptions.api.event.PlayerMessageJoinEvent;
import io.github.srvenient.elegantoptions.api.user.User;
import io.github.srvenient.elegantoptions.api.user.UserMatcher;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import javax.inject.Inject;
import javax.inject.Named;

import java.util.UUID;

import static io.github.srvenient.elegantoptions.plugin.translation.Translation.colorize;

public class PlayerMessageJoinListener implements Listener {

    @Inject private UserMatcher userMatcher;

    @Inject @Named("language") private Configuration language;

    @EventHandler
    public void onJoin(PlayerMessageJoinEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        User user = userMatcher.getUserId(uuid);

        if (user == null) return;

        switch (user.getMessageJoin()) {
            case ON:
                Bukkit.getOnlinePlayers().forEach(players -> players.sendMessage(colorize(players, language.getString("message-join.successful"))));

                break;
            case OFF:
            case NO_PERMISSION:
                break;
        }
    }
}
