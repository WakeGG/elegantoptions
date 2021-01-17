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

import java.util.Objects;
import java.util.logging.Level;

public class DatabaseService implements Service {

    @Inject private Plugin plugin;

    @Inject @Named("config") private Configuration config;

    @Override
    public void start() {
        Database database;

        if (Objects.equals(config.getString("data.type"), "MySQL")) {
            database = new MySQLDatabase();
            FreyaAPI.setMySQLdb(database);
        } else {
            database = new SQLiteDatabase();
            FreyaAPI.setSqLitedb(database);
        }

        database.init(plugin, config);
        database.create("CREATE TABLE IF NOT EXISTS `ElegantOptions` (`id` VARCHAR(36), `dropped` BOOLEAN, `visibility` VARCHAR(16), `chat` VARCHAR(16), `doubleJump` VARCHAR(16), `mount` VARCHAR(16), `fly` VARCHAR(16), `messageJoin` VARCHAR(16), `effectJoin` VARCHAR(16))");
    }

    @Override
    public void stop() {
        if (Objects.equals(config.getString("data.type"), "MySQL")) {
            FreyaAPI.getMySQLdb().close();
        } else {
            FreyaAPI.getSQLitedb().close();
        }
    }
}
