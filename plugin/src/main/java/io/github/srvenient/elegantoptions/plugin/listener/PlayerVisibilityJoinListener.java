package io.github.srvenient.elegantoptions.plugin.listener;

import io.github.srvenient.elegantoptions.api.event.PlayerVisibilityJoinEvent;
import io.github.srvenient.elegantoptions.api.user.User;
import io.github.srvenient.elegantoptions.api.user.UserMatcher;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import javax.inject.Inject;

import java.util.UUID;

public class PlayerVisibilityJoinListener implements Listener {

    @Inject private UserMatcher userMatcher;

    @EventHandler
    public void onJoin(PlayerVisibilityJoinEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        User user = userMatcher.getUserId(uuid);

        if (user == null) return;

        switch (user.getVisibility()) {
            case ON:
                Bukkit.getOnlinePlayers().forEach(player::showPlayer);

                break;
            case OFF:
            case NO_PERMISSION:
                Bukkit.getOnlinePlayers().forEach(player::hidePlayer);

                break;
            case ONLY_RANK:
                Bukkit.getOnlinePlayers().forEach(players -> {
                    if (players.hasPermission("elegant_options.user.rank")) {
                        player.showPlayer(players);
                    } else {
                        player.hidePlayer(players);
                    }
                });

                break;
        }

        for (Player players : Bukkit.getOnlinePlayers()) {
            User users = userMatcher.getUserId(players.getUniqueId());

            switch (users.getVisibility()) {
                case ON:
                    players.showPlayer(player);

                    break;
                case NO_PERMISSION:
                case OFF:
                    players.hidePlayer(player);

                    break;
                case ONLY_RANK:
                    if (player.hasPermission("elegant_options.user.rank")) {
                        players.showPlayer(player);
                    } else {
                        players.hidePlayer(player);
                    }

                    break;
            }
        }
    }
}
