package com.gmail.luxdevpl.battleroyal.basic.types;

public enum Building {

    STAIRS(10), FLOOR(10), WALL(15), CONE(20);

    private int health;

    Building(int health) {
        this.health = health;
    }

    public int getHealth(){
        return this.health;
    }

}
