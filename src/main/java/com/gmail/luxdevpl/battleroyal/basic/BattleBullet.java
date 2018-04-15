/*
 * Copyright (c) 2018.  created by xdev-pl.
 */

package com.gmail.luxdevpl.battleroyal.basic;

import org.bukkit.Location;
import org.bukkit.util.Vector;

public abstract class BattleBullet {

    private Vector velocity;

    private Location location;

    public BattleBullet(Location location, double speed) {
        this.location = location;

        this.velocity = location.getDirection().multiply(speed);
    }

    protected Vector getVelocity() {
        return velocity;
    }

    public Location getLocation() {
        return location;
    }

    protected double getSpeed() {
        return velocity.length();
    }

    protected void setSpeed(double newSpeed) {
        this.velocity = velocity.normalize().multiply(newSpeed);
    }

    public abstract void updateTrajectory();
}
