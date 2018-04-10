package com.gmail.luxdevpl.battleroyal.utils;

import com.gmail.luxdevpl.battleroyal.basic.BattlePlayer;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.List;

public class BuildingUtils {

    public static void clearBlockChanges(BattlePlayer battlePlayer, boolean clearList){
        for(Block block : battlePlayer.getBlockList()){
            battlePlayer.getPlayer().sendBlockChange(block.getLocation(), block.getType(), block.getData());
        }

        if(clearList) battlePlayer.getBlockList().clear();
    }

    public static boolean canBuild(List<Block> blocks){
        for(Block block : blocks){
            if(block.getType() != Material.AIR){
                return false;
            }
        }
        return true;
    }

}
