/*
 * Copyright (c) 2018.  created by xdev-pl.
 */

package com.gmail.luxdevpl.battleroyal.basic;

import com.gmail.luxdevpl.battleroyal.Main;
import com.gmail.luxdevpl.battleroyal.basic.game.BattlePlayer;
import com.gmail.luxdevpl.battleroyal.basic.types.Building;
import com.gmail.luxdevpl.battleroyal.managers.BattlePlayerManager;
import com.gmail.luxdevpl.battleroyal.utils.StringUtils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class BuildingMenu {

    private final Inventory inv;

    public BuildingMenu(){
        this.inv = Bukkit.createInventory(null, 9, "Menu budowania");

        inv.setItem(0, new ItemStack(Material.FENCE, 1));
        inv.setItem(4, new ItemStack(Material.WOOD, 1));
        inv.setItem(8, new ItemStack(Material.COBBLESTONE_STAIRS, 1));
        inv.setItem(2, new ItemStack(Material.GLASS, 1));
    }

    public void open(Player player){
        player.openInventory(this.inv);
    }

    public void checkAction(InventoryClickEvent e){
        e.setCancelled(true);

        BattlePlayer battlePlayer = BattlePlayerManager.getBattlePlayer(e.getWhoClicked().getUniqueId());

        if(e.getCurrentItem().getType() == Material.FENCE){
            battlePlayer.setStructure(Main.getInstance().getBuildingManager().getStructure(Building.WALL));
            e.getWhoClicked().sendMessage(StringUtils.color("&3Ustawiles strukture sciany."));
        }

        if(e.getCurrentItem().getType() == Material.WOOD){
            battlePlayer.setStructure(Main.getInstance().getBuildingManager().getStructure(Building.FLOOR));
            e.getWhoClicked().sendMessage(StringUtils.color("&3Ustawiles strukture podlogi."));
        }

        if(e.getCurrentItem().getType() == Material.COBBLESTONE_STAIRS){
            battlePlayer.setStructure(Main.getInstance().getBuildingManager().getStructure(Building.STAIRS));
            e.getWhoClicked().sendMessage(StringUtils.color("&3Ustawiles strukture schodow."));
        }

        if(e.getCurrentItem().getType() == Material.GLASS){
            battlePlayer.setStructure(Main.getInstance().getBuildingManager().getStructure(Building.CONE));
            e.getWhoClicked().sendMessage(StringUtils.color("&3Ustawiles strukture stozka."));
        }
    }

}
