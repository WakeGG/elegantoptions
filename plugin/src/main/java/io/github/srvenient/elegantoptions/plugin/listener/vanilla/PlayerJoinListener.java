package io.github.srvenient.elegantoptions.plugin.listener.vanilla;

import dev.srvenient.freya.abstraction.concurrent.Callback;
import dev.srvenient.freya.abstraction.configuration.Configuration;
import dev.srvenient.freya.abstraction.storage.ObjectCache;

import dev.srvenient.freya.api.BukkitEventMessenger;
import dev.srvenient.freya.api.FreyaAPI;
import io.github.srvenient.elegantoptions.api.Enums;
import io.github.srvenient.elegantoptions.api.database.DatabaseHandler;
import io.github.srvenient.elegantoptions.api.event.PlayerEffectJoinEvent;
import io.github.srvenient.elegantoptions.api.user.User;
import io.github.srvenient.elegantoptions.api.user.UserMatcher;
import io.github.srvenient.elegantoptions.plugin.utils.Utils;

import lombok.SneakyThrows;
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

import java.sql.ResultSet;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;

import static io.github.srvenient.elegantoptions.plugin.translation.Translation.colorize;

public class PlayerJoinListener implements Listener {

    @Inject private Plugin plugin;

    @Inject private ObjectCache<User> userCache;

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

                                userCache.update(newUser);

                                return;
                            }

                            databaseHandler.createPlayer(newUser);

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

                            userCache.update(user);

                            return;
                        }

                        databaseHandler.createPlayer(user);

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

        Optional<User> userOptional = userCache.getIfPresent(uuid.toString());

        if (!userOptional.isPresent()) return;

        User user = userOptional.get();

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
                Optional<User> usersOptional = userCache.getIfPresent(players.getUniqueId().toString());

                if (!usersOptional.isPresent()) return;

                User users = usersOptional.get();

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

            switch (user.getMessageJoin()) {
                case ON:
                    Bukkit.getOnlinePlayers().forEach(players -> players.sendMessage(colorize(players, language.getString("message-join.successful"))));

                    break;
                case OFF:
                case NO_PERMISSION:
                    break;
            }

            if (user.getEffectJoin() != Enums.Effects.NOTHING) {
                BukkitEventMessenger.callEvent(new PlayerEffectJoinEvent(player));
            }
        }
    }
}
