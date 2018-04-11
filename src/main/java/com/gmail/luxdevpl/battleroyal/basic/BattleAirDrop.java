package com.gmail.luxdevpl.battleroyal.basic;

import com.gmail.luxdevpl.battleroyal.Main;
import com.gmail.luxdevpl.battleroyal.utils.StringUtils;
import com.gmail.luxdevpl.battleroyal.utils.WorldUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.scheduler.BukkitTask;

import java.util.concurrent.TimeUnit;

public class BattleAirDrop implements Runnable {

    private BattleArena arena;

    private BukkitTask task;

    BattleAirDrop(BattleArena arena) {
        this.arena = arena;
    }

    public void startAirDropTask() {
        this.task = Bukkit.getScheduler().runTaskTimer(Main.getInstance(), this, 20*60, 20*60*3);
    }

    public void stopAirDropTask(){
        task.cancel();
    }

    //Tests :P
    @Override
    public void run() {
        Location randomLocation = WorldUtils.getLocationForDrop(this.arena);

        this.arena.getArenaWorld().spawnFallingBlock(randomLocation, Material.CHEST, (byte) 0);

        this.arena.broadcastMessage(StringUtils.color("&7Pojawil sie zrzut! &3X:" + randomLocation.getX() + " Z: " + randomLocation.getZ()));
    }
}

