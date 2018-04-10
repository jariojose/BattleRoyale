package com.gmail.luxdevpl.battleroyal.system.structure.structures;

import com.gmail.luxdevpl.battleroyal.basic.BattlePlayer;
import com.gmail.luxdevpl.battleroyal.managers.BattlePlayerManager;
import com.gmail.luxdevpl.battleroyal.system.structure.AbstractStructure;
import com.gmail.luxdevpl.battleroyal.utils.BuildingUtils;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ConeStructureImpl extends AbstractStructure {

    @Override
    public void visualizeStructure(Player player, Location where) {
        BuildingUtils.clearBlockChanges(BattlePlayerManager.getBattlePlayer(player.getUniqueId()), true);

        List<Block> blocks = this.getStructure(where);

        BattlePlayer battlePlayer = BattlePlayerManager.getBattlePlayer(player.getUniqueId());

        byte byteId = BuildingUtils.canBuild(blocks) ? (byte) 13 : (byte) 0;

        for (Block block : blocks) {

            battlePlayer.getBlockList().add(block);

            player.sendBlockChange(block.getLocation(), Material.STAINED_GLASS, byteId);

        }
    }

    @Override
    protected void performBuildAction(Player player, List<Block> blockList) {
        for (int i = 0; i < blockList.size(); i++) {
            placeWithCooldown(player, blockList.get(i).getLocation(), Material.GLASS, i);
        }
    }

    private List<Block> getStructure(Location location) {
        List<Block> blocks = new ArrayList<>();

        int size = 5;

        Location copy = location.clone();

        for(int y = 0; y < size / 2D; y++) {
            for(int x = 0; x < size - y*2; x++) {
                for(int z = 0; z < size - y*2; z++) {
                    blocks.add(copy.getBlock());
                    copy.add(0, 0, 1);
                }
                copy.add(1, 0, -size + y*2);
            }
            copy.add(-size + y * 2 + 1, 1, 1);
        }
        return blocks;
    }

}

