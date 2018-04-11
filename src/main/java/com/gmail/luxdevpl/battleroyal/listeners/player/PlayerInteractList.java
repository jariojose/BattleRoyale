package com.gmail.luxdevpl.battleroyal.listeners.player;

import com.gmail.luxdevpl.battleroyal.basic.BattleStructure;
import com.gmail.luxdevpl.battleroyal.utils.Packets;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.gmail.luxdevpl.battleroyal.Main;
import com.gmail.luxdevpl.battleroyal.basic.BattleArena;
import com.gmail.luxdevpl.battleroyal.basic.BattlePlayer;
import com.gmail.luxdevpl.battleroyal.basic.types.GameState;
import com.gmail.luxdevpl.battleroyal.managers.ArenaManager;
import com.gmail.luxdevpl.battleroyal.managers.BattlePlayerManager;
import com.gmail.luxdevpl.battleroyal.utils.StringUtils;

public class PlayerInteractList implements Listener {

    @EventHandler
    public void handle(PlayerInteractEvent e){
        BattlePlayer battlePlayer = BattlePlayerManager.getBattlePlayer(e.getPlayer().getUniqueId());

        if(e.getAction() == Action.RIGHT_CLICK_BLOCK){
            Player player = e.getPlayer();

            if(e.getClickedBlock().getType() == Material.WALL_SIGN) {
                BlockState blockState = e.getClickedBlock().getState();

                if (blockState instanceof Sign) {

                    Sign sign = (Sign) blockState;

                    BattleArena clickedArena = ArenaManager.getBattleArena(Integer.valueOf(org.apache.commons.lang3.StringUtils.substringAfter(sign.getLine(0), ": ")));

                    if (clickedArena.getGameState() == GameState.WAITING) {
                        //todo teleport do lobby-areny

                        clickedArena.addPlayer(battlePlayer);

                        clickedArena.updateSign();
                        return;
                    }

                    if (clickedArena.getGameState() == GameState.INGAME) {
                        player.sendMessage(StringUtils.color("&cGra jest w toku."));
                    }
                }
            }
        }

        if(e.getAction() == Action.LEFT_CLICK_BLOCK){
            if(e.getClickedBlock().getType() == Material.WOOD){
                BattleStructure battleStructure = Main.getInstance().getStructureManager().getStructureByLocation(e.getClickedBlock().getLocation());

                if(battleStructure != null){
                    battleStructure.subtractLive();

                    Packets.sendActionBar(e.getPlayer(), StringUtils.color("&4HP Struktury: " + battleStructure.getHealthPercentage()));
                }
            }
        }

        if(e.getPlayer().getItemInHand().getType() == Material.BOOK) {
            e.setCancelled(true);

            if (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) {
                Main.getInstance().getBuildingMenu().open(e.getPlayer());
            } else {
                if (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
                    if (battlePlayer.getStructure() != null) {
                        battlePlayer.getStructure().build(e.getPlayer());
                    }
                }
            }

        }
    }

}
