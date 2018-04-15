/*
 * Copyright (c) 2018.  created by xdev-pl.
 */

package com.gmail.luxdevpl.battleroyal.system.weapon;

import com.gmail.luxdevpl.battleroyal.basic.BattleBullet;
import com.gmail.luxdevpl.battleroyal.basic.game.BattlePlayer;
import org.bukkit.Location;

import java.util.function.BiFunction;

public abstract class Weapon {

    private BattlePlayer owner;

    private BiFunction<Location, Double, BattleBullet> bulletCreator;

    public Weapon(BattlePlayer owner, BiFunction<Location, Double, BattleBullet> bulletCreator) {
        this.owner = owner;

        this.bulletCreator = bulletCreator;
    }

    public final void shoot(Double speed) {
        owner.getCurrentArena().getShootingSystem().registerBullet(bulletCreator.apply(owner.getPlayer().getLocation(), speed));
    }
}
