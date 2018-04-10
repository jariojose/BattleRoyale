package com.gmail.luxdevpl.battleroyal.system.structure.structures;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import com.gmail.luxdevpl.battleroyal.basic.BattlePlayer;
import com.gmail.luxdevpl.battleroyal.managers.BattlePlayerManager;
import com.gmail.luxdevpl.battleroyal.system.structure.AbstractStructure;
import com.gmail.luxdevpl.battleroyal.utils.BuildingUtils;

public class FloorStructureImpl extends AbstractStructure {

    @Override
    protected void performBuildAction(Player player, List<Block> blockList) {
		for(int i = 0; i < blockList.size(); i++){
			placeWithCooldown(player, blockList.get(i).getLocation(), Material.WOOD, i);
		}
	}

    @Override
    public void visualizeStructure(Player player, Location buildLocation) {
        BuildingUtils.clearBlockChanges(BattlePlayerManager.getBattlePlayer(player.getUniqueId()), true);

        buildLocation.add(0, 1, 0);

        BattlePlayer battlePlayer = BattlePlayerManager.getBattlePlayer(player.getUniqueId());

        List<Block> blocks = this.getStructure(buildLocation);

        byte byteId = this.canBuild(blocks) ? (byte) 13 : (byte) 14;

        for (Block block : blocks) {
            battlePlayer.getBlockList().add(block);

            player.sendBlockChange(block.getLocation(), Material.STAINED_GLASS, byteId);
        }
    }

    private List<Block> getStructure(Location location){
        //Todo add Direction support.
        List<Block> blocks = new ArrayList<>();
        for (int x = 0; x < 5; x++) {
            for (int z = 0; z < 6; z++) {
                Block block = location.getWorld().getBlockAt((int) location.getX() + x, (int) location.getY(), (int) location.getZ() + z);
                blocks.add(block);
            }
        }
        return blocks;
    }
}
