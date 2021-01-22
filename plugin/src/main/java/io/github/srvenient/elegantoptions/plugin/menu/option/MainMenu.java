package io.github.srvenient.elegantoptions.plugin.menu.option;

import dev.srvenient.freya.abstraction.configuration.Configuration;
import dev.srvenient.freya.api.xseries.XSound;

import io.github.srvenient.elegantoptions.api.Enums;
import io.github.srvenient.elegantoptions.api.menu.MenuCreator;
import io.github.srvenient.elegantoptions.api.user.User;
import io.github.srvenient.elegantoptions.api.user.UserMatcher;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import team.unnamed.gui.abstraction.item.ItemClickable;
import team.unnamed.gui.core.gui.GUIBuilder;
import team.unnamed.gui.core.item.type.ItemBuilder;

import javax.inject.Inject;
import javax.inject.Named;

import static io.github.srvenient.elegantoptions.plugin.utils.InventoryUtils.*;
import static io.github.srvenient.elegantoptions.plugin.translation.Translation.colorize;

public class MainMenu implements MenuCreator {

    @Inject private UserMatcher userMatcher;

    @Inject @Named("effect-menu") private MenuCreator effectMenu;

    @Inject @Named("menus") private Configuration menus;
    @Inject @Named("language") private Configuration language;

