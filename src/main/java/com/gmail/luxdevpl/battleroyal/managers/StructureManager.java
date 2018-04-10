package com.gmail.luxdevpl.battleroyal.managers;

import com.gmail.luxdevpl.battleroyal.basic.BattleStructure;
import com.gmail.luxdevpl.battleroyal.basic.types.Building;
import org.bukkit.block.Block;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StructureManager {

    private Map<Integer, BattleStructure> battleStructureMap = new HashMap<>();

    public BattleStructure getStrucureById(int structureId){
        return this.battleStructureMap.get(structureId);
    }

    public void addStructure(Building building, List<Block> fromBlocks){
        BattleStructure structure = new BattleStructure(building, this.battleStructureMap.size()+1);

        this.battleStructureMap.putIfAbsent(structure.getStructureID(), structure);

        structure.prepareStructure(fromBlocks);
    }

    public void removeStructure(int structureId){
        this.battleStructureMap.remove(structureId);
    }

}
