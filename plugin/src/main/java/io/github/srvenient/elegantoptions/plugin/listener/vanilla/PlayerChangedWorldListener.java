package io.github.srvenient.elegantoptions.plugin.listener.vanilla;

import dev.srvenient.freya.abstraction.configuration.Configuration;

import io.github.srvenient.elegantoptions.api.user.User;
import io.github.srvenient.elegantoptions.api.user.UserMatcher;

import io.github.srvenient.elegantoptions.plugin.utils.Utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

import javax.inject.Inject;
import javax.inject.Named;

import java.util.UUID;

public class PlayerChangedWorldListener implements Listener {

    @Inject private UserMatcher userMatcher;

    @Inject @Named("config") private Configuration config;

    @EventHandler
    public void onPlayerChanged(PlayerChangedWorldEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        User user = userMatcher.getUserId(uuid);

        if (user == null) return;

        if (Utils.playerValidWorld(player, config)) {
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

            switch (user.getFly()) {
                case ON:
                    player.setFallDistance(0F);
                    player.setAllowFlight(true);

                    if (!player.getAllowFlight()) {
                        player.setFlying(false);
                    }

                    break;
                case OFF:
                case NO_PERMISSION:
                    player.setAllowFlight(false);

                    break;
            }
        } else {
            switch (user.getVisibility()) {
                case ON:
                case OFF:
                case NO_PERMISSION:
                case ONLY_RANK:
                    Bukkit.getOnlinePlayers().forEach(player::showPlayer);

                    break;
            }

            switch (user.getFly()) {
                case ON:
                case OFF:
                case NO_PERMISSION:
                    player.setAllowFlight(false);

                    break;
            }
        }
    }
}