    @Override
    public Inventory create(Player player) {
        User user = userMatcher.getUserId(player.getUniqueId());

        GUIBuilder guiBuilder = GUIBuilder.builder(colorize(player, menus.getString("main-menu.title")), menus.getInt("main-menu.rows"))
                .addItem(
                        ItemClickable
                                .builder(getItemSlot(menus, "main-menu", "visibility"))
                                .setItemStack(
                                        ItemBuilder
                                                .newBuilder(
                                                        getItemMaterial(menus, "main-menu", "visibility"),
                                                        getItemAmount(menus, "main-menu", "visibility"),
                                                        getItemData(menus, "main-menu", "visibility")
                                                )
                                                .setName(getItemName(menus, player, "main-menu", "visibility"))
                                                .setLore(getItemLore(menus, player, "main-menu", "visibility"))
                                                .build()
                                )
                                .setAction(event -> {
                                    switch (user.getVisibility()) {
                                        case ON:
                                            user.setVisibility(Enums.TypeStatus.ONLY_RANK);

                                            event.getClickedInventory().setItem(
                                                    getItemSlot(menus, "main-menu", "visibility"),
                                                    ItemBuilder
                                                            .newBuilder(
                                                                    getItemMaterial(menus, "main-menu", "visibility"),
                                                                    getItemAmount(menus, "main-menu", "visibility"),
                                                                    getItemData(menus, "main-menu", "visibility")
                                                            )
                                                            .setName(getItemName(menus, player, "main-menu", "visibility"))
                                                            .setLore(getItemLore(menus, player, "main-menu", "visibility"))
                                                            .build()
                                            );

                                            event.getClickedInventory().setItem(
                                                    getItemSlot(menus, "main-menu", "visibility") + 9,
                                                    ItemBuilder
                                                            .newBuilder(
                                                                    getItemMaterial(menus, "main-menu", "visibility.toggle.state-only-rank"),
                                                                    getItemAmount(menus, "main-menu", "visibility.toggle.state-only-rank"),
                                                                    getItemData(menus, "main-menu", "visibility.toggle.state-only-rank")
                                                            )
                                                            .setName(getItemName(menus, player, "main-menu", "visibility.toggle.state-only-rank"))
                                                            .setLore(getItemLore(menus, player, "main-menu", "visibility.toggle.state-only-rank"))
                                                            .build()
                                            );

                                            XSound.play(player, "UI_BUTTON_CLICK");

                                            Bukkit.getOnlinePlayers().forEach(players -> {
                                                if (players.hasPermission("elegantoptions.options.visibility.rank")) {
                                                    player.showPlayer(players);
                                                } else {
                                                    player.hidePlayer(players);
                                                }
                                            });

                                            break;
                                        case ONLY_RANK:
                                            user.setVisibility(Enums.TypeStatus.OFF);

                                            event.getClickedInventory().setItem(
                                                    getItemSlot(menus, "main-menu", "visibility"),
                                                    ItemBuilder
                                                            .newBuilder(
                                                                    getItemMaterial(menus, "main-menu", "visibility"),
                                                                    getItemAmount(menus, "main-menu", "visibility"),
                                                                    getItemData(menus, "main-menu", "visibility")
                                                            )
                                                            .setName(getItemName(menus, player, "main-menu", "visibility"))
                                                            .setLore(getItemLore(menus, player, "main-menu", "visibility"))
                                                            .build()
                                            );

                                            event.getClickedInventory().setItem(
                                                    getItemSlot(menus, "main-menu", "visibility") + 9,
                                                    ItemBuilder
                                                            .newBuilder(
                                                                    getItemMaterial(menus, "main-menu", "visibility.toggle.state-off"),
                                                                    getItemAmount(menus, "main-menu", "visibility.toggle.state-off"),
                                                                    getItemData(menus, "main-menu", "visibility.toggle.state-off")
                                                            )
                                                            .setName(getItemName(menus, player, "main-menu", "visibility.toggle.state-off"))
                                                            .setLore(getItemLore(menus, player, "main-menu", "visibility.toggle.state-off"))
                                                            .build()
                                            );

                                            XSound.play(player, "UI_BUTTON_CLICK");

                                            Bukkit.getOnlinePlayers().forEach(player::hidePlayer);

                                            break;
                                        case OFF:
                                            user.setVisibility(Enums.TypeStatus.ON);

                                            event.getClickedInventory().setItem(
                                                    getItemSlot(menus, "main-menu", "visibility"),
                                                    ItemBuilder
                                                            .newBuilder(
                                                                    getItemMaterial(menus, "main-menu", "visibility"),
                                                                    getItemAmount(menus, "main-menu", "visibility"),
                                                                    getItemData(menus, "main-menu", "visibility")
                                                            )
                                                            .setName(getItemName(menus, player, "main-menu", "visibility"))
                                                            .setLore(getItemLore(menus, player, "main-menu", "visibility"))
                                                            .build()
                                            );

                                            event.getClickedInventory().setItem(
                                                    getItemSlot(menus, "main-menu", "visibility") + 9,
                                                    ItemBuilder
                                                            .newBuilder(
                                                                    getItemMaterial(menus, "main-menu", "visibility.toggle.state-on"),
                                                                    getItemAmount(menus, "main-menu", "visibility.toggle.state-on"),
                                                                    getItemData(menus, "main-menu", "visibility.toggle.state-on")
                                                            )
                                                            .setName(getItemName(menus, player, "main-menu", "visibility.toggle.state-on"))
                                                            .setLore(getItemLore(menus, player, "main-menu", "visibility.toggle.state-on"))
                                                            .build()
                                            );

                                            XSound.play(player, "UI_BUTTON_CLICK");

                                            Bukkit.getOnlinePlayers().forEach(player::showPlayer);

                                            break;
                                        case NO_PERMISSION:
                                            player.closeInventory();
                                            player.sendMessage(colorize(player, language.getString("visibility.no-permission")));

                                            XSound.play(player, "ENTITY_VILLAGER_NO");

                                            break;
                                    }

                                    return true;
                                })
                                .build()
                ).addItem(
                        ItemClickable
                                .builder(getItemSlot(menus, "main-menu", "chat"))
                                .setItemStack(
                                        ItemBuilder
                                                .newBuilder(
                                                        getItemMaterial(menus, "main-menu", "chat"),
                                                        getItemAmount(menus, "main-menu", "chat"),
                                                        getItemData(menus, "main-menu", "chat")
                                                )
                                                .setName(getItemName(menus, player, "main-menu", "chat"))
                                                .setLore(getItemLore(menus, player, "main-menu", "chat"))
                                                .build()
                                )
                                .setAction(event -> {
                                    switch (user.getChat()) {
                                        case ON:
                                            user.setChat(Enums.TypeStatus.OFF);

                                            event.getClickedInventory().setItem(
                                                    getItemSlot(menus, "main-menu", "chat"),
                                                    ItemBuilder
                                                            .newBuilder(
                                                                    getItemMaterial(menus, "main-menu", "chat"),
                                                                    getItemAmount(menus, "main-menu", "chat"),
                                                                    getItemData(menus, "main-menu", "chat")
                                                            )
                                                            .setName(getItemName(menus, player, "main-menu", "chat"))
                                                            .setLore(getItemLore(menus, player, "main-menu", "chat"))
                                                            .build()
                                            );

                                            event.getClickedInventory().setItem(
                                                    getItemSlot(menus, "main-menu", "chat") + 9,
                                                    ItemBuilder
                                                            .newBuilder(
                                                                    getItemMaterial(menus, "main-menu", "chat.toggle.state-off"),
                                                                    getItemAmount(menus, "main-menu", "chat.toggle.state-off"),
                                                                    getItemData(menus, "main-menu", "chat.toggle.state-off")
                                                            )
                                                            .setName(getItemName(menus, player, "main-menu", "chat.toggle.state-off"))
                                                            .setLore(getItemLore(menus, player, "main-menu", "chat.toggle.state-off"))
                                                            .build()
                                            );

                                            XSound.play(player, "UI_BUTTON_CLICK");

                                            break;
                                        case OFF:
                                            user.setChat(Enums.TypeStatus.ON);

                                            event.getClickedInventory().setItem(
                                                    getItemSlot(menus, "main-menu", "chat"),
                                                    ItemBuilder
                                                            .newBuilder(
                                                                    getItemMaterial(menus, "main-menu", "chat"),
                                                                    getItemAmount(menus, "main-menu", "chat"),
                                                                    getItemData(menus, "main-menu", "chat")
                                                            )
                                                            .setName(getItemName(menus, player, "main-menu", "chat"))
                                                            .setLore(getItemLore(menus, player, "main-menu", "chat"))
                                                            .build()
                                            );

                                            event.getClickedInventory().setItem(
                                                    getItemSlot(menus, "main-menu", "chat") + 9,
                                                    ItemBuilder
                                                            .newBuilder(
                                                                    getItemMaterial(menus, "main-menu", "chat.toggle.state-on"),
                                                                    getItemAmount(menus, "main-menu", "chat.toggle.state-on"),
                                                                    getItemData(menus, "main-menu", "chat.toggle.state-on")
                                                            )
                                                            .setName(getItemName(menus, player, "main-menu", "chat.toggle.state-on"))
                                                            .setLore(getItemLore(menus, player, "main-menu", "chat.toggle.state-on"))
                                                            .build()
                                            );

                                            XSound.play(player, "UI_BUTTON_CLICK");

                                            break;
                                        case NO_PERMISSION:
                                            player.closeInventory();
                                            player.sendMessage(colorize(player, language.getString("chat.no-permission")));

                                            XSound.play(player, "ENTITY_VILLAGER_NO");

                                            break;
                                    }

                                    return true;
                                })
                                .build()
                ).addItem(
                        ItemClickable
                                .builder(getItemSlot(menus, "main-menu", "double-jump"))
                                .setItemStack(
                                        ItemBuilder
                                                .newBuilder(
                                                        getItemMaterial(menus, "main-menu", "double-jump"),
                                                        getItemAmount(menus, "main-menu", "double-jump"),
                                                        getItemData(menus, "main-menu", "double-jump")
                                                )
                                                .setName(getItemName(menus, player, "main-menu", "double-jump"))
                                                .setLore(getItemLore(menus, player, "main-menu", "double-jump"))
                                                .build()
                                )
                                .setAction(event -> {
                                    switch (user.getDoubleJump()) {
                                        case ON:
                                            user.setDoubleJump(Enums.TypeStatus.OFF);

                                            event.getClickedInventory().setItem(
                                                    getItemSlot(menus, "main-menu", "double-jump"),
                                                    ItemBuilder
                                                            .newBuilder(
                                                                    getItemMaterial(menus, "main-menu", "double-jump"),
                                                                    getItemAmount(menus, "main-menu", "double-jump"),
                                                                    getItemData(menus, "main-menu", "double-jump")
                                                            )
                                                            .setName(getItemName(menus, player, "main-menu", "double-jump"))
                                                            .setLore(getItemLore(menus, player, "main-menu", "double-jump"))
                                                            .build()
                                            );

                                            event.getClickedInventory().setItem(
                                                    getItemSlot(menus, "main-menu", "double-jump") + 9,
                                                    ItemBuilder
                                                            .newBuilder(
                                                                    getItemMaterial(menus, "main-menu", "double-jump.toggle.state-off"),
                                                                    getItemAmount(menus, "main-menu", "double-jump.toggle.state-off"),
                                                                    getItemData(menus, "main-menu", "double-jump.toggle.state-off")
                                                            )
                                                            .setName(getItemName(menus, player, "main-menu", "double-jump.toggle.state-off"))
                                                            .setLore(getItemLore(menus, player, "main-menu", "double-jump.toggle.state-off"))
                                                            .build()
                                            );

                                            XSound.play(player, "UI_BUTTON_CLICK");

                                            break;
                                        case OFF:
                                            if (user.getFly() == Enums.TypeStatus.ON) {
                                                player.sendMessage(colorize(player, language.getString("double-jump.not-change")));

                                                break;
                                            }

                                            user.setDoubleJump(Enums.TypeStatus.ON);

                                            event.getClickedInventory().setItem(
                                                    getItemSlot(menus, "main-menu", "double-jump"),
                                                    ItemBuilder
                                                            .newBuilder(
                                                                    getItemMaterial(menus, "main-menu", "double-jump"),
                                                                    getItemAmount(menus, "main-menu", "double-jump"),
                                                                    getItemData(menus, "main-menu", "double-jump")
                                                            )
                                                            .setName(getItemName(menus, player, "main-menu", "double-jump"))
                                                            .setLore(getItemLore(menus, player, "main-menu", "double-jump"))
                                                            .build()
                                            );

                                            event.getClickedInventory().setItem(
                                                    getItemSlot(menus, "main-menu", "double-jump") + 9,
                                                    ItemBuilder
                                                            .newBuilder(
                                                                    getItemMaterial(menus, "main-menu", "double-jump.toggle.state-on"),
                                                                    getItemAmount(menus, "main-menu", "double-jump.toggle.state-on"),
                                                                    getItemData(menus, "main-menu", "double-jump.toggle.state-on")
                                                            )
                                                            .setName(getItemName(menus, player, "main-menu", "double-jump.toggle.state-on"))
                                                            .setLore(getItemLore(menus, player, "main-menu", "double-jump.toggle.state-on"))
                                                            .build()
                                            );

                                            XSound.play(player, "UI_BUTTON_CLICK");

                                            break;
                                        case NO_PERMISSION:
                                            player.closeInventory();
                                            player.sendMessage(colorize(player, language.getString("double-jump.no-permission")));

                                            XSound.play(player, "ENTITY_VILLAGER_NO");

                                            break;
                                    }

                                    return true;
                                })
                                .build()
                ).addItem(
                        ItemClickable
                                .builder(getItemSlot(menus, "main-menu", "mount"))
                                .setItemStack(
                                        ItemBuilder
                                                .newBuilder(
                                                        getItemMaterial(menus, "main-menu", "mount"),
                                                        getItemAmount(menus, "main-menu", "mount"),
                                                        getItemData(menus, "main-menu", "mount")
                                                )
                                                .setName(getItemName(menus, player, "main-menu", "mount"))
                                                .setLore(getItemLore(menus, player, "main-menu", "mount"))
                                                .build()
                                )
                                .setAction(event -> {
                                    switch (user.getMount()) {
                                        case ON:
                                            user.setMount(Enums.TypeStatus.OFF);

                                            event.getClickedInventory().setItem(
                                                    getItemSlot(menus, "main-menu", "mount"),
                                                    ItemBuilder
                                                            .newBuilder(
                                                                    getItemMaterial(menus, "main-menu", "mount"),
                                                                    getItemAmount(menus, "main-menu", "mount"),
                                                                    getItemData(menus, "main-menu", "mount")
                                                            )
                                                            .setName(getItemName(menus, player, "main-menu", "mount"))
                                                            .setLore(getItemLore(menus, player, "main-menu", "mount"))
                                                            .build()
                                            );

                                            event.getClickedInventory().setItem(
                                                    getItemSlot(menus, "main-menu", "mount") + 9,
                                                    ItemBuilder
                                                            .newBuilder(
                                                                    getItemMaterial(menus, "main-menu", "mount.toggle.state-off"),
                                                                    getItemAmount(menus, "main-menu", "mount.toggle.state-off"),
                                                                    getItemData(menus, "main-menu", "mount.toggle.state-off")
                                                            )
                                                            .setName(getItemName(menus, player, "main-menu", "mount.toggle.state-off"))
                                                            .setLore(getItemLore(menus, player, "main-menu", "mount.toggle.state-off"))
                                                            .build()
                                            );

                                            XSound.play(player, "UI_BUTTON_CLICK");

                                            break;
                                        case OFF:
                                            user.setMount(Enums.TypeStatus.ON);

                                            event.getClickedInventory().setItem(
                                                    getItemSlot(menus, "main-menu", "mount"),
                                                    ItemBuilder
                                                            .newBuilder(
                                                                    getItemMaterial(menus, "main-menu", "mount"),
                                                                    getItemAmount(menus, "main-menu", "mount"),
                                                                    getItemData(menus, "main-menu", "mount")
                                                            )
                                                            .setName(getItemName(menus, player, "main-menu", "mount"))
                                                            .setLore(getItemLore(menus, player, "main-menu", "mount"))
                                                            .build()
                                            );

                                            event.getClickedInventory().setItem(
                                                    getItemSlot(menus, "main-menu", "mount") + 9,
                                                    ItemBuilder
                                                            .newBuilder(
                                                                    getItemMaterial(menus, "main-menu", "mount.toggle.state-on"),
                                                                    getItemAmount(menus, "main-menu", "mount.toggle.state-on"),
                                                                    getItemData(menus, "main-menu", "mount.toggle.state-on")
                                                            )
                                                            .setName(getItemName(menus, player, "main-menu", "mount.toggle.state-on"))
                                                            .setLore(getItemLore(menus, player, "main-menu", "mount.toggle.state-on"))
                                                            .build()
                                            );

                                            XSound.play(player, "UI_BUTTON_CLICK");

                                            break;
                                        case NO_PERMISSION:
                                            player.closeInventory();
                                            player.sendMessage(colorize(player, language.getString("mount.no-permission")));

                                            XSound.play(player, "ENTITY_VILLAGER_NO");

                                            break;
                                    }

                                    return true;
                                })
                                .build()
                ).addItem(
                        ItemClickable
                                .builder(getItemSlot(menus, "main-menu", "fly"))
                                .setItemStack(
                                        ItemBuilder
                                                .newBuilder(
                                                        getItemMaterial(menus, "main-menu", "fly"),
                                                        getItemAmount(menus, "main-menu", "fly"),
                                                        getItemData(menus, "main-menu", "fly")
                                                )
                                                .setName(getItemName(menus, player, "main-menu", "fly"))
                                                .setLore(getItemLore(menus, player, "main-menu", "fly"))
                                                .build()
                                )
                                .setAction(event -> {
                                    switch (user.getFly()) {
                                        case ON:
                                            user.setFly(Enums.TypeStatus.OFF);

                                            event.getClickedInventory().setItem(
                                                    getItemSlot(menus, "main-menu", "fly"),
                                                    ItemBuilder
                                                            .newBuilder(
                                                                    getItemMaterial(menus, "main-menu", "fly"),
                                                                    getItemAmount(menus, "main-menu", "fly"),
                                                                    getItemData(menus, "main-menu", "fly")
                                                            )
                                                            .setName(getItemName(menus, player, "main-menu", "fly"))
                                                            .setLore(getItemLore(menus, player, "main-menu", "fly"))
                                                            .build()
                                            );

                                            event.getClickedInventory().setItem(
                                                    getItemSlot(menus, "main-menu", "fly") + 9,
                                                    ItemBuilder
                                                            .newBuilder(
                                                                    getItemMaterial(menus, "main-menu", "fly.toggle.state-off"),
                                                                    getItemAmount(menus, "main-menu", "fly.toggle.state-off"),
                                                                    getItemData(menus, "main-menu", "fly.toggle.state-off")
                                                            )
                                                            .setName(getItemName(menus, player, "main-menu", "fly.toggle.state-off"))
                                                            .setLore(getItemLore(menus, player, "main-menu", "fly.toggle.state-off"))
                                                            .build()
                                            );

                                            XSound.play(player, "UI_BUTTON_CLICK");

                                            player.setAllowFlight(false);
                                            player.setFlying(false);

                                            break;
                                        case OFF:
                                            if (user.getDoubleJump() == Enums.TypeStatus.ON) {
                                                player.sendMessage(colorize(player, language.getString("fly.not-change")));

                                                break;
                                            }

                                            user.setFly(Enums.TypeStatus.ON);

                                            event.getClickedInventory().setItem(
                                                    getItemSlot(menus, "main-menu", "fly"),
                                                    ItemBuilder
                                                            .newBuilder(
                                                                    getItemMaterial(menus, "main-menu", "fly"),
                                                                    getItemAmount(menus, "main-menu", "fly"),
                                                                    getItemData(menus, "main-menu", "fly")
                                                            )
                                                            .setName(getItemName(menus, player, "main-menu", "fly"))
                                                            .setLore(getItemLore(menus, player, "main-menu", "fly"))
                                                            .build()
                                            );

                                            event.getClickedInventory().setItem(
                                                    getItemSlot(menus, "main-menu", "fly") + 9,
                                                    ItemBuilder
                                                            .newBuilder(
                                                                    getItemMaterial(menus, "main-menu", "fly.toggle.state-on"),
                                                                    getItemAmount(menus, "main-menu", "fly.toggle.state-on"),
                                                                    getItemData(menus, "main-menu", "fly.toggle.state-on")
                                                            )
                                                            .setName(getItemName(menus, player, "main-menu", "fly.toggle.state-on"))
                                                            .setLore(getItemLore(menus, player, "main-menu", "fly.toggle.state-on"))
                                                            .build()
                                            );

                                            XSound.play(player, "UI_BUTTON_CLICK");

                                            player.setFallDistance(0f);
                                            player.setAllowFlight(true);

                                            if (!player.getAllowFlight()) {
                                                player.setFlying(false);
                                            }

                                            break;
                                        case NO_PERMISSION:
                                            player.closeInventory();
                                            player.sendMessage(colorize(player, language.getString("fly.no-permission")));

                                            XSound.play(player, "ENTITY_VILLAGER_NO");

                                            break;
                                    }

                                    return true;
                                })
                                .build()
                ).addItem(
                        ItemClickable
                                .builder(getItemSlot(menus, "main-menu", "message-join"))
                                .setItemStack(
                                        ItemBuilder
                                                .newBuilder(
                                                        getItemMaterial(menus, "main-menu", "message-join"),
                                                        getItemAmount(menus, "main-menu", "message-join"),
                                                        getItemData(menus, "main-menu", "message-join")
                                                )
                                                .setName(getItemName(menus, player, "main-menu", "message-join"))
                                                .setLore(getItemLore(menus, player, "main-menu", "message-join"))
                                                .build()
                                )
                                .setAction(event -> {
                                    switch (user.getMessageJoin()) {
                                        case ON:
                                            user.setMessageJoin(Enums.TypeStatus.OFF);

                                            event.getClickedInventory().setItem(
                                                    getItemSlot(menus, "main-menu", "message-join"),
                                                    ItemBuilder
                                                            .newBuilder(
                                                                    getItemMaterial(menus, "main-menu", "message-join"),
                                                                    getItemAmount(menus, "main-menu", "message-join"),
                                                                    getItemData(menus, "main-menu", "message-join")
                                                            )
                                                            .setName(getItemName(menus, player, "main-menu", "message-join"))
                                                            .setLore(getItemLore(menus, player, "main-menu", "message-join"))
                                                            .build()
                                            );

                                            event.getClickedInventory().setItem(
                                                    getItemSlot(menus, "main-menu", "message-join") + 9,
                                                    ItemBuilder
                                                            .newBuilder(
                                                                    getItemMaterial(menus, "main-menu", "message-join.toggle.state-off"),
                                                                    getItemAmount(menus, "main-menu", "message-join.toggle.state-off"),
                                                                    getItemData(menus, "main-menu", "message-join.toggle.state-off")
                                                            )
                                                            .setName(getItemName(menus, player, "main-menu", "message-join.toggle.state-off"))
                                                            .setLore(getItemLore(menus, player, "main-menu", "message-join.toggle.state-off"))
                                                            .build()
                                            );

                                            XSound.play(player, "UI_BUTTON_CLICK");

                                            break;
                                        case OFF:
                                            user.setMessageJoin(Enums.TypeStatus.ON);

                                            event.getClickedInventory().setItem(
                                                    getItemSlot(menus, "main-menu", "message-join"),
                                                    ItemBuilder
                                                            .newBuilder(
                                                                    getItemMaterial(menus, "main-menu", "message-join"),
                                                                    getItemAmount(menus, "main-menu", "message-join"),
                                                                    getItemData(menus, "main-menu", "message-join")
                                                            )
                                                            .setName(getItemName(menus, player, "main-menu", "message-join"))
                                                            .setLore(getItemLore(menus, player, "main-menu", "message-join"))
                                                            .build()
                                            );

                                            event.getClickedInventory().setItem(
                                                    getItemSlot(menus, "main-menu", "message-join") + 9,
                                                    ItemBuilder
                                                            .newBuilder(
                                                                    getItemMaterial(menus, "main-menu", "message-join.toggle.state-on"),
                                                                    getItemAmount(menus, "main-menu", "message-join.toggle.state-on"),
                                                                    getItemData(menus, "main-menu", "message-join.toggle.state-on")
                                                            )
                                                            .setName(getItemName(menus, player, "main-menu", "message-join.toggle.state-on"))
                                                            .setLore(getItemLore(menus, player, "main-menu", "message-join.toggle.state-on"))
                                                            .build()
                                            );

                                            XSound.play(player, "UI_BUTTON_CLICK");

                                            break;
                                        case NO_PERMISSION:
                                            player.closeInventory();
                                            player.sendMessage(colorize(player, language.getString("message-join.no-permission")));

                                            XSound.play(player, "ENTITY_VILLAGER_NO");

                                            break;
                                    }

                                    return true;
                                })
                                .build()
                ).addItem(
                        ItemClickable
                                .builder(getItemSlot(menus, "main-menu", "effect-join"))
                                .setItemStack(
                                        ItemBuilder
                                                .newBuilder(
                                                        getItemMaterial(menus, "main-menu", "effect-join"),
                                                        getItemAmount(menus, "main-menu", "effect-join"),
                                                        getItemData(menus, "main-menu", "effect-join")
                                                )
                                                .setName(getItemName(menus, player, "main-menu", "effect-join"))
                                                .setLore(getItemLore(menus, player, "main-menu", "effect-join"))
                                                .build()
                                )
                                .setAction(event -> {
                                    if (!player.hasPermission("elegantoptions.effect.menu")) {
                                        player.closeInventory();
                                        player.sendMessage(colorize(player, language.getString("effect-join.no-permission-open")));

                                        XSound.play(player, "ENTITY_VILLAGER_NO");

                                        return false;
                                    }

                                    player.closeInventory();

                                    player.openInventory(effectMenu.create(player));

                                    XSound.play(player, "UI_BUTTON_CLICK");

                                    return true;
                                })
                                .build()
                ).addItem(
                        ItemClickable
                                .builder(getItemSlot(menus, "main-menu", "close"))
                                .setItemStack(
                                        ItemBuilder
                                                .newBuilder(
                                                        getItemMaterial(menus, "main-menu", "close"),
                                                        getItemAmount(menus, "main-menu", "close"),
                                                        getItemData(menus, "main-menu", "close")
                                                )
                                                .setName(getItemName(menus, player, "main-menu", "close"))
                                                .setLore(getItemLore(menus, player, "main-menu", "close"))
                                                .build()
                                )
                                .setAction(event -> {
                                    XSound.play(player, "BLOCK_CHEST_CLOSE");

                                    player.closeInventory();

                                    return true;
                                })
                                .build()
                ).openAction(event -> {
                    if (player.hasPermission("elegantoptions.option.visibility")) {
                        switch (user.getVisibility()) {
                            case ON:
                                event.getInventory().setItem(
                                        getItemSlot(menus, "main-menu", "visibility") + 9,
                                        ItemBuilder
                                                .newBuilder(
                                                        getItemMaterial(menus, "main-menu", "visibility.toggle.state-on"),
                                                        getItemAmount(menus, "main-menu", "visibility.toggle.state-on"),
                                                        getItemData(menus, "main-menu", "visibility.toggle.state-on")
                                                )
                                                .setName(getItemName(menus, player, "main-menu", "visibility.toggle.state-on"))
                                                .setLore(getItemLore(menus, player, "main-menu", "visibility.toggle.state-on"))
                                                .build()
                                );

                                break;
                            case ONLY_RANK:
                                event.getInventory().setItem(
                                        getItemSlot(menus, "main-menu", "visibility") + 9,
                                        ItemBuilder
                                                .newBuilder(
                                                        getItemMaterial(menus, "main-menu", "visibility.toggle.state-only-rank"),
                                                        getItemAmount(menus, "main-menu", "visibility.toggle.state-only-rank"),
                                                        getItemData(menus, "main-menu", "visibility.toggle.state-only-rank")
                                                )
                                                .setName(getItemName(menus, player, "main-menu", "visibility.toggle.state-only-rank"))
                                                .setLore(getItemLore(menus, player, "main-menu", "visibility.toggle.state-only-rank"))
                                                .build()
                                );

                                break;
                            case OFF:
                                event.getInventory().setItem(
                                        getItemSlot(menus, "main-menu", "visibility") + 9,
                                        ItemBuilder
                                                .newBuilder(
                                                        getItemMaterial(menus, "main-menu", "visibility.toggle.state-off"),
                                                        getItemAmount(menus, "main-menu", "visibility.toggle.state-off"),
                                                        getItemData(menus, "main-menu", "visibility.toggle.state-off")
                                                )
                                                .setName(getItemName(menus, player, "main-menu", "visibility.toggle.state-off"))
                                                .setLore(getItemLore(menus, player, "main-menu", "visibility.toggle.state-off"))
                                                .build()
                                );

                                break;
                            case NO_PERMISSION:
                                event.getInventory().setItem(
                                        getItemSlot(menus, "main-menu", "visibility") + 9,
                                        ItemBuilder
                                                .newBuilder(
                                                        getItemMaterial(menus, "main-menu", "visibility.toggle.state-no-permission"),
                                                        getItemAmount(menus, "main-menu", "visibility.toggle.state-no-permission"),
                                                        getItemData(menus, "main-menu", "visibility.toggle.state-no-permission")
                                                )
                                                .setName(getItemName(menus, player, "main-menu", "visibility.toggle.state-no-permission"))
                                                .setLore(getItemLore(menus, player, "main-menu", "visibility.toggle.state-no-permission"))
                                                .build()
                                );

                                break;
                        }
                    } else {
                        user.setVisibility(Enums.TypeStatus.NO_PERMISSION);

                        event.getInventory().setItem(
                                getItemSlot(menus, "main-menu", "visibility") + 9,
                                ItemBuilder
                                        .newBuilder(
                                                getItemMaterial(menus, "main-menu", "visibility.toggle.state-no-permission"),
                                                getItemAmount(menus, "main-menu", "visibility.toggle.state-no-permission"),
                                                getItemData(menus, "main-menu", "visibility.toggle.state-no-permission")
                                        )
                                        .setName(getItemName(menus, player, "main-menu", "visibility.toggle.state-no-permission"))
                                        .setLore(getItemLore(menus, player, "main-menu", "visibility.toggle.state-no-permission"))
                                        .build()
                        );
                    }

                    if (player.hasPermission("elegantoptions.option.chat")) {
                        switch (user.getChat()) {
                            case ON:
                                event.getInventory().setItem(
                                        getItemSlot(menus, "main-menu", "chat") + 9,
                                        ItemBuilder
                                                .newBuilder(
                                                        getItemMaterial(menus, "main-menu", "chat.toggle.state-on"),
                                                        getItemAmount(menus, "main-menu", "chat.toggle.state-on"),
                                                        getItemData(menus, "main-menu", "chat.toggle.state-on")
                                                )
                                                .setName(getItemName(menus, player, "main-menu", "chat.toggle.state-on"))
                                                .setLore(getItemLore(menus, player, "main-menu", "chat.toggle.state-on"))
                                                .build()
                                );

                                break;
                            case OFF:
                                event.getInventory().setItem(
                                        getItemSlot(menus, "main-menu", "chat") + 9,
                                        ItemBuilder
                                                .newBuilder(
                                                        getItemMaterial(menus, "main-menu", "chat.toggle.state-off"),
                                                        getItemAmount(menus, "main-menu", "chat.toggle.state-off"),
                                                        getItemData(menus, "main-menu", "chat.toggle.state-off")
                                                )
                                                .setName(getItemName(menus, player, "main-menu", "chat.toggle.state-off"))
                                                .setLore(getItemLore(menus, player, "main-menu", "chat.toggle.state-off"))
                                                .build()
                                );

                                break;
                            case NO_PERMISSION:
                                event.getInventory().setItem(
                                        getItemSlot(menus, "main-menu", "chat") + 9,
                                        ItemBuilder
                                                .newBuilder(
                                                        getItemMaterial(menus, "main-menu", "chat.toggle.state-no-permission"),
                                                        getItemAmount(menus, "main-menu", "chat.toggle.state-no-permission"),
                                                        getItemData(menus, "main-menu", "chat.toggle.state-no-permission")
                                                )
                                                .setName(getItemName(menus, player, "main-menu", "chat.toggle.state-no-permission"))
                                                .setLore(getItemLore(menus, player, "main-menu", "chat.toggle.state-no-permission"))
                                                .build()
                                );

                                break;
                        }
                    } else {
                        user.setChat(Enums.TypeStatus.NO_PERMISSION);

                        event.getInventory().setItem(
                                getItemSlot(menus, "main-menu", "chat") + 9,
                                ItemBuilder
                                        .newBuilder(
                                                getItemMaterial(menus, "main-menu", "chat.toggle.state-no-permission"),
                                                getItemAmount(menus, "main-menu", "chat.toggle.state-no-permission"),
                                                getItemData(menus, "main-menu", "chat.toggle.state-no-permission")
                                        )
                                        .setName(getItemName(menus, player, "main-menu", "chat.toggle.state-no-permission"))
                                        .setLore(getItemLore(menus, player, "main-menu", "chat.toggle.state-no-permission"))
                                        .build()
                        );
                    }

                    if (player.hasPermission("elegantoptions.option.doubleJump")) {
                        switch (user.getDoubleJump()) {
                            case ON:
                                event.getInventory().setItem(
                                        getItemSlot(menus, "main-menu", "double-jump") + 9,
                                        ItemBuilder
                                                .newBuilder(
                                                        getItemMaterial(menus, "main-menu", "double-jump.toggle.state-on"),
                                                        getItemAmount(menus, "main-menu", "double-jump.toggle.state-on"),
                                                        getItemData(menus, "main-menu", "double-jump.toggle.state-on")
                                                )
                                                .setName(getItemName(menus, player, "main-menu", "double-jump.toggle.state-on"))
                                                .setLore(getItemLore(menus, player, "main-menu", "double-jump.toggle.state-on"))
                                                .build()
                                );

                                break;
                            case OFF:
                                event.getInventory().setItem(
                                        getItemSlot(menus, "main-menu", "double-jump") + 9,
                                        ItemBuilder
                                                .newBuilder(
                                                        getItemMaterial(menus, "main-menu", "double-jump.toggle.state-off"),
                                                        getItemAmount(menus, "main-menu", "double-jump.toggle.state-off"),
                                                        getItemData(menus, "main-menu", "double-jump.toggle.state-off")
                                                )
                                                .setName(getItemName(menus, player, "main-menu", "double-jump.toggle.state-off"))
                                                .setLore(getItemLore(menus, player, "main-menu", "double-jump.toggle.state-off"))
                                                .build()
                                );

                                break;
                            case NO_PERMISSION:
                                event.getInventory().setItem(
                                        getItemSlot(menus, "main-menu", "double-jump") + 9,
                                        ItemBuilder
                                                .newBuilder(
                                                        getItemMaterial(menus, "main-menu", "double-jump.toggle.state-no-permission"),
                                                        getItemAmount(menus, "main-menu", "double-jump.toggle.state-no-permission"),
                                                        getItemData(menus, "main-menu", "double-jump.toggle.state-no-permission")
                                                )
                                                .setName(getItemName(menus, player, "main-menu", "double-jump.toggle.state-no-permission"))
                                                .setLore(getItemLore(menus, player, "main-menu", "double-jump.toggle.state-no-permission"))
                                                .build()
                                );

                                break;
                        }
                    } else {
                        user.setDoubleJump(Enums.TypeStatus.NO_PERMISSION);

                        event.getInventory().setItem(
                                getItemSlot(menus, "main-menu", "double-jump") + 9,
                                ItemBuilder
                                        .newBuilder(
                                                getItemMaterial(menus, "main-menu", "double-jump.toggle.state-no-permission"),
                                                getItemAmount(menus, "main-menu", "double-jump.toggle.state-no-permission"),
                                                getItemData(menus, "main-menu", "double-jump.toggle.state-no-permission")
                                        )
                                        .setName(getItemName(menus, player, "main-menu", "double-jump.toggle.state-no-permission"))
                                        .setLore(getItemLore(menus, player, "main-menu", "double-jump.toggle.state-no-permission"))
                                        .build()
                        );
                    }

                    if (player.hasPermission("elegantoptions.option.mount")) {
                        switch (user.getMount()) {
                            case ON:
                                event.getInventory().setItem(
                                        getItemSlot(menus, "main-menu", "mount") + 9,
                                        ItemBuilder
                                                .newBuilder(
                                                        getItemMaterial(menus, "main-menu", "mount.toggle.state-on"),
                                                        getItemAmount(menus, "main-menu", "mount.toggle.state-on"),
                                                        getItemData(menus, "main-menu", "mount.toggle.state-on")
                                                )
                                                .setName(getItemName(menus, player, "main-menu", "mount.toggle.state-on"))
                                                .setLore(getItemLore(menus, player, "main-menu", "mount.toggle.state-on"))
                                                .build()
                                );

                                break;
                            case OFF:
                                event.getInventory().setItem(
                                        getItemSlot(menus, "main-menu", "mount") + 9,
                                        ItemBuilder
                                                .newBuilder(
                                                        getItemMaterial(menus, "main-menu", "mount.toggle.state-off"),
                                                        getItemAmount(menus, "main-menu", "mount.toggle.state-off"),
                                                        getItemData(menus, "main-menu", "mount.toggle.state-off")
                                                )
                                                .setName(getItemName(menus, player, "main-menu", "mount.toggle.state-off"))
                                                .setLore(getItemLore(menus, player, "main-menu", "mount.toggle.state-off"))
                                                .build()
                                );

                                break;
                            case NO_PERMISSION:
                                event.getInventory().setItem(
                                        getItemSlot(menus, "main-menu", "mount") + 9,
                                        ItemBuilder
                                                .newBuilder(
                                                        getItemMaterial(menus, "main-menu", "mount.toggle.state-no-permission"),
                                                        getItemAmount(menus, "main-menu", "mount.toggle.state-no-permission"),
                                                        getItemData(menus, "main-menu", "mount.toggle.state-no-permission")
                                                )
                                                .setName(getItemName(menus, player, "main-menu", "mount.toggle.state-no-permission"))
                                                .setLore(getItemLore(menus, player, "main-menu", "mount.toggle.state-no-permission"))
                                                .build()
                                );

                                break;
                        }
                    } else {
                        user.setMount(Enums.TypeStatus.NO_PERMISSION);

                        event.getInventory().setItem(
                                getItemSlot(menus, "main-menu", "mount") + 9,
                                ItemBuilder
                                        .newBuilder(
                                                getItemMaterial(menus, "main-menu", "mount.toggle.state-no-permission"),
                                                getItemAmount(menus, "main-menu", "mount.toggle.state-no-permission"),
                                                getItemData(menus, "main-menu", "mount.toggle.state-no-permission")
                                        )
                                        .setName(getItemName(menus, player, "main-menu", "mount.toggle.state-no-permission"))
                                        .setLore(getItemLore(menus, player, "main-menu", "mount.toggle.state-no-permission"))
                                        .build()
                        );
                    }

                    if (player.hasPermission("elegantoptions.option.fly")) {
                        switch (user.getFly()) {
                            case ON:
                                event.getInventory().setItem(
                                        getItemSlot(menus, "main-menu", "fly") + 9,
                                        ItemBuilder
                                                .newBuilder(
                                                        getItemMaterial(menus, "main-menu", "fly.toggle.state-on"),
                                                        getItemAmount(menus, "main-menu", "fly.toggle.state-on"),
                                                        getItemData(menus, "main-menu", "fly.toggle.state-on")
                                                )
                                                .setName(getItemName(menus, player, "main-menu", "fly.toggle.state-on"))
                                                .setLore(getItemLore(menus, player, "main-menu", "fly.toggle.state-on"))
                                                .build()
                                );

                                break;
                            case OFF:
                                event.getInventory().setItem(
                                        getItemSlot(menus, "main-menu", "fly") + 9,
                                        ItemBuilder
                                                .newBuilder(
                                                        getItemMaterial(menus, "main-menu", "fly.toggle.state-off"),
                                                        getItemAmount(menus, "main-menu", "fly.toggle.state-off"),
                                                        getItemData(menus, "main-menu", "fly.toggle.state-off")
                                                )
                                                .setName(getItemName(menus, player, "main-menu", "fly.toggle.state-off"))
                                                .setLore(getItemLore(menus, player, "main-menu", "fly.toggle.state-off"))
                                                .build()
                                );

                                break;
                            case NO_PERMISSION:
                                event.getInventory().setItem(
                                        getItemSlot(menus, "main-menu", "fly") + 9,
                                        ItemBuilder
                                                .newBuilder(
                                                        getItemMaterial(menus, "main-menu", "fly.toggle.state-no-permission"),
                                                        getItemAmount(menus, "main-menu", "fly.toggle.state-no-permission"),
                                                        getItemData(menus, "main-menu", "fly.toggle.state-no-permission")
                                                )
                                                .setName(getItemName(menus, player, "main-menu", "fly.toggle.state-no-permission"))
                                                .setLore(getItemLore(menus, player, "main-menu", "fly.toggle.state-no-permission"))
                                                .build()
                                );

                                break;
                        }
                    } else {
                        user.setFly(Enums.TypeStatus.NO_PERMISSION);

                        event.getInventory().setItem(
                                getItemSlot(menus, "main-menu", "fly") + 9,
                                ItemBuilder
                                        .newBuilder(
                                                getItemMaterial(menus, "main-menu", "fly.toggle.state-no-permission"),
                                                getItemAmount(menus, "main-menu", "fly.toggle.state-no-permission"),
                                                getItemData(menus, "main-menu", "fly.toggle.state-no-permission")
                                        )
                                        .setName(getItemName(menus, player, "main-menu", "fly.toggle.state-no-permission"))
                                        .setLore(getItemLore(menus, player, "main-menu", "fly.toggle.state-no-permission"))
                                        .build()
                        );
                    }

                    if (player.hasPermission("elegantoptions.option.messageJoin")) {
                        switch (user.getMessageJoin()) {
                            case ON:
                                event.getInventory().setItem(
                                        getItemSlot(menus, "main-menu", "message-join") + 9,
                                        ItemBuilder
                                                .newBuilder(
                                                        getItemMaterial(menus, "main-menu", "message-join.toggle.state-on"),
                                                        getItemAmount(menus, "main-menu", "message-join.toggle.state-on"),
                                                        getItemData(menus, "main-menu", "message-join.toggle.state-on")
                                                )
                                                .setName(getItemName(menus, player, "main-menu", "message-join.toggle.state-on"))
                                                .setLore(getItemLore(menus, player, "main-menu", "message-join.toggle.state-on"))
                                                .build()
                                );

                                break;
                            case OFF:
                                event.getInventory().setItem(
                                        getItemSlot(menus, "main-menu", "message-join") + 9,
                                        ItemBuilder
                                                .newBuilder(
                                                        getItemMaterial(menus, "main-menu", "message-join.toggle.state-off"),
                                                        getItemAmount(menus, "main-menu", "message-join.toggle.state-off"),
                                                        getItemData(menus, "main-menu", "message-join.toggle.state-off")
                                                )
                                                .setName(getItemName(menus, player, "main-menu", "message-join.toggle.state-off"))
                                                .setLore(getItemLore(menus, player, "main-menu", "message-join.toggle.state-off"))
                                                .build()
                                );

                                break;
                            case NO_PERMISSION:
                                event.getInventory().setItem(
                                        getItemSlot(menus, "main-menu", "message-join") + 9,
                                        ItemBuilder
                                                .newBuilder(
                                                        getItemMaterial(menus, "main-menu", "message-join.toggle.state-no-permission"),
                                                        getItemAmount(menus, "main-menu", "message-join.toggle.state-no-permission"),
                                                        getItemData(menus, "main-menu", "message-join.toggle.state-no-permission")
                                                )
                                                .setName(getItemName(menus, player, "main-menu", "message-join.toggle.state-no-permission"))
                                                .setLore(getItemLore(menus, player, "main-menu", "message-join.toggle.state-no-permission"))
                                                .build()
                                );

                                break;
                        }
                    } else {
                        user.setMessageJoin(Enums.TypeStatus.NO_PERMISSION);

                        event.getInventory().setItem(
                                getItemSlot(menus, "main-menu", "message-join") + 9,
                                ItemBuilder
                                        .newBuilder(
                                                getItemMaterial(menus, "main-menu", "message-join.toggle.state-no-permission"),
                                                getItemAmount(menus, "main-menu", "message-join.toggle.state-no-permission"),
                                                getItemData(menus, "main-menu", "message-join.toggle.state-no-permission")
                                        )
                                        .setName(getItemName(menus, player, "main-menu", "message-join.toggle.state-no-permission"))
                                        .setLore(getItemLore(menus, player, "main-menu", "message-join.toggle.state-no-permission"))
                                        .build()
                        );
                    }

                    switch (user.getEffectJoin()) {
                        case NOTHING:
                        case FIREWORK:
                        case ZEUS:
                        case SHEEP:
                            event.getInventory().setItem(
                                    getItemSlot(menus, "main-menu", "effect-join") + 9,
                                    ItemBuilder
                                            .newBuilder(
                                                    getItemMaterial(menus, "main-menu", "effect-join.toggle.state"),
                                                    getItemAmount(menus, "main-menu", "effect-join.toggle.state"),
                                                    getItemData(menus, "main-menu", "effect-join.toggle.state")
                                            )
                                            .setName(getItemName(menus, player, "main-menu", "effect-join.toggle.state"))
                                            .setLore(getItemLore(menus, player, "main-menu", "effect-join.toggle.state"))
                                            .build()
                            );

                            break;
                    }

                    return false;
                });

        return guiBuilder.build();
    }
}
