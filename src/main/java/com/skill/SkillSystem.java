package com.aerhead09.oop_rpg_battle.skill;

import com.aerhead09.oop_rpg_battle.core.entity.player.Hero;
import com.aerhead09.oop_rpg_battle.core.entity.enemy.Enemy;
import com.aerhead09.oop_rpg_battle.enums.SkillType;
import com.aerhead09.oop_rpg_battle.system.DamageCalculator;
import com.aerhead09.oop_rpg_battle.system.StatusEffect;
import com.aerhead09.oop_rpg_battle.enums.StatusEffectType;

import java.util.Random;

public class SkillSystem {

    private static final Random random = new Random();

    public static void useSkill(Hero caster, Enemy target, Skill skill) {

        System.out.println(caster.getName() + " uses " + skill.getName());

        int damage = 0;

        // =========================
        // MAGIC SKILL
        // =========================
        if (skill.getType() == SkillType.MAGIC) {

            damage = DamageCalculator.calculateMagicDamage(
                    caster.getMatk(),
                    target.getMdef(),
                    caster.getJob(),
                    caster.getLuck(),
                    skill.getElement(),
                    target.getElement()
            );
        }

        // =========================
        // PHYSICAL SKILL
        // =========================
        else if (skill.getType() == SkillType.PHYSICAL) {

            damage = DamageCalculator.calculatePhysicalDamage(
                    caster.getAtk(),
                    target.getDef(),
                    caster.getWeapon(),
                    caster.getJob(),
                    caster.getLuck(),
                    skill.getElement(),
                    target.getElement()
            );
        }

        // =========================
        // HEAL SKILL
        // =========================
        else if (skill.getType() == SkillType.HEAL) {

            int heal = skill.getPower();
            caster.heal(heal);

            System.out.println(caster.getName() + " heals for " + heal);
            return;
        }

        // =========================
        // APPLY DAMAGE
        // =========================
        target.takeDamage(damage);

        System.out.println(target.getName() + " takes " + damage + " damage");

        // =========================
        // STATUS EFFECT (OPTIONAL)
        // =========================
        if (skill.getStatusEffect() != StatusEffectType.NONE) {

            int roll = random.nextInt(100);

            if (roll < skill.getEffectChance()) {

                StatusEffect effect = new StatusEffect(
                        skill.getStatusEffect(),
                        skill.getEffectDuration(),
                        skill.getPower()
                );

                target.getStatusManager().addEffect(effect);

                System.out.println(target.getName()
                        + " is affected by " + skill.getStatusEffect());
            }
        }
    }
}