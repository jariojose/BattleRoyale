package com.gmail.luxdevpl.battleroyal.basic;

import com.gmail.luxdevpl.battleroyal.basic.types.Building;
import net.minecraft.server.v1_9_R2.NBTTagCompound;
import net.minecraft.server.v1_9_R2.TileEntity;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_9_R2.CraftWorld;

import java.util.List;

public class BattleStructure {

    private Building building;

    private int healthPercentage, structureID;

    public BattleStructure(Building building, int battleStructureID) {
        this.structureID = battleStructureID;

        this.building = building;
    }

    public void prepareStructure(List<Block> fromBlocks){
        for(Block block : fromBlocks){
            TileEntity tileEntity = ((CraftWorld) block.getWorld()).getTileEntityAt(block.getX(), block.getY(), block.getZ());
            NBTTagCompound tagCompound = new NBTTagCompound();

            tagCompound.setInt("structure", this.structureID);

            tileEntity.save(tagCompound);
        }

        this.healthPercentage = building.getHealth();

    }

    public int getHealthPercentage() {
        return healthPercentage;
    }

    public void setHealthPercentage(int healthPercentage){
        this.healthPercentage = healthPercentage;
    }

    public int getStructureID() {
        return structureID;
    }

}
