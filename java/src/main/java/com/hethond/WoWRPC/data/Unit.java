package com.hethond.WoWRPC.data;

import com.hethond.math.Vector3f;

public class Unit {
    private String name;
    private int level;

    private Vector3f position;

    public String getName() { return name; }
    public int getLevel() { return level; }

    public Vector3f getPosition() { return position; }

    public void setName(String name) { this.name = name; }
    public void setLevel(int level) { this.level = level; }

    public void setPosition(Vector3f pos) { this.position = pos; }
}
