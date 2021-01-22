package io.github.srvenient.elegantoptions.plugin.menu.option;

import dev.srvenient.freya.abstraction.configuration.Configuration;
import dev.srvenient.freya.api.xseries.XSound;

import io.github.srvenient.elegantoptions.api.Enums;
import io.github.srvenient.elegantoptions.api.menu.MenuCreator;
import io.github.srvenient.elegantoptions.api.user.User;
import io.github.srvenient.elegantoptions.api.user.UserMatcher;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import team.unnamed.gui.abstraction.item.ItemClickable;
import team.unnamed.gui.core.gui.GUIBuilder;
import team.unnamed.gui.core.item.type.ItemBuilder;

import javax.inject.Inject;
import javax.inject.Named;

import static io.github.srvenient.elegantoptions.plugin.translation.Translation.colorize;
import static io.github.srvenient.elegantoptions.plugin.utils.InventoryUtils.*;

public class EffectMenu implements MenuCreator {

    @Inject private UserMatcher userMatcher;

    @Inject @Named("main-menu") private MenuCreator mainMenu;

    @Inject @Named("menus") private Configuration menus;
    @Inject @Named("language") private Configuration language;

    @Override
    public Inventory create(Player player) {
        User user = userMatcher.getUserId(player.getUniqueId());

        if (user == null) return null;

        GUIBuilder guiBuilder = GUIBuilder.builder(colorize(player, menus.getString("effect-menu.title")), menus.getInt("effect-menu.rows"))
                .addItem(
                        ItemClickable
                                .builder(getItemSlot(menus, "effect-menu", "nothing"))
                                .setItemStack(
                                        ItemBuilder
                                                .newBuilder(
                                                        getItemMaterial(menus, "effect-menu", "nothing"),
                                                        getItemAmount(menus, "effect-menu", "nothing"),
                                                        getItemData(menus, "effect-menu", "nothing")
                                                )
                                                .setName(getItemName(menus, player, "effect-menu", "nothing"))
                                                .setLore(getItemLore(menus, player, "effect-menu", "nothing"))
                                                .build()
                                )
                                .setAction(event -> {
                                    if (!player.hasPermission("elegantoptions.effect.nothing")) {
                                        XSound.play(player, "ENTITY_VILLAGER_NO");

                                        player.closeInventory();
                                        player.sendMessage(colorize(player, language.getString("nothing.no-permission")));

                                        return true;
                                    }

                                    if (user.getEffectJoin() != Enums.Effects.NOTHING) {
                                        event.getClickedInventory().setItem(
                                                getItemSlot(menus, "effect-menu","nothing"),
                                                ItemBuilder
                                                        .newBuilder(
                                                                getItemMaterial(menus, "effect-menu","nothing"),
                                                                getItemAmount(menus, "effect-menu","nothing"),
                                                                getItemData(menus, "effect-menu","nothing")
                                                        )
                                                        .setName(getItemName(menus, player, "effect-menu",  "nothing"))
                                                        .setLore(getItemLore(menus, player, "effect-menu", "nothing"))
                                                        .build()
                                        );

                                        if (user.getEffectJoin() == Enums.Effects.FIREWORK) {
                                            event.getClickedInventory().setItem(
                                                    getItemSlot(menus, "effect-menu","firework"),
                                                    ItemBuilder
                                                            .newBuilder(
                                                                    getItemMaterial(menus, "effect-menu","firework"),
                                                                    getItemAmount(menus, "effect-menu","firework"),
                                                                    getItemData(menus, "effect-menu","firework")
                                                            )
                                                            .setName(getItemName(menus, player, "effect-menu",  "firework"))
                                                            .setLore(getItemLore(menus, player, "effect-menu", "firework"))
                                                            .build()
                                            );

                                            event.getClickedInventory().setItem(
                                                    getItemSlot(menus, "effect-menu","firework") + 9,
                                                    ItemBuilder
                                                            .newBuilder(
                                                                    getItemMaterial(menus, "effect-menu","firework.toggle.state-off"),
                                                                    getItemAmount(menus, "effect-menu","firework.toggle.state-off"),
                                                                    getItemData(menus, "effect-menu","firework.toggle.state-off")
                                                            )
                                                            .setName(getItemName(menus, player, "effect-menu", "firework.toggle.state-off"))
                                                            .setLore(getItemLore(menus, player, "effect-menu", "firework.toggle.state-off"))
                                                            .build()
                                            );
                                        }

                                        if (user.getEffectJoin() == Enums.Effects.ZEUS) {
                                            event.getClickedInventory().setItem(
                                                    getItemSlot(menus, "effect-menu","zeus"),
                                                    ItemBuilder
                                                            .newBuilder(
                                                                    getItemMaterial(menus, "effect-menu","zeus"),
                                                                    getItemAmount(menus, "effect-menu","zeus"),
                                                                    getItemData(menus, "effect-menu","zeus")
                                                            )
                                                            .setName(getItemName(menus, player, "effect-menu", "zeus"))
                                                            .setLore(getItemLore(menus, player, "effect-menu", "zeus"))
                                                            .build()
                                            );

                                            event.getClickedInventory().setItem(
                                                    getItemSlot(menus, "effect-menu","zeus") + 9,
                                                    ItemBuilder
                                                            .newBuilder(
                                                                    getItemMaterial(menus, "effect-menu","zeus.toggle.state-off"),
                                                                    getItemAmount(menus, "effect-menu","zeus.toggle.state-off"),
                                                                    getItemData(menus, "effect-menu","zeus.toggle.state-off")
                                                            )
                                                            .setName(getItemName(menus, player, "effect-menu", "zeus.toggle.state-off"))
                                                            .setLore(getItemLore(menus, player, "effect-menu", "zeus.toggle.state-off"))
                                                            .build()
                                            );
                                        }


                                        if (user.getEffectJoin() == Enums.Effects.SHEEP) {
                                            event.getClickedInventory().setItem(
                                                    getItemSlot(menus, "effect-menu","sheeps"),
                                                    ItemBuilder
                                                            .newBuilder(
                                                                    getItemMaterial(menus, "effect-menu","sheeps"),
                                                                    getItemAmount(menus, "effect-menu","sheeps"),
                                                                    getItemData(menus, "effect-menu","sheeps")
                                                            )
                                                            .setName(getItemName(menus, player, "effect-menu", "sheeps"))
                                                            .setLore(getItemLore(menus, player, "effect-menu", "sheeps"))
                                                            .build()
                                            );

                                            event.getClickedInventory().setItem(
                                                    getItemSlot(menus, "effect-menu","sheeps") + 9,
                                                    ItemBuilder
                                                            .newBuilder(
                                                                    getItemMaterial(menus, "effect-menu","sheeps.toggle.state-off"),
                                                                    getItemAmount(menus, "effect-menu","sheeps.toggle.state-off"),
                                                                    getItemData(menus, "effect-menu","sheeps.toggle.state-off")
                                                            )
                                                            .setName(getItemName(menus, player, "effect-menu", "sheeps.toggle.state-off"))
                                                            .setLore(getItemLore(menus, player, "effect-menu", "sheeps.toggle.state-off"))
                                                            .build()
                                            );
                                        }

                                        user.setEffectJoin(Enums.Effects.NOTHING);
                                        XSound.play(player, "UI_BUTTON_CLICK");

                                        return true;
                                    }

                                    XSound.play(player, "ENTITY_VILLAGER_NO");

                                    player.closeInventory();
                                    player.sendMessage(colorize(player, language.getString("nothing.no-effect-select")));

                                    return true;
                                })
                                .build()
                ).addItem(
                        ItemClickable
                                .builder(getItemSlot(menus, "effect-menu","firework"))
                                .setItemStack(
                                        ItemBuilder
                                                .newBuilder(
                                                        getItemMaterial(menus, "effect-menu","firework"),
                                                        getItemAmount(menus, "effect-menu","firework"),
                                                        getItemData(menus, "effect-menu","firework")
                                                )
                                                .setName(getItemName(menus, player, "effect-menu", "firework"))
                                                .setLore(getItemLore(menus, player, "effect-menu", "firework"))
                                                .build()
                                )
                                .setAction(event -> {
                                    if (!player.hasPermission("elegantoptions.effect.firework")) {
                                        XSound.play(player, "ENTITY_VILLAGER_NO");

                                        player.closeInventory();
                                        player.sendMessage(colorize(player, language.getString("firework.no-permission")));

                                        return true;
                                    }

                                    if (user.getEffectJoin() == Enums.Effects.FIREWORK) {
                                        XSound.play(player, "ENTITY_VILLAGER_NO");

                                        player.closeInventory();
                                        player.sendMessage(colorize(player, language.getString("firework.already-select")));

                                        return true;
                                    }

                                    event.getClickedInventory().setItem(
                                            getItemSlot(menus, "effect-menu","firework"),
                                            ItemBuilder
                                                    .newBuilder(
                                                            getItemMaterial(menus, "effect-menu","firework"),
                                                            getItemAmount(menus, "effect-menu","firework"),
                                                            getItemData(menus, "effect-menu","firework")
                                                    )
                                                    .setName(getItemName(menus, player, "effect-menu", "firework"))
                                                    .setLore(getItemLore(menus, player, "effect-menu", "firework"))
                                                    .build()
                                    );

                                    event.getClickedInventory().setItem(
                                            getItemSlot(menus, "effect-menu","firework") + 9,
                                            ItemBuilder
                                                    .newBuilder(
                                                            getItemMaterial(menus, "effect-menu","firework.toggle.state-on"),
                                                            getItemAmount(menus, "effect-menu","firework.toggle.state-on"),
                                                            getItemData(menus, "effect-menu","firework.toggle.state-on")
                                                    )
                                                    .setName(getItemName(menus, player, "effect-menu", "firework.toggle.state-on"))
                                                    .setLore(getItemLore(menus, player, "effect-menu", "firework.toggle.state-on"))
                                                    .build()
                                    );

                                    if (user.getEffectJoin() == Enums.Effects.ZEUS) {
                                        event.getClickedInventory().setItem(
                                                getItemSlot(menus, "effect-menu","zeus"),
                                                ItemBuilder
                                                        .newBuilder(
                                                                getItemMaterial(menus, "effect-menu","zeus"),
                                                                getItemAmount(menus, "effect-menu","zeus"),
                                                                getItemData(menus, "effect-menu","zeus")
                                                        )
                                                        .setName(getItemName(menus, player, "effect-menu", "zeus"))
                                                        .setLore(getItemLore(menus, player, "effect-menu", "zeus"))
                                                        .build()
                                        );

                                        event.getClickedInventory().setItem(
                                                getItemSlot(menus, "effect-menu","zeus") + 9,
                                                ItemBuilder
                                                        .newBuilder(
                                                                getItemMaterial(menus, "effect-menu","zeus.toggle.state-off"),
                                                                getItemAmount(menus, "effect-menu","zeus.toggle.state-off"),
                                                                getItemData(menus, "effect-menu","zeus.toggle.state-off")
                                                        )
                                                        .setName(getItemName(menus, player, "effect-menu", "zeus.toggle.state-off"))
                                                        .setLore(getItemLore(menus, player, "effect-menu", "zeus.toggle.state-off"))
                                                        .build()
                                        );
                                    }


                                    if (user.getEffectJoin() == Enums.Effects.SHEEP) {
                                        event.getClickedInventory().setItem(
                                                getItemSlot(menus, "effect-menu","sheeps"),
                                                ItemBuilder
                                                        .newBuilder(
                                                                getItemMaterial(menus, "effect-menu","sheeps"),
                                                                getItemAmount(menus, "effect-menu","sheeps"),
                                                                getItemData(menus, "effect-menu","sheeps")
                                                        )
                                                        .setName(getItemName(menus, player, "effect-menu", "sheeps"))
                                                        .setLore(getItemLore(menus, player, "effect-menu", "sheeps"))
                                                        .build()
                                        );

                                        event.getClickedInventory().setItem(
                                                getItemSlot(menus, "effect-menu","sheeps") + 9,
                                                ItemBuilder
                                                        .newBuilder(
                                                                getItemMaterial(menus, "effect-menu","sheeps.toggle.state-off"),
                                                                getItemAmount(menus, "effect-menu","sheeps.toggle.state-off"),
                                                                getItemData(menus, "effect-menu","sheeps.toggle.state-off")
                                                        )
                                                        .setName(getItemName(menus, player, "effect-menu", "sheeps.toggle.state-off"))
                                                        .setLore(getItemLore(menus, player, "effect-menu", "sheeps.toggle.state-off"))
                                                        .build()
                                        );
                                    }

                                    user.setEffectJoin(Enums.Effects.FIREWORK);
                                    XSound.play(player, "UI_BUTTON_CLICK");

                                    return true;
                                })
                                .build()
                ).addItem(
                        ItemClickable
                                .builder(getItemSlot(menus, "effect-menu","zeus"))
                                .setItemStack(
                                        ItemBuilder
                                                .newBuilder(
                                                        getItemMaterial(menus, "effect-menu","zeus"),
                                                        getItemAmount(menus, "effect-menu","zeus"),
                                                        getItemData(menus, "effect-menu","zeus")
                                                )
                                                .setName(getItemName(menus, player, "effect-menu", "zeus"))
                                                .setLore(getItemLore(menus, player, "effect-menu", "zeus"))
                                                .build()
                                )
                                .setAction(event -> {
                                    if (!player.hasPermission("elegantoptions.effect.zeus")) {
                                        XSound.play(player, "ENTITY_VILLAGER_NO");

                                        player.closeInventory();
                                        player.sendMessage(colorize(player, language.getString("zeus.no-permission")));

                                        return true;
                                    }

                                    if (user.getEffectJoin() == Enums.Effects.ZEUS) {
                                        XSound.play(player, "ENTITY_VILLAGER_NO");

                                        player.closeInventory();
                                        player.sendMessage(colorize(player, language.getString("zeus.already-select")));

                                        return true;
                                    }

                                    event.getClickedInventory().setItem(
                                            getItemSlot(menus, "effect-menu","zeus"),
                                            ItemBuilder
                                                    .newBuilder(
                                                            getItemMaterial(menus, "effect-menu","zeus"),
                                                            getItemAmount(menus, "effect-menu","zeus"),
                                                            getItemData(menus, "effect-menu","zeus")
                                                    )
                                                    .setName(getItemName(menus, player, "effect-menu", "zeus"))
                                                    .setLore(getItemLore(menus, player, "effect-menu", "zeus"))
                                                    .build()
                                    );

                                    event.getClickedInventory().setItem(
                                            getItemSlot(menus, "effect-menu","zeus") + 9,
                                            ItemBuilder
                                                    .newBuilder(
                                                            getItemMaterial(menus, "effect-menu","zeus.toggle.state-on"),
                                                            getItemAmount(menus, "effect-menu","zeus.toggle.state-on"),
                                                            getItemData(menus, "effect-menu","zeus.toggle.state-on")
                                                    )
                                                    .setName(getItemName(menus, player, "effect-menu", "zeus.toggle.state-on"))
                                                    .setLore(getItemLore(menus, player, "effect-menu", "zeus.toggle.state-on"))
                                                    .build()
                                    );

                                    if (user.getEffectJoin() == Enums.Effects.FIREWORK) {
                                        event.getClickedInventory().setItem(
                                                getItemSlot(menus, "effect-menu","firework"),
                                                ItemBuilder
                                                        .newBuilder(
                                                                getItemMaterial(menus, "effect-menu","firework"),
                                                                getItemAmount(menus, "effect-menu","firework"),
                                                                getItemData(menus, "effect-menu","firework")
                                                        )
                                                        .setName(getItemName(menus, player, "effect-menu", "firework"))
                                                        .setLore(getItemLore(menus, player, "effect-menu", "firework"))
                                                        .build()
                                        );

                                        event.getClickedInventory().setItem(
                                                getItemSlot(menus, "effect-menu","firework") + 9,
                                                ItemBuilder
                                                        .newBuilder(
                                                                getItemMaterial(menus, "effect-menu","firework.toggle.state-off"),
                                                                getItemAmount(menus, "effect-menu","firework.toggle.state-off"),
                                                                getItemData(menus, "effect-menu","firework.toggle.state-off")
                                                        )
                                                        .setName(getItemName(menus, player, "effect-menu", "firework.toggle.state-off"))
                                                        .setLore(getItemLore(menus, player, "effect-menu", "firework.toggle.state-off"))
                                                        .build()
                                        );
                                    }


                                    if (user.getEffectJoin() == Enums.Effects.SHEEP) {
                                        event.getClickedInventory().setItem(
                                                getItemSlot(menus, "effect-menu","sheeps"),
                                                ItemBuilder
                                                        .newBuilder(
                                                                getItemMaterial(menus, "effect-menu","sheeps"),
                                                                getItemAmount(menus, "effect-menu","sheeps"),
                                                                getItemData(menus, "effect-menu","sheeps")
                                                        )
                                                        .setName(getItemName(menus, player, "effect-menu", "sheeps"))
                                                        .setLore(getItemLore(menus, player, "effect-menu", "sheeps"))
                                                        .build()
                                        );

                                        event.getClickedInventory().setItem(
                                                getItemSlot(menus, "effect-menu","sheeps") + 9,
                                                ItemBuilder
                                                        .newBuilder(
                                                                getItemMaterial(menus, "effect-menu","sheeps.toggle.state-off"),
                                                                getItemAmount(menus, "effect-menu","sheeps.toggle.state-off"),
                                                                getItemData(menus, "effect-menu","sheeps.toggle.state-off")
                                                        )
                                                        .setName(getItemName(menus, player, "effect-menu", "sheeps.toggle.state-off"))
                                                        .setLore(getItemLore(menus, player, "effect-menu", "sheeps.toggle.state-off"))
                                                        .build()
                                        );
                                    }

                                    user.setEffectJoin(Enums.Effects.ZEUS);
                                    XSound.play(player, "UI_BUTTON_CLICK");

                                    return true;
                                })
                                .build()
                ).addItem(
                        ItemClickable
                                .builder(getItemSlot(menus, "effect-menu","sheeps"))
                                .setItemStack(
                                        ItemBuilder
                                                .newBuilder(
                                                        getItemMaterial(menus, "effect-menu","sheeps"),
                                                        getItemAmount(menus, "effect-menu","sheeps"),
                                                        getItemData(menus, "effect-menu","sheeps")
                                                )
                                                .setName(getItemName(menus, player, "effect-menu", "sheeps"))
                                                .setLore(getItemLore(menus, player, "effect-menu", "sheeps"))
                                                .build()
                                )
                                .setAction(event -> {
                                    if (!player.hasPermission("elegantoptions.effect.sheeps")) {
                                        XSound.play(player, "ENTITY_VILLAGER_NO");

                                        player.closeInventory();
                                        player.sendMessage(colorize(player, language.getString("sheeps.no-permission")));

                                        return true;
                                    }

                                    if (user.getEffectJoin() == Enums.Effects.SHEEP) {
                                        XSound.play(player, "ENTITY_VILLAGER_NO");

                                        player.closeInventory();
                                        player.sendMessage(colorize(player, language.getString("sheeps.already-select")));

                                        return true;
                                    }

                                    event.getClickedInventory().setItem(
                                            getItemSlot(menus, "effect-menu","sheeps"),
                                            ItemBuilder
                                                    .newBuilder(
                                                            getItemMaterial(menus, "effect-menu","sheeps"),
                                                            getItemAmount(menus, "effect-menu","sheeps"),
                                                            getItemData(menus, "effect-menu","sheeps")
                                                    )
                                                    .setName(getItemName(menus, player, "effect-menu", "sheeps"))
                                                    .setLore(getItemLore(menus, player, "effect-menu", "sheeps"))
                                                    .build()
                                    );

                                    event.getClickedInventory().setItem(
                                            getItemSlot(menus, "effect-menu","sheeps") + 9,
                                            ItemBuilder
                                                    .newBuilder(
                                                            getItemMaterial(menus, "effect-menu","sheeps.toggle.state-on"),
                                                            getItemAmount(menus, "effect-menu","sheeps.toggle.state-on"),
                                                            getItemData(menus, "effect-menu","sheeps.toggle.state-on")
                                                    )
                                                    .setName(getItemName(menus, player, "effect-menu", "sheeps.toggle.state-on"))
                                                    .setLore(getItemLore(menus, player, "effect-menu", "sheeps.toggle.state-on"))
                                                    .build()
                                    );

                                    if (user.getEffectJoin() == Enums.Effects.FIREWORK) {
                                        event.getClickedInventory().setItem(
                                                getItemSlot(menus, "effect-menu","firework"),
                                                ItemBuilder
                                                        .newBuilder(
                                                                getItemMaterial(menus, "effect-menu","firework"),
                                                                getItemAmount(menus, "effect-menu","firework"),
                                                                getItemData(menus, "effect-menu","firework")
                                                        )
                                                        .setName(getItemName(menus, player, "effect-menu", "firework"))
                                                        .setLore(getItemLore(menus, player, "effect-menu", "firework"))
                                                        .build()
                                        );

                                        event.getClickedInventory().setItem(
                                                getItemSlot(menus, "effect-menu","firework") + 9,
                                                ItemBuilder
                                                        .newBuilder(
                                                                getItemMaterial(menus, "effect-menu","firework.toggle.state-off"),
                                                                getItemAmount(menus, "effect-menu","firework.toggle.state-off"),
                                                                getItemData(menus, "effect-menu","firework.toggle.state-off")
                                                        )
                                                        .setName(getItemName(menus, player, "effect-menu", "firework.toggle.state-off"))
                                                        .setLore(getItemLore(menus, player, "effect-menu", "firework.toggle.state-off"))
                                                        .build()
                                        );
                                    }


                                    if (user.getEffectJoin() == Enums.Effects.ZEUS) {
                                        event.getClickedInventory().setItem(
                                                getItemSlot(menus, "effect-menu","zeus"),
                                                ItemBuilder
                                                        .newBuilder(
                                                                getItemMaterial(menus, "effect-menu","zeus"),
                                                                getItemAmount(menus, "effect-menu","zeus"),
                                                                getItemData(menus, "effect-menu","zeus")
                                                        )
                                                        .setName(getItemName(menus, player, "effect-menu", "zeus"))
                                                        .setLore(getItemLore(menus, player, "effect-menu", "zeus"))
                                                        .build()
                                        );

                                        event.getClickedInventory().setItem(
                                                getItemSlot(menus, "effect-menu","zeus") + 9,
                                                ItemBuilder
                                                        .newBuilder(
                                                                getItemMaterial(menus, "effect-menu","zeus.toggle.state-off"),
                                                                getItemAmount(menus, "effect-menu","zeus.toggle.state-off"),
                                                                getItemData(menus, "effect-menu","zeus.toggle.state-off")
                                                        )
                                                        .setName(getItemName(menus, player, "effect-menu", "zeus.toggle.state-off"))
                                                        .setLore(getItemLore(menus, player, "effect-menu", "zeus.toggle.state-off"))
                                                        .build()
                                        );
                                    }

                                    user.setEffectJoin(Enums.Effects.SHEEP);
                                    XSound.play(player, "UI_BUTTON_CLICK");

                                    return true;
                                })
                                .build()
                ).addItem(
                        ItemClickable
                                .builder(getItemSlot(menus, "effect-menu","back"))
                                .setItemStack(
                                        ItemBuilder
                                                .newBuilder(
                                                        getItemMaterial(menus, "effect-menu","back"),
                                                        getItemAmount(menus, "effect-menu","back"),
                                                        getItemData(menus, "effect-menu","back")
                                                )
                                                .setName(getItemName(menus, player, "effect-menu", "back"))
                                                .setLore(getItemLore(menus, player, "effect-menu", "back"))
                                                .build()
                                )
                                .setAction(event -> {
                                    XSound.play(player, "UI_BUTTON_CLICK");

                                    player.openInventory(mainMenu.create(player));

                                    return true;
                                })
                                .build()
                ).addItem(
                        ItemClickable
                                .builder(getItemSlot(menus, "effect-menu","close"))
                                .setItemStack(
                                        ItemBuilder
                                                .newBuilder(
                                                        getItemMaterial(menus, "effect-menu","close"),
                                                        getItemAmount(menus, "effect-menu","close"),
                                                        getItemData(menus, "effect-menu","close")
                                                )
                                                .setName(getItemName(menus, player, "effect-menu", "close"))
                                                .setLore(getItemLore(menus, player, "effect-menu", "close"))
                                                .build()
                                )
                                .setAction(event -> {
                                    XSound.play(player, "BLOCK_CHEST_CLOSE");

                                    player.closeInventory();

                                    return true;
                                })
                                .build()
                ).openAction(event -> {
                    if (user.getEffectJoin() == Enums.Effects.NOTHING) {
                        event.getInventory().setItem(
                                getItemSlot(menus, "effect-menu","firework") + 9,
                                ItemBuilder
                                        .newBuilder(
                                                getItemMaterial(menus, "effect-menu","firework.toggle.state-off"),
                                                getItemAmount(menus, "effect-menu","firework.toggle.state-off"),
                                                getItemData(menus, "effect-menu","firework.toggle.state-off")
                                        )
                                        .setName(getItemName(menus, player, "effect-menu", "firework.toggle.state-off"))
                                        .setLore(getItemLore(menus, player, "effect-menu", "firework.toggle.state-off"))
                                        .build()
                        );

                        event.getInventory().setItem(
                                getItemSlot(menus, "effect-menu","zeus") + 9,
                                ItemBuilder
                                        .newBuilder(
                                                getItemMaterial(menus, "effect-menu","zeus.toggle.state-off"),
                                                getItemAmount(menus, "effect-menu","zeus.toggle.state-off"),
                                                getItemData(menus, "effect-menu","zeus.toggle.state-off")
                                        )
                                        .setName(getItemName(menus, player, "effect-menu", "zeus.toggle.state-off"))
                                        .setLore(getItemLore(menus, player, "effect-menu", "zeus.toggle.state-off"))
                                        .build()
                        );

                        event.getInventory().setItem(
                                getItemSlot(menus, "effect-menu","sheeps") + 9,
                                ItemBuilder
                                        .newBuilder(
                                                getItemMaterial(menus, "effect-menu","sheeps.toggle.state-off"),
                                                getItemAmount(menus, "effect-menu","sheeps.toggle.state-off"),
                                                getItemData(menus, "effect-menu","sheeps.toggle.state-off")
                                        )
                                        .setName(getItemName(menus, player, "effect-menu", "sheeps.toggle.state-off"))
                                        .setLore(getItemLore(menus, player, "effect-menu", "sheeps.toggle.state-off"))
                                        .build()
                        );
                    }

                    if (player.hasPermission("elegantoptions.effect.firework")) {
                        if (user.getEffectJoin() == Enums.Effects.FIREWORK) {
                            event.getInventory().setItem(
                                    getItemSlot(menus, "effect-menu","firework") + 9,
                                    ItemBuilder
                                            .newBuilder(
                                                    getItemMaterial(menus, "effect-menu","firework.toggle.state-on"),
                                                    getItemAmount(menus, "effect-menu","firework.toggle.state-on"),
                                                    getItemData(menus, "effect-menu","firework.toggle.state-on")
                                            )
                                            .setName(getItemName(menus, player, "effect-menu", "firework.toggle.state-on"))
                                            .setLore(getItemLore(menus, player, "effect-menu", "firework.toggle.state-on"))
                                            .build()
                            );
                        } else {
                            event.getInventory().setItem(
                                    getItemSlot(menus, "effect-menu","firework") + 9,
                                    ItemBuilder
                                            .newBuilder(
                                                    getItemMaterial(menus, "effect-menu","firework.toggle.state-off"),
                                                    getItemAmount(menus, "effect-menu","firework.toggle.state-off"),
                                                    getItemData(menus, "effect-menu","firework.toggle.state-off")
                                            )
                                            .setName(getItemName(menus, player, "effect-menu", "firework.toggle.state-off"))
                                            .setLore(getItemLore(menus, player, "effect-menu", "firework.toggle.state-off"))
                                            .build()
                            );
                        }
                    } else {
                        event.getInventory().setItem(
                                getItemSlot(menus, "effect-menu","firework") + 9,
                                ItemBuilder
                                        .newBuilder(
                                                getItemMaterial(menus, "effect-menu","firework.toggle.state-no-permission"),
                                                getItemAmount(menus, "effect-menu","firework.toggle.state-no-permission"),
                                                getItemData(menus, "effect-menu","firework.toggle.state-no-permission")
                                        )
                                        .setName(getItemName(menus, player, "effect-menu", "firework.toggle.state-no-permission"))
                                        .setLore(getItemLore(menus, player, "effect-menu", "firework.toggle.state-no-permission"))
                                        .build()
                        );
                    }

                    if (player.hasPermission("elegantoptions.effect.zeus")) {
                        if (user.getEffectJoin() == Enums.Effects.ZEUS) {
                            event.getInventory().setItem(
                                    getItemSlot(menus, "effect-menu","zeus") + 9,
                                    ItemBuilder
                                            .newBuilder(
                                                    getItemMaterial(menus, "effect-menu","zeus.toggle.state-on"),
                                                    getItemAmount(menus, "effect-menu","zeus.toggle.state-on"),
                                                    getItemData(menus, "effect-menu","zeus.toggle.state-on")
                                            )
                                            .setName(getItemName(menus, player, "effect-menu", "zeus.toggle.state-on"))
                                            .setLore(getItemLore(menus, player, "effect-menu", "zeus.toggle.state-on"))
                                            .build()
                            );
                        } else {
                            event.getInventory().setItem(
                                    getItemSlot(menus, "effect-menu","zeus") + 9,
                                    ItemBuilder
                                            .newBuilder(
                                                    getItemMaterial(menus, "effect-menu","zeus.toggle.state-off"),
                                                    getItemAmount(menus, "effect-menu","zeus.toggle.state-off"),
                                                    getItemData(menus, "effect-menu","zeus.toggle.state-off")
                                            )
                                            .setName(getItemName(menus, player, "effect-menu", "zeus.toggle.state-off"))
                                            .setLore(getItemLore(menus, player, "effect-menu", "zeus.toggle.state-off"))
                                            .build()
                            );
                        }
                    } else {
                        event.getInventory().setItem(
                                getItemSlot(menus, "effect-menu","zeus") + 9,
                                ItemBuilder
                                        .newBuilder(
                                                getItemMaterial(menus, "effect-menu","zeus.toggle.state-no-permission"),
                                                getItemAmount(menus, "effect-menu","zeus.toggle.state-no-permission"),
                                                getItemData(menus, "effect-menu","zeus.toggle.state-no-permission")
                                        )
                                        .setName(getItemName(menus, player, "effect-menu", "zeus.toggle.state-no-permission"))
                                        .setLore(getItemLore(menus, player, "effect-menu", "zeus.toggle.state-no-permission"))
                                        .build()
                        );
                    }

                    if (player.hasPermission("elegantoptions.effect.sheeps")) {
                        if (user.getEffectJoin() == Enums.Effects.SHEEP) {
                            event.getInventory().setItem(
                                    getItemSlot(menus, "effect-menu","sheeps") + 9,
                                    ItemBuilder
                                            .newBuilder(
                                                    getItemMaterial(menus, "effect-menu","sheeps.toggle.state-on"),
                                                    getItemAmount(menus, "effect-menu","sheeps.toggle.state-on"),
                                                    getItemData(menus, "effect-menu","sheeps.toggle.state-on")
                                            )
                                            .setName(getItemName(menus, player, "effect-menu", "sheeps.toggle.state-on"))
                                            .setLore(getItemLore(menus, player, "effect-menu", "sheeps.toggle.state-on"))
                                            .build()
                            );
                        } else {
                            event.getInventory().setItem(
                                    getItemSlot(menus, "effect-menu","sheeps") + 9,
                                    ItemBuilder
                                            .newBuilder(
                                                    getItemMaterial(menus, "effect-menu","sheeps.toggle.state-off"),
                                                    getItemAmount(menus, "effect-menu","sheeps.toggle.state-off"),
                                                    getItemData(menus, "effect-menu","sheeps.toggle.state-off")
                                            )
                                            .setName(getItemName(menus, player, "effect-menu", "sheeps.toggle.state-off"))
                                            .setLore(getItemLore(menus, player, "effect-menu", "sheeps.toggle.state-off"))
                                            .build()
                            );
                        }
                    } else {
                        event.getInventory().setItem(
                                getItemSlot(menus, "effect-menu","sheeps") + 9,
                                ItemBuilder
                                        .newBuilder(
                                                getItemMaterial(menus, "effect-menu","sheeps.toggle.state-no-permission"),
                                                getItemAmount(menus, "effect-menu","sheeps.toggle.state-no-permission"),
                                                getItemData(menus, "effect-menu","sheeps.toggle.state-no-permission")
                                        )
                                        .setName(getItemName(menus, player, "effect-menu", "sheeps.toggle.state-no-permission"))
                                        .setLore(getItemLore(menus, player, "effect-menu", "sheeps.toggle.state-no-permission"))
                                        .build()
                        );
                    }

                    return false;
                });

        return guiBuilder.build();
    }
}