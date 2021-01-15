package io.github.srvenient.elegantoptions.plugin.listener.vanilla;

import dev.srvenient.freya.abstraction.configuration.Configuration;
import dev.srvenient.freya.api.xseries.XSound;

import io.github.srvenient.elegantoptions.api.Enums;
import io.github.srvenient.elegantoptions.api.user.User;
import io.github.srvenient.elegantoptions.api.user.UserMatcher;
import io.github.srvenient.elegantoptions.plugin.utils.Utils;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;

import javax.inject.Inject;
import javax.inject.Named;

public class PlayerToggleFlightListener implements Listener {

    @Inject private UserMatcher userMatcher;

    @Inject @Named("config") private Configuration config;
    @Inject @Named("language") private Configuration language;

    @EventHandler
    public void onToggleFlight(PlayerToggleFlightEvent event) {
        Player player = event.getPlayer();

        User user = userMatcher.getUserId(player.getUniqueId());

        if (user == null) return;

        if (Utils.playerValidWorld(player, config)) {
            if (user.getDoubleJump() == Enums.TypeStatus.ON) {
                if (player.getGameMode() == GameMode.CREATIVE || player.getGameMode() == GameMode.SPECTATOR) return;

                event.setCancelled(true);

                player.setAllowFlight(false);
                player.setFlying(false);

                player.setVelocity(player.getLocation().getDirection().multiply(1.5D).setY(1));

                XSound.play(player, language.getString("double-jump.sound"));
            }
        }
    }
}
