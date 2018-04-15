/*
 * Copyright (c) 2018.  created by xdev-pl.
 */

package com.gmail.luxdevpl.battleroyal.managers;

import com.gmail.luxdevpl.battleroyal.basic.types.Building;
import com.gmail.luxdevpl.battleroyal.system.structure.AbstractStructure;
import com.gmail.luxdevpl.battleroyal.system.structure.structures.ConeStructureImpl;
import com.gmail.luxdevpl.battleroyal.system.structure.structures.FloorStructureImpl;
import com.gmail.luxdevpl.battleroyal.system.structure.structures.StairsStructureImpl;
import com.gmail.luxdevpl.battleroyal.system.structure.structures.WallStructureImpl;

import java.util.HashMap;
import java.util.Map;

public class BuildingManager {

    private final Map<Building, AbstractStructure> STRUCTURE_MAP = new HashMap<>();

    public AbstractStructure getStructure(Building structure){
        return STRUCTURE_MAP.get(structure);
    }

    public void initStructures(){
        this.STRUCTURE_MAP.put(Building.FLOOR, new FloorStructureImpl());
        this.STRUCTURE_MAP.put(Building.STAIRS, new StairsStructureImpl());
        this.STRUCTURE_MAP.put(Building.WALL, new WallStructureImpl());
        this.STRUCTURE_MAP.put(Building.CONE, new ConeStructureImpl());
    }

}
