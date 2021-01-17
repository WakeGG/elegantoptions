package io.github.srvenient.elegantoptions.plugin.loader;

import dev.srvenient.freya.abstraction.loader.Loader;

import io.github.srvenient.elegantoptions.plugin.listener.PlayerEffectJoinListener;
import io.github.srvenient.elegantoptions.plugin.listener.PlayerFlyJoinListener;
import io.github.srvenient.elegantoptions.plugin.listener.PlayerMessageJoinListener;
import io.github.srvenient.elegantoptions.plugin.listener.PlayerVisibilityJoinListener;
import io.github.srvenient.elegantoptions.plugin.listener.vanilla.*;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import team.unnamed.gui.core.GUIListeners;

import javax.inject.Inject;

public class EventLoader implements Loader {

    @Inject private Plugin plugin;

    @Inject private PlayerJoinListener playerJoinListener;
    @Inject private PlayerMoveListener playerMoveListener;
    @Inject private AsyncPlayerChatListener asyncPlayerChatListener;
    @Inject private PlayerToggleFlightListener playerToggleFlightListener;
    @Inject private PlayerChangedWorldListener playerChangedWorldListener;
    @Inject private PlayerInteractAtEntityListener playerInteractAtEntityListener;

    @Inject private PlayerVisibilityJoinListener playerVisibilityJoinListener;
    @Inject private PlayerFlyJoinListener playerFlyJoinListener;
    @Inject private PlayerMessageJoinListener playerMessageJoinListener;
    @Inject private PlayerEffectJoinListener playerEffectJoinListener;

    @Override
    public void load() {
        register(
                playerJoinListener,
                playerMoveListener,
                asyncPlayerChatListener,
                playerToggleFlightListener,
                playerChangedWorldListener,
                playerInteractAtEntityListener,
                playerVisibilityJoinListener,
                playerFlyJoinListener,
                playerMessageJoinListener,
                playerEffectJoinListener,
                new GUIListeners()
        );
    }

    private void register(Listener... listeners) {
        PluginManager pluginManager = Bukkit.getPluginManager();

        for (Listener listener : listeners) {
            pluginManager.registerEvents(listener, plugin);
        }
    }
}
