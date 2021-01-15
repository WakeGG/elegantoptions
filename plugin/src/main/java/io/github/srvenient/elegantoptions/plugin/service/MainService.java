package io.github.srvenient.elegantoptions.plugin.service;

import dev.srvenient.freya.abstraction.service.Service;

import io.github.srvenient.elegantoptions.plugin.translation.placeholder.PlaceholderWrapper;
import io.github.srvenient.elegantoptions.plugin.loader.CommandLoader;
import io.github.srvenient.elegantoptions.plugin.loader.EventLoader;

import javax.inject.Inject;

public class MainService implements Service {

    @Inject private DatabaseService databaseService;

    @Inject private EventLoader eventLoader;
    @Inject private CommandLoader commandLoader;

    @Inject private PlaceholderWrapper placeholderWrapper;

    @Override
    public void start() {
        databaseService.start();

        eventLoader.load();
        commandLoader.load();

        placeholderWrapper.register();
    }

    @Override
    public void stop() {
        databaseService.stop();
    }
}
