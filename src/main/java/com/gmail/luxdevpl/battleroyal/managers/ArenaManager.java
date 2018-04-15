/*
 * Copyright (c) 2018.  created by xdev-pl.
 */

package com.gmail.luxdevpl.battleroyal.managers;

import com.gmail.luxdevpl.battleroyal.basic.game.BattleArena;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArenaManager {

    private static Map<Integer, BattleArena> arenas = new HashMap<>();

    public static BattleArena getBattleArena(int arenaId){
        return arenas.get(arenaId);
    }

    public static void addArena(int id, BattleArena arena){
        arenas.putIfAbsent(id, arena);
    }

    public static void removeArena(int id){
        arenas.remove(id);
    }

    public static List<BattleArena> getArenas(){
        return new ArrayList<>(arenas.values());
    }

}
