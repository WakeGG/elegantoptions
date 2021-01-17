package io.github.srvenient.elegantoptions.plugin.listener;

import io.github.srvenient.elegantoptions.api.event.PlayerFlyJoinEvent;
import io.github.srvenient.elegantoptions.api.user.User;
import io.github.srvenient.elegantoptions.api.user.UserMatcher;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import javax.inject.Inject;

import java.util.UUID;

public class PlayerFlyJoinListener implements Listener {

    @Inject private UserMatcher userMatcher;

    @EventHandler
    public void onJoin(PlayerFlyJoinEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        User user = userMatcher.getUserId(uuid);

        if (user == null) return;

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
    }
}
