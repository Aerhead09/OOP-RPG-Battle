package com.aerhead09.oop_rpg_battle.system;

import com.aerhead09.oop_rpg_battle.enums.ElementType;

public class ElementSystem {

    // =========================
    // ELEMENT MULTIPLIER RULES
    // =========================
    public static double getMultiplier(ElementType attack, ElementType defense) {

        if (attack == ElementType.NONE || defense == ElementType.NONE) {
            return 1.0;
        }

        // FIRE > THUNDER (contoh kamu bisa adjust nanti)
        if (attack == ElementType.FIRE && defense == ElementType.THUNDER) {
            return 1.5;
        }

        // WATER > FIRE
        if (attack == ElementType.WATER && defense == ElementType.FIRE) {
            return 1.5;
        }

        // THUNDER > WATER
        if (attack == ElementType.THUNDER && defense == ElementType.WATER) {
            return 1.5;
        }

        // reverse weakness
        if (attack == ElementType.THUNDER && defense == ElementType.FIRE) {
            return 0.8;
        }

        if (attack == ElementType.FIRE && defense == ElementType.WATER) {
            return 0.8;
        }

        if (attack == ElementType.WATER && defense == ElementType.THUNDER) {
            return 0.8;
        }

        return 1.0;
    }
}