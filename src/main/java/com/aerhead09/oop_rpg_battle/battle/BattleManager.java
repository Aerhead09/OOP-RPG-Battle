package com.aerhead09.oop_rpg_battle.battle;

import com.aerhead09.oop_rpg_battle.core.entity.enemy.Enemy;
import com.aerhead09.oop_rpg_battle.core.entity.player.Hero;
import com.aerhead09.oop_rpg_battle.skill.Skill;
import com.aerhead09.oop_rpg_battle.skill.SkillSystem;

import java.util.*;

public class BattleManager {

    private List<Hero> party;
    private List<Enemy> enemies;

    private Scanner scanner = new Scanner(System.in);

    public BattleManager(List<Hero> party, List<Enemy> enemies) {
        this.party = party;
        this.enemies = enemies;
    }

    // =========================
    // MAIN LOOP
    // =========================
    public void startBattle() {

        System.out.println("=== BATTLE START ===");

        while (isBattleRunning()) {

            List<Object> turnOrder = buildTurnOrder();

            for (Object actor : turnOrder) {

                if (!isBattleRunning()) break;

                applyStatusEffects(actor);

                if (actor instanceof Hero hero) {
                    playerTurn(hero);
                } else if (actor instanceof Enemy enemy) {
                    enemyTurn(enemy);
                }
            }
        }

        System.out.println(isPartyAlive() ? "YOU WIN!" : "YOU LOSE!");
    }

    // =========================
    // TURN ORDER
    // =========================
    private List<Object> buildTurnOrder() {

        List<Object> all = new ArrayList<>();
        all.addAll(party);
        all.addAll(enemies);

        all.sort(Comparator.comparingInt(this::getAGI).reversed());

        return all;
    }

    private int getAGI(Object obj) {
        if (obj instanceof Hero h) return h.getAgi();
        if (obj instanceof Enemy e) return e.getAgi();
        return 0;
    }

    // =========================
    // PLAYER TURN
    // =========================
    private void playerTurn(Hero hero) {

        if (!hero.isAlive()) return;

        System.out.println("\n" + hero.getName() + " TURN");
        System.out.println("HP: " + hero.getHp());
        System.out.println("-------------------");
        System.out.println("1. Attack");
        System.out.println("2. Skill");
        System.out.println("3. Defend");
        System.out.println("4. Item");
        System.out.println("5. Flee");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1 -> basicAttack(hero);
            case 2 -> useSkill(hero);
            case 3 -> defend(hero);
            case 4 -> useItem(hero);
            case 5 -> flee(hero);
        }
    }

    // =========================
    // ACTIONS
    // =========================
    private void basicAttack(Hero hero) {

        Enemy target = getFirstAliveEnemy();
        if (target == null) return;

        int damage = hero.getAtk() - target.getDef();
        if (damage < 1) damage = 1;

        target.takeDamage(damage);

        System.out.println(hero.getName() + " attacks " +
                target.getName() + " for " + damage);
    }

    private void useSkill(Hero hero) {

        Enemy target = getFirstAliveEnemy();
        if (target == null) return;

        Skill skill = hero.getDefaultSkill();

        SkillSystem.useSkill(hero, target, skill);
    }

    private void defend(Hero hero) {
        hero.setDefending(true);
        System.out.println(hero.getName() + " is defending");
    }

    private void useItem(Hero hero) {
        System.out.println("Item system not ready");
    }

    private void flee(Hero hero) {

        if (Math.random() > 0.5) {
            System.out.println("You escaped!");
            enemies.clear();
        } else {
            System.out.println("Failed to escape!");
        }
    }

    // =========================
    // ENEMY TURN
    // =========================
    private void enemyTurn(Enemy enemy) {

        if (!enemy.isAlive()) return;

        Hero target = getRandomAliveHero();
        if (target == null) return;

        int damage = enemy.getAtk() - target.getDef();

        if (target.isDefending()) {
            damage *= 0.5;
        }

        if (damage < 1) damage = 1;

        target.takeDamage(damage);

        System.out.println(enemy.getName() + " attacks " +
                target.getName() + " for " + damage);

        target.setDefending(false);
    }

    // =========================
    // STATUS EFFECT PROCESS
    // =========================
    private void applyStatusEffects(Object actor) {

        if (actor instanceof Hero hero) {
            hero.getStatusManager().processTurn();
        }

        if (actor instanceof Enemy enemy) {
            enemy.getStatusManager().processTurn();
        }
    }

    // =========================
    // HELPERS
    // =========================
    private Enemy getFirstAliveEnemy() {
        return enemies.stream().filter(Enemy::isAlive).findFirst().orElse(null);
    }

    private Hero getRandomAliveHero() {
        List<Hero> alive = party.stream().filter(Hero::isAlive).toList();
        if (alive.isEmpty()) return null;
        return alive.get(new Random().nextInt(alive.size()));
    }

    private boolean isBattleRunning() {
        return isPartyAlive() && isEnemyAlive();
    }

    private boolean isPartyAlive() {
        return party.stream().anyMatch(Hero::isAlive);
    }

    private boolean isEnemyAlive() {
        return enemies.stream().anyMatch(Enemy::isAlive);
    }
}