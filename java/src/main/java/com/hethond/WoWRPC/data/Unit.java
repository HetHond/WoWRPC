package com.hethond.WoWRPC.data;

import com.hethond.math.Vector3f;

public class Unit {
    private String name;
    private int level;

    private int health;
    private int maxHealth;

    public String getName() { return name; }
    public int getLevel() { return level; }
    public int getHealth() { return health; }
    public int getMaxHealth() { return maxHealth; }

    public void setName(String name) { this.name = name; }
    public void setLevel(int level) { this.level = level; }
    public void setHealth(int health) { this.health = health; }
    public void setMaxHealth(int maxHealth) { this.maxHealth = maxHealth; }
}
