/*
 * Copyright (c) 2018.  created by xdev-pl.
 */

package com.gmail.luxdevpl.battleroyal.system.entity;

import com.gmail.luxdevpl.battleroyal.system.ICustomEnderdragonBus;
import net.minecraft.server.v1_9_R2.EntityEnderDragon;
import net.minecraft.server.v1_9_R2.World;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_9_R2.CraftWorld;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class CustomEnderDragonBus extends EntityEnderDragon implements ICustomEnderdragonBus {

    private final int travelY = 90;

    private Location arenaStartLocation;

    private Location fromLoc;
    private Location toLoc;

    private Location midLocA;
    private Location midLocB;

    private boolean finalMove = false;

    private double xPerTick;
    private double yPerTick;
    private double zPerTick;

    private List<Player> passangers = new ArrayList<>();

    public CustomEnderDragonBus(Location loc) {
        this(loc, ((CraftWorld) loc.getWorld()).getHandle());
    }

    private CustomEnderDragonBus(Location loc, World notchWorld) {
        super(notchWorld);

        setPosition(loc.getX(), loc.getY(), loc.getZ());

        yaw = loc.getYaw() + 180;
        pitch = 0f;
        while (yaw > 360)
            yaw -= 360;
        while (yaw < 0)
            yaw += 360;

        notchWorld.addEntity(this);

        this.arenaStartLocation = loc;
    }

    @Override
    public void m() {
        if (midLocA != null || toLoc != null) {
            Vector a = fromLoc.toVector();
            Vector b = midLocA != null ? midLocA.toVector() : toLoc.toVector();
            double distX = b.getX() - a.getX();
            double distY = b.getY() - a.getY();
            double distZ = b.getZ() - a.getZ();

            float yaw = 0f, pitch = (float) -Math.atan(distY / Math.sqrt(distX * distX + distZ * distZ));

            if (distX != 0) {
                if (distX < 0) {
                    yaw = (float) (1.5 * Math.PI);
                } else {
                    yaw = (float) (0.5 * Math.PI);
                }
                yaw = yaw - (float) Math.atan(distZ / distX);
            } else if (distZ < 0) {
                yaw = (float) Math.PI;
            }

            setYawPitch(-yaw * 180F / (float) Math.PI - 180F, pitch * 180F / (float) Math.PI - 180F);
        }

        travel();

    }

    @Override
    public void travel() {
        if (this.passangers.isEmpty()) {
            return;
        }

        double myX = locX;
        double myY = locY;
        double myZ = locZ;

        if (finalMove) {
            if ((int) locY > (int) toLoc.getY()) {
                myY -= 0.03d;
            } else if ((int) locY < (int) toLoc.getY())
                myY += 0.03d;
        }
        setPosition(myX, myY, myZ);

        if ((int) locY < travelY) {
            myY += 0.03d;
        }

        if (myX < toLoc.getX()) {
            myX += xPerTick;
        } else {
            myX -= xPerTick;
        }

        if (myZ < toLoc.getZ()) {
            myZ += zPerTick;

            myZ -= zPerTick;
        }

        if ((int) myZ == (int) toLoc.getZ() && Math.abs(myX - (int) toLoc.getX()) <= 1) {
            finalMove = true;
        }

        setPosition(myX, myY, myZ);
    }

    @Override
    public void setMoveTravel() {
        double dist;
        double distX;
        double distY;
        double distZ;
        if (midLocA != null) {
            dist = fromLoc.distance(midLocA);
            distX = fromLoc.getX() - midLocA.getX();
            distY = fromLoc.getY() - midLocA.getY();
            distZ = fromLoc.getZ() - midLocA.getZ();
        } else {
            dist = fromLoc.distance(toLoc);
            distX = fromLoc.getX() - toLoc.getX();
            distY = fromLoc.getY() - toLoc.getY();
            distZ = fromLoc.getZ() - toLoc.getZ();
        }
        double tick = dist / 0.03d;
        xPerTick = Math.abs(distX) / tick;
        zPerTick = Math.abs(distZ) / tick;
        yPerTick = Math.abs(distY) / tick;
    }

    @Override
    public void startTravel(Location toLocation) {

        this.enderTeleportTo(arenaStartLocation.getX(), arenaStartLocation.getY(), arenaStartLocation.getZ());

        this.fromLoc = getEntity().getLocation();

        this.toLoc = toLocation;

        setMoveTravel();
    }

    @Override
    public Entity getEntity() {
        if (bukkitEntity != null) return bukkitEntity;
        return null;
    }

    @Override
    public List<Player> getPassangers() {
        return passangers;
    }

    @Override
    public void addPassanger(Player passanger){
        this.passangers.add(passanger);
    }

    @Override
    public void removePassanger(Player passanger){
        this.passangers.remove(passanger);
    }
}


