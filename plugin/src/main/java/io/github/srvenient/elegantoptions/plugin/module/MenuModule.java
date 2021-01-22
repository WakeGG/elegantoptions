package io.github.srvenient.elegantoptions.plugin.module;

import io.github.srvenient.elegantoptions.api.menu.MenuCreator;

import io.github.srvenient.elegantoptions.plugin.menu.option.EffectMenu;
import io.github.srvenient.elegantoptions.plugin.menu.option.MainMenu;

import me.yushust.inject.AbstractModule;

public class MenuModule extends AbstractModule {

    @Override
    protected void configure() {
        this.bind(MenuCreator.class).named("main-menu").to(MainMenu.class).singleton();
        this.bind(MenuCreator.class).named("effect-menu").to(EffectMenu.class).singleton();
    }
}
