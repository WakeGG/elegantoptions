package io.github.srvenient.elegantoptions.plugin.service;

import dev.srvenient.freya.abstraction.service.Service;

import io.github.srvenient.elegantoptions.plugin.database.Database;
import io.github.srvenient.elegantoptions.plugin.loader.DatabaseLoader;
import io.github.srvenient.elegantoptions.plugin.translation.placeholder.PlaceholderWrapper;
import io.github.srvenient.elegantoptions.plugin.loader.CommandLoader;
import io.github.srvenient.elegantoptions.plugin.loader.EventLoader;
import io.github.srvenient.elegantoptions.plugin.utils.UpdateChecker;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import javax.inject.Inject;

public class MainService implements Service {

    @Inject private Plugin plugin;

    @Inject private CommandLoader commandLoader;
    @Inject private EventLoader eventLoader;
    @Inject private DatabaseLoader databaseLoader;

    @Inject private PlaceholderWrapper placeholderWrapper;

    @Override
    public void start() {
        new UpdateChecker(plugin, 76472)
                .getVersion(version -> {
                    if (plugin.getDescription().getVersion().equalsIgnoreCase(version)) {
                        Bukkit.getLogger().info("[ElegantOptions] There is not a new update available.");
                    } else {
                        Bukkit.getLogger().info("[ElegantOptions] There is a new update available.");
                    }
                });

        commandLoader.load();
        eventLoader.load();
        databaseLoader.load();

        placeholderWrapper.register();
    }

    @Override
    public void stop() {}
}
