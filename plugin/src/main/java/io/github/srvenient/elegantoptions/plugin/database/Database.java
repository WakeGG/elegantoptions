package io.github.srvenient.elegantoptions.plugin.database;

import dev.srvenient.freya.abstraction.configuration.Configuration;

import dev.srvenient.freya.api.BukkitEventMessenger;
import io.github.srvenient.elegantoptions.api.Enums;
import io.github.srvenient.elegantoptions.api.event.PlayerActionEvent;
import io.github.srvenient.elegantoptions.api.user.User;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import java.io.File;
import java.io.IOException;

import java.sql.*;

import java.util.TimeZone;

@Singleton
public class Database {

    @Inject private Plugin plugin;

    @Inject @Named("config") private Configuration config;
    @Inject @Named("language") private Configuration language;

    private Connection connection;

    public void init() {
        if (config.getString("data.type").equals("MySQL")) {
            try {
                Class.forName("com.mysql.jdbc.Driver");

                this.connection = DriverManager.getConnection("jdbc:mysql://" + this.plugin.getConfig().getString("data.mysql.address") + ":" + this.plugin.getConfig().getString("data.mysql.port") + "/" + this.plugin.getConfig().getString("data.mysql.database") + "?serverTimezone=" + TimeZone.getDefault().getID() + "&autoReconnect=true&wait_timeout=31536000&interactive_timeout=31536000", this.plugin.getConfig().getString("data.mysql.username"), this.plugin.getConfig().getString("data.mysql.password"));
                newTables();

                Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[ElegantOptions] §aMySQL connected.");
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        } else if (config.getString("data.type").equals("SQLite")) {
            File file = new File(this.plugin.getDataFolder(), "/database.db");

            if (!file.exists())
                try {
                    file.createNewFile();
                } catch (IOException iOException) {
                    iOException.printStackTrace();
                    Bukkit.getPluginManager().disablePlugin(plugin);
                }

            try {
                Class.forName("org.sqlite.JDBC");

                try {
                    this.connection = DriverManager.getConnection("jdbc:sqlite:" + file);
                    newTables();

                    Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[ElegantOptions] §aSQLite connected.");
                } catch (SQLException sQLException) {
                    sQLException.printStackTrace();
                    Bukkit.getPluginManager().disablePlugin(plugin);
                }

            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
                Bukkit.getPluginManager().disablePlugin(plugin);
            }
        }
    }

    public void checkConnection() {
        try {
            if (this.connection.isClosed() || this.connection == null)
                init();
        } catch (SQLException sQLException) {
            sQLException.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void newTables() {
        Connection connection = getConnection();

        try {
            Statement statement = connection.createStatement();

            try {
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS ElegantOptions (id VARCHAR(60) primary key, name VARCHAR(60), dropped INT, visibility TEXT, chat TEXT, doubleJump TEXT, mount TEXT, fly TEXT, messageJoin TEXT, effectJoin TEXT);");
            } catch (SQLException sQLException) {
                try {
                    statement.close();
                } catch (SQLException ignored) {}
                return;
            } finally {
                try {
                    if (statement != null)
                        statement.close();
                } catch (SQLException ignored) {}
            }
            try {
                statement.close();
            } catch (SQLException ignored) {}
        } catch (SQLException ignored) {}
    }

    public boolean existPlayer(Player player) {
        PreparedStatement preparedStatement = null;
        String id = player.getUniqueId().toString();

        try {
            preparedStatement = this.connection.prepareStatement("SELECT id FROM ElegantOptions WHERE id ='" + id + "'");
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) return true;
        } catch (SQLException exception) {
            exception.printStackTrace();

            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException sQLException1) {
                    sQLException1.printStackTrace();
                }

                return false;
            }

            return false;
        } finally {
            if (preparedStatement != null)
                try {
                    preparedStatement.close();
                } catch (SQLException sQLException) {
                    sQLException.printStackTrace();
                }
        }

        try {
            preparedStatement.close();
        } catch (SQLException sQLException) {
            sQLException.printStackTrace();
        }

        return false;
    }

    public void createUser(User user) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = this.connection.prepareStatement("INSERT INTO `ElegantOptions` (`id`, `name`, `dropped`, `visibility`, `chat`, `doubleJump`, `mount`, `fly`, `messageJoin`, `effectJoin`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");

            preparedStatement.setString(1, user.getId());
            preparedStatement.setString(2, user.getPlayerName());
            preparedStatement.setBoolean(3, user.isDropped());
            preparedStatement.setString(4, user.getVisibility().name());
            preparedStatement.setString(5, user.getChat().name());
            preparedStatement.setString(6, user.getDoubleJump().name());
            preparedStatement.setString(7, user.getMount().name());
            preparedStatement.setString(8, user.getFly().name());
            preparedStatement.setString(9, user.getMessageJoin().name());
            preparedStatement.setString(10, user.getEffectJoin().name());

