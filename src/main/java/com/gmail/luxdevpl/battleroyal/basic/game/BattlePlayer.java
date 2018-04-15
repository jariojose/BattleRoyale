/*
 * Copyright (c) 2018.  created by xdev-pl.
 */

package com.gmail.luxdevpl.battleroyal.basic.game;

import com.gmail.luxdevpl.battleroyal.basic.types.WeaponTypes;
import com.gmail.luxdevpl.battleroyal.system.structure.AbstractStructure;

import java.util.*;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class BattlePlayer {

    private UUID uuid;

    private BattleArena currentArena;

    private AbstractStructure structure;

    private List<Block> blockList;

    private Map<WeaponTypes, Long> weaponLongMap;

    public BattlePlayer(UUID uuid){
        this.uuid = uuid;

        this.blockList = new ArrayList<>();

        this.weaponLongMap = new HashMap<>();
    }

    public UUID getUniqueId() {
        return uuid;
    }

    public BattleArena getCurrentArena() {
        return currentArena;
    }

    public void setCurrentArena(BattleArena currentArena) {
        this.currentArena = currentArena;
    }

    public Player getPlayer(){
        return Bukkit.getPlayer(this.uuid);
    }

    public AbstractStructure getStructure() {
        return structure;
    }

    public void setStructure(AbstractStructure structure) {
        this.structure = structure;
    }

    public List<Block> getBlockList() {
        return blockList;
    }

    public Map<WeaponTypes, Long> getWeaponLongMap() {
        return weaponLongMap;
    }

}
