package com.aerhead09.oop_rpg_battle.system;

import com.aerhead09.oop_rpg_battle.enums.StatusEffectType;

import java.util.ArrayList;
import java.util.List;

public class StatusManager {

    private List<StatusEffect> effects = new ArrayList<>();

    // =========================
    // ADD EFFECT (STACK RULE)
    // =========================
    public void addEffect(StatusEffect newEffect) {

        for (StatusEffect e : effects) {

            // =========================
            // STACKING RULE FOR DOT
            // =========================
            if (isDot(e.getType()) && e.getType() == newEffect.getType()) {

                // tidak bikin effect baru, hanya tambah duration
                int newDuration = Math.min(e.getDuration() + newEffect.getDuration(), 6);
                e.setDuration(newDuration);

                return;
            }

            // =========================
            // BUFF/DEBUFF REAPPLY RULE
            // =========================
            if (isBuffOrDebuff(e.getType())
                    && e.getType() == newEffect.getType()) {

                // refresh duration ke 3 turn
                e.setDuration(3);

                return;
            }
        }

        // kalau belum ada, add baru
        effects.add(newEffect);
    }

    // =========================
    // PROCESS TURN
    // =========================
    public void processTurn() {

        for (StatusEffect e : effects) {

            e.tick();

            // DOT DAMAGE
            if (isDot(e.getType())) {
                applyDotDamage(e);
            }

            // BUFF/DEBUFF EFFECT HANDLING
            if (isBuffOrDebuff(e.getType())) {
                applyStatModifier(e);
            }
        }

        effects.removeIf(StatusEffect::isExpired);
    }

    // =========================
    // DOT LOGIC
    // =========================
    private void applyDotDamage(StatusEffect e) {
        // nanti di-attach ke owner (Hero/Enemy)
        // contoh: owner.takeDamage(e.getValue());
    }

    // =========================
    // BUFF/DEBUFF LOGIC
    // =========================
    private void applyStatModifier(StatusEffect e) {
        // multiplier logic:
        // ATK_UP = +1.5
        // ATK_DOWN = x0.8
    }

    // =========================
    // HELPERS
    // =========================
    private boolean isDot(StatusEffectType type) {
        return type == StatusEffectType.BURN
                || type == StatusEffectType.POISON;
    }

    private boolean isBuffOrDebuff(StatusEffectType type) {
        return type == StatusEffectType.ATK_UP
                || type == StatusEffectType.ATK_DOWN
                || type == StatusEffectType.DEF_UP
                || type == StatusEffectType.DEF_DOWN
                || type == StatusEffectType.AGI_UP
                || type == StatusEffectType.AGI_DOWN;
    }

    public List<StatusEffect> getEffects() {
        return effects;
    }
}