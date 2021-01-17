package io.github.srvenient.elegantoptions.plugin.module;

import dev.srvenient.freya.abstraction.configuration.Configuration;

import io.github.srvenient.elegantoptions.plugin.ElegantOptions;
import io.github.srvenient.elegantoptions.api.Binder;

import me.yushust.inject.AbstractModule;

import org.bukkit.plugin.Plugin;

public class MainModule extends AbstractModule {

    private final ElegantOptions plugin;

    public MainModule(ElegantOptions plugin) {
        this.plugin = plugin;
    }

    @Override
    protected void configure() {
        this.install(new StorageModule());

        this.bind(ElegantOptions.class).toInstance(plugin);
        this.bind(Plugin.class).to(ElegantOptions.class);

        Binder binder = new Binder()
                .bind("config", new Configuration(plugin, "config"))
                .bind("language", new Configuration(plugin, "language"))
                .bind("menus", new Configuration(plugin, "menus"));

        this.install(binder.build());

        this.install(new ManagerModule());
    }
}
