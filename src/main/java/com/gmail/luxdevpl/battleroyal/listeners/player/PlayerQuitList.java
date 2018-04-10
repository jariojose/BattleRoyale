package com.gmail.luxdevpl.battleroyal.listeners.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import com.gmail.luxdevpl.battleroyal.basic.BattleArena;
import com.gmail.luxdevpl.battleroyal.basic.BattlePlayer;
import com.gmail.luxdevpl.battleroyal.basic.types.GameState;
import com.gmail.luxdevpl.battleroyal.managers.BattlePlayerManager;

public class PlayerQuitList implements Listener {

    @EventHandler
    public void handle(PlayerQuitEvent e){
        BattlePlayer battlePlayer = BattlePlayerManager.getBattlePlayer(e.getPlayer().getUniqueId());

        if(battlePlayer.getCurrentArena() != null){
            BattleArena currentArena = battlePlayer.getCurrentArena();

            if(currentArena.getGameState() == GameState.INGAME){
                //todo kara za ucieczke ty kurwo UBOJNIA!!!!!!
                currentArena.broadcastMessage("&3" + e.getPlayer().getName() + " &7spierdolil wam z areny, spokojnie my go zajebiemy.");

                currentArena.removePlayer(battlePlayer);
            } else {
                if(currentArena.getGameState() == GameState.WAITING){
                    currentArena.removePlayer(battlePlayer);
                }
            }

            currentArena.updateSign();
        }

        BattlePlayerManager.removeBattlePlayer(e.getPlayer().getUniqueId());
    }
}
