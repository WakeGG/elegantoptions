package io.github.srvenient.elegantoptions.plugin.user;

import io.github.srvenient.elegantoptions.api.user.User;
import io.github.srvenient.elegantoptions.api.user.UserMatcher;

import dev.srvenient.freya.abstraction.storage.ObjectCache;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import javax.inject.Inject;

import java.util.Optional;
import java.util.UUID;

public class SimpleUserMatcher implements UserMatcher {

    @Inject private ObjectCache<User> userCache;

    @Override
    public User getUserId(UUID uuid) {
        Player player = Bukkit.getPlayer(uuid);

        if (player == null) {
            return null;
        } else {
            if (player.isOnline()) {
                Optional<User> userOptional = userCache.getIfPresent(uuid.toString());

                return userOptional.orElse(null);
            } else {
                return null;
            }
        }
    }
}
