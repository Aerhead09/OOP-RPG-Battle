package com.aerhead09.oop_rpg_battle.system;

import com.aerhead09.oop_rpg_battle.enums.JobType;
import com.aerhead09.oop_rpg_battle.enums.WeaponType;
import com.aerhead09.oop_rpg_battle.enums.ArmorType;
import com.aerhead09.oop_rpg_battle.enums.ElementType;

import java.util.Random;

public class DamageCalculator {

    private static final Random random = new Random();

    // =========================
    // PHYSICAL ATTACK
    // =========================
    public static int calculatePhysicalDamage(
        int attackerAtk,
        int defenderDef,
        WeaponType weapon,
        JobType job,
        int luck,
        ElementType attackElement,
        ElementType enemyElement
    ) {

        double weaponMultiplier = weapon.getMultiplier();
        double jobMultiplier = job.getAtkGrowth();

        int rawDamage = (int) ((attackerAtk * jobMultiplier) * weaponMultiplier);

        int damage = rawDamage - defenderDef;

        // 🔥 ELEMENT MODIFIER
        double elementMultiplier = ElementSystem.getMultiplier(attackElement, enemyElement);
        damage *= elementMultiplier;

        // crit
        if (isCritical(luck)) {
            damage *= 1.5;
            System.out.println("CRITICAL HIT!");
        }

        return Math.max(damage, 1);
    }

    // =========================
    // MAGIC ATTACK
    // =========================
    public static int calculateMagicDamage(
        int attackerMatk,
        int defenderMdef,
        JobType job,
        int luck,
        ElementType attackElement,
        ElementType enemyElement
    ) {

        double jobMultiplier = job.getMatkGrowth();

        int rawDamage = (int) (attackerMatk * jobMultiplier);

        int damage = rawDamage - defenderMdef;

        // 🔥 ELEMENT MODIFIER
        double elementMultiplier = ElementSystem.getMultiplier(attackElement, enemyElement);
        damage *= elementMultiplier;

        // crit
        if (isCritical(luck)) {
            damage *= 1.5;
            System.out.println("MAGIC CRITICAL!");
        }

        return Math.max(damage, 1);
    }

    // =========================
    // CRIT SYSTEM (LUCK ONLY)
    // =========================
    private static boolean isCritical(int luck) {

        // scaling kecil biar gak rusak balance
        double critChance = luck * 0.5; // 0.5% per point luck

        double roll = random.nextDouble() * 100;

        return roll < critChance;
    }

    // =========================
    // DEFEND MODIFIER (OPTIONAL)
    // =========================
    public static int applyDefend(int damage) {

        return (int) (damage * 0.5);
    }
}