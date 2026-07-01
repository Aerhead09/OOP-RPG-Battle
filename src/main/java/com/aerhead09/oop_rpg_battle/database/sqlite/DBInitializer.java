package com.aerhead09.oop_rpg_battle.database.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DBInitializer {

    private static final String DB_URL =
            "jdbc:sqlite:src/main/resources/sqlite/game.db";

    private static final String SCHEMA_PATH =
            "src/main/resources/sqlite/schema.sql";

    public static void init() {

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {

            String schema = Files.readString(Paths.get(SCHEMA_PATH));

            stmt.executeUpdate(schema);

            System.out.println("DB initialization success");

        } catch (Exception e) {
            System.out.println("DB initialization failed");
            e.printStackTrace();
        }
    }
}