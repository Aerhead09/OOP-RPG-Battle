package com.aerhead09.oop_rpg_battle.core.entity.base;

import com.aerhead09.oop_rpg_battle.system.StatusManager;

public abstract class BaseEntity {

    protected String name;

    protected int hp;
    protected int maxHp;

    protected int atk;
    protected int def;
    protected int matk;
    protected int mdef;

    protected int agi;
    protected int luck;

    protected StatusManager statusManager = new StatusManager();

    // =========================
    // BASIC METHODS
    // =========================
    public boolean isAlive() {
        return hp > 0;
    }

    public void takeDamage(int damage) {
        hp -= damage;
        if (hp < 0) hp = 0;
    }

    public void heal(int value) {
        hp += value;
        if (hp > maxHp) hp = maxHp;
    }

    // =========================
    // GETTERS
    // =========================
    public String getName() { return name; }
    public int getHp() { return hp; }
    public int getAtk() { return atk; }
    public int getDef() { return def; }
    public int getMatk() { return matk; }
    public int getMdef() { return mdef; }
    public int getAgi() { return agi; }
    public int getLuck() { return luck; }

    public StatusManager getStatusManager() {
        return statusManager;
    }
}