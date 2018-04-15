/*
 * Copyright (c) 2018.  created by xdev-pl.
 */

package com.gmail.luxdevpl.battleroyal.utils;

import com.gmail.luxdevpl.battleroyal.basic.types.Direction;
import net.minecraft.server.v1_9_R2.EnumParticle;
import net.minecraft.server.v1_9_R2.PacketPlayOutWorldParticles;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.craftbukkit.v1_9_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class MathUtils {

    public static Direction getDirection(Player player) {
        float yaw = player.getLocation().getYaw();

        if (yaw < 0) {
            yaw += 360;

        }
        if (yaw >= 315 || yaw < 45) {
            return Direction.SOUTH;
        } else if (yaw < 135) {
            return Direction.WEST;
        } else if (yaw < 225) {
            return Direction.NORTH;
        } else if (yaw < 315) {
            return Direction.EAST;
        }

        return Direction.NORTH;
    }

    public static BlockFace directionToBlockFace(Direction direction){
        switch(direction){
            case NORTH:
                return BlockFace.NORTH;
            case WEST:
                return BlockFace.WEST;
            case SOUTH:
                return BlockFace.SOUTH;
            case EAST:
                return BlockFace.EAST;
        }
        return BlockFace.DOWN;
    }

    public static void drawLineBeetweenLocations(Player player, Location startLocation, Location endLocation){
        Vector difference = startLocation.toVector().subtract(endLocation.toVector());

        //todo
    }
}
