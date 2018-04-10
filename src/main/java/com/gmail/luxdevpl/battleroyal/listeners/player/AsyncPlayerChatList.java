package com.gmail.luxdevpl.battleroyal.listeners.player;

import com.gmail.luxdevpl.battleroyal.basic.BattlePlayer;
import com.gmail.luxdevpl.battleroyal.managers.BattlePlayerManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class AsyncPlayerChatList implements Listener {

    @EventHandler
    public void handle(AsyncPlayerChatEvent e){
        if(e.getPlayer().isOp()){
            if(e.getMessage().equalsIgnoreCase("restart")){
                BattlePlayer battlePlayer = BattlePlayerManager.getBattlePlayer(e.getPlayer().getUniqueId());

                //todo
            }
        }
    }
}
