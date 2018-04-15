/*
 * Copyright (c) 2018.  created by xdev-pl.
 */

package com.gmail.luxdevpl.battleroyal.listeners.player;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

import com.gmail.luxdevpl.battleroyal.Main;
import com.gmail.luxdevpl.battleroyal.basic.game.BattleArena;
import com.gmail.luxdevpl.battleroyal.basic.game.BattlePlayer;
import com.gmail.luxdevpl.battleroyal.managers.BattlePlayerManager;

public class PlayerMoveList implements Listener {

    @EventHandler
    public void handle(PlayerMoveEvent e){
        BattlePlayer battlePlayer = BattlePlayerManager.getBattlePlayer(e.getPlayer().getUniqueId());

        if(battlePlayer.getCurrentArena() != null) {
            BattleArena arena = battlePlayer.getCurrentArena();

            Player player = e.getPlayer();

            if(e.getTo() != e.getFrom()){
                if(player.getLocation().subtract(0, 1, 0).getBlock().getType() == Material.SLIME_BLOCK) {
                    player.getLocation().subtract(0, 1, 0).getBlock().setType(Material.AIR);

                    Vector pushVector = player.getLocation().getDirection().setY(4);

                    player.setVelocity(pushVector.multiply(0.7));

                    Bukkit.getScheduler().scheduleAsyncDelayedTask(Main.getInstance(), () -> arena.enableFlying(battlePlayer), 35);
                }
            }
        }
    }

}
