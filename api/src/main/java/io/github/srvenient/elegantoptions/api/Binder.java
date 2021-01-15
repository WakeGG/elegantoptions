package io.github.srvenient.elegantoptions.api;

import dev.srvenient.freya.abstraction.configuration.Configuration;

import me.yushust.inject.Module;

import java.util.HashMap;
import java.util.Map;

public class Binder {

    private final Map<String, Configuration> files = new HashMap<>();

    public Binder bind(String name, Configuration configuration) {
        files.put(name, configuration);

        return this;
    }

    public Module build() {
        return binder -> files.forEach((name, file) -> binder.bind(Configuration.class).named(name).toInstance(file));
    }
}
