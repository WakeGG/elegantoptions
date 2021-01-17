package io.github.srvenient.elegantoptions.plugin.database;

import dev.srvenient.freya.abstraction.configuration.Configuration;

import dev.srvenient.freya.api.FreyaAPI;

import io.github.srvenient.elegantoptions.api.database.DatabaseHandler;
import io.github.srvenient.elegantoptions.api.user.User;
import io.github.srvenient.elegantoptions.api.user.UserMatcher;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import javax.inject.Inject;
import javax.inject.Named;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Objects;
import java.util.UUID;

public class SimpleDatabaseHandler implements DatabaseHandler {

    @Inject private Plugin plugin;

    @Inject private UserMatcher userMatcher;

    @Inject @Named("config") private Configuration config;

    @Override
    public boolean existPlayer(Player player) throws SQLException {
        UUID uuid = player.getUniqueId();

        ResultSet resultSet;

        if (Objects.equals(config.getString("data.type"), "MySQL")) {
            resultSet = FreyaAPI.getMySQLdb().query("SELECT * FROM `ElegantOptions` WHERE (`id` VARCHAR(36))", uuid.toString());
        } else {
            resultSet = FreyaAPI.getSQLitedb().query("SELECT * FROM `ElegantOptions` WHERE (`id` VARCHAR(36))", uuid.toString());
        }

        return resultSet.next();
    }

    @Override
    public void setDroppedAsync(Player player, boolean dropped) throws SQLException {
        if (!existPlayer(player)) return;

        UUID uuid = player.getUniqueId();

        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            if (Objects.equals(config.getString("data.type"), "MySQL")) {
                FreyaAPI.getMySQLdb().update("UPDATE ElegantOptions SET `dropped` = ?  WHERE uuid = ?;", dropped, uuid.toString());
            } else {
                FreyaAPI.getSQLitedb().update("UPDATE ElegantOptions SET `dropped` = ? WHERE uuid = ?;", dropped, uuid.toString());
            }
        });
    }

    @Override
    public void updatePlayerAsync(Player player) throws SQLException {
        if (!existPlayer(player)) return;

        UUID uuid = player.getUniqueId();

        User user = userMatcher.getUserId(uuid);

        user.drop();

        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            if (Objects.equals(config.getString("data.type"), "MySQL")) {
                FreyaAPI.getMySQLdb().update("UPDATE ElegantOptions SET `visibility` = ?, `chat` = ?, `doubleJump` = ?, `mount` = ?, `fly` = ?, `messageJoin` = ?, `effectJoin` = ?, `dropped` = ?  WHERE uuid = ?;", user.getVisibility().name(), user.getChat().name(), user.getDoubleJump().name(), user.getMount().name(), user.getFly().name(), user.getMessageJoin().name(), user.getEffectJoin().name(), user.isDropped(), uuid.toString());
            } else {
                FreyaAPI.getSQLitedb().update("UPDATE ElegantOptions SET `visibility` = ?, `chat` = ?, `doubleJump` = ?, `mount` = ?, `fly` = ?, `messageJoin` = ?, `effectJoin` = ?, `dropped` = ? WHERE uuid = ?;", user.getVisibility().name(), user.getChat().name(), user.getDoubleJump().name(), user.getMount().name(), user.getFly().name(), user.getMessageJoin().name(), user.getEffectJoin().name(), user.isDropped(), uuid.toString());
            }
        });
    }
}
