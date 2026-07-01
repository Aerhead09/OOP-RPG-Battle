package com.aerhead09.oop_rpg_battle;

import com.aerhead09.oop_rpg_battle.database.sqlite.DBInitializer;

public class Main {
    public static void main(String[] args) {

        DBInitializer.init();

        System.out.println("Game started");
    }
}