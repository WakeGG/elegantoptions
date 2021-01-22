package io.github.srvenient.elegantoptions.plugin.listener.vanilla;

import dev.srvenient.freya.abstraction.configuration.Configuration;

import io.github.srvenient.elegantoptions.api.Enums;
import io.github.srvenient.elegantoptions.api.user.User;
import io.github.srvenient.elegantoptions.api.user.UserMatcher;
import io.github.srvenient.elegantoptions.plugin.utils.Utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

import javax.inject.Inject;
import javax.inject.Named;

import java.util.Objects;

import static io.github.srvenient.elegantoptions.plugin.translation.Translation.colorize;

public class PlayerInteractAtEntityListener implements Listener {

    @Inject private UserMatcher userMatcher;

    @Inject @Named("config") private Configuration config;
    @Inject @Named("language") private Configuration language;

    @EventHandler
    public void onMount(PlayerInteractAtEntityEvent event) {
        if (event.isCancelled())
            return;

        Entity entity = event.getRightClicked();
        if (entity.hasMetadata("NPC"))
            return;

        if (entity instanceof Player) {
            Player player = event.getPlayer();

            if (Utils.playerValidWorld(player, config)) {
                User target = userMatcher.getUserId(entity.getUniqueId());

                if (target.getMount() == Enums.TypeStatus.ON) {
                    if (player.hasPermission("elegantoptions.options.mount.rank")) {
                        if (player.getPassenger() != null) {
                            player.getPassenger().leaveVehicle();
                        } else {
                            entity.setPassenger(player);
                        }
                    } else {
                        player.sendMessage(colorize(player, language.getString("mount.has-no-rank")));
                    }
                } else {
                    player.sendMessage(colorize(player, language.getString("mount.target-disable"))
                            .replace("%target_name%", Objects.requireNonNull(Bukkit.getPlayer(entity.getUniqueId())).getName()));
                }
            }
        }
    }
}
