package database;

import play.db.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseQueryHandler {
    private Connection connection;

    public DatabaseQueryHandler() {
        this.connection = DB.getConnection();
    }

    public ResultSet getResults(String query) {
        try {
            Statement statement = connection.createStatement();
            return statement.executeQuery(query);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void executeUpdate(String insertQuery) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(insertQuery);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
