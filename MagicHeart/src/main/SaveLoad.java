package main;

import java.sql.*;

public class SaveLoad { //SINGLETON
    private static SaveLoad instance;
    private GamePanel gp;
    private Connection c;

    private SaveLoad(GamePanel gp) {
        this.gp = gp;
        connect();
        createTable();
    }

    public static synchronized SaveLoad getInstance(GamePanel gp) {
        if (instance == null) {
            instance = new SaveLoad(gp);
        }
        return instance;
    }

    public void connect() {
        if (c != null) return;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:save.db");
        } catch (Exception e) {
            System.out.println("Eroare la conexiune: " + e.getMessage());
        }
    }

    public void createTable() {
        if (c == null) {
            System.out.println("Conexiunea nu este inițializată.");
            return;
        }
        String sql = "CREATE TABLE IF NOT EXISTS save_data (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "max_life INTEGER, " +
                "life INTEGER, " +
                "player_x INTEGER, " +
                "player_y INTEGER, " +
                "monster_x INTEGER, " +
                "monster_y INTEGER, " +
                "npc_x INTEGER, " +
                "npc_y INTEGER, " +
                "current_map INTEGER, " +
                "varialbila STRING" +
        ");";

        try (Statement stmt = c.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("Eroare creare tabel: " + e.getMessage());
        }
    }

    public void save() {
        String sql = "INSERT OR REPLACE INTO save_data(" +
                "max_life, life, player_x, player_y, monster_x, monster_y, npc_x, npc_y, current_map)" +
                " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = c.prepareStatement(sql)) {
            pstmt.setInt(1, gp.player.maxLife);
            pstmt.setInt(2, gp.player.life);          // corectat: life nu maxLife
            pstmt.setInt(3, gp.player.worldX);
            pstmt.setInt(4, gp.player.worldY);
            pstmt.setInt(5, gp.monster[0].worldX);
            pstmt.setInt(6, gp.monster[0].worldY);
            pstmt.setInt(7, gp.npc[0].worldX);
            pstmt.setInt(8, gp.npc[0].worldY);
            pstmt.setInt(9, gp.mapManager.currentIndex);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Eroare la salvarea în baza de date: " + e.getMessage());
        }

    }

    public void load() {
        String sql = "SELECT * FROM save_data ORDER BY id DESC LIMIT 1";

        try (Statement stmt = c.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                gp.player.maxLife = rs.getInt("max_life");
                gp.player.life = rs.getInt("life");
                gp.player.worldX = rs.getInt("player_x");
                gp.player.worldY = rs.getInt("player_y");
                gp.monster[0].worldX = rs.getInt("monster_x");
                gp.monster[0].worldY = rs.getInt("monster_y");
                gp.npc[0].worldX = rs.getInt("npc_x");
                gp.npc[0].worldY = rs.getInt("npc_y");
                gp.mapManager.currentIndex = rs.getInt("current_map");
            }
        } catch (SQLException e) {
            System.out.println("Eroare la încărcare din DB: " + e.getMessage());
        }
    }

    public void deleteAllSaves() {
        String sql = "DELETE FROM save_data";

        try (Statement stmt = c.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("Toate salvările au fost șterse.");
        } catch (SQLException e) {
            System.out.println("Eroare la ștergerea salvărilor: " + e.getMessage());
        }
    }

    public void close() {
        try {
            if (c != null) c.close();
        } catch (SQLException e) {
            System.out.println("Eroare la închidere: " + e.getMessage());
        }
    }
}
