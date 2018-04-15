/*
 * Copyright (c) 2018.  created by xdev-pl.
 */

package com.gmail.luxdevpl.battleroyal.utils;

import com.gmail.luxdevpl.battleroyal.basic.game.BattleArena;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.LinkedList;
import java.util.List;

public class WorldUtils {

    public static Location getLocationForDrop(BattleArena arena){
        int size = arena.getArenaSize() / 2;

        Location loc;
        do {
            double x = RandomUtils.getRandDouble(-size, size);
            double z = RandomUtils.getRandDouble(-size, size);

            loc = new Location(arena.getArenaWorld(), x, 90, z);
        } while (loc.getBlock().getType() != Material.AIR);
        return loc;
    }

    public static void chopWholeTree(Location location) {
        List<Block> blocks = new LinkedList<>();

        for (int i = location.getBlockY(); i < location.getWorld().getHighestBlockYAt(location.getBlockX(), location.getBlockZ()); i++) {
            Location l = location.add(0, 1, 0);
            if (l.getBlock().getType() == Material.LOG || l.getBlock().getType() == Material.LOG_2) {
                blocks.add(l.getBlock());
            } else {
                break;
            }
            l = null;
        }
        for (Block block : blocks) {
            block.breakNaturally();
        }
        blocks = null;
    }

}
