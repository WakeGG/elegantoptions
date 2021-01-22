package io.github.srvenient.elegantoptions.plugin.listener.vanilla;

import dev.srvenient.freya.abstraction.configuration.Configuration;
import dev.srvenient.freya.abstraction.storage.ObjectCache;

import dev.srvenient.freya.api.BukkitEventMessenger;

import io.github.srvenient.elegantoptions.api.event.PlayerActionEvent;
import io.github.srvenient.elegantoptions.api.user.User;
import io.github.srvenient.elegantoptions.api.user.UserMatcher;

import io.github.srvenient.elegantoptions.plugin.database.Database;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

import javax.inject.Inject;
import javax.inject.Named;

import java.util.UUID;

public class PlayerJoinListener implements Listener {

    @Inject private Plugin plugin;

    @Inject private UserMatcher userMatcher;

    @Inject private Database database;
    @Inject private ObjectCache<User> cache;

    @Inject @Named("config") private Configuration config;
    @Inject @Named("language") private Configuration language;

    @EventHandler
    public void onPreJoin(PlayerJoinEvent event) {
        this.loadPlayer(event.getPlayer());
    }

    public void loadPlayer(Player player) {
        UUID uuid = player.getUniqueId();

        String playerId = uuid.toString();
        String playerName = player.getName();

        cache.load(playerId)
                .addCallback(user -> {
                    if (user == null) {
                        User rawUser = new User(playerId, playerName);

                        database.loadUser(rawUser);

                        cache.update(rawUser);

                        return;
                    }

                    user.setPlayerName(playerName);

                    database.loadUser(user);

                    cache.update(user);
                });
    }
}
