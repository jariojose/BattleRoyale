package com.gmail.luxdevpl.battleroyal.basic;

import com.gmail.luxdevpl.battleroyal.Main;
import com.gmail.luxdevpl.battleroyal.utils.Packets;
import com.gmail.luxdevpl.battleroyal.utils.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.WorldBorder;
import org.bukkit.scheduler.BukkitTask;

public class BattleZone implements Runnable {

    private int centerX, centerZ, timesRan, zoneTour;

    private double size;

    private boolean tourCooldown;

    private WorldBorder worldBorder;

    private BukkitTask task;

    private BattleArena arena;

    BattleZone(BattleArena arena, Location arenaMiddlePoint){
        this.arena = arena;

        this.size = arena.getArenaBorderStartSize();

        this.worldBorder = arena.getArenaWorld().getWorldBorder();

        this.centerX = arenaMiddlePoint.getBlockX();
        this.centerZ = arenaMiddlePoint.getBlockZ();
    }

    public void setupBorder(){
        //Setting the default arena worldBorder size
        this.worldBorder.setSize(this.size);

        //Setting the default middle point for border.
        this.worldBorder.setCenter(this.centerX, this.centerZ);

        //Setting the amount of damage a player takes when outside the border plus the border buffer.
        this.worldBorder.setDamageAmount(2.5);

        //Setting the amount of blocks a player may safely be outside the border before taking damage.
        this.worldBorder.setDamageBuffer(3.5);

        //Setting the warning distance that causes the screen to be tinted red when the player is within the specified number of blocks from the border.
        this.worldBorder.setWarningDistance(5);

        //Starting border closing to default arena middlePoint task.
        this.task = Bukkit.getScheduler().runTaskTimerAsynchronously(Main.getInstance(), this, 20, 20);
    }

    private void updateBorder(){
        this.timesRan++;

        //Moving the border closer to middlePoint
        if(this.zoneTour > 0 && !this.tourCooldown) {
            this.worldBorder.setSize(this.size / this.zoneTour, 25);


        }

        //This game should be over after 4th zoneTour.
        if(this.zoneTour == 4){
            this.task.cancel();

            this.zoneTour = 0;
            this.timesRan = 0;

            this.worldBorder.setSize(this.size);

            //Debug
            Bukkit.broadcastMessage("TASK SIE SKONCZYL, TO POWINIEN BYC KONIEC GRY.");
        }

        //todo comment
        if(timesRan == 150){
            this.timesRan = 0;

            this.zoneTour++;

            this.arena.getPlayers().forEach(battlePlayer -> Packets.sendTitle(battlePlayer.getPlayer(), StringUtils.color("(" + this.zoneTour + ") Oko burzy sie zbliza"), "", 1, 1, 10));
        }
    }

    @Override
    public void run() {
        this.updateBorder();
    }
}
