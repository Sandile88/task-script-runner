package task.exec.database;

import java.sql.*;
import java.time.LocalDateTime;

public class ExecutionLogger {
    private final String dbUrl = "jdbc:sqlite:logs.db";

    public ExecutionLogger() {
        try (
            Connection conn = DriverManager.getConnection(dbUrl);
            Statement stmt = conn.createStatement()) {
            String sql = """
             CREATE TABLE IF NOT EXISTS scripts_run (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    script_name TEXT NOT NULL,
                    timestamp TEXT NOT NULL,
                    exit_code INTEGER NOT NULL
                );
            """;
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void log(String scriptName, int exitCode) {
        String sql = "INSERT INTO scripts_run (script_name, timestamp, exit_code) VALUES (?, ?, ?)";
        try (
            Connection conn = DriverManager.getConnection(dbUrl); 
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, scriptName);
                pstmt.setString(2, LocalDateTime.now().toString());
                pstmt.setInt(3, exitCode);
                pstmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showLogs() {
        String sql = "SELECT id, script_name, timestamp, exit_code FROM scripts_run ORDER BY timestamp DESC";
        try (
            Connection conn = DriverManager.getConnection(dbUrl);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
        ) {
            System.out.printf("%-3s | %-15s | %-25s | %s%n", "ID", "Script", "Timestamp", "Exit");
            System.out.println("----+-----------------+---------------------------+-------");

            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("script_name");
                String time = rs.getString("timestamp");
                int exit = rs.getInt("exit_code");

                System.out.printf("%-3d | %-15s | %-25s | %d%n", id, name, time, exit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
