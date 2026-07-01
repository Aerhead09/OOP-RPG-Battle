package com.aerhead09.oop_rpg_battle.skill;

import com.aerhead09.oop_rpg_battle.enums.ElementType;
import com.aerhead09.oop_rpg_battle.enums.SkillType;
import com.aerhead09.oop_rpg_battle.enums.StatusEffectType;

public class Skill {

    private String name;

    private SkillType type;

    private ElementType element;

    private int power;

    private int manaCost;

    //  STATUS EFFECT SUPPORT
    private StatusEffectType statusEffect;
    private int effectChance;
    private int effectDuration;

    public Skill(String name,
                 SkillType type,
                 ElementType element,
                 int power,
                 int manaCost,
                 StatusEffectType statusEffect,
                 int effectChance,
                 int effectDuration) {

        this.name = name;
        this.type = type;
        this.element = element;
        this.power = power;
        this.manaCost = manaCost;
        this.statusEffect = statusEffect;
        this.effectChance = effectChance;
        this.effectDuration = effectDuration;
    }

    // getters
    public String getName() { return name; }
    public SkillType getType() { return type; }
    public ElementType getElement() { return element; }
    public int getPower() { return power; }
    public int getManaCost() { return manaCost; }

    public StatusEffectType getStatusEffect() { return statusEffect; }
    public int getEffectChance() { return effectChance; }
    public int getEffectDuration() { return effectDuration; }
}