            preparedStatement.executeUpdate();
        } catch (SQLException sQLException) {
            sQLException.printStackTrace();
        } finally {
            if (preparedStatement != null)
                try {
                    preparedStatement.close();
                } catch (SQLException ignored) {}
        }
    }

    public void loadUser(User user) {
                Connection connection = Database.this.getConnection();

                if (!existPlayer(user.getRawPlayer())) {
                    createUser(user);
                    loadUser(user);

                    return;
                }

                PreparedStatement preparedStatement = null;
                ResultSet resultSet = null;

                try {
                    preparedStatement = connection.prepareStatement("SELECT `dropped`, `visibility`, `chat`, `doubleJump`, `mount`, `fly`, `messageJoin`, `effectJoin` FROM `ElegantOptions` WHERE `id` = ? LIMIT 1;");
                    preparedStatement.setString(1, user.getId());

                    resultSet = preparedStatement.executeQuery();

                    Player player = user.getRawPlayer();

                    if (resultSet != null && resultSet.next()) {
                        user.setDropped(true);

                        if (player.hasPermission("elegantoptions.effect.visibility")) {
                            if (resultSet.getString("visibility").equals("NO_PERMISSION")) {
                                user.setVisibility(Enums.TypeStatus.ON);
                            } else {
                                user.setVisibility(Enums.TypeStatus.valueOf(resultSet.getString("visibility")));
                            }
                        } else {
                            user.setVisibility(Enums.TypeStatus.NO_PERMISSION);
                        }

                        if (player.hasPermission("elegantoptions.effect.chat")) {
                            if (resultSet.getString("chat").equals("NO_PERMISSION")) {
                                user.setChat(Enums.TypeStatus.ON);
                            } else {
                                user.setChat(Enums.TypeStatus.valueOf(resultSet.getString("chat")));
                            }
                        } else {
                            user.setChat(Enums.TypeStatus.NO_PERMISSION);
                        }

                        if (player.hasPermission("elegantoptions.effect.doubleJump")) {
                            if (resultSet.getString("doubleJump").equals("NO_PERMISSION")) {
                                user.setDoubleJump(Enums.TypeStatus.OFF);
                            } else {
                                user.setDoubleJump(Enums.TypeStatus.valueOf(resultSet.getString("doubleJump")));
                            }
                        } else {
                            user.setDoubleJump(Enums.TypeStatus.NO_PERMISSION);
                        }

                        if (player.hasPermission("elegantoptions.effect.mount")) {
                            if (resultSet.getString("mount").equals("NO_PERMISSION")) {
                                user.setMount(Enums.TypeStatus.ON);
                            } else {
                                user.setMount(Enums.TypeStatus.valueOf(resultSet.getString("mount")));
                            }
                        } else {
                            user.setMount(Enums.TypeStatus.NO_PERMISSION);
                        }

                        if (player.hasPermission("elegantoptions.effect.fly")) {
                            if (resultSet.getString("fly").equals("NO_PERMISSION")) {
                                user.setFly(Enums.TypeStatus.OFF);
                            } else {
                                user.setFly(Enums.TypeStatus.valueOf(resultSet.getString("fly")));
                            }
                        } else {
                            user.setFly(Enums.TypeStatus.NO_PERMISSION);
                        }

                        if (player.hasPermission("elegantoptions.effect.messageJoin")) {
                            if (resultSet.getString("messageJoin").equals("NO_PERMISSION")) {
                                user.setMessageJoin(Enums.TypeStatus.OFF);
                            } else {
                                user.setMessageJoin(Enums.TypeStatus.valueOf(resultSet.getString("messageJoin")));
                            }
                        } else {
                            user.setMessageJoin(Enums.TypeStatus.NO_PERMISSION);
                        }

                        user.setEffectJoin(Enums.Effects.valueOf(resultSet.getString("effectJoin")));
                    } else {
                        if (!player.hasPermission("elegantoptions.effect.visibility")) {
                            user.setVisibility(Enums.TypeStatus.NO_PERMISSION);
                        } else {
                            user.setVisibility(Enums.TypeStatus.ON);
                        }

                        if (!player.hasPermission("elegantoptions.effect.chat")) {
                            user.setChat(Enums.TypeStatus.NO_PERMISSION);
                        } else {
                            user.setChat(Enums.TypeStatus.ON);
                        }

                        if (!player.hasPermission("elegantoptions.effect.doubleJump")) {
                            user.setDoubleJump(Enums.TypeStatus.NO_PERMISSION);
                        } else {
                            user.setDoubleJump(Enums.TypeStatus.OFF);
                        }

                        if (!player.hasPermission("elegantoptions.effect.mount")) {
                            user.setMount(Enums.TypeStatus.NO_PERMISSION);
                        } else {
                            user.setMount(Enums.TypeStatus.ON);
                        }

                        user.setFly(Enums.TypeStatus.NO_PERMISSION);
                        user.setMessageJoin(Enums.TypeStatus.NO_PERMISSION);
                    }

                    BukkitEventMessenger.callEvent(new PlayerActionEvent(player, user));
                } catch (SQLException sQLException) {
                    sQLException.printStackTrace();
                } finally {
                    if (resultSet != null)
                        try {
                            resultSet.close();
                        } catch (SQLException ignored) {}
                    if (preparedStatement != null)
                        try {
                            preparedStatement.close();
                        } catch (SQLException ignored) {}
                }
            }



    public void saveData(User user) {
        checkConnection();

        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("UPDATE `ElegantOptions` SET `dropped` = ?, `visibility` = ?, `chat` = ?, `doubleJump` = ?, `mount` = ?, `fly` = ?, `messageJoin` = ?, `effectJoin` = ? WHERE `id` = ?;");

            preparedStatement.setBoolean(1, user.isDropped());
            preparedStatement.setString(2, user.getVisibility().name());
            preparedStatement.setString(3, user.getChat().name());
            preparedStatement.setString(4, user.getDoubleJump().name());
            preparedStatement.setString(5, user.getMount().name());
            preparedStatement.setString(6, user.getFly().name());
            preparedStatement.setString(7, user.getMessageJoin().name());
            preparedStatement.setString(8, user.getEffectJoin().name());
            preparedStatement.setString(9, user.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException sQLException) {
            sQLException.printStackTrace();
        } finally {
            if (preparedStatement != null)
                try {
                    preparedStatement.close();
                } catch (SQLException ignored) {}
        }
    }
}
