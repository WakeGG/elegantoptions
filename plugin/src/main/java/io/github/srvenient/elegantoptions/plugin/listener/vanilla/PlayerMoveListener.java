package io.github.srvenient.elegantoptions.plugin.listener.vanilla;


import dev.srvenient.freya.abstraction.configuration.Configuration;
import dev.srvenient.freya.api.xseries.XMaterial;

import io.github.srvenient.elegantoptions.api.Enums;
import io.github.srvenient.elegantoptions.api.user.User;
import io.github.srvenient.elegantoptions.api.user.UserMatcher;
import io.github.srvenient.elegantoptions.plugin.utils.Utils;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import javax.inject.Inject;
import javax.inject.Named;

public class PlayerMoveListener implements Listener {

    @Inject private UserMatcher userMatcher;

    @Inject @Named("config") private Configuration config;
    @Inject @Named("language") private Configuration language;

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        User user = userMatcher.getUserId(player.getUniqueId());

        if (user == null) return;

        if (Utils.playerValidWorld(player, config)) {
            if (user.getDoubleJump() == Enums.TypeStatus.ON) {
                if (player.getGameMode() != GameMode.CREATIVE && player.getLocation().subtract(0.0D, 1.0D, 0.0D).getBlock().getType() != XMaterial.AIR.parseMaterial() && !player.isFlying())
                    player.setAllowFlight(true);
            }
        }
    }
}
