/*
 * Copyright (c) 2018.  created by xdev-pl.
 */

package com.gmail.luxdevpl.battleroyal.system.structure.structures;

import java.util.ArrayList;
import java.util.List;

import com.gmail.luxdevpl.battleroyal.Main;
import com.gmail.luxdevpl.battleroyal.basic.types.Building;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import com.gmail.luxdevpl.battleroyal.basic.game.BattlePlayer;
import com.gmail.luxdevpl.battleroyal.basic.types.Direction;
import com.gmail.luxdevpl.battleroyal.managers.BattlePlayerManager;
import com.gmail.luxdevpl.battleroyal.system.structure.AbstractStructure;
import com.gmail.luxdevpl.battleroyal.utils.BuildingUtils;
import com.gmail.luxdevpl.battleroyal.utils.MathUtils;

public class WallStructureImpl extends AbstractStructure {

    @Override
    protected void performBuildAction(Player player, List<Block> blockList) {
        for(int i = 0; i < blockList.size(); i++){
            this.placeWithCooldown(player, blockList.get(i).getLocation(), Material.WOOD, i);
        }
        Main.getInstance().getStructureManager().addStructure(Building.WALL, blockList);
	}

    @Override
    public void visualizeStructure(Player player, Location buildLocation) {
        BuildingUtils.clearBlockChanges(BattlePlayerManager.getBattlePlayer(player.getUniqueId()), true);

        if(buildLocation.getBlock().getType() == Material.WOOD) {
            buildLocation.setY(((double) buildLocation.getWorld().getHighestBlockYAt(buildLocation)) -1);
        }

        List<Block> blocks = this.getStructure(buildLocation, MathUtils.getDirection(player));

        BattlePlayer battlePlayer = BattlePlayerManager.getBattlePlayer(player.getUniqueId());

        byte byteId = BuildingUtils.canBuild(blocks) ? (byte) 13 : (byte) 0;

        for (Block block : blocks) {

            battlePlayer.getBlockList().add(block);

            player.sendBlockChange(block.getLocation(), Material.STAINED_GLASS, byteId);

        }
    }

    private List<Block> getStructure(Location location, Direction direction) {
        List<Block> blocks = new ArrayList<>();

        switch (direction) {
            case NORTH:
                for (int x = 0; x < 5; x++) {
                    for (int y = 1; y < 4; y++) {
                        Block block = location.getWorld().getBlockAt((int) location.getX() + x, (int) location.getY() + y, (int) location.getZ());
                        blocks.add(block);
                    }
                }
                break;
            case WEST:
                for (int z = 0; z < 5; z++) {
                    for (int y = 1; y < 4; y++) {
                        Block block = location.getWorld().getBlockAt((int) location.getX(), (int) location.getY() + y, (int) location.getZ() + z);
                        blocks.add(block);
                    }
                }
                break;
            case SOUTH:
                for (int x = 0; x < 5; x++) {
                    for (int y = 1; y < 4; y++) {
                        Block block = location.getWorld().getBlockAt((int) location.getX() + x, (int) location.getY() + y, (int) location.getZ());
                        blocks.add(block);
                    }
                }
                break;
            case EAST:
                for (int z = 0; z < 5; z++) {
                    for (int y = 1; y < 4; y++) {
                        Block block = location.getWorld().getBlockAt((int) location.getX(), (int) location.getY() + y, (int) location.getZ() + z);
                        blocks.add(block);
                    }
                }
                break;
        }
        return blocks;
    }

}
