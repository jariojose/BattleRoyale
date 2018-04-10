package com.gmail.luxdevpl.battleroyal.system.structure.structures;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

import com.gmail.luxdevpl.battleroyal.basic.BattlePlayer;
import com.gmail.luxdevpl.battleroyal.basic.types.Direction;
import com.gmail.luxdevpl.battleroyal.managers.BattlePlayerManager;
import com.gmail.luxdevpl.battleroyal.system.structure.AbstractStructure;
import com.gmail.luxdevpl.battleroyal.utils.BuildingUtils;
import com.gmail.luxdevpl.battleroyal.utils.MathUtils;

public class StairsStructureImpl extends AbstractStructure {

    @Override
    protected void performBuildAction(Player player, List<Block> blockList) {
        BlockFace direction = MathUtils.directionToBlockFace(MathUtils.getDirection(player));
        for(int i = 0; i < blockList.size(); i++){
            placeWithCooldown(player, blockList.get(i).getLocation(), direction, i);
        }
	}

    @Override
    public void visualizeStructure(Player player, Location buildLocation) {
        BuildingUtils.clearBlockChanges(BattlePlayerManager.getBattlePlayer(player.getUniqueId()), true);

        Direction direction = MathUtils.getDirection(player);

        if(buildLocation.getBlock().getType() == Material.COBBLESTONE_STAIRS) {
            switch (direction) {
                case NORTH:
                    buildLocation.add(0, 0, -1);
                    break;
                case WEST:
                    buildLocation.add(-1, 0, 0);
                    break;
                case SOUTH:
                    buildLocation.add(0, 0, 1);
                    break;
                case EAST:
                    buildLocation.add(1, 0, 0);
                    break;
            }
        }

        List<Block> blocks = this.getStructure(buildLocation, direction);

        BattlePlayer battlePlayer = BattlePlayerManager.getBattlePlayer(player.getUniqueId());

        byte byteId = BuildingUtils.canBuild(blocks) ? (byte) 13 : (byte) 0;

        for (Block block : blocks) {

            battlePlayer.getBlockList().add(block);

            player.sendBlockChange(block.getLocation(), Material.STAINED_GLASS, byteId);

        }
    }

    private List<Block> getStructure(Location location, Direction direction) {
        List<Block> blocks = new ArrayList<>();

        switch(direction){
            case NORTH:
                for (int z = 0; z < 3; z++) {
                    for(int x = 0; x < 3; x++) {
                        Block block = location.getWorld().getBlockAt((int) location.getX() - z, (int) location.getY() + x + 1, (int) location.getZ() - x); //z tylko bylo plusowane o z.
                        blocks.add(block);
                    }
                }
                break;
            case WEST:
                for (int x = 0; x < 3; x++) {
                    for (int z = 0; z < 3; z++) {
                        Block block = location.getWorld().getBlockAt((int) location.getX() - x, (int) location.getY() + x + 1, (int) location.getZ() - z);
                        blocks.add(block);
                    }
                }
                break;
            case SOUTH:
                for (int x = 0; x < 3; x++) {
                    for (int z = 0; z < 3; z++) {
                        Block block = location.getWorld().getBlockAt((int) location.getX() + z, (int) location.getY() + x + 1, (int) location.getZ() + x);
                        blocks.add(block);
                    }
                }
                break;
            case EAST:
                for (int x = 0; x < 3; x++) {
                    for (int z = 0; z < 3; z++) {
                        Block block = location.getWorld().getBlockAt((int) location.getX() + x, (int) location.getY() + x + 1, (int) location.getZ() + z);
                        blocks.add(block);
                    }
                }
                break;
        }
        return blocks;
    }
}
