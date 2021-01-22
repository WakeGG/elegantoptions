package io.github.srvenient.elegantoptions.plugin.loader;

import dev.srvenient.freya.abstraction.loader.Loader;

import io.github.srvenient.elegantoptions.plugin.command.MainCommand;

import me.fixeddev.commandflow.CommandManager;
import me.fixeddev.commandflow.annotated.AnnotatedCommandTreeBuilder;
import me.fixeddev.commandflow.annotated.AnnotatedCommandTreeBuilderImpl;
import me.fixeddev.commandflow.annotated.part.PartInjector;
import me.fixeddev.commandflow.annotated.part.SimplePartInjector;
import me.fixeddev.commandflow.annotated.part.defaults.DefaultsModule;
import me.fixeddev.commandflow.brigadier.BrigadierCommandManager;
import me.fixeddev.commandflow.bukkit.factory.BukkitModule;

import org.bukkit.plugin.Plugin;

import javax.inject.Inject;

public class CommandLoader implements Loader {

    @Inject private Plugin plugin;

    @Inject private MainCommand mainCommand;

    @Override
    public void load() {
        CommandManager commandManager = new BrigadierCommandManager(plugin);

        PartInjector partInjector = new SimplePartInjector();

        partInjector.install(new DefaultsModule());
        partInjector.install(new BukkitModule());

        AnnotatedCommandTreeBuilder builder = new AnnotatedCommandTreeBuilderImpl(partInjector);

        commandManager.registerCommands(builder.fromClass(mainCommand));
    }
}
