package com.gmail.luxdevpl.battleroyal.utils;

import org.bukkit.Location;
import org.bukkit.block.BlockFace;

public class FacingDataUtils {

    public static void fixRelativeData(Location location, BlockFace dir){
        byte d = 0;

        if(dir == BlockFace.WEST){
            d = 0x1;
        }else if(dir == BlockFace.EAST){
            d = 0x0;
        }else if(dir == BlockFace.NORTH){
            d = 0x3;
        }else if(dir == BlockFace.SOUTH){
            d = 0x2;
        }

        location.getWorld().getBlockAt(location.getBlockX(), location.getBlockY(), location.getBlockZ()).setTypeIdAndData(67, d, false);
    }
}
