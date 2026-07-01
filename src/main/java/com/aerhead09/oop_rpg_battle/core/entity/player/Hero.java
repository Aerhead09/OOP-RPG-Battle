package com.aerhead09.oop_rpg_battle.core.entity.player;

import com.aerhead09.oop_rpg_battle.core.entity.base.BaseEntity;
import com.aerhead09.oop_rpg_battle.enums.JobType;
import com.aerhead09.oop_rpg_battle.enums.WeaponType;
import com.aerhead09.oop_rpg_battle.skill.Skill;

public class Hero extends BaseEntity {

    private JobType job;
    private WeaponType weapon;

    private boolean defending = false;

    private Skill defaultSkill;

    public Hero(String name, JobType job) {
        this.name = name;
        this.job = job;
    }

    public JobType getJob() {
        return job;
    }

    public WeaponType getWeapon() {
        return weapon;
    }

    public void setWeapon(WeaponType weapon) {
        this.weapon = weapon;
    }

    public boolean isDefending() {
        return defending;
    }

    public void setDefending(boolean defending) {
        this.defending = defending;
    }

    public Skill getDefaultSkill() {
        return defaultSkill;
    }

    public void setDefaultSkill(Skill defaultSkill) {
        this.defaultSkill = defaultSkill;
    }
}