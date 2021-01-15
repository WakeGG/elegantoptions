package io.github.srvenient.elegantoptions.plugin;

import io.github.srvenient.elegantoptions.plugin.module.MainModule;
import io.github.srvenient.elegantoptions.plugin.service.MainService;

import me.yushust.inject.Injector;

import org.bukkit.plugin.java.JavaPlugin;

import javax.inject.Inject;

public class ElegantOptions extends JavaPlugin {

    @Inject private MainService mainService;

    @Override
    public void onEnable() {
        Injector injector = Injector.create(new MainModule(this));
        injector.injectMembers(this);

        mainService.start();
    }

    @Override
    public void onDisable() {
        mainService.stop();
    }
}
