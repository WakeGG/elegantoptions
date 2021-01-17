package io.github.srvenient.elegantoptions.plugin.listener;

import io.github.srvenient.elegantoptions.api.effects.EffectEntering;
import io.github.srvenient.elegantoptions.api.event.PlayerEffectJoinEvent;
import io.github.srvenient.elegantoptions.api.user.User;
import io.github.srvenient.elegantoptions.api.user.UserMatcher;

import io.github.srvenient.elegantoptions.plugin.effects.FireworkEffect;
import io.github.srvenient.elegantoptions.plugin.effects.SheepEffect;
import io.github.srvenient.elegantoptions.plugin.effects.ZeusEffect;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import javax.inject.Inject;
import java.util.UUID;

public class PlayerEffectJoinListener implements Listener {

    @Inject private UserMatcher userMatcher;

    @EventHandler
    public void onJoin(PlayerEffectJoinEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        User user = userMatcher.getUserId(uuid);

        if (user == null) return;

        EffectEntering effectEntering;

        switch (user.getEffectJoin()) {
            case FIREWORK:
                effectEntering = new FireworkEffect();
                effectEntering.init(player);

                break;
            case ZEUS:
                effectEntering = new ZeusEffect();
                effectEntering.init(player);

                break;
            case SHEEP:
                effectEntering = new SheepEffect();
                effectEntering.init(player);

                break;
        }
    }
}
