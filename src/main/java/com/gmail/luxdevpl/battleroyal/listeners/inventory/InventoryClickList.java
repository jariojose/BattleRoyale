package com.gmail.luxdevpl.battleroyal.listeners.inventory;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import com.gmail.luxdevpl.battleroyal.Main;

public class InventoryClickList implements Listener {
    @EventHandler
    public void handle(InventoryClickEvent e){
        if(e.getInventory().getName().equals("Menu budowania")){
            Main.getInstance().getBuildingMenu().checkAction(e);
        }
    }
}
