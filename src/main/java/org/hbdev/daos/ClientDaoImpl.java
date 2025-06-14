package org.hbdev.daos;

import lombok.extern.java.Log;
import org.hbdev.models.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log
public class ClientDaoImpl implements ClientDao {

    private static final String TABLE_NAME = "clients";
    private final Connection connection;
    private final Statement statement;

    public static final ClientDaoImpl instance = new ClientDaoImpl();

    private ClientDaoImpl() {
        try {
            this.connection = DatabaseConnection.getInstance().getConnection();
            this.statement = this.connection.createStatement();
        } catch (SQLException e) {
            log.severe("Failed to initialize ClientDaoImpl: " + e.getMessage());
            throw new RuntimeException("Database connection failed", e);
        }
    }

    @Override
    public Client save(Client client) {
        String query = "INSERT INTO " + TABLE_NAME +
                " (first_name, last_name, address, birth_date, cin, email, phone, client_type, characteristic, characteristic_value) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        executePreparedStatement(client, query);
        log.info("Client saved: " + client);
        return client;
    }

    @Override
    public Client update(Client client, Integer id) {
        if (findById(id) == null) {
            throw new RuntimeException("Client with ID " + id + " not found");
        }

        String query = "UPDATE " + TABLE_NAME + " SET " +
                "first_name = ?, last_name = ?, address = ?, birth_date = ?, cin = ?, " +
                "email = ?, phone = ?, client_type = ?, characteristic = ?, characteristic_value = ? " +
                "WHERE id = " + id;

        executePreparedStatement(client, query);
        log.info("Client updated: " + client);
        return client;
    }

    @Override
    public void deleteById(Integer id) {
        String query = "DELETE FROM " + TABLE_NAME + " WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();
            log.info("Deleted " + rowsAffected + " client(s) with ID " + id);
        } catch (SQLException e) {
            log.severe("Error deleting client by ID: " + e.getMessage());
            throw new RuntimeException("Failed to delete client", e);
        }
    }

    @Override
    public void delete(Client client) {
        deleteById(client.getId());
    }

    @Override
    public void deleteAll() {
        String query = "DELETE FROM " + TABLE_NAME;
        try {
            int rows = statement.executeUpdate(query);
            log.info("Deleted all clients (" + rows + " rows).");
        } catch (SQLException e) {
            log.severe("Error deleting all clients: " + e.getMessage());
            throw new RuntimeException("Failed to delete all clients", e);
        }
    }

    @Override
    public Client findById(Integer id) {
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, id);
            ResultSet result = pstmt.executeQuery();
            if (result.next()) {
                return mapResultSetToClient(result);
            } else {
                log.warning("Client not found with ID: " + id);
                return null;
            }
        } catch (SQLException e) {
            log.severe("Error finding client by ID: " + e.getMessage());
            throw new RuntimeException("Failed to find client", e);
        }
    }

    @Override
    public Iterable<Client> findAll() {
        List<Client> clients = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME;

        try (ResultSet result = statement.executeQuery(query)) {
            while (result.next()) {
                clients.add(mapResultSetToClient(result));
            }
        } catch (SQLException e) {
            log.severe("Error retrieving clients: " + e.getMessage());
            throw new RuntimeException("Failed to fetch clients", e);
        }

        return clients;
    }

    private void executePreparedStatement(Client client, String query) {
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, client.getFirstName());
            pstmt.setString(2, client.getLastName());
            pstmt.setString(3, client.getAddress());
            pstmt.setDate(4, Date.valueOf(client.getBirthDate()));
            pstmt.setString(5, client.getCin());
            pstmt.setString(6, client.getEmail());
            pstmt.setString(7, client.getPhone());
            pstmt.setString(8, client.getClientType());
            pstmt.setString(9, client.getCharacteristic());
            pstmt.setString(10, client.getCharacteristicValue());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            log.severe("SQL execution failed: " + e.getMessage());
            throw new RuntimeException("Failed to execute SQL statement", e);
        }
    }

    private Client mapResultSetToClient(ResultSet result) throws SQLException {
        return Client.builder()
                .id(result.getInt("id"))
                .firstName(result.getString("first_name"))
                .lastName(result.getString("last_name"))
                .address(result.getString("address"))
                .birthDate(result.getDate("birth_date").toLocalDate())
                .cin(result.getString("cin"))
                .email(result.getString("email"))
                .phone(result.getString("phone"))
                .clientType(result.getString("client_type"))
                .characteristic(result.getString("characteristic"))
                .characteristicValue(result.getString("characteristic_value"))
                .build();
    }
}
