package io.github.srvenient.elegantoptions.plugin.listener.vanilla;

import dev.srvenient.freya.abstraction.concurrent.Callback;
import dev.srvenient.freya.abstraction.configuration.Configuration;
import dev.srvenient.freya.abstraction.storage.ObjectCache;

import io.github.srvenient.elegantoptions.api.user.User;
import io.github.srvenient.elegantoptions.api.user.UserMatcher;
import io.github.srvenient.elegantoptions.plugin.utils.Utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import javax.inject.Inject;
import javax.inject.Named;

import java.util.UUID;
import java.util.logging.Level;

import static io.github.srvenient.elegantoptions.plugin.translation.Translation.colorize;

public class PlayerJoinListener implements Listener {

    @Inject private Plugin plugin;

    @Inject private UserMatcher userMatcher;
    @Inject private ObjectCache<User> userCache;

    @Inject @Named("config") private Configuration config;
    @Inject @Named("language") private Configuration language;

    @EventHandler(priority = EventPriority.LOWEST)
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        userCache
                .load(uuid.toString())
                .addCallback(new Callback<User>() {

                    @Override
                    public void call(User user) {
                        if (user == null) {
                            User newUser = new User(player);

                            newUser.setDropped(false);

                            // cosas por mysql.

                            userCache.update(newUser);

                            return;
                        }

                        user.setDropped(false);

                        // cosas por mysql.

                        userCache.update(user);
                    }

                    @Override
                    public void handleError(Throwable error) {
                        /*
                          An error has occurred.

                          TODO: Add error message
                         */
                        player.kickPlayer("");

                        Bukkit.getLogger().log(
                                Level.WARNING,
                                "An error has occurred while getting player " + player.getName(),
                                error
                        );
                    }
                });
    }

    @EventHandler
    public void onPlayerAction(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        User user = userMatcher.getUserId(uuid);

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
                    if (config.getBoolean("visibility-throws.enabled")) {
                        Vector v = new Vector(
                                config.getInt("visibility-throws.distance.x"),
                                config.getInt("visibility-throws.distance.y"),
                                config.getInt("visibility-throws.distance.z")
                        );

                        player.setVelocity(v);
                    }

                    player.setAllowFlight(true);
                    player.setFlying(true);

                    break;
                case OFF:
                case NO_PERMISSION:
                    player.setAllowFlight(false);

                    break;
            }

            switch (user.getMessageJoin()) {
                case ON:
                    Bukkit.getOnlinePlayers()
                            .forEach(players -> players.sendMessage(colorize(players, language.getString("message-join.successful"))));

                    break;
                case OFF:
                case NO_PERMISSION:
                    break;
            }
        }
    }
}
