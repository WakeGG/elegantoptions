package io.github.srvenient.elegantoptions.api.database;

import io.github.srvenient.elegantoptions.api.user.User;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public interface DatabaseHandler {

    boolean existPlayer(Player player) throws SQLException;

    void createPlayer(User user);

    ResultSet getUser(UUID uuid);

    void setDroppedAsync(Player player, boolean dropped) throws SQLException;

    void updatePlayerAsync(Player player) throws SQLException;

}
