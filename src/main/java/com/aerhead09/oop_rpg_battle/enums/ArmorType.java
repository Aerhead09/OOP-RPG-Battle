package com.aerhead09.oop_rpg_battle.enums;

public enum ArmorType {
    HEAVY(1.5, 0.85),
    LIGHT(1.2, 1.0),
    CLOTH(1.0, 1.2);

    private final double defMultiplier;
    private final double spdMultiplier;

    ArmorType (double defMultiplier, double spdMultiplier) {
        this.defMultiplier = defMultiplier;
        this.spdMultiplier = spdMultiplier;
    }

    public double getDefMultiplier() {
        return defMultiplier;
    }

    public double getSpdMultiplier() {
        return spdMultiplier;
    }
}