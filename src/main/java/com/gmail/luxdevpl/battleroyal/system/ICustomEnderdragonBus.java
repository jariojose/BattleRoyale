/*
 * Copyright (c) 2018.  created by xdev-pl.
 */

package com.gmail.luxdevpl.battleroyal.system;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.List;

public interface ICustomEnderdragonBus {

    void travel();

    void setMoveTravel();

    void startTravel(Location toLocation);

    void addPassanger(Player player);

    void removePassanger(Player player);

    List<Player> getPassangers();

    Entity getEntity();

}
