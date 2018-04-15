/*
 * Copyright (c) 2018.  created by xdev-pl.
 */

package com.gmail.luxdevpl.battleroyal.system.structure;

import java.util.Collections;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

import com.gmail.luxdevpl.battleroyal.Main;
import com.gmail.luxdevpl.battleroyal.basic.game.BattlePlayer;
import com.gmail.luxdevpl.battleroyal.managers.BattlePlayerManager;
import com.gmail.luxdevpl.battleroyal.utils.BuildingUtils;
import com.gmail.luxdevpl.battleroyal.utils.FacingDataUtils;
import com.gmail.luxdevpl.battleroyal.utils.Packets;
import com.gmail.luxdevpl.battleroyal.utils.StringUtils;

public abstract class AbstractStructure {

    /**
     * This method checks if player can build his visualized structure
     * @param blocks blocks of structure (visualized before)
     * @return whether player can build his structure.
     */
    protected final boolean canBuild(List<Block> blocks) {
        for (Block block : blocks) {
            if (block.getType() != Material.AIR) {
                return false;
            }
        }
        return true;
    }

    /**
     * This method should build the structure that player want to build.
     * @param player player
     */
    public final void build(Player player) {
    	BattlePlayer battlePlayer = BattlePlayerManager.getBattlePlayer(player.getUniqueId());

        if(!this.canBuild(battlePlayer.getBlockList())){
            Packets.sendActionBar(player, StringUtils.color("&4TUTAJ NIE MOZNA NIC ZBUDOWAC."));
            return;
        }

        BuildingUtils.clearBlockChanges(battlePlayer, false);

        List<Block> blockList = battlePlayer.getBlockList();
        Collections.shuffle(blockList);

        performBuildAction(player, blockList);

        BuildingUtils.clearBlockChanges(battlePlayer, true);
    }

    /**
     * This method should visualize the structure that player want to build.
     * @param player player
     * @param where location where the player is looking
     */
    public abstract void visualizeStructure(Player player, Location where);

    protected abstract void performBuildAction(Player player, List<Block> blockList);
    
    protected void placeWithCooldown(Player player, Location where, Material with, int delay){
        Main.getInstance().getServer().getScheduler().scheduleSyncDelayedTask(Main.getInstance(), () -> {
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 0.6f, 0.8f);

            where.getBlock().setType(with);
        }, delay * 3);
    }
    
    protected void placeWithCooldown(Player player, Location where, BlockFace direction, int delay){
        Main.getInstance().getServer().getScheduler().scheduleSyncDelayedTask(Main.getInstance(), () -> {
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 0.6f, 0.8f);

            FacingDataUtils.fixRelativeData(where, direction);
        }, delay * 3);
    }
}
