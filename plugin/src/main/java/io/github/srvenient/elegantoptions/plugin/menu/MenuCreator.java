package io.github.srvenient.elegantoptions.plugin.menu;

import dev.srvenient.freya.abstraction.configuration.Configuration;
import dev.srvenient.freya.api.xseries.XMaterial;

import dev.srvenient.freya.api.xseries.XSound;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import team.unnamed.gui.abstraction.item.ItemClickable;
import team.unnamed.gui.core.item.type.ItemBuilder;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

import static io.github.srvenient.elegantoptions.plugin.translation.Translation.colorize;
import static io.github.srvenient.elegantoptions.plugin.translation.Translation.colorizeList;

public abstract class MenuCreator {

    private final String menuKey;

    @Inject @Named("menus") private Configuration menus;

    protected MenuCreator(String menuKey) {
        this.menuKey = menuKey;
    }

    public abstract Inventory create(Player player);

    protected ItemClickable backItem(Player player, MenuCreator backMenu, int slot) {
        return ItemClickable.builder(slot)
                .setItemStack(
                         ItemBuilder
                                 .newBuilder(XMaterial.ARROW.parseMaterial())
                                 .setName(colorize(player, menus.getString("commons.back.name")))
                                 .build()
                )
                .setAction(
                        click -> {
                            player.openInventory(backMenu.create(player));
                            XSound.play(player, "UI_BUTTON_CLICK");

                            return true;
                        }
                )
                .build();
    }

    protected ItemClickable closeItem(Player player, int slot) {
        return ItemClickable
                .builder(slot)
                .setItemStack(
                        ItemBuilder
                                .newBuilder(XMaterial.BARRIER.parseMaterial())
                                .setName(colorize(player, menus.getString("commons.close.name")))
                                .build()
                )
                .setAction(
                        click -> {
                            player.closeInventory();
                            XSound.play(player, "UI_BUTTON_CLICK");

                            return true;
                        }
                )
                .build();
    }

    protected String getTitle(Player player) {
        return colorize(player, menus.getString(menuKey + ".title"));
    }

    protected int getRows() {
        return menus.getInt(menuKey + ".rows");
    }

    protected String getItemName(Player player, String itemKey) {
        return colorize(player, menus.getString(menuKey + ".items." + itemKey + ".name"));
    }

    protected List<String> getItemLore(Player player, String itemKey) {
        return colorizeList(player, menus.getStringList(menuKey + ".items." + itemKey + ".lore"));
    }

    protected Material getItemMaterial(String itemKey) {
        return XMaterial.valueOf(menus.getString(menuKey + ".items." + itemKey + ".material")).parseMaterial();
    }

    protected int getItemAmount(String itemKey) {
        return menus.getInt(menuKey + ".items." + itemKey + ".amount");
    }

    protected byte getItemData(String itemKey) {
        return XMaterial.valueOf(menus.getString(menuKey + ".items." + itemKey + ".material")).getData();
    }

    protected int getItemSlot(String itemKey) {
        return menus.getInt(menuKey + ".items." + itemKey + ".slot");
    }
}
