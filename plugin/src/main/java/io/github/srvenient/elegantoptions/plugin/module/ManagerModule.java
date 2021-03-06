package io.github.srvenient.elegantoptions.plugin.module;

import io.github.srvenient.elegantoptions.plugin.user.SimpleUserMatcher;

import io.github.srvenient.elegantoptions.api.user.UserMatcher;

import me.yushust.inject.AbstractModule;

public class ManagerModule extends AbstractModule {

    @Override
    protected void configure() {
        this.bind(UserMatcher.class).to(SimpleUserMatcher.class).singleton();
    }
}
