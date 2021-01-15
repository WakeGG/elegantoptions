package io.github.srvenient.elegantoptions.plugin.loader;

import dev.srvenient.freya.abstraction.loader.Loader;

import io.github.srvenient.elegantoptions.plugin.command.MainCommand;

import me.fixeddev.commandflow.SimpleCommandManager;
import me.fixeddev.commandflow.annotated.AnnotatedCommandTreeBuilder;
import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.part.PartInjector;
import me.fixeddev.commandflow.annotated.part.defaults.DefaultsModule;
import me.fixeddev.commandflow.bukkit.BukkitAuthorizer;
import me.fixeddev.commandflow.bukkit.BukkitCommandManager;
import me.fixeddev.commandflow.bukkit.factory.BukkitModule;

import javax.inject.Inject;

public class CommandLoader implements Loader {

    @Inject private MainCommand mainCommand;

    @Override
    public void load() {
        register(
                mainCommand
        );
    }

    private void register(CommandClass... commandClasses) {
        PartInjector injector = PartInjector.create();

        injector.install(new DefaultsModule());
        injector.install(new BukkitModule());

        AnnotatedCommandTreeBuilder builder = AnnotatedCommandTreeBuilder.create(injector);
        BukkitCommandManager bukkitCommandManager = new BukkitCommandManager(new SimpleCommandManager(new BukkitAuthorizer()), "DemeterOptions");

        for (CommandClass commandClass : commandClasses)
            bukkitCommandManager.registerCommands(builder.fromClass(commandClass));
    }
}
