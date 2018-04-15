/*
 * Copyright (c) 2018.  created by xdev-pl.
 */

package com.gmail.luxdevpl.battleroyal.basic.game;

import com.gmail.luxdevpl.battleroyal.Main;
import com.gmail.luxdevpl.battleroyal.basic.types.Building;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.List;

public class BattleStructure {

    private List<Location> structureBlocks;

    private Building building;

    private int healthPercentage, structureID;

    public BattleStructure(Building building, int battleStructureID) {
        this.structureID = battleStructureID;
        this.structureBlocks = new ArrayList<>();

        this.healthPercentage = building.getHealth();
        this.building = building;
    }

    public int getHealthPercentage() {
        return healthPercentage;
    }

    public Building getBuilding() {
        return building;
    }

    public int getStructureID() {
        return structureID;
    }

    public void setStructureBlocks(List<Block> structureBlocks) {
        structureBlocks.forEach(block -> this.structureBlocks.add(block.getLocation()));
    }

    public void subtractLive(){
        this.healthPercentage--;

        if(this.healthPercentage <= 0){
            this.structureBlocks.forEach(block -> block.getWorld().getBlockAt(block).setType(Material.AIR));

            Main.getInstance().getStructureManager().removeStructure(this.structureID);
        }
    }

}
