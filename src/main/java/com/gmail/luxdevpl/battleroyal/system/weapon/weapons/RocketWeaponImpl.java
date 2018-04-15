/*
 * Copyright (c) 2018.  created by xdev-pl.
 */

package com.gmail.luxdevpl.battleroyal.system.weapon.weapons;

import com.gmail.luxdevpl.battleroyal.basic.BattleBullet;
import com.gmail.luxdevpl.battleroyal.basic.game.BattlePlayer;
import com.gmail.luxdevpl.battleroyal.system.weapon.Weapon;

import net.minecraft.server.v1_9_R2.EnumParticle;
import net.minecraft.server.v1_9_R2.PacketPlayOutWorldParticles;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_9_R2.entity.CraftPlayer;

public class RocketWeaponImpl extends Weapon {

    public RocketWeaponImpl(BattlePlayer owner) {
        super(owner, RocketBullet::new);
    }

    public static class RocketBullet extends BattleBullet {

        RocketBullet(Location location, double speed) {
            super(location, speed);
        }

        @Override
        public void updateTrajectory() {
            Location location = this.getLocation();

            this.setSpeed(this.getSpeed() + 1);

            double x = this.getVelocity().getX() * this.getSpeed();
            double y = this.getVelocity().getY() * this.getSpeed() + 1.5;
            double z = this.getVelocity().getZ() * this.getSpeed();

            location.add(x, y, z);

            PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(EnumParticle.FIREWORKS_SPARK, true,
                    (float) (location.getX()),
                    (float) (location.getY()),
                    (float) (location.getZ()),
                    0, 0, 0, 0, 1, (int[]) null);

            Bukkit.getOnlinePlayers().forEach(player -> {
                ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
            });

            if (location.getBlock().getType() != Material.AIR) {
                //Todo bullet impact etc.
            }
            location.subtract(x, y, z);
        }
    }
}
