package io.github.srvenient.elegantoptions.api.database;

import org.bukkit.entity.Player;

import java.sql.SQLException;

public interface DatabaseHandler {

    boolean existPlayer(Player player) throws SQLException;

    void setDroppedAsync(Player player, boolean dropped) throws SQLException;

    void updatePlayerAsync(Player player) throws SQLException;

}
