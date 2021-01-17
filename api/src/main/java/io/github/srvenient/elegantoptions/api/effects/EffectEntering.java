package io.github.srvenient.elegantoptions.api.effects;

import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.concurrent.ThreadLocalRandom;

public abstract class EffectEntering {

    public abstract void init(Player player);

    protected double random(double var1, double var3) {
        return var1 + ThreadLocalRandom.current().nextDouble() * (var3 - var1);
    }

    protected boolean check(Player player, World world) {
        return (player != null && player.isOnline() && player.getWorld() == world);
    }
}
