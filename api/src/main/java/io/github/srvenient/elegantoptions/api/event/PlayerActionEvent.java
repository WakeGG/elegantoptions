package io.github.srvenient.elegantoptions.api.event;

import io.github.srvenient.elegantoptions.api.user.User;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import org.jetbrains.annotations.NotNull;

public class PlayerActionEvent extends Event {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Player player;
    private final User user;

    public PlayerActionEvent(@NotNull Player player, @NotNull User user) {
        this.player = player;
        this.user = user;
    }

    public Player getPlayer() {
        return player;
    }

    public User getUser() {
        return user;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
