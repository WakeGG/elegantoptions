package io.github.srvenient.elegantoptions.plugin.effects;

import io.github.srvenient.elegantoptions.api.effects.EffectEntering;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class ZeusEffect extends EffectEntering {

    @Override
    public void init(Player player) {
        this.throwThunder(player);
    }

    private void throwThunder(Player player) {
        World world = player.getWorld();
        Location location = player.getLocation();

        world.strikeLightningEffect(location);
    }
}
