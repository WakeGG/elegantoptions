package io.github.srvenient.elegantoptions.plugin.menu.option;

import dev.srvenient.freya.abstraction.configuration.Configuration;
import dev.srvenient.freya.api.xseries.XSound;

import io.github.srvenient.elegantoptions.api.Enums;
import io.github.srvenient.elegantoptions.api.user.User;
import io.github.srvenient.elegantoptions.api.user.UserMatcher;
import io.github.srvenient.elegantoptions.plugin.menu.MenuCreator;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import team.unnamed.gui.abstraction.item.ItemClickable;
import team.unnamed.gui.core.gui.GUIBuilder;
import team.unnamed.gui.core.item.type.ItemBuilder;

import javax.inject.Inject;
import javax.inject.Named;

import static io.github.srvenient.elegantoptions.plugin.translation.Translation.colorize;

public class EffectMenu extends MenuCreator {

    @Inject private UserMatcher userMatcher;

    @Inject @Named("language") private Configuration language;

    protected EffectMenu() {
        super("effect-menu");
    }

    @Override
    public Inventory create(Player player) {
        User user = userMatcher.getUserId(player.getUniqueId());

        if (user == null) return null;

        GUIBuilder guiBuilder = GUIBuilder.builder(this.getTitle(player), this.getRows())
                .addItem(
                        ItemClickable
                                .builder(this.getItemSlot("nothing"))
                                .setItemStack(
                                        ItemBuilder
                                                .newBuilder(
                                                        this.getItemMaterial("nothing"),
                                                        this.getItemAmount("nothing"),
                                                        this.getItemData("nothing")
                                                )
                                                .setName(this.getItemName(player, "nothing"))
                                                .setLore(this.getItemLore(player, "nothing"))
                                                .build()
                                )
                                .setAction(event -> {
                                    if (!player.hasPermission("elegantoptions.effect.nothing")) {
                                        player.closeInventory();
                                        player.sendMessage(colorize(player, language.getString("nothing.no-permission")));

                                        return true;
                                    }

                                    if (user.getEffectJoin() != Enums.Effects.NOTHING) {
                                        event.getClickedInventory().setItem(
                                                this.getItemSlot("nothing"),
                                                ItemBuilder
                                                        .newBuilder(
                                                                this.getItemMaterial("nothing"),
                                                                this.getItemAmount("nothing"),
                                                                this.getItemData("nothing")
                                                        )
                                                        .setName(this.getItemName(player, "nothing"))
                                                        .setLore(this.getItemLore(player, "nothing"))
                                                        .build()
                                        );

                                        if (user.getEffectJoin() == Enums.Effects.FIREWORK) {
                                            event.getClickedInventory().setItem(
                                                    this.getItemSlot("firework"),
                                                    ItemBuilder
                                                            .newBuilder(
                                                                    this.getItemMaterial("firework"),
                                                                    this.getItemAmount("firework"),
                                                                    this.getItemData("firework")
                                                            )
                                                            .setName(this.getItemName(player, "firework"))
                                                            .setLore(this.getItemLore(player, "firework"))
                                                            .build()
                                            );

                                            event.getClickedInventory().setItem(
                                                    this.getItemSlot("firework") + 9,
                                                    ItemBuilder
                                                            .newBuilder(
                                                                    this.getItemMaterial("firework.toggle.state-off"),
                                                                    this.getItemAmount("firework.toggle.state-off"),
                                                                    this.getItemData("firework.toggle.state-off")
                                                            )
                                                            .setName(this.getItemName(player, "firework.toggle.state-off"))
                                                            .setLore(this.getItemLore(player, "firework.toggle.state-off"))
                                                            .build()
                                            );
                                        }

                                        if (user.getEffectJoin() == Enums.Effects.ZEUS) {
                                            event.getClickedInventory().setItem(
                                                    this.getItemSlot("zeus"),
                                                    ItemBuilder
                                                            .newBuilder(
                                                                    this.getItemMaterial("zeus"),
                                                                    this.getItemAmount("zeus"),
                                                                    this.getItemData("zeus")
                                                            )
                                                            .setName(this.getItemName(player, "zeus"))
                                                            .setLore(this.getItemLore(player, "zeus"))
                                                            .build()
                                            );

                                            event.getClickedInventory().setItem(
                                                    this.getItemSlot("zeus") + 9,
                                                    ItemBuilder
                                                            .newBuilder(
                                                                    this.getItemMaterial("zeus.toggle.state-off"),
                                                                    this.getItemAmount("zeus.toggle.state-off"),
                                                                    this.getItemData("zeus.toggle.state-off")
                                                            )
                                                            .setName(this.getItemName(player, "zeus.toggle.state-off"))
                                                            .setLore(this.getItemLore(player, "zeus.toggle.state-off"))
                                                            .build()
                                            );
                                        }


                                        if (user.getEffectJoin() == Enums.Effects.SHEEP) {
                                            event.getClickedInventory().setItem(
                                                    this.getItemSlot("sheeps"),
                                                    ItemBuilder
                                                            .newBuilder(
                                                                    this.getItemMaterial("sheeps"),
                                                                    this.getItemAmount("sheeps"),
                                                                    this.getItemData("sheeps")
                                                            )
                                                            .setName(this.getItemName(player, "sheeps"))
                                                            .setLore(this.getItemLore(player, "sheeps"))
                                                            .build()
                                            );

                                            event.getClickedInventory().setItem(
                                                    this.getItemSlot("sheeps") + 9,
                                                    ItemBuilder
                                                            .newBuilder(
                                                                    this.getItemMaterial("sheeps.toggle.state-off"),
                                                                    this.getItemAmount("sheeps.toggle.state-off"),
                                                                    this.getItemData("sheeps.toggle.state-off")
                                                            )
                                                            .setName(this.getItemName(player, "sheeps.toggle.state-off"))
                                                            .setLore(this.getItemLore(player, "sheeps.toggle.state-off"))
                                                            .build()
                                            );
                                        }

                                        user.setEffectJoin(Enums.Effects.NOTHING);
                                        XSound.play(player, "UI_BUTTON_CLICK");

                                        return true;
                                    }

                                    player.closeInventory();
                                    player.sendMessage(colorize(player, language.getString("nothing.no-select-effects")));

                                    return true;
                                })
                                .build()
                ).addItem(
                        ItemClickable
                                .builder(this.getItemSlot("firework"))
                                .setItemStack(
                                        ItemBuilder
                                                .newBuilder(
                                                        this.getItemMaterial("firework"),
                                                        this.getItemAmount("firework"),
                                                        this.getItemData("firework")
                                                )
                                                .setName(this.getItemName(player, "firework"))
                                                .setLore(this.getItemLore(player, "firework"))
                                                .build()
                                )
                                .setAction(event -> {
                                    if (!player.hasPermission("elegantoptions.effect.firework")) {
                                        player.closeInventory();
                                        player.sendMessage(colorize(player, language.getString("firework.no-permission")));

                                        return true;
                                    }

                                    if (user.getEffectJoin() == Enums.Effects.FIREWORK) {
                                        player.closeInventory();
                                        player.sendMessage(colorize(player, language.getString("firework.already-select")));

                                        return true;
                                    }

                                    event.getClickedInventory().setItem(
                                            this.getItemSlot("firework"),
                                            ItemBuilder
                                                    .newBuilder(
                                                            this.getItemMaterial("firework"),
                                                            this.getItemAmount("firework"),
                                                            this.getItemData("firework")
                                                    )
                                                    .setName(this.getItemName(player, "firework"))
                                                    .setLore(this.getItemLore(player, "firework"))
                                                    .build()
                                    );

                                    event.getClickedInventory().setItem(
                                            this.getItemSlot("firework") + 9,
                                            ItemBuilder
                                                    .newBuilder(
                                                            this.getItemMaterial("firework.toggle.state-on"),
                                                            this.getItemAmount("firework.toggle.state-on"),
                                                            this.getItemData("firework.toggle.state-on")
                                                    )
                                                    .setName(this.getItemName(player, "firework.toggle.state-on"))
                                                    .setLore(this.getItemLore(player, "firework.toggle.state-on"))
                                                    .build()
                                    );

                                    if (user.getEffectJoin() == Enums.Effects.ZEUS) {
                                        event.getClickedInventory().setItem(
                                                this.getItemSlot("zeus"),
                                                ItemBuilder
                                                        .newBuilder(
                                                                this.getItemMaterial("zeus"),
                                                                this.getItemAmount("zeus"),
                                                                this.getItemData("zeus")
                                                        )
                                                        .setName(this.getItemName(player, "zeus"))
                                                        .setLore(this.getItemLore(player, "zeus"))
                                                        .build()
                                        );

                                        event.getClickedInventory().setItem(
                                                this.getItemSlot("zeus") + 9,
                                                ItemBuilder
                                                        .newBuilder(
                                                                this.getItemMaterial("zeus.toggle.state-off"),
                                                                this.getItemAmount("zeus.toggle.state-off"),
                                                                this.getItemData("zeus.toggle.state-off")
                                                        )
                                                        .setName(this.getItemName(player, "zeus.toggle.state-off"))
                                                        .setLore(this.getItemLore(player, "zeus.toggle.state-off"))
                                                        .build()
                                        );
                                    }


                                    if (user.getEffectJoin() == Enums.Effects.SHEEP) {
                                        event.getClickedInventory().setItem(
                                                this.getItemSlot("sheeps"),
                                                ItemBuilder
                                                        .newBuilder(
                                                                this.getItemMaterial("sheeps"),
                                                                this.getItemAmount("sheeps"),
                                                                this.getItemData("sheeps")
                                                        )
                                                        .setName(this.getItemName(player, "sheeps"))
                                                        .setLore(this.getItemLore(player, "sheeps"))
                                                        .build()
                                        );

                                        event.getClickedInventory().setItem(
                                                this.getItemSlot("sheeps") + 9,
                                                ItemBuilder
                                                        .newBuilder(
                                                                this.getItemMaterial("sheeps.toggle.state-off"),
                                                                this.getItemAmount("sheeps.toggle.state-off"),
                                                                this.getItemData("sheeps.toggle.state-off")
                                                        )
                                                        .setName(this.getItemName(player, "sheeps.toggle.state-off"))
                                                        .setLore(this.getItemLore(player, "sheeps.toggle.state-off"))
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
                                .builder(this.getItemSlot("zeus"))
                                .setItemStack(
                                        ItemBuilder
                                                .newBuilder(
                                                        this.getItemMaterial("zeus"),
                                                        this.getItemAmount("zeus"),
                                                        this.getItemData("zeus")
                                                )
                                                .setName(this.getItemName(player, "zeus"))
                                                .setLore(this.getItemLore(player, "zeus"))
                                                .build()
                                )
                                .setAction(event -> {
                                    if (!player.hasPermission("elegantoptions.effect.zeus")) {
                                        player.closeInventory();
                                        player.sendMessage(colorize(player, language.getString("zeus.no-permission")));

                                        return true;
                                    }

                                    if (user.getEffectJoin() == Enums.Effects.ZEUS) {
                                        player.closeInventory();
                                        player.sendMessage(colorize(player, language.getString("zeus.already-select")));

                                        return true;
                                    }

                                    event.getClickedInventory().setItem(
                                            this.getItemSlot("zeus"),
                                            ItemBuilder
                                                    .newBuilder(
                                                            this.getItemMaterial("zeus"),
                                                            this.getItemAmount("zeus"),
                                                            this.getItemData("zeus")
                                                    )
                                                    .setName(this.getItemName(player, "zeus"))
                                                    .setLore(this.getItemLore(player, "zeus"))
                                                    .build()
                                    );

                                    event.getClickedInventory().setItem(
                                            this.getItemSlot("zeus") + 9,
                                            ItemBuilder
                                                    .newBuilder(
                                                            this.getItemMaterial("zeus.toggle.state-on"),
                                                            this.getItemAmount("zeus.toggle.state-on"),
                                                            this.getItemData("zeus.toggle.state-on")
                                                    )
                                                    .setName(this.getItemName(player, "zeus.toggle.state-on"))
                                                    .setLore(this.getItemLore(player, "zeus.toggle.state-on"))
                                                    .build()
                                    );

                                    if (user.getEffectJoin() == Enums.Effects.FIREWORK) {
                                        event.getClickedInventory().setItem(
                                                this.getItemSlot("firework"),
                                                ItemBuilder
                                                        .newBuilder(
                                                                this.getItemMaterial("firework"),
                                                                this.getItemAmount("firework"),
                                                                this.getItemData("firework")
                                                        )
                                                        .setName(this.getItemName(player, "firework"))
                                                        .setLore(this.getItemLore(player, "firework"))
                                                        .build()
                                        );

                                        event.getClickedInventory().setItem(
                                                this.getItemSlot("firework") + 9,
                                                ItemBuilder
                                                        .newBuilder(
                                                                this.getItemMaterial("firework.toggle.state-off"),
                                                                this.getItemAmount("firework.toggle.state-off"),
                                                                this.getItemData("firework.toggle.state-off")
                                                        )
                                                        .setName(this.getItemName(player, "firework.toggle.state-off"))
                                                        .setLore(this.getItemLore(player, "firework.toggle.state-off"))
                                                        .build()
                                        );
                                    }


                                    if (user.getEffectJoin() == Enums.Effects.SHEEP) {
                                        event.getClickedInventory().setItem(
                                                this.getItemSlot("sheeps"),
                                                ItemBuilder
                                                        .newBuilder(
                                                                this.getItemMaterial("sheeps"),
                                                                this.getItemAmount("sheeps"),
                                                                this.getItemData("sheeps")
                                                        )
                                                        .setName(this.getItemName(player, "sheeps"))
                                                        .setLore(this.getItemLore(player, "sheeps"))
                                                        .build()
                                        );

                                        event.getClickedInventory().setItem(
                                                this.getItemSlot("sheeps") + 9,
                                                ItemBuilder
                                                        .newBuilder(
                                                                this.getItemMaterial("sheeps.toggle.state-off"),
                                                                this.getItemAmount("sheeps.toggle.state-off"),
                                                                this.getItemData("sheeps.toggle.state-off")
                                                        )
                                                        .setName(this.getItemName(player, "sheeps.toggle.state-off"))
                                                        .setLore(this.getItemLore(player, "sheeps.toggle.state-off"))
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
                                .builder(this.getItemSlot("sheeps"))
                                .setItemStack(
                                        ItemBuilder
                                                .newBuilder(
                                                        this.getItemMaterial("sheeps"),
                                                        this.getItemAmount("sheeps"),
                                                        this.getItemData("sheeps")
                                                )
                                                .setName(this.getItemName(player, "sheeps"))
                                                .setLore(this.getItemLore(player, "sheeps"))
                                                .build()
                                )
                                .setAction(event -> {
                                    if (!player.hasPermission("elegantoptions.effect.sheeps")) {
                                        player.closeInventory();
                                        player.sendMessage(colorize(player, language.getString("sheeps.no-permission")));

                                        return true;
                                    }

                                    if (user.getEffectJoin() == Enums.Effects.SHEEP) {
                                        player.closeInventory();
                                        player.sendMessage(colorize(player, language.getString("sheeps.already-select")));

                                        return true;
                                    }

                                    event.getClickedInventory().setItem(
                                            this.getItemSlot("sheeps"),
                                            ItemBuilder
                                                    .newBuilder(
                                                            this.getItemMaterial("sheeps"),
                                                            this.getItemAmount("sheeps"),
                                                            this.getItemData("sheeps")
                                                    )
                                                    .setName(this.getItemName(player, "sheeps"))
                                                    .setLore(this.getItemLore(player, "sheeps"))
                                                    .build()
                                    );

                                    event.getClickedInventory().setItem(
                                            this.getItemSlot("sheeps") + 9,
                                            ItemBuilder
                                                    .newBuilder(
                                                            this.getItemMaterial("sheeps.toggle.state-on"),
                                                            this.getItemAmount("sheeps.toggle.state-on"),
                                                            this.getItemData("sheeps.toggle.state-on")
                                                    )
                                                    .setName(this.getItemName(player, "sheeps.toggle.state-on"))
                                                    .setLore(this.getItemLore(player, "sheeps.toggle.state-on"))
                                                    .build()
                                    );

                                    if (user.getEffectJoin() == Enums.Effects.FIREWORK) {
                                        event.getClickedInventory().setItem(
                                                this.getItemSlot("firework"),
                                                ItemBuilder
                                                        .newBuilder(
                                                                this.getItemMaterial("firework"),
                                                                this.getItemAmount("firework"),
                                                                this.getItemData("firework")
                                                        )
                                                        .setName(this.getItemName(player, "firework"))
                                                        .setLore(this.getItemLore(player, "firework"))
                                                        .build()
                                        );

                                        event.getClickedInventory().setItem(
                                                this.getItemSlot("firework") + 9,
                                                ItemBuilder
                                                        .newBuilder(
                                                                this.getItemMaterial("firework.toggle.state-off"),
                                                                this.getItemAmount("firework.toggle.state-off"),
                                                                this.getItemData("firework.toggle.state-off")
                                                        )
                                                        .setName(this.getItemName(player, "firework.toggle.state-off"))
                                                        .setLore(this.getItemLore(player, "firework.toggle.state-off"))
                                                        .build()
                                        );
                                    }


                                    if (user.getEffectJoin() == Enums.Effects.ZEUS) {
                                        event.getClickedInventory().setItem(
                                                this.getItemSlot("zeus"),
                                                ItemBuilder
                                                        .newBuilder(
                                                                this.getItemMaterial("zeus"),
                                                                this.getItemAmount("zeus"),
                                                                this.getItemData("zeus")
                                                        )
                                                        .setName(this.getItemName(player, "zeus"))
                                                        .setLore(this.getItemLore(player, "zeus"))
                                                        .build()
                                        );

                                        event.getClickedInventory().setItem(
                                                this.getItemSlot("zeus") + 9,
                                                ItemBuilder
                                                        .newBuilder(
                                                                this.getItemMaterial("zeus.toggle.state-off"),
                                                                this.getItemAmount("zeus.toggle.state-off"),
                                                                this.getItemData("zeus.toggle.state-off")
                                                        )
                                                        .setName(this.getItemName(player, "zeus.toggle.state-off"))
                                                        .setLore(this.getItemLore(player, "zeus.toggle.state-off"))
                                                        .build()
                                        );
                                    }

                                    user.setEffectJoin(Enums.Effects.SHEEP);
                                    XSound.play(player, "UI_BUTTON_CLICK");

                                    return true;
                                })
                                .build()
                ).openAction(event -> {
                    if (user.getEffectJoin() == Enums.Effects.NOTHING) {
                        event.getInventory().setItem(
                                this.getItemSlot("firework") + 9,
                                ItemBuilder
                                        .newBuilder(
                                                this.getItemMaterial("firework.toggle.state-off"),
                                                this.getItemAmount("firework.toggle.state-off"),
                                                this.getItemData("firework.toggle.state-off")
                                        )
                                        .setName(this.getItemName(player, "firework.toggle.state-off"))
                                        .setLore(this.getItemLore(player, "firework.toggle.state-off"))
                                        .build()
                        );

                        event.getInventory().setItem(
                                this.getItemSlot("zeus") + 9,
                                ItemBuilder
                                        .newBuilder(
                                                this.getItemMaterial("zeus.toggle.state-off"),
                                                this.getItemAmount("zeus.toggle.state-off"),
                                                this.getItemData("zeus.toggle.state-off")
                                        )
                                        .setName(this.getItemName(player, "zeus.toggle.state-off"))
                                        .setLore(this.getItemLore(player, "zeus.toggle.state-off"))
                                        .build()
                        );

                        event.getInventory().setItem(
                                this.getItemSlot("sheeps") + 9,
                                ItemBuilder
                                        .newBuilder(
                                                this.getItemMaterial("sheeps.toggle.state-off"),
                                                this.getItemAmount("sheeps.toggle.state-off"),
                                                this.getItemData("sheeps.toggle.state-off")
                                        )
                                        .setName(this.getItemName(player, "sheeps.toggle.state-off"))
                                        .setLore(this.getItemLore(player, "sheeps.toggle.state-off"))
                                        .build()
                        );
                    }

                    if (player.hasPermission("elegantoptions.effect.firework")) {
                        if (user.getEffectJoin() == Enums.Effects.FIREWORK) {
                            event.getInventory().setItem(
                                    this.getItemSlot("firework") + 9,
                                    ItemBuilder
                                            .newBuilder(
                                                    this.getItemMaterial("firework.toggle.state-on"),
                                                    this.getItemAmount("firework.toggle.state-on"),
                                                    this.getItemData("firework.toggle.state-on")
                                            )
                                            .setName(this.getItemName(player, "firework.toggle.state-on"))
                                            .setLore(this.getItemLore(player, "firework.toggle.state-on"))
                                            .build()
                            );
                        } else {
                            event.getInventory().setItem(
                                    this.getItemSlot("firework") + 9,
                                    ItemBuilder
                                            .newBuilder(
                                                    this.getItemMaterial("firework.toggle.state-off"),
                                                    this.getItemAmount("firework.toggle.state-off"),
                                                    this.getItemData("firework.toggle.state-off")
                                            )
                                            .setName(this.getItemName(player, "firework.toggle.state-off"))
                                            .setLore(this.getItemLore(player, "firework.toggle.state-off"))
                                            .build()
                            );
                        }
                    } else {
                        event.getInventory().setItem(
                                this.getItemSlot("firework") + 9,
                                ItemBuilder
                                        .newBuilder(
                                                this.getItemMaterial("firework.toggle.state-no-permission"),
                                                this.getItemAmount("firework.toggle.state-no-permission"),
                                                this.getItemData("firework.toggle.state-no-permission")
                                        )
                                        .setName(this.getItemName(player, "firework.toggle.state-no-permission"))
                                        .setLore(this.getItemLore(player, "firework.toggle.state-no-permission"))
                                        .build()
                        );
                    }

                    if (player.hasPermission("elegantoptions.effect.zeus")) {
                        if (user.getEffectJoin() == Enums.Effects.ZEUS) {
                            event.getInventory().setItem(
                                    this.getItemSlot("zeus") + 9,
                                    ItemBuilder
                                            .newBuilder(
                                                    this.getItemMaterial("zeus.toggle.state-on"),
                                                    this.getItemAmount("zeus.toggle.state-on"),
                                                    this.getItemData("zeus.toggle.state-on")
                                            )
                                            .setName(this.getItemName(player, "zeus.toggle.state-on"))
                                            .setLore(this.getItemLore(player, "zeus.toggle.state-on"))
                                            .build()
                            );
                        } else {
                            event.getInventory().setItem(
                                    this.getItemSlot("zeus") + 9,
                                    ItemBuilder
                                            .newBuilder(
                                                    this.getItemMaterial("zeus.toggle.state-off"),
                                                    this.getItemAmount("zeus.toggle.state-off"),
                                                    this.getItemData("zeus.toggle.state-off")
                                            )
                                            .setName(this.getItemName(player, "zeus.toggle.state-off"))
                                            .setLore(this.getItemLore(player, "zeus.toggle.state-off"))
                                            .build()
                            );
                        }
                    } else {
                        event.getInventory().setItem(
                                this.getItemSlot("zeus") + 9,
                                ItemBuilder
                                        .newBuilder(
                                                this.getItemMaterial("zeus.toggle.state-no-permission"),
                                                this.getItemAmount("zeus.toggle.state-no-permission"),
                                                this.getItemData("zeus.toggle.state-no-permission")
                                        )
                                        .setName(this.getItemName(player, "zeus.toggle.state-no-permission"))
                                        .setLore(this.getItemLore(player, "zeus.toggle.state-no-permission"))
                                        .build()
                        );
                    }

                    if (player.hasPermission("elegantoptions.effect.sheeps")) {
                        if (user.getEffectJoin() == Enums.Effects.SHEEP) {
                            event.getInventory().setItem(
                                    this.getItemSlot("sheeps") + 9,
                                    ItemBuilder
                                            .newBuilder(
                                                    this.getItemMaterial("sheeps.toggle.state-on"),
                                                    this.getItemAmount("sheeps.toggle.state-on"),
                                                    this.getItemData("sheeps.toggle.state-on")
                                            )
                                            .setName(this.getItemName(player, "sheeps.toggle.state-on"))
                                            .setLore(this.getItemLore(player, "sheeps.toggle.state-on"))
                                            .build()
                            );
                        } else {
                            event.getInventory().setItem(
                                    this.getItemSlot("sheeps") + 9,
                                    ItemBuilder
                                            .newBuilder(
                                                    this.getItemMaterial("sheeps.toggle.state-off"),
                                                    this.getItemAmount("sheeps.toggle.state-off"),
                                                    this.getItemData("sheeps.toggle.state-off")
                                            )
                                            .setName(this.getItemName(player, "sheeps.toggle.state-off"))
                                            .setLore(this.getItemLore(player, "sheeps.toggle.state-off"))
                                            .build()
                            );
                        }
                    } else {
                        event.getInventory().setItem(
                                this.getItemSlot("sheeps") + 9,
                                ItemBuilder
                                        .newBuilder(
                                                this.getItemMaterial("sheeps.toggle.state-no-permission"),
                                                this.getItemAmount("sheeps.toggle.state-no-permission"),
                                                this.getItemData("sheeps.toggle.state-no-permission")
                                        )
                                        .setName(this.getItemName(player, "sheeps.toggle.state-no-permission"))
                                        .setLore(this.getItemLore(player, "sheeps.toggle.state-no-permission"))
                                        .build()
                        );
                    }

                    return false;
                });

        return guiBuilder.build();
    }
}