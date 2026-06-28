package com.aerhead09.oop_rpg_battle.enums;

import java.util.EnumSet;

public enum JobType {

    SWORDMAN(
            // Base Stats
            55, 18,
            12, 5,
            8, 6,
            7, 5,

            // Growth Multiplier
            1.15, 0.90,
            1.10, 0.70,
            1.00, 0.80,
            0.95, 1.00,

            // Equipment
            EnumSet.of(WeaponType.SWORD),
            EnumSet.of(ArmorType.HEAVY, ArmorType.LIGHT)
    ),

    TANKER(
            70, 12,
            10, 4,
            12, 9,
            5, 4,

            1.35, 0.80,
            1.00, 0.60,
            1.30, 1.20,
            0.75, 0.80,

            EnumSet.of(WeaponType.SWORD),
            EnumSet.of(ArmorType.HEAVY)
    ),

    SUPPORT(
            42, 30,
            6, 10,
            6, 8,
            9, 5,

            0.90, 1.30,
            0.70, 1.15,
            0.80, 1.05,
            1.10, 0.90,

            EnumSet.of(WeaponType.STAFF),
            EnumSet.of(ArmorType.LIGHT, ArmorType.CLOTH)
    ),

    MAGE(
            48, 42,
            5, 14,
            5, 9,
            7, 6,

            1.00, 1.45,
            0.60, 1.35,
            0.75, 1.10,
            0.90, 1.10,

            EnumSet.of(WeaponType.STAFF),
            EnumSet.of(ArmorType.CLOTH)
    );

    // Base Stats

    private final int baseHp;
    private final int baseMp;

    private final int baseAtk;
    private final int baseMatk;

    private final int baseDef;
    private final int baseMdef;

    private final int baseAgi;
    private final int baseLuck;

    // Growth

    private final double hpGrowth;
    private final double mpGrowth;

    private final double atkGrowth;
    private final double matkGrowth;

    private final double defGrowth;
    private final double mdefGrowth;

    private final double agiGrowth;
    private final double luckGrowth;

    // Equipment Restriction

    private final EnumSet<WeaponType> allowedWeapons;
    private final EnumSet<ArmorType> allowedArmors;

    JobType(
            int baseHp,
            int baseMp,

            int baseAtk,
            int baseMatk,

            int baseDef,
            int baseMdef,

            int baseAgi,
            int baseLuck,

            double hpGrowth,
            double mpGrowth,

            double atkGrowth,
            double matkGrowth,

            double defGrowth,
            double mdefGrowth,

            double agiGrowth,
            double luckGrowth,

            EnumSet<WeaponType> allowedWeapons,
            EnumSet<ArmorType> allowedArmors
    ) {

        this.baseHp = baseHp;
        this.baseMp = baseMp;

        this.baseAtk = baseAtk;
        this.baseMatk = baseMatk;

        this.baseDef = baseDef;
        this.baseMdef = baseMdef;

        this.baseAgi = baseAgi;
        this.baseLuck = baseLuck;

        this.hpGrowth = hpGrowth;
        this.mpGrowth = mpGrowth;

        this.atkGrowth = atkGrowth;
        this.matkGrowth = matkGrowth;

        this.defGrowth = defGrowth;
        this.mdefGrowth = mdefGrowth;

        this.agiGrowth = agiGrowth;
        this.luckGrowth = luckGrowth;

        this.allowedWeapons = allowedWeapons;
        this.allowedArmors = allowedArmors;
    }

    // Getter

    public int getBaseHp() {
        return baseHp;
    }

    public int getBaseMp() {
        return baseMp;
    }

    public int getBaseAtk() {
        return baseAtk;
    }

    public int getBaseMatk() {
        return baseMatk;
    }

    public int getBaseDef() {
        return baseDef;
    }

    public int getBaseMdef() {
        return baseMdef;
    }

    public int getBaseAgi() {
        return baseAgi;
    }

    public int getBaseLuck() {
        return baseLuck;
    }

    public double getHpGrowth() {
        return hpGrowth;
    }

    public double getMpGrowth() {
        return mpGrowth;
    }

    public double getAtkGrowth() {
        return atkGrowth;
    }

    public double getMatkGrowth() {
        return matkGrowth;
    }

    public double getDefGrowth() {
        return defGrowth;
    }

    public double getMdefGrowth() {
        return mdefGrowth;
    }

    public double getAgiGrowth() {
        return agiGrowth;
    }

    public double getLuckGrowth() {
        return luckGrowth;
    }

    public EnumSet<WeaponType> getAllowedWeapons() {
        return EnumSet.copyOf(allowedWeapons);
    }

    public EnumSet<ArmorType> getAllowedArmors() {
        return EnumSet.copyOf(allowedArmors);
    }

    // Helper

    public boolean canUseWeapon(WeaponType weaponType) {
        return allowedWeapons.contains(weaponType);
    }

    public boolean canUseArmor(ArmorType armorType) {
        return allowedArmors.contains(armorType);
    }
}