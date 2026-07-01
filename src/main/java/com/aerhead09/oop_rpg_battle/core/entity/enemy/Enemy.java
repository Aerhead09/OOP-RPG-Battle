package com.aerhead09.oop_rpg_battle.core.entity.enemy;

import com.aerhead09.oop_rpg_battle.core.entity.base.BaseEntity;
import com.aerhead09.oop_rpg_battle.enums.ElementType;
import com.aerhead09.oop_rpg_battle.skill.Skill;

import java.util.List;
import java.util.Random;

public class Enemy extends BaseEntity {

    private ElementType element;

    private List<Skill> skills;

    private static final Random random = new Random();

    public Enemy(String name) {
        this.name = name;
    }

    public ElementType getElement() {
        return element;
    }

    public void setElement(ElementType element) {
        this.element = element;
    }

    public Skill getRandomSkill() {

        if (skills == null || skills.isEmpty()) {
            return null;
        }

        return skills.get(random.nextInt(skills.size()));
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }
}