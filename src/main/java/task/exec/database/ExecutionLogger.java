package task.exec.database;

import java.sql.*;

public class ExecutionLogger {
    private final String dbUrl = "jdbc:sqlite:logs.db";

    public ExecutionLogger() {
        try (Connection conn = DriverManager.getConnection(dbUrl);
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
}
