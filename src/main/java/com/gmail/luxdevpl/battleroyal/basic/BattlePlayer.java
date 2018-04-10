package com.gmail.luxdevpl.battleroyal.basic;

import com.gmail.luxdevpl.battleroyal.system.structure.AbstractStructure;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class BattlePlayer {

    private UUID uuid;

    private BattleArena currentArena;

    private AbstractStructure structure;

    private List<Block> blockList = new ArrayList<>();

    public BattlePlayer(UUID uuid){
        this.uuid = uuid;
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

}
