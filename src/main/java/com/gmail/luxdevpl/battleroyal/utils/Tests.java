/*
 * Copyright (c) 2018.  created by xdev-pl.
 */

package com.gmail.luxdevpl.battleroyal.utils;

import com.gmail.luxdevpl.battleroyal.Main;
import com.gmail.luxdevpl.battleroyal.basic.game.BattlePlayer;
import com.gmail.luxdevpl.battleroyal.managers.BattlePlayerManager;
import com.gmail.luxdevpl.battleroyal.system.structure.AbstractStructure;

import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

public class Tests implements Runnable {

    private final Main instance;

    private BukkitTask task;

    public Tests(Main instance) {
        this.instance = instance;
    }

    public void initTestTask() {
        this.task = Bukkit.getScheduler().runTaskTimerAsynchronously(instance, this, 20, 10);
    }

    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if(player.getInventory().getItemInMainHand().getType() == Material.GOLD_HOE){
                BattlePlayer battlePlayer = BattlePlayerManager.getBattlePlayer(player.getUniqueId());

                if(battlePlayer.getCurrentArena() != null) {
                    MathUtils.drawLineBeetweenLocations(player, player.getLocation(), battlePlayer.getCurrentArena().getArenaMiddlePoint());
                }
            }

            if (player.getItemInHand().getType() == Material.BOOK) {
                BattlePlayer battlePlayer = BattlePlayerManager.getBattlePlayer(player.getUniqueId());

                AbstractStructure structure = battlePlayer.getStructure();

                if (structure != null) {
                    structure.visualizeStructure(player, player.getTargetBlock((Set<Material>) null, 4).getLocation());
                }
            }
        }
    }

}