package com.gmail.luxdevpl.battleroyal.listeners.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;

import com.gmail.luxdevpl.battleroyal.basic.BattlePlayer;
import com.gmail.luxdevpl.battleroyal.managers.BattlePlayerManager;
import com.gmail.luxdevpl.battleroyal.utils.BuildingUtils;

public class PlayerHeldItemSlotList implements Listener {
    @EventHandler
    public void handle(PlayerItemHeldEvent e){
        if(e.getPlayer().getInventory().getItem(e.getPreviousSlot()) != null){
            BattlePlayer battlePlayer = BattlePlayerManager.getBattlePlayer(e.getPlayer().getUniqueId());

            BuildingUtils.clearBlockChanges(battlePlayer, true);
        }
    }
}
