/*
 * Copyright (c) 2018.  created by xdev-pl.
 */

package com.gmail.luxdevpl.battleroyal.listeners.player;

import com.gmail.luxdevpl.battleroyal.basic.game.BattleArena;
import com.gmail.luxdevpl.battleroyal.basic.game.BattlePlayer;
import com.gmail.luxdevpl.battleroyal.managers.BattlePlayerManager;
import com.gmail.luxdevpl.battleroyal.utils.Packets;
import com.gmail.luxdevpl.battleroyal.utils.StringUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;

public class PlayerToggleSneakList implements Listener {

    @EventHandler
    public void handle(PlayerToggleSneakEvent e){
        BattlePlayer battlePlayer = BattlePlayerManager.getBattlePlayer(e.getPlayer().getUniqueId());

        if(battlePlayer.getCurrentArena() != null){
            BattleArena arena = battlePlayer.getCurrentArena();

            if(arena.isFlyingDisabled(battlePlayer)){
                if(arena.getDragon().getPassangers().contains(e.getPlayer())) {
                    arena.getDragon().removePassanger(e.getPlayer());

                    Packets.sendTitle(e.getPlayer(), StringUtils.color("&6Powodzenia w grze"), "", 1, 0, 10);
                }
            }
        }
    }
}
