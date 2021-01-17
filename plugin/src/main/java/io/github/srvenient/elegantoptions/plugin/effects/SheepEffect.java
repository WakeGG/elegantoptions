package io.github.srvenient.elegantoptions.plugin.effects;

import dev.srvenient.freya.api.xseries.XSound;
import io.github.srvenient.elegantoptions.api.effects.EffectEntering;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import javax.inject.Inject;

import java.util.concurrent.ThreadLocalRandom;

public class SheepEffect extends EffectEntering {

    @Inject private Plugin plugin;

    @Override
    public void init(Player player) {
        World world = player.getWorld();

        for (int i = 0; i < 5; i++) {
            if (this.check(player, world)) {
                Bukkit.getScheduler().runTaskLater(plugin, () -> {
                    if (this.check(player, world)) return;

                    XSound.play(player, "ENTITY_GENERIC_EXPLODE");

                    Location location = new Location(player.getWorld(), player.getLocation().getX(), player.getLocation().getY() + 2.0D, player.getLocation().getZ());
                    Sheep sheep = player.getWorld().spawn(location, Sheep.class);

                    sheep.setColor(DyeColor.values()[ThreadLocalRandom.current().nextInt(15)]);

                    sheep.setVelocity(speed());
                    sheep.setBaby();
                    sheep.setAgeLock(true);
                    sheep.setNoDamageTicks(120);

                    Bukkit.getScheduler().runTaskLater(plugin, sheep::remove, 110L);
                }, i * 5L);
            }
        }
    }

    private Vector speed() {
        return new Vector(
                ThreadLocalRandom.current().nextDouble() - 0.5D,
                ThreadLocalRandom.current().nextDouble() / 2.0D,
                ThreadLocalRandom.current().nextDouble() - 0.5D
        )
                .multiply(2)
                .add(new Vector(0.0D, 0.8D, 0.0D));
    }
}
