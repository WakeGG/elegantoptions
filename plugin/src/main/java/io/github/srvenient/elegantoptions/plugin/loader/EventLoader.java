package io.github.srvenient.elegantoptions.plugin.loader;

import dev.srvenient.freya.abstraction.loader.Loader;

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
    @Inject private PlayerToggleFlightListener playerToggleFlightListener;
    @Inject private PlayerInteractAtEntityListener playerInteractAtEntityListener;
    @Inject private AsyncPlayerChatListener asyncPlayerChatListener;

    @Override
    public void load() {
        register(
                playerJoinListener,
                playerMoveListener,
                playerToggleFlightListener,
                playerInteractAtEntityListener,
                asyncPlayerChatListener,
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
