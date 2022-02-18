package com.hethond.WoWRPC.data;

import com.hethond.math.Vector3f;

public class Player extends Unit {
    private String server;

    private Unit target;

    private Vector3f position;
    private String zoneText;

    public String getServer() { return server; }
    public String getZoneText() { return zoneText; }
    public Unit getTarget() { return target; }
    public Vector3f getPosition() { return position; }

    public void setServer(String server) { this.server = server; }
    public void setZoneText(String text) { this.zoneText = text; }
    public void setTarget(Unit unit) { this.target = unit; }
    public void setPosition(Vector3f pos) { this.position = pos; }
}
