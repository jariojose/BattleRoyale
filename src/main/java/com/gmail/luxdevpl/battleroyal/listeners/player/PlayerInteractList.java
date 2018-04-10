package com.gmail.luxdevpl.battleroyal.listeners.player;

import java.sql.Struct;
import java.util.Set;

import com.gmail.luxdevpl.battleroyal.basic.BattleStructure;
import com.gmail.luxdevpl.battleroyal.managers.StructureManager;
import com.gmail.luxdevpl.battleroyal.utils.Packets;
import net.minecraft.server.v1_9_R2.NBTTagCompound;
import net.minecraft.server.v1_9_R2.TileEntity;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.block.Structure;
import org.bukkit.craftbukkit.v1_9_R2.CraftWorld;
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
            if(e.getClickedBlock().getType() == Material.WOOD){
                TileEntity tileEntity = ((CraftWorld) e.getClickedBlock().getWorld()).getTileEntityAt(e.getClickedBlock().getX(), e.getClickedBlock().getY(), e.getClickedBlock().getZ());
                NBTTagCompound tagCompound = tileEntity.E_();
                if(tagCompound != null){
                    int structureID = tagCompound.getInt("structure");

                    Bukkit.broadcastMessage("STR ID: " + structureID);

                    BattleStructure structure = Main.getInstance().getStructureManager().getStrucureById(structureID);

                    structure.setHealthPercentage(structure.getHealthPercentage() -1);

                    Packets.sendActionBar(e.getPlayer(), "HP: " + structure.getHealthPercentage());
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
