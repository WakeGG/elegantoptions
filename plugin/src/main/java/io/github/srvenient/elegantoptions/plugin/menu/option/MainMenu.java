package io.github.srvenient.elegantoptions.plugin.menu.option;

import dev.srvenient.freya.abstraction.configuration.Configuration;
import dev.srvenient.freya.api.xseries.XSound;

import io.github.srvenient.elegantoptions.plugin.menu.MenuCreator;

import io.github.srvenient.elegantoptions.api.Enums;
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

import static io.github.srvenient.elegantoptions.plugin.translation.Translation.colorize;

public class MainMenu extends MenuCreator {

    @Inject private UserMatcher userMatcher;

    @Inject private EffectMenu effectMenu;

    @Inject @Named("language") private Configuration language;

    public MainMenu() {
        super("main-menu");
    }

    @Override
    public Inventory create(Player player) {
        User user = userMatcher.getUserId(player.getUniqueId());

        if (user == null) return null;

        GUIBuilder guiBuilder = GUIBuilder.builder(this.getTitle(player), this.getRows())
                .addItem(
                        ItemClickable
                                .builder(this.getItemSlot("visibility"))
                                .setItemStack(
                                        ItemBuilder
                                                .newBuilder(
                                                        this.getItemMaterial("visibility"),
                                                        this.getItemAmount("visibility"),
                                                        this.getItemData("visibility")
                                                )
                                                .setName(this.getItemName(player, "visibility"))
                                                .setLore(this.getItemLore(player, "visibility"))
                                                .build()
                                )
                                .setAction(event -> {
                                    switch (user.getVisibility()) {
                                        case ON:
                                            user.setVisibility(Enums.TypeStatus.ONLY_RANK);

                                            event.getClickedInventory().setItem(
                                                    this.getItemSlot("visibility"),
                                                    ItemBuilder
                                                            .newBuilder(
                                                                    this.getItemMaterial("visibility"),
                                                                    this.getItemAmount("visibility"),
                                                                    this.getItemData("visibility")
                                                            )
                                                            .setName(this.getItemName(player, "visibility"))
                                                            .setLore(this.getItemLore(player, "visibility"))
                                                            .build()
                                            );

                                            event.getClickedInventory().setItem(
                                                    this.getItemSlot("visibility") + 9,
                                                    ItemBuilder
                                                            .newBuilder(
                                                                    this.getItemMaterial("visibility.toggle.state-only-rank"),
                                                                    this.getItemAmount("visibility.toggle.state-only-rank"),
                                                                    this.getItemData("visibility.toggle.state-only-rank")
                                                            )
                                                            .setName(this.getItemName(player, "visibility.toggle.state-only-rank"))
                                                            .setLore(this.getItemLore(player, "visibility.toggle.state-only-rank"))
                                                            .build()
                                            );

                                            XSound.play(player, "UI_BUTTON_CLICK");

                                            Bukkit.getOnlinePlayers().forEach(players -> {
                                                if (players.hasPermission("elegant_options.user.rank")) {
                                                    player.showPlayer(players);
                                                } else {
                                                    player.hidePlayer(players);
                                                }
                                            });

                                            break;
                                        case ONLY_RANK:
                                            user.setVisibility(Enums.TypeStatus.OFF);

                                            event.getClickedInventory().setItem(
                                                    this.getItemSlot("visibility"),
                                                    ItemBuilder
                                                            .newBuilder(
                                                                    this.getItemMaterial("visibility"),
                                                                    this.getItemAmount("visibility"),
                                                                    this.getItemData("visibility")
                                                            )
                                                            .setName(this.getItemName(player, "visibility"))
                                                            .setLore(this.getItemLore(player, "visibility"))
                                                            .build()
                                            );

                                            event.getClickedInventory().setItem(
                                                    this.getItemSlot("visibility") + 9,
                                                    ItemBuilder
                                                            .newBuilder(
                                                                    this.getItemMaterial("visibility.toggle.state-off"),
                                                                    this.getItemAmount("visibility.toggle.state-off"),
                                                                    this.getItemData("visibility.toggle.state-off")
                                                            )
                                                            .setName(this.getItemName(player, "visibility.toggle.state-off"))
                                                            .setLore(this.getItemLore(player, "visibility.toggle.state-off"))
                                                            .build()
                                            );

                                            XSound.play(player, "UI_BUTTON_CLICK");

                                            Bukkit.getOnlinePlayers().forEach(player::hidePlayer);

                                            break;
                                        case OFF:
                                            user.setVisibility(Enums.TypeStatus.ON);

                                            event.getClickedInventory().setItem(
                                                    this.getItemSlot("visibility"),
                                                    ItemBuilder
                                                            .newBuilder(
                                                                    this.getItemMaterial("visibility"),
                                                                    this.getItemAmount("visibility"),
                                                                    this.getItemData("visibility")
                                                            )
                                                            .setName(this.getItemName(player, "visibility"))
                                                            .setLore(this.getItemLore(player, "visibility"))
                                                            .build()
                                            );

                                            event.getClickedInventory().setItem(
                                                    this.getItemSlot("visibility") + 9,
                                                    ItemBuilder
                                                            .newBuilder(
                                                                    this.getItemMaterial("visibility.toggle.state-on"),
                                                                    this.getItemAmount("visibility.toggle.state-on"),
                                                                    this.getItemData("visibility.toggle.state-on")
                                                            )
                                                            .setName(this.getItemName(player, "visibility.toggle.state-on"))
                                                            .setLore(this.getItemLore(player, "visibility.toggle.state-on"))
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
                                .builder(this.getItemSlot("chat"))
                                .setItemStack(
                                        ItemBuilder
                                                .newBuilder(
                                                        this.getItemMaterial("chat"),
                                                        this.getItemAmount("chat"),
                                                        this.getItemData("chat")
                                                )
                                                .setName(this.getItemName(player, "chat"))
                                                .setLore(this.getItemLore(player, "chat"))
                                                .build()
                                )
                                .setAction(event -> {
                                    switch (user.getChat()) {
                                        case ON:
                                            user.setChat(Enums.TypeStatus.OFF);

                                            event.getClickedInventory().setItem(
                                                    this.getItemSlot("chat"),
                                                    ItemBuilder
                                                            .newBuilder(
                                                                    this.getItemMaterial("chat"),
                                                                    this.getItemAmount("chat"),
                                                                    this.getItemData("chat")
                                                            )
                                                            .setName(this.getItemName(player, "chat"))
                                                            .setLore(this.getItemLore(player, "chat"))
                                                            .build()
                                            );

                                            event.getClickedInventory().setItem(
                                                    this.getItemSlot("chat") + 9,
                                                    ItemBuilder
                                                            .newBuilder(
                                                                    this.getItemMaterial("chat.toggle.state-off"),
                                                                    this.getItemAmount("chat.toggle.state-off"),
                                                                    this.getItemData("chat.toggle.state-off")
                                                            )
                                                            .setName(this.getItemName(player, "chat.toggle.state-off"))
                                                            .setLore(this.getItemLore(player, "chat.toggle.state-off"))
                                                            .build()
                                            );

                                            XSound.play(player, "UI_BUTTON_CLICK");

                                            break;
                                        case OFF:
                                            user.setChat(Enums.TypeStatus.ON);

                                            event.getClickedInventory().setItem(
                                                    this.getItemSlot("chat"),
                                                    ItemBuilder
                                                            .newBuilder(
                                                                    this.getItemMaterial("chat"),
                                                                    this.getItemAmount("chat"),
                                                                    this.getItemData("chat")
                                                            )
                                                            .setName(this.getItemName(player, "chat"))
                                                            .setLore(this.getItemLore(player, "chat"))
                                                            .build()
                                            );

                                            event.getClickedInventory().setItem(
                                                    this.getItemSlot("chat") + 9,
                                                    ItemBuilder
                                                            .newBuilder(
                                                                    this.getItemMaterial("chat.toggle.state-on"),
                                                                    this.getItemAmount("chat.toggle.state-on"),
                                                                    this.getItemData("chat.toggle.state-on")
                                                            )
                                                            .setName(this.getItemName(player, "chat.toggle.state-on"))
                                                            .setLore(this.getItemLore(player, "chat.toggle.state-on"))
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
                                .builder(this.getItemSlot("double-jump"))
                                .setItemStack(
                                        ItemBuilder
                                                .newBuilder(
                                                        this.getItemMaterial("double-jump"),
                                                        this.getItemAmount("double-jump"),
                                                        this.getItemData("double-jump")
                                                )
                                                .setName(this.getItemName(player, "double-jump"))
                                                .setLore(this.getItemLore(player, "double-jump"))
                                                .build()
                                )
                                .setAction(event -> {
                                    switch (user.getDoubleJump()) {
                                        case ON:
                                            user.setDoubleJump(Enums.TypeStatus.OFF);

                                            event.getClickedInventory().setItem(
                                                    this.getItemSlot("double-jump"),
                                                    ItemBuilder
                                                            .newBuilder(
                                                                    this.getItemMaterial("double-jump"),
                                                                    this.getItemAmount("double-jump"),
                                                                    this.getItemData("double-jump")
                                                            )
                                                            .setName(this.getItemName(player, "double-jump"))
                                                            .setLore(this.getItemLore(player, "double-jump"))
                                                            .build()
                                            );

                                            event.getClickedInventory().setItem(
                                                    this.getItemSlot("double-jump") + 9,
                                                    ItemBuilder
                                                            .newBuilder(
                                                                    this.getItemMaterial("double-jump.toggle.state-off"),
                                                                    this.getItemAmount("double-jump.toggle.state-off"),
                                                                    this.getItemData("double-jump.toggle.state-off")
                                                            )
                                                            .setName(this.getItemName(player, "double-jump.toggle.state-off"))
                                                            .setLore(this.getItemLore(player, "double-jump.toggle.state-off"))
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
                                                    this.getItemSlot("double-jump"),
                                                    ItemBuilder
                                                            .newBuilder(
                                                                    this.getItemMaterial("double-jump"),
                                                                    this.getItemAmount("double-jump"),
                                                                    this.getItemData("double-jump")
                                                            )
                                                            .setName(this.getItemName(player, "double-jump"))
                                                            .setLore(this.getItemLore(player, "double-jump"))
                                                            .build()
                                            );

                                            event.getClickedInventory().setItem(
                                                    this.getItemSlot("double-jump") + 9,
                                                    ItemBuilder
                                                            .newBuilder(
                                                                    this.getItemMaterial("double-jump.toggle.state-on"),
                                                                    this.getItemAmount("double-jump.toggle.state-on"),
                                                                    this.getItemData("double-jump.toggle.state-on")
                                                            )
                                                            .setName(this.getItemName(player, "double-jump.toggle.state-on"))
                                                            .setLore(this.getItemLore(player, "double-jump.toggle.state-on"))
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
                                .builder(this.getItemSlot("mount"))
                                .setItemStack(
                                        ItemBuilder
                                                .newBuilder(
                                                        this.getItemMaterial("mount"),
                                                        this.getItemAmount("mount"),
                                                        this.getItemData("mount")
                                                )
                                                .setName(this.getItemName(player, "mount"))
                                                .setLore(this.getItemLore(player, "mount"))
                                                .build()
                                )
                                .setAction(event -> {
                                    switch (user.getMount()) {
                                        case ON:
                                            user.setMount(Enums.TypeStatus.OFF);

                                            event.getClickedInventory().setItem(
                                                    this.getItemSlot("mount"),
                                                    ItemBuilder
                                                            .newBuilder(
                                                                    this.getItemMaterial("mount"),
                                                                    this.getItemAmount("mount"),
                                                                    this.getItemData("mount")
                                                            )
                                                            .setName(this.getItemName(player, "mount"))
                                                            .setLore(this.getItemLore(player, "mount"))
                                                            .build()
                                            );

                                            event.getClickedInventory().setItem(
                                                    this.getItemSlot("mount") + 9,
                                                    ItemBuilder
                                                            .newBuilder(
                                                                    this.getItemMaterial("mount.toggle.state-off"),
                                                                    this.getItemAmount("mount.toggle.state-off"),
                                                                    this.getItemData("mount.toggle.state-off")
                                                            )
                                                            .setName(this.getItemName(player, "mount.toggle.state-off"))
                                                            .setLore(this.getItemLore(player, "mount.toggle.state-off"))
                                                            .build()
                                            );

                                            XSound.play(player, "UI_BUTTON_CLICK");

                                            break;
                                        case OFF:
                                            user.setMount(Enums.TypeStatus.ON);

                                            event.getClickedInventory().setItem(
                                                    this.getItemSlot("mount"),
                                                    ItemBuilder
                                                            .newBuilder(
                                                                    this.getItemMaterial("mount"),
                                                                    this.getItemAmount("mount"),
                                                                    this.getItemData("mount")
                                                            )
                                                            .setName(this.getItemName(player, "mount"))
                                                            .setLore(this.getItemLore(player, "mount"))
                                                            .build()
                                            );

                                            event.getClickedInventory().setItem(
                                                    this.getItemSlot("mount") + 9,
                                                    ItemBuilder
                                                            .newBuilder(
                                                                    this.getItemMaterial("mount.toggle.state-on"),
                                                                    this.getItemAmount("mount.toggle.state-on"),
                                                                    this.getItemData("mount.toggle.state-on")
                                                            )
                                                            .setName(this.getItemName(player, "mount.toggle.state-on"))
                                                            .setLore(this.getItemLore(player, "mount.toggle.state-on"))
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
                                .builder(this.getItemSlot("fly"))
                                .setItemStack(
                                        ItemBuilder
                                                .newBuilder(
                                                        this.getItemMaterial("fly"),
                                                        this.getItemAmount("fly"),
                                                        this.getItemData("fly")
                                                )
                                                .setName(this.getItemName(player, "fly"))
                                                .setLore(this.getItemLore(player, "fly"))
                                                .build()
                                )
                                .setAction(event -> {
                                    switch (user.getFly()) {
                                        case ON:
                                            user.setFly(Enums.TypeStatus.OFF);

                                            event.getClickedInventory().setItem(
                                                    this.getItemSlot("fly"),
                                                    ItemBuilder
                                                            .newBuilder(
                                                                    this.getItemMaterial("fly"),
                                                                    this.getItemAmount("fly"),
                                                                    this.getItemData("fly")
                                                            )
                                                            .setName(this.getItemName(player, "fly"))
                                                            .setLore(this.getItemLore(player, "fly"))
                                                            .build()
                                            );

                                            event.getClickedInventory().setItem(
                                                    this.getItemSlot("fly") + 9,
                                                    ItemBuilder
                                                            .newBuilder(
                                                                    this.getItemMaterial("fly.toggle.state-off"),
                                                                    this.getItemAmount("fly.toggle.state-off"),
                                                                    this.getItemData("fly.toggle.state-off")
                                                            )
                                                            .setName(this.getItemName(player, "fly.toggle.state-off"))
                                                            .setLore(this.getItemLore(player, "fly.toggle.state-off"))
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
                                                    this.getItemSlot("fly"),
                                                    ItemBuilder
                                                            .newBuilder(
                                                                    this.getItemMaterial("fly"),
                                                                    this.getItemAmount("fly"),
                                                                    this.getItemData("fly")
                                                            )
                                                            .setName(this.getItemName(player, "fly"))
                                                            .setLore(this.getItemLore(player, "fly"))
                                                            .build()
                                            );

                                            event.getClickedInventory().setItem(
                                                    this.getItemSlot("fly") + 9,
                                                    ItemBuilder
                                                            .newBuilder(
                                                                    this.getItemMaterial("fly.toggle.state-on"),
                                                                    this.getItemAmount("fly.toggle.state-on"),
                                                                    this.getItemData("fly.toggle.state-on")
                                                            )
                                                            .setName(this.getItemName(player, "fly.toggle.state-on"))
                                                            .setLore(this.getItemLore(player, "fly.toggle.state-on"))
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
                                .builder(this.getItemSlot("message-join"))
                                .setItemStack(
                                        ItemBuilder
                                                .newBuilder(
                                                        this.getItemMaterial("message-join"),
                                                        this.getItemAmount("message-join"),
                                                        this.getItemData("message-join")
                                                )
                                                .setName(this.getItemName(player, "message-join"))
                                                .setLore(this.getItemLore(player, "message-join"))
                                                .build()
                                )
                                .setAction(event -> {
                                    switch (user.getMessageJoin()) {
                                        case ON:
                                            user.setMessageJoin(Enums.TypeStatus.OFF);

                                            event.getClickedInventory().setItem(
                                                    this.getItemSlot("message-join"),
                                                    ItemBuilder
                                                            .newBuilder(
                                                                    this.getItemMaterial("message-join"),
                                                                    this.getItemAmount("message-join"),
                                                                    this.getItemData("message-join")
                                                            )
                                                            .setName(this.getItemName(player, "message-join"))
                                                            .setLore(this.getItemLore(player, "message-join"))
                                                            .build()
                                            );

                                            event.getClickedInventory().setItem(
                                                    this.getItemSlot("message-join") + 9,
                                                    ItemBuilder
                                                            .newBuilder(
                                                                    this.getItemMaterial("message-join.toggle.state-off"),
                                                                    this.getItemAmount("message-join.toggle.state-off"),
                                                                    this.getItemData("message-join.toggle.state-off")
                                                            )
                                                            .setName(this.getItemName(player, "message-join.toggle.state-off"))
                                                            .setLore(this.getItemLore(player, "message-join.toggle.state-off"))
                                                            .build()
                                            );

                                            XSound.play(player, "UI_BUTTON_CLICK");

                                            break;
                                        case OFF:
                                            user.setMessageJoin(Enums.TypeStatus.ON);

                                            event.getClickedInventory().setItem(
                                                    this.getItemSlot("message-join"),
                                                    ItemBuilder
                                                            .newBuilder(
                                                                    this.getItemMaterial("message-join"),
                                                                    this.getItemAmount("message-join"),
                                                                    this.getItemData("message-join")
                                                            )
                                                            .setName(this.getItemName(player, "message-join"))
                                                            .setLore(this.getItemLore(player, "message-join"))
                                                            .build()
                                            );

                                            event.getClickedInventory().setItem(
                                                    this.getItemSlot("message-join") + 9,
                                                    ItemBuilder
                                                            .newBuilder(
                                                                    this.getItemMaterial("message-join.toggle.state-on"),
                                                                    this.getItemAmount("message-join.toggle.state-on"),
                                                                    this.getItemData("message-join.toggle.state-on")
                                                            )
                                                            .setName(this.getItemName(player, "message-join.toggle.state-on"))
                                                            .setLore(this.getItemLore(player, "message-join.toggle.state-on"))
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
                                .builder(this.getItemSlot("effect-join"))
                                .setItemStack(
                                        ItemBuilder
                                                .newBuilder(
                                                        this.getItemMaterial("effect-join"),
                                                        this.getItemAmount("effect-join"),
                                                        this.getItemData("effect-join")
                                                )
                                                .setName(this.getItemName(player, "effect-join"))
                                                .setLore(this.getItemLore(player, "effect-join"))
                                                .build()
                                )
                                .setAction(event -> {
                                    if (!player.hasPermission("elegantoptions.effect.menu")) {
                                        player.closeInventory();
                                        player.sendMessage(colorize(player, language.getString("effect-join.no-permission-open")));

                                        return false;
                                    }

                                    player.closeInventory();

                                    effectMenu.create(player);

                                    XSound.play(player, "UI_BUTTON_CLICK");

                                    return true;
                                })
                                .build()
                ).openAction(event -> {
                    switch (user.getVisibility()) {
                        case ON:
                            event.getInventory().setItem(
                                    this.getItemSlot("visibility") + 9,
                                    ItemBuilder
                                            .newBuilder(
                                                    this.getItemMaterial("visibility.toggle.state-on"),
                                                    this.getItemAmount("visibility.toggle.state-on"),
                                                    this.getItemData("visibility.toggle.state-on")
                                            )
                                            .setName(this.getItemName(player, "visibility.toggle.state-on"))
                                            .setLore(this.getItemLore(player, "visibility.toggle.state-on"))
                                            .build()
                            );

                            break;
                        case ONLY_RANK:
                            event.getInventory().setItem(
                                    this.getItemSlot("visibility") + 9,
                                    ItemBuilder
                                            .newBuilder(
                                                    this.getItemMaterial("visibility.toggle.state-only-rank"),
                                                    this.getItemAmount("visibility.toggle.state-only-rank"),
                                                    this.getItemData("visibility.toggle.state-only-rank")
                                            )
                                            .setName(this.getItemName(player, "visibility.toggle.state-only-rank"))
                                            .setLore(this.getItemLore(player, "visibility.toggle.state-only-rank"))
                                            .build()
                            );

                            break;
                        case OFF:
                            event.getInventory().setItem(
                                    this.getItemSlot("visibility") + 9,
                                    ItemBuilder
                                            .newBuilder(
                                                    this.getItemMaterial("visibility.toggle.state-off"),
                                                    this.getItemAmount("visibility.toggle.state-off"),
                                                    this.getItemData("visibility.toggle.state-off")
                                            )
                                            .setName(this.getItemName(player, "visibility.toggle.state-off"))
                                            .setLore(this.getItemLore(player, "visibility.toggle.state-off"))
                                            .build()
                            );

                            break;
                        case NO_PERMISSION:
                            event.getInventory().setItem(
                                    this.getItemSlot("visibility") + 9,
                                    ItemBuilder
                                            .newBuilder(
                                                    this.getItemMaterial("visibility.toggle.state-no-permission"),
                                                    this.getItemAmount("visibility.toggle.state-no-permission"),
                                                    this.getItemData("visibility.toggle.state-no-permission")
                                            )
                                            .setName(this.getItemName(player, "visibility.toggle.state-no-permission"))
                                            .setLore(this.getItemLore(player, "visibility.toggle.state-no-permission"))
                                            .build()
                            );

                            break;
                    }

                    switch (user.getChat()) {
                        case ON:
                            event.getInventory().setItem(
                                    this.getItemSlot("chat") + 9,
                                    ItemBuilder
                                            .newBuilder(
                                                    this.getItemMaterial("chat.toggle.state-on"),
                                                    this.getItemAmount("chat.toggle.state-on"),
                                                    this.getItemData("chat.toggle.state-on")
                                            )
                                            .setName(this.getItemName(player, "chat.toggle.state-on"))
                                            .setLore(this.getItemLore(player, "chat.toggle.state-on"))
                                            .build()
                            );

                            break;
                        case OFF:
                            event.getInventory().setItem(
                                    this.getItemSlot("chat") + 9,
                                    ItemBuilder
                                            .newBuilder(
                                                    this.getItemMaterial("chat.toggle.state-off"),
                                                    this.getItemAmount("chat.toggle.state-off"),
                                                    this.getItemData("chat.toggle.state-off")
                                            )
                                            .setName(this.getItemName(player, "chat.toggle.state-off"))
                                            .setLore(this.getItemLore(player, "chat.toggle.state-off"))
                                            .build()
                            );

                            break;
                        case NO_PERMISSION:
                            event.getInventory().setItem(
                                    this.getItemSlot("chat") + 9,
                                    ItemBuilder
                                            .newBuilder(
                                                    this.getItemMaterial("chat.toggle.state-no-permission"),
                                                    this.getItemAmount("chat.toggle.state-no-permission"),
                                                    this.getItemData("chat.toggle.state-no-permission")
                                            )
                                            .setName(this.getItemName(player, "chat.toggle.state-no-permission"))
                                            .setLore(this.getItemLore(player, "chat.toggle.state-no-permission"))
                                            .build()
                            );

                            break;
                    }

                    switch (user.getDoubleJump()) {
                        case ON:
                            event.getInventory().setItem(
                                    this.getItemSlot("double-jump") + 9,
                                    ItemBuilder
                                            .newBuilder(
                                                    this.getItemMaterial("double-jump.toggle.state-on"),
                                                    this.getItemAmount("double-jump.toggle.state-on"),
                                                    this.getItemData("double-jump.toggle.state-on")
                                            )
                                            .setName(this.getItemName(player, "double-jump.toggle.state-on"))
                                            .setLore(this.getItemLore(player, "double-jump.toggle.state-on"))
                                            .build()
                            );

                            break;
                        case OFF:
                            event.getInventory().setItem(
                                    this.getItemSlot("double-jump") + 9,
                                    ItemBuilder
                                            .newBuilder(
                                                    this.getItemMaterial("double-jump.toggle.state-off"),
                                                    this.getItemAmount("double-jump.toggle.state-off"),
                                                    this.getItemData("double-jump.toggle.state-off")
                                            )
                                            .setName(this.getItemName(player, "double-jump.toggle.state-off"))
                                            .setLore(this.getItemLore(player, "double-jump.toggle.state-off"))
                                            .build()
                            );

                            break;
                        case NO_PERMISSION:
                            event.getInventory().setItem(
                                    this.getItemSlot("double-jump") + 9,
                                    ItemBuilder
                                            .newBuilder(
                                                    this.getItemMaterial("double-jump.toggle.state-no-permission"),
                                                    this.getItemAmount("double-jump.toggle.state-no-permission"),
                                                    this.getItemData("double-jump.toggle.state-no-permission")
                                            )
                                            .setName(this.getItemName(player, "double-jump.toggle.state-no-permission"))
                                            .setLore(this.getItemLore(player, "double-jump.toggle.state-no-permission"))
                                            .build()
                            );

                            break;
                    }

                    switch (user.getMount()) {
                        case ON:
                            event.getInventory().setItem(
                                    this.getItemSlot("mount") + 9,
                                    ItemBuilder
                                            .newBuilder(
                                                    this.getItemMaterial("mount.toggle.state-on"),
                                                    this.getItemAmount("mount.toggle.state-on"),
                                                    this.getItemData("mount.toggle.state-on")
                                            )
                                            .setName(this.getItemName(player, "mount.toggle.state-on"))
                                            .setLore(this.getItemLore(player, "mount.toggle.state-on"))
                                            .build()
                            );

                            break;
                        case OFF:
                            event.getInventory().setItem(
                                    this.getItemSlot("mount") + 9,
                                    ItemBuilder
                                            .newBuilder(
                                                    this.getItemMaterial("mount.toggle.state-off"),
                                                    this.getItemAmount("mount.toggle.state-off"),
                                                    this.getItemData("mount.toggle.state-off")
                                            )
                                            .setName(this.getItemName(player, "mount.toggle.state-off"))
                                            .setLore(this.getItemLore(player, "mount.toggle.state-off"))
                                            .build()
                            );

                            break;
                        case NO_PERMISSION:
                            event.getInventory().setItem(
                                    this.getItemSlot("mount") + 9,
                                    ItemBuilder
                                            .newBuilder(
                                                    this.getItemMaterial("mount.toggle.state-no-permission"),
                                                    this.getItemAmount("mount.toggle.state-no-permission"),
                                                    this.getItemData("mount.toggle.state-no-permission")
                                            )
                                            .setName(this.getItemName(player, "mount.toggle.state-no-permission"))
                                            .setLore(this.getItemLore(player, "mount.toggle.state-no-permission"))
                                            .build()
                            );

                            break;
                    }

                    switch (user.getFly()) {
                        case ON:
                            event.getInventory().setItem(
                                    this.getItemSlot("fly") + 9,
                                    ItemBuilder
                                            .newBuilder(
                                                    this.getItemMaterial("fly.toggle.state-on"),
                                                    this.getItemAmount("fly.toggle.state-on"),
                                                    this.getItemData("fly.toggle.state-on")
                                            )
                                            .setName(this.getItemName(player, "fly.toggle.state-on"))
                                            .setLore(this.getItemLore(player, "fly.toggle.state-on"))
                                            .build()
                            );

                            break;
                        case OFF:
                            event.getInventory().setItem(
                                    this.getItemSlot("fly") + 9,
                                    ItemBuilder
                                            .newBuilder(
                                                    this.getItemMaterial("fly.toggle.state-off"),
                                                    this.getItemAmount("fly.toggle.state-off"),
                                                    this.getItemData("fly.toggle.state-off")
                                            )
                                            .setName(this.getItemName(player, "fly.toggle.state-off"))
                                            .setLore(this.getItemLore(player, "fly.toggle.state-off"))
                                            .build()
                            );

                            break;
                        case NO_PERMISSION:
                            event.getInventory().setItem(
                                    this.getItemSlot("fly") + 9,
                                    ItemBuilder
                                            .newBuilder(
                                                    this.getItemMaterial("fly.toggle.state-no-permission"),
                                                    this.getItemAmount("fly.toggle.state-no-permission"),
                                                    this.getItemData("fly.toggle.state-no-permission")
                                            )
                                            .setName(this.getItemName(player, "fly.toggle.state-no-permission"))
                                            .setLore(this.getItemLore(player, "fly.toggle.state-no-permission"))
                                            .build()
                            );

                            break;
                    }

                    switch (user.getMessageJoin()) {
                        case ON:
                            event.getInventory().setItem(
                                    this.getItemSlot("message-join") + 9,
                                    ItemBuilder
                                            .newBuilder(
                                                    this.getItemMaterial("message-join.toggle.state-on"),
                                                    this.getItemAmount("message-join.toggle.state-on"),
                                                    this.getItemData("message-join.toggle.state-on")
                                            )
                                            .setName(this.getItemName(player, "message-join.toggle.state-on"))
                                            .setLore(this.getItemLore(player, "message-join.toggle.state-on"))
                                            .build()
                            );

                            break;
                        case OFF:
                            event.getInventory().setItem(
                                    this.getItemSlot("message-join") + 9,
                                    ItemBuilder
                                            .newBuilder(
                                                    this.getItemMaterial("message-join.toggle.state-off"),
                                                    this.getItemAmount("message-join.toggle.state-off"),
                                                    this.getItemData("message-join.toggle.state-off")
                                            )
                                            .setName(this.getItemName(player, "message-join.toggle.state-off"))
                                            .setLore(this.getItemLore(player, "message-join.toggle.state-off"))
                                            .build()
                            );

                            break;
                        case NO_PERMISSION:
                            event.getInventory().setItem(
                                    this.getItemSlot("message-join") + 9,
                                    ItemBuilder
                                            .newBuilder(
                                                    this.getItemMaterial("message-join.toggle.state-no-permission"),
                                                    this.getItemAmount("message-join.toggle.state-no-permission"),
                                                    this.getItemData("message-join.toggle.state-no-permission")
                                            )
                                            .setName(this.getItemName(player, "message-join.toggle.state-no-permission"))
                                            .setLore(this.getItemLore(player, "message-join.toggle.state-no-permission"))
                                            .build()
                            );

                            break;
                    }

                    switch (user.getEffectJoin()) {
                        case NOTHING:
                        case FIREWORK:
                        case ZEUS:
                        case SHEEP:
                            event.getInventory().setItem(
                                    this.getItemSlot("effect-join") + 9,
                                    ItemBuilder
                                            .newBuilder(
                                                    this.getItemMaterial("effect-join.toggle.state"),
                                                    this.getItemAmount("effect-join.toggle.state"),
                                                    this.getItemData("effect-join.toggle.state")
                                            )
                                            .setName(this.getItemName(player, "effect-join.toggle.state"))
                                            .setLore(this.getItemLore(player, "effect-join.toggle.state"))
                                            .build()
                            );

                            break;
                    }

                    return false;
                });

        return guiBuilder.build();
    }
}
