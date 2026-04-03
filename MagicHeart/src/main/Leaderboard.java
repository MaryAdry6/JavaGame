package main;

import java.sql.*;
import java.util.ArrayList;

public class Leaderboard {//SINGLETON

    private static Leaderboard instance;
    private Connection c;

    private Leaderboard() {}

    public static synchronized Leaderboard getInstance() {
        if (instance == null) {
            instance = new Leaderboard();
        }
        return instance;
    }

    public void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:HighScore.db");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Eroare la conexiune baza de date: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public void createTable() {
        if (c == null) {
            System.out.println("Conexiunea nu este inițializată.");
            return;
        }

        String sql = "CREATE TABLE IF NOT EXISTS scores ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "username TEXT NOT NULL, "
                + "time INTEGER "
                + ");";


        try (Statement stmt = c.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("Eroare creare tabel: " + e.getMessage());
        }
    }

    public void newEntry(String username, int time) {
        String sql = "INSERT INTO scores(username, time) VALUES(?,?)";
        try (PreparedStatement pstmt = c.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setInt(2, time);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Eroare la salvare: " + e.getMessage());
        }
    }

    public ArrayList<String> getBest(int limit) {
        ArrayList<String> top = new ArrayList<>();
        String sql = "SELECT username, time FROM scores ORDER BY time ASC LIMIT ?";
        try (PreparedStatement pstmt = c.prepareStatement(sql)) {
            pstmt.setInt(1, limit);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                top.add(rs.getString("username") + " - " + rs.getInt("time") + " secunde");
            }
        } catch (SQLException e) {
            System.out.println("Eroare top scoruri: " + e.getMessage());
        }

        return top;
    }

    public void close() {
        try {
            if (c != null) c.close();
        } catch (SQLException e) {
            System.out.println("Eroare la închidere: " + e.getMessage());
        }
    }
}
