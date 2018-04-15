/*
 * Copyright (c) 2018.  created by xdev-pl.
 */

package com.gmail.luxdevpl.battleroyal.basic.game;

import com.gmail.luxdevpl.battleroyal.Main;
import com.gmail.luxdevpl.battleroyal.basic.BattleBullet;
import net.minecraft.server.v1_9_R2.EnumParticle;
import net.minecraft.server.v1_9_R2.PacketPlayOutWorldParticles;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_9_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class BattleShootingSystem implements Runnable {

    private List<BattleBullet> bulletList;

    private final BattleArena arena;

    private BukkitTask task;

    BattleShootingSystem(BattleArena arena) {
        this.arena = arena;

        this.bulletList = new CopyOnWriteArrayList<>();
    }

    public void start() {
        this.task = Bukkit.getScheduler().runTaskTimerAsynchronously(Main.getInstance(), this, 0, 1);
    }

    public void stop(){
        this.task.cancel();

        this.bulletList.clear();
    }

    public void registerBullet(BattleBullet bullet) {
        bulletList.add(bullet);
    }

    @Override
    public void run() {
        bulletList.forEach(BattleBullet::updateTrajectory);
    }
}
