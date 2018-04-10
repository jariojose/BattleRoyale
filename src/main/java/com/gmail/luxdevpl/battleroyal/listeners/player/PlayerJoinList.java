package com.gmail.luxdevpl.battleroyal.listeners.player;

import com.gmail.luxdevpl.battleroyal.basic.BattlePlayer;
import com.gmail.luxdevpl.battleroyal.managers.BattlePlayerManager;
import com.gmail.luxdevpl.battleroyal.utils.StringUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinList implements Listener {
    @EventHandler
    public void handle(PlayerJoinEvent e){
        BattlePlayer battlePlayer = new BattlePlayer(e.getPlayer().getUniqueId());
        BattlePlayerManager.addBattlePlayer(e.getPlayer().getUniqueId(), battlePlayer);
        e.getPlayer().sendMessage(StringUtils.color("&3Czesc."));
    }
}
