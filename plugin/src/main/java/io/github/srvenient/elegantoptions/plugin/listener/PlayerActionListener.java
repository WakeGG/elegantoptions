package io.github.srvenient.elegantoptions.plugin.listener;

import dev.srvenient.freya.abstraction.configuration.Configuration;

import io.github.srvenient.elegantoptions.api.event.PlayerActionEvent;
import io.github.srvenient.elegantoptions.api.user.User;

import io.github.srvenient.elegantoptions.api.user.UserMatcher;
import io.github.srvenient.elegantoptions.plugin.effects.FireworkEffect;
import io.github.srvenient.elegantoptions.plugin.effects.SheepEffect;
import io.github.srvenient.elegantoptions.plugin.effects.ZeusEffect;
import io.github.srvenient.elegantoptions.plugin.utils.Utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import javax.inject.Inject;
import javax.inject.Named;

import static io.github.srvenient.elegantoptions.plugin.translation.Translation.colorize;

public class PlayerActionListener implements Listener {

    @Inject private UserMatcher userMatcher;

    @Inject private FireworkEffect fireworkEffect;
    @Inject private ZeusEffect zeusEffect;
    @Inject private SheepEffect sheepEffect;

    @Inject @Named("config") private Configuration config;
    @Inject @Named("language") private Configuration language;

    @EventHandler
    public void playerAction(PlayerActionEvent event) {
        Player player = event.getPlayer();
        User user = event.getUser();

        if (Utils.playerValidWorld(player, config)) {
            this.playerVisibility(user, player);

            this.playerFly(user, player);

            this.playerMessageJoin(user, player);

            this.playerEffectJoin(user, player);
        }
    }

    public void playerVisibility(User user, Player player) {
        switch (user.getVisibility()) {
            case NO_PERMISSION:
            case ON:
                Bukkit.getOnlinePlayers().forEach(player::showPlayer);

                break;
            case OFF:
                Bukkit.getOnlinePlayers().forEach(player::hidePlayer);

                break;
            case ONLY_RANK:
                Bukkit.getOnlinePlayers().forEach(players -> {
                    if (players.hasPermission("elegantoptions.options.visibility.rank")) {
                        player.showPlayer(players);
                    } else {
                        player.hidePlayer(players);
                    }
                });

                break;
        }

        for (Player players : Bukkit.getOnlinePlayers()) {
            User users = userMatcher.getUserId(players.getUniqueId());

            if (users != null) {
                switch (users.getVisibility()) {
                    case NO_PERMISSION:
                    case ON:
                        players.showPlayer(player);

                        break;
                    case OFF:
                        players.hidePlayer(player);

                        break;
                    case ONLY_RANK:
                        if (player.hasPermission("elegantoptions.options.visibility.rank")) {
                            players.showPlayer(player);
                        } else {
                            players.hidePlayer(player);
                        }

                        break;
                }
            }
        }
    }

    public void playerFly(User user, Player player) {
        switch (user.getFly()) {
            case ON:
                player.setFallDistance(0f);
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

    public void playerMessageJoin(User user, Player player) {
        switch (user.getMessageJoin()) {
            case ON:
                Bukkit.getOnlinePlayers().forEach(players -> players.sendMessage(colorize(players, language.getString("message-join.successful").replace("%player_name%", player.getName()))));

                break;
            case OFF:
            case NO_PERMISSION:
                break;
        }
    }

    public void playerEffectJoin(User user, Player player) {
        switch (user.getEffectJoin()) {
            case NOTHING:
                break;
            case FIREWORK:
                fireworkEffect.init(player);

                break;
            case ZEUS:
                zeusEffect.init(player);

                break;
            case SHEEP:
                sheepEffect.init(player);

                break;
            default:
                throw new IllegalStateException("Unexpected value: " + user.getEffectJoin());
        }
    }
}
