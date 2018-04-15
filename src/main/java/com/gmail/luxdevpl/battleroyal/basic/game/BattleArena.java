/*
 * Copyright (c) 2018.  created by xdev-pl.
 */

package com.gmail.luxdevpl.battleroyal.basic.game;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

import com.gmail.luxdevpl.battleroyal.basic.types.GameState;
import com.gmail.luxdevpl.battleroyal.managers.ArenaManager;
import com.gmail.luxdevpl.battleroyal.system.ICustomEnderdragonBus;
import com.gmail.luxdevpl.battleroyal.system.entity.CustomEnderDragonBus;
import com.gmail.luxdevpl.battleroyal.utils.StringUtils;

public class BattleArena {

    private List<BattlePlayer> players;
    private List<BattlePlayer> disabledFlyingMode;

    private GameState gameState;

    private World world;

    private int id, playersLimit, arenaBorderStartSize, arenaSize;

    private final Location signLocation, arenaMiddlePoint;

    private final ICustomEnderdragonBus dragon;

    private final BattleZone zone;

    private final BattleAirDrop airDrop;

    private final BattleShootingSystem battleShootingSystem;

    public BattleArena(World world, int id, int playersLimit, int arenaBorderStartSize, int arenaSize, Location signLocation, Location arenaMiddlePoint){
        this.world = world;

        this.id = id;
        this.playersLimit = playersLimit;
        this.arenaBorderStartSize = arenaBorderStartSize;
        this.arenaSize = arenaSize;

        this.signLocation = signLocation;
        this.arenaMiddlePoint = arenaMiddlePoint;

        this.gameState = GameState.WAITING;

        this.players = new ArrayList<>(this.playersLimit);
        this.disabledFlyingMode = new ArrayList<>(this.playersLimit);

        this.dragon = new CustomEnderDragonBus(new Location(this.signLocation.getWorld(), 5, 90, 0));

        this.zone = new BattleZone(this, this.arenaMiddlePoint);

        this.airDrop = new BattleAirDrop(this);

        this.battleShootingSystem = new BattleShootingSystem(this);
    }

    public int getId() {
        return id;
    }

    public GameState getGameState() {
        return gameState;
    }

    public List<BattlePlayer> getPlayers() {
        return players;
    }

    public void addPlayer(BattlePlayer battlePlayer){
        this.players.add(battlePlayer);

        battlePlayer.setCurrentArena(this);

        this.broadcastMessage("&7" + battlePlayer.getPlayer().getName() + " &3dolaczyl do oczekiwania &7(" + this.players.size() + " / " + this.playersLimit + ")");

        if(players.size() == this.playersLimit){
            //todo liczniki
            this.startGame();

            this.broadcastMessage("&7Gra rozpoczyna sie!");
        }
    }

    private void startTimer(){
        //todo odliczanie do startu.
    }

    private void startGame(){
        this.gameState = GameState.INGAME;

        this.zone.setupBorder();
        this.airDrop.startAirDropTask();
        this.battleShootingSystem.start();

        dragon.startTravel(new Location(signLocation.getWorld(), 145, 90, -125));

        for(BattlePlayer player : this.players){
            Player loopPlayer = player.getPlayer();

            dragon.addPassanger(loopPlayer);
            dragon.getEntity().setPassenger(loopPlayer);
        }

        this.updateSign();
    }

    private void endGame(){
        this.airDrop.stopAirDropTask();
        this.zone.stopShrinking();
        this.battleShootingSystem.stop();

        this.gameState = GameState.WAITING;

        //todo teleport all "spectators" and players to main lobby.
        //todo reset world

        this.updateSign();
    }

    public void removePlayer(BattlePlayer battlePlayer){
        this.players.remove(battlePlayer);

        if(this.players.size() == 0){
            this.endGame();
        }

        this.broadcastMessage("&7" + battlePlayer.getPlayer().getName() + " &3wyszedl z lobby &7(" + this.players.size() + " / " + this.playersLimit + ")");
    }

    public void broadcastMessage(String message){
        players.forEach(player -> player.getPlayer().sendMessage(StringUtils.color(message)));
    }

    public void updateSign(){
        if(signLocation.getWorld().getBlockAt(signLocation).isEmpty()){
            signLocation.getBlock().setType(Material.WALL_SIGN);
        }

        BlockState blockState = signLocation.getWorld().getBlockAt(signLocation).getState();

        if(blockState instanceof Sign) {

            Sign sign = (Sign) blockState;

            sign.setLine(1, StringUtils.color("&4" + this.gameState.toString()));
            sign.setLine(2, StringUtils.color("&4" + Integer.toString(this.players.size()) + " / " + this.playersLimit));

            sign.update();
        }
    }

    public void enableFlying(BattlePlayer battlePlayer){
        this.disabledFlyingMode.remove(battlePlayer);
    }

    public boolean isFlyingDisabled(BattlePlayer player){
        return this.disabledFlyingMode.contains(player);
    }

    public ICustomEnderdragonBus getDragon() {
        return dragon;
    }

    public int getArenaBorderStartSize() {
        return arenaBorderStartSize;
    }

    public World getArenaWorld(){
        return this.world;
    }

    public int getArenaSize() {
        return arenaSize;
    }

    public Location getArenaMiddlePoint() {
        return arenaMiddlePoint;
    }

    public BattleShootingSystem getShootingSystem() {
        return battleShootingSystem;
    }

    public void create(){
        ArenaManager.addArena(this.id, this);

        this.updateSign();
    }

}
