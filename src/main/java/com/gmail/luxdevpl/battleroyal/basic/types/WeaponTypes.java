/*
 * Copyright (c) 2018.  created by xdev-pl.
 */

package com.gmail.luxdevpl.battleroyal.basic.types;

public enum WeaponTypes {

    ROCKET(1, 0.8, 5000), SHOTGUN(4, 1.5, 2000);

    int shootPartsAmount, shootDelay;

    double bulletSpeed;

    WeaponTypes(int shootPartsAmount, double bulletSpeed, int cooldown) {
         this.shootPartsAmount = shootPartsAmount;

         this.bulletSpeed = bulletSpeed;

         this.shootDelay = cooldown;
    }

    public int getShootParts(){
        return shootPartsAmount;
    }

    public double getBulletSpeed() {
        return bulletSpeed;
    }

    public int getShootDelay() {
        return shootDelay;
    }

}
