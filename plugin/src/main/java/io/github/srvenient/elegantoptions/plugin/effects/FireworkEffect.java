package io.github.srvenient.elegantoptions.plugin.effects;

import io.github.srvenient.elegantoptions.api.effects.EffectEntering;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class FireworkEffect extends EffectEntering {

    private static final List<Color> colors = Arrays.asList(Color.WHITE, Color.PURPLE, Color.RED, Color.GREEN, Color.AQUA, Color.BLUE, Color.FUCHSIA, Color.GRAY, Color.LIME, Color.MAROON, Color.YELLOW, Color.SILVER, Color.TEAL, Color.ORANGE, Color.OLIVE, Color.NAVY, Color.BLACK);
    private static final List<org.bukkit.FireworkEffect.Type> types = Arrays.asList(org.bukkit.FireworkEffect.Type.BURST, org.bukkit.FireworkEffect.Type.BALL, org.bukkit.FireworkEffect.Type.BALL_LARGE, org.bukkit.FireworkEffect.Type.CREEPER, org.bukkit.FireworkEffect.Type.STAR);

    private final Random random = new Random();

    @Override
    public void init(Player player) {
        this.launchRandomFirework(player);
    }

    private org.bukkit.FireworkEffect.Type getRandomType() {
        int i = types.size();

        return types.get(random.nextInt(i));
    }

    private Color getRandomColor() {
        int i = colors.size();

        return colors.get(random.nextInt(i));
    }

    private void launchRandomFirework(Player player) {
        Location location = player.getLocation();

        Firework firework = location.getWorld().spawn(location, Firework.class);
        FireworkMeta fireworkMeta = firework.getFireworkMeta();

        fireworkMeta.setPower(1);
        fireworkMeta.addEffects(
                org.bukkit.FireworkEffect
                        .builder()
                        .flicker(true)
                        .with(getRandomType())
                        .withColor(getRandomColor(), getRandomColor())
                        .withFade(getRandomColor())
                        .build()
        );

        firework.setFireworkMeta(fireworkMeta);
    }
}
