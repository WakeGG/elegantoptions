package io.github.srvenient.elegantoptions.plugin.listener.vanilla;

import io.github.srvenient.elegantoptions.api.user.User;
import io.github.srvenient.elegantoptions.api.user.UserMatcher;
import io.github.srvenient.elegantoptions.plugin.database.Database;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

import javax.inject.Inject;

public class PlayerLeaveListener implements Listener {

    @Inject private Plugin plugin;

    @Inject private Database database;
    @Inject private UserMatcher userMatcher;

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        User user = userMatcher.getUserId(player.getUniqueId());

        if (user == null) return;

        user.drop();

        database.saveData(user);
    }

    @EventHandler
    public void onKicked(PlayerKickEvent event) {
        Player player = event.getPlayer();

        User user = userMatcher.getUserId(player.getUniqueId());

        if (user == null) return;

        user.drop();

        database.saveData(user);
    }
}
