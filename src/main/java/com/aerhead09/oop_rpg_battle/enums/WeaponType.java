package com.aerhead09.oop_rpg_battle.enums;

public enum WeaponType {
    SWORD(1.0),
    AXE(1.5),
    WAND(0.7),
    STAFF(0.6);

    private final double multiplier;

    WeaponType (double multiplier) {
        this.multiplier = multiplier;
    }

    public double getMultiplier() {
        return multiplier;
    }
}