package com.gmail.luxdevpl.battleroyal.managers;

import com.gmail.luxdevpl.battleroyal.basic.BattleStructure;
import com.gmail.luxdevpl.battleroyal.basic.types.Building;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

public class StructureManager {

    private Map<Integer, BattleStructure> battleStructureMap = new HashMap<>();

    private Map<Location, Integer> locationIntegerMap = new WeakHashMap<>();

    public BattleStructure getStructureByLocation(Location location){
        return this.battleStructureMap.get(this.locationIntegerMap.get(location));
    }

    public void addStructure(Building building, List<Block> fromBlocks){
        int newId = this.battleStructureMap.size() +1;

        BattleStructure structure = new BattleStructure(building, newId);
        structure.setStructureBlocks(fromBlocks);

        this.battleStructureMap.putIfAbsent(newId, structure);

        fromBlocks.forEach(block -> this.locationIntegerMap.putIfAbsent(block.getLocation(), newId));
    }

    public void removeStructure(int structureId){
        this.battleStructureMap.remove(structureId);
    }

}
