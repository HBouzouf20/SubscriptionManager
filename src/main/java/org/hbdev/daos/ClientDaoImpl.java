package org.hbdev.daos;

import lombok.extern.java.Log;
import org.hbdev.models.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log
public class ClientDaoImpl implements ClientDao {
    private Statement statement;
    private final Connection connection;
    private final String tableName = "clients";
    public static ClientDaoImpl instance = new ClientDaoImpl();
    private ClientDaoImpl() {
        try {
            statement = DatabaseConnection.getInstance().getConnection().createStatement();
            connection = DatabaseConnection.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public Client save(Client client) {
        String query = "INSERT INTO " + tableName +
                " (id, first_name, last_name, address, birth_date, cin, email, phone) " +
                "VALUES (null, ?, ?, ?, ?, ?, ?, ?)";

        try {
            preparedInsertOrUpdateQuery(client, query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return client;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public Client findById(Integer id) {
        try {
            ResultSet result = statement.executeQuery(String.format("SELECT * FROM %s WHERE ID = %s", tableName, id));
            result.next();
            Client client =   Client.builder()
                    .id(result.getInt("id"))
                    .firstName(result.getString("first_name"))
                    .lastName(result.getString("last_name"))
                    .address(result.getString("address"))
                    .birthDate(result.getDate("birth_date").toLocalDate())
                    .characteristic(result.getString("characteristic"))
                    .characteristicValue(result.getString("characteristic_value"))
                    .email(result.getString("email"))
                    .cin(result.getString("cin"))
                    .build();
            if (client == null) {
                log.warning("Client not found");
                throw new RuntimeException("Client not found");
            }
            return client;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Iterable<Client> findAll() {
        List<Client> clients = new ArrayList<>();
        try {
            ResultSet result = statement.executeQuery(String.format("SELECT * FROM %s", tableName));
            while (result.next()) {
                Client client = Client.builder()
                        .id(result.getInt("id"))
                        .firstName(result.getString("first_name"))
                        .lastName(result.getString("last_name"))
                        .address(result.getString("address"))
                        .birthDate(result.getDate("birth_date").toLocalDate())
                        .characteristic(result.getString("characteristic"))
                        .characteristicValue(result.getString("characteristic_value"))
                        .email(result.getString("email"))
                        .cin(result.getString("cin"))
                        .build();
                clients.add(client);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return clients;
    }

    @Override
    public Client update(Client client, Integer id) {
        Client existClient = this.findById(id);
        String query = "UPDATE " + tableName + " SET " +
                "first_name = ?, " +
                "last_name = ?, " +
                "address = ?, " +
                "birth_date = ?, " +
                "cin = ?, " +
                "email = ?, " +
                "phone = ?, " +
                "client_type = ?, " +
                "characteristic = ?, " +
                "characteristic_value = ? " +
                "WHERE id = " + existClient.getId();

        try {
            preparedInsertOrUpdateQuery(client, query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return client;
    }

    private void preparedInsertOrUpdateQuery(Client client,String query) throws SQLException {
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
            throw new RuntimeException("Error while saving client", e);
        }

    }

    @Override
    public void delete(Client entity) {

    }

    @Override
    public void deleteAll() {

    }
}
