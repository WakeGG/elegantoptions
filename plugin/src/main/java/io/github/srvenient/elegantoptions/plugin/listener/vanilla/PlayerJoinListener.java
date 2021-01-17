package io.github.srvenient.elegantoptions.plugin.listener.vanilla;

import dev.srvenient.freya.abstraction.concurrent.Callback;
import dev.srvenient.freya.abstraction.configuration.Configuration;
import dev.srvenient.freya.abstraction.storage.ObjectCache;

import dev.srvenient.freya.api.BukkitEventMessenger;

import io.github.srvenient.elegantoptions.api.Enums;
import io.github.srvenient.elegantoptions.api.database.DatabaseHandler;
import io.github.srvenient.elegantoptions.api.event.PlayerVisibilityJoinEvent;
import io.github.srvenient.elegantoptions.api.event.PlayerEffectJoinEvent;
import io.github.srvenient.elegantoptions.api.event.PlayerFlyJoinEvent;
import io.github.srvenient.elegantoptions.api.event.PlayerMessageJoinEvent;
import io.github.srvenient.elegantoptions.api.user.User;
import io.github.srvenient.elegantoptions.api.user.UserMatcher;
import io.github.srvenient.elegantoptions.plugin.utils.Utils;

import lombok.SneakyThrows;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

import javax.inject.Inject;
import javax.inject.Named;

import java.sql.ResultSet;

import java.util.UUID;
import java.util.logging.Level;

public class PlayerJoinListener implements Listener {

    @Inject private Plugin plugin;

    @Inject private ObjectCache<User> userCache;
    @Inject private UserMatcher userMatcher;

    @Inject private DatabaseHandler databaseHandler;

    @Inject @Named("config") private Configuration config;
    @Inject @Named("language") private Configuration language;

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        userCache
                .load(uuid.toString())
                .addCallback(new Callback<User>() {

                    @SneakyThrows
                    @Override
                    public void call(User user) {
                        if (user == null) {
                            User newUser = new User(player);

                            newUser.setDropped(false);

                            if (databaseHandler.existPlayer(player)) {
                                try (ResultSet resultSet = databaseHandler.getUser(uuid)) {
                                    newUser.setVisibility(Enums.TypeStatus.valueOf(resultSet.getString("visibility")));
                                    newUser.setChat(Enums.TypeStatus.valueOf(resultSet.getString("chat")));
                                    newUser.setDoubleJump(Enums.TypeStatus.valueOf(resultSet.getString("doubleJump")));
                                    newUser.setMount(Enums.TypeStatus.valueOf(resultSet.getString("mount")));
                                    newUser.setFly(Enums.TypeStatus.valueOf(resultSet.getString("fly")));
                                    newUser.setMessageJoin(Enums.TypeStatus.valueOf(resultSet.getString("messageJoin")));
                                    newUser.setEffectJoin(Enums.Effects.valueOf(resultSet.getString("effectJoin")));
                                }

                                databaseHandler.setDroppedAsync(player, false);

                            } else {
                                databaseHandler.createPlayer(newUser);
                            }

                            if (Utils.playerValidWorld(player, config)) {
                                if (newUser.getVisibility() != null) {
                                    BukkitEventMessenger.callEvent(new PlayerVisibilityJoinEvent(player));
                                }

                                if (newUser.getFly() != null) {
                                    BukkitEventMessenger.callEvent(new PlayerFlyJoinEvent(player));
                                }

                                if (newUser.getMessageJoin() != null) {
                                    BukkitEventMessenger.callEvent(new PlayerMessageJoinEvent(player));
                                }

                                if (newUser.getEffectJoin() != Enums.Effects.NOTHING) {
                                    BukkitEventMessenger.callEvent(new PlayerEffectJoinEvent(player));
                                }
                            }

                            userCache.update(newUser);

                            return;
                        }

                        user.setDropped(false);

                        if (databaseHandler.existPlayer(player)) {
                            try (ResultSet resultSet = databaseHandler.getUser(uuid)) {
                                user.setVisibility(Enums.TypeStatus.valueOf(resultSet.getString("visibility")));
                                user.setChat(Enums.TypeStatus.valueOf(resultSet.getString("chat")));
                                user.setDoubleJump(Enums.TypeStatus.valueOf(resultSet.getString("doubleJump")));
                                user.setMount(Enums.TypeStatus.valueOf(resultSet.getString("mount")));
                                user.setFly(Enums.TypeStatus.valueOf(resultSet.getString("fly")));
                                user.setMessageJoin(Enums.TypeStatus.valueOf(resultSet.getString("messageJoin")));
                                user.setEffectJoin(Enums.Effects.valueOf(resultSet.getString("effectJoin")));
                            }

                            databaseHandler.setDroppedAsync(player, false);
                        } else {
                            databaseHandler.createPlayer(user);
                        }

                        if (Utils.playerValidWorld(player, config)) {
                            if (user.getVisibility() != null) {
                                BukkitEventMessenger.callEvent(new PlayerVisibilityJoinEvent(player));
                            }

                            if (user.getFly() != null) {
                                BukkitEventMessenger.callEvent(new PlayerFlyJoinEvent(player));
                            }

                            if (user.getMessageJoin() != null) {
                                BukkitEventMessenger.callEvent(new PlayerMessageJoinEvent(player));
                            }

                            if (user.getEffectJoin() != Enums.Effects.NOTHING) {
                                BukkitEventMessenger.callEvent(new PlayerEffectJoinEvent(player));
                            }
                        }

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
}
