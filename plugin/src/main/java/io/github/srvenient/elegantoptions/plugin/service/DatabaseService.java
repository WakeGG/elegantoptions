package io.github.srvenient.elegantoptions.plugin.service;

import dev.srvenient.freya.abstraction.configuration.Configuration;
import dev.srvenient.freya.abstraction.database.Database;
import dev.srvenient.freya.abstraction.service.Service;

import dev.srvenient.freya.api.FreyaAPI;
import dev.srvenient.freya.api.database.MySQLDatabase;
import dev.srvenient.freya.api.database.SQLiteDatabase;

import io.github.srvenient.elegantoptions.api.Enums;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import javax.inject.Inject;
import javax.inject.Named;

import java.util.logging.Level;

public class DatabaseService implements Service {

    @Inject private Plugin plugin;

    @Inject @Named("config") private Configuration config;

    @Override
    public void start() {
        Database database;

        if (config.getString("data.type").equals(Enums.TypeDatabase.MySQL.name())) {
            database = new MySQLDatabase();

            database.init(plugin, config);
            database.create("CREATE TABLE IF NOT EXISTS `DemeterOption` (`id` VARCHAR(36), `dropped` VARCHAR(36), `visibility` VARCHAR(16), `chat` VARCHAR(16))");

            FreyaAPI.setMySQLdb(database);
        } else if (config.getString("data.type").equals(Enums.TypeDatabase.SQLite.name())) {
            database = new SQLiteDatabase();

            database.init(plugin, config);
            database.create("CREATE TABLE IF NOT EXISTS `DemeterOption` (`id` VARCHAR(36), `dropped` VARCHAR(36), `visibility` VARCHAR(16), `chat` VARCHAR(16))");

            FreyaAPI.setSqLitedb(database);
        } else {
            Bukkit.getPluginManager().disablePlugin(plugin);

            Bukkit.getLogger().log(Level.SEVERE, "An error occurred connecting to the database.");
        }
    }

    @Override
    public void stop() {
        if (config.getString("data.type").equals(Enums.TypeDatabase.MySQL.name())) {
            FreyaAPI.getMySQLdb().close();
        } else if (config.getString("data.type").equals(Enums.TypeDatabase.SQLite.name())) {
            FreyaAPI.getSQLitedb().close();
        }
    }
}
