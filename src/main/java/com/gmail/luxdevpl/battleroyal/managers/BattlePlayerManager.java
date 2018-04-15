/*
 * Copyright (c) 2018.  created by xdev-pl.
 */

package com.gmail.luxdevpl.battleroyal.managers;

import com.gmail.luxdevpl.battleroyal.basic.game.BattlePlayer;

import java.util.*;

public class BattlePlayerManager {

    private static Map<UUID, BattlePlayer> battlePlayerMap = new HashMap<>();

    public static BattlePlayer getBattlePlayer(UUID uuid){
        return battlePlayerMap.get(uuid);
    }

    public static void addBattlePlayer(UUID uuid, BattlePlayer player){
        battlePlayerMap.putIfAbsent(uuid, player);
    }

    public static void removeBattlePlayer(UUID uuid){
        battlePlayerMap.remove(uuid);
    }

    public static List<BattlePlayer> getBattlePlayers(){
        return new ArrayList<>(battlePlayerMap.values());
    }

}
