package io.github.srvenient.elegantoptions.plugin.module;

import dev.srvenient.freya.abstraction.storage.ObjectCache;
import dev.srvenient.freya.api.storage.cache.LocalObjectCache;

import io.github.srvenient.elegantoptions.api.user.User;

import me.yushust.inject.AbstractModule;
import me.yushust.inject.key.TypeReference;

public class StorageModule extends AbstractModule {

    @Override
    protected void configure() {
        this.bind(new TypeReference<ObjectCache<User>>() {}).toInstance(new LocalObjectCache<>());
    }
}
