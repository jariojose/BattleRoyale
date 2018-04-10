package com.gmail.luxdevpl.battleroyal.utils;

import com.gmail.luxdevpl.battleroyal.Main;
import com.gmail.luxdevpl.battleroyal.basic.BattlePlayer;
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
        this.task = Bukkit.getScheduler().runTaskTimerAsynchronously(instance, this, 20, 5);
    }

    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
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