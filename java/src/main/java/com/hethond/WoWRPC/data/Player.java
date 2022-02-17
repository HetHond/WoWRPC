package com.hethond.WoWRPC.data;

public class Player extends Unit {
    private Unit target;

    private String zoneText;

    public String getZoneText() { return zoneText; }
    public Unit getTarget() { return target; }

    public void setZoneText(String text) { this.zoneText = text; }
    public void setTarget(Unit unit) { this.target = unit; }
}
