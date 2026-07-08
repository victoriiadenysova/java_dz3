package ua.goit.database;

import ua.goit.database.dto.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientService {

    private void validateName(String name) {
        if (name == null || name.trim().length() < 2 || name.trim().length() > 1000) {
            throw new IllegalArgumentException("Client name cannot be null and must be between 2 and 1000 characters long.");
        }
    }

    public long create(String name) {
        validateName(name);
        String sql = "INSERT INTO client (name) VALUES (?)";
        Connection conn = Database.getInstance().getConnection();

        try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getLong(1);
                } else {
                    throw new SQLException("Creating client failed, no generated key obtained.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error occurred while creating client", e);
        }
    }

    public String getById(long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Client ID must be positive.");
        }
        String sql = "SELECT name FROM client WHERE id = ?";
        Connection conn = Database.getInstance().getConnection();

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("name");
                } else {
                    throw new IllegalArgumentException("Client with ID " + id + " does not exist.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error occurred while fetching client by ID", e);
        }
    }

    public void setName(long id, String name) {
        if (id <= 0) {
            throw new IllegalArgumentException("Client ID must be positive.");
        }
        validateName(name);
        String sql = "UPDATE client SET name = ? WHERE id = ?";
        Connection conn = Database.getInstance().getConnection();

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setLong(2, id);
            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated == 0) {
                throw new IllegalArgumentException("Client with ID " + id + " does not exist.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error occurred while updating client name", e);
        }
    }

    public void deleteById(long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Client ID must be positive.");
        }
        String sql = "DELETE FROM client WHERE id = ?";
        Connection conn = Database.getInstance().getConnection();

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted == 0) {
                throw new IllegalArgumentException("Client with ID " + id + " does not exist.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error occurred while deleting client", e);
        }
    }

    public List<Client> listAll() {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT id, name FROM client";
        Connection conn = Database.getInstance().getConnection();

        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                clients.add(new Client(id, name));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error occurred while listing all clients", e);
        }
        return clients;
    }
}
