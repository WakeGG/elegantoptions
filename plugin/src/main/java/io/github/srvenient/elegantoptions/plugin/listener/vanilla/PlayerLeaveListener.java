package io.github.srvenient.elegantoptions.plugin.listener.vanilla;

import io.github.srvenient.elegantoptions.api.database.DatabaseHandler;

import lombok.SneakyThrows;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import javax.inject.Inject;

public class PlayerLeaveListener implements Listener {

    @Inject private DatabaseHandler databaseHandler;

    @SneakyThrows
    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        databaseHandler.updatePlayerAsync(event.getPlayer());
    }

    @SneakyThrows
    @EventHandler
    public void onKicked(PlayerKickEvent event) {
        databaseHandler.updatePlayerAsync(event.getPlayer());
    }
}
