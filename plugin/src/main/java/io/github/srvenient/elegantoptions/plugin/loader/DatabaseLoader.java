package io.github.srvenient.elegantoptions.plugin.loader;

import dev.srvenient.freya.abstraction.loader.Loader;
import io.github.srvenient.elegantoptions.plugin.database.Database;

import javax.inject.Inject;

public class DatabaseLoader implements Loader {

    @Inject private Database database;

    @Override
    public void load() {
        database.init();
    }
}
