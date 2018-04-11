package com.gmail.luxdevpl.battleroyal;

import com.gmail.luxdevpl.battleroyal.basic.BattleArena;
import com.gmail.luxdevpl.battleroyal.basic.BuildingMenu;
import com.gmail.luxdevpl.battleroyal.listeners.inventory.InventoryClickList;
import com.gmail.luxdevpl.battleroyal.listeners.player.*;

import com.gmail.luxdevpl.battleroyal.managers.BuildingManager;
import com.gmail.luxdevpl.battleroyal.managers.StructureManager;
import com.gmail.luxdevpl.battleroyal.system.entity.EntityRegister;
import com.gmail.luxdevpl.battleroyal.utils.Tests;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.InvocationTargetException;

public class Main extends JavaPlugin {

    private static Main instance;

    private BuildingManager buildingManager;

    private BuildingMenu buildingMenu;

    private StructureManager structureManager;

    @Override
    public void onLoad(){
        instance = this;
    }

    @Override
    public void onEnable(){
        PluginManager pluginManager = this.getServer().getPluginManager();

        pluginManager.registerEvents(new PlayerInteractList(), this);
        pluginManager.registerEvents(new AsyncPlayerChatList(), this);
        pluginManager.registerEvents(new PlayerMoveList(), this);
        pluginManager.registerEvents(new PlayerJoinList(), this);
        pluginManager.registerEvents(new PlayerQuitList(), this);
        pluginManager.registerEvents(new PlayerToggleSneakList(), this);
        pluginManager.registerEvents(new PlayerHeldItemSlotList(), this);
        pluginManager.registerEvents(new InventoryClickList(), this);

        Bukkit.getWorlds().get(0).getEntities().forEach(Entity::remove);

        Class<?> entityRegisterClazz = EntityRegister.class;
        try {
            EntityRegister entityRegister = (EntityRegister) entityRegisterClazz.getConstructor().newInstance();
            entityRegister.registerEntity();
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }

        this.buildingManager = new BuildingManager();

        this.buildingMenu = new BuildingMenu();

        this.structureManager = new StructureManager();

        buildingManager.initStructures();

        Tests tests = new Tests(this);
        tests.initTestTask();

        this.initArenas();
    }

    @Override
    public void onDisable(){
        Bukkit.getOnlinePlayers().forEach(player -> player.kickPlayer("[BattleRoyale] Our plugin is not supporing reload while players are on the server."));

        instance = null;
    }

    private void initArenas(){
        BattleArena testArena = new BattleArena(Bukkit.getWorlds().get(0), //Arena world
                1, 1, 400, 400,//Arena ID - Players limit - ArenaBorderStartSize - Arena Size
                new Location(Bukkit.getWorlds().get(0), 0, 90, 0), //Arena dragon spawn point.
                new Location(Bukkit.getWorlds().get(0), 40, 90, 40)); //Arena middlePoint

        testArena.create();
    }

    public BuildingManager getBuildingManager() {
        return buildingManager;
    }

    public BuildingMenu getBuildingMenu() {
        return buildingMenu;
    }

    public StructureManager getStructureManager() {
        return structureManager;
    }

    public static Main getInstance(){
        return instance;
    }

}
