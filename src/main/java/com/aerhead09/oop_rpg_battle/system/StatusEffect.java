package com.aerhead09.oop_rpg_battle.system;

import com.aerhead09.oop_rpg_battle.enums.StatusEffectType;

public class StatusEffect {

    private StatusEffectType type;
    private int duration; // turn berapa lama
    private int value;    // damage / buff value

    public StatusEffect(StatusEffectType type, int duration, int value) {
        this.type = type;
        this.duration = duration;
        this.value = value;
    }

    // =========================
    // GETTERS
    // =========================
    public StatusEffectType getType() {
        return type;
    }

    public int getDuration() {
        return duration;
    }

    public int getValue() {
        return value;
    }

    // =========================
    // CORE LOGIC
    // =========================
    public void tick() {
        duration--;
    }

    public boolean isExpired() {
        return duration <= 0;
    }

    // =========================
    // IMPORTANT FIX (WAJIB)
    // =========================
    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void addDuration(int extra, int max) {
        this.duration = Math.min(this.duration + extra, max);
    }

    public void refreshDuration(int fixed) {
        this.duration = fixed;
    }
}