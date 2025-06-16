package org.hbdev.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.hbdev.models.Client;
import org.hbdev.services.ClientService;
import org.hbdev.services.ClientServiceImpl;

import java.util.List;

public class ClientController {

    @FXML
    private TableView<Client> clientTable;

    @FXML
    private TableColumn<Client, String> firstNameColumn;

    @FXML
    private TableColumn<Client, String> lastNameColumn;

    @FXML
    private TableColumn<Client, String> birthdateColumn;

    @FXML
    private TableColumn<Client, String> cinColumn;

    @FXML
    private TableColumn<Client, String> phoneColumn;

    @FXML
    private TableColumn<Client, String> emailColumn;

    @FXML
    private TableColumn<Client, String> genderColumn;

    @FXML
    private Button insertButton;

    @FXML
    private Button updateButton;

    private final ClientService clientService = new ClientServiceImpl();

    @FXML
    public void initialize() {
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        birthdateColumn.setCellValueFactory(new PropertyValueFactory<>("birthDate")); // assuming birthDate is String or override toString
        cinColumn.setCellValueFactory(new PropertyValueFactory<>("cin"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));

        loadClients();

        insertButton.setOnAction(e -> openAddClientWindow());
        updateButton.setOnAction(e -> openUpdateClientWindow());
    }

    private void openAddClientWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/AddClientView.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Add Client");
            stage.setScene(new Scene(loader.load()));
            stage.showAndWait();
            // After window closes, refresh table:
            loadClients();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void openUpdateClientWindow() {
        Client selected = clientTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            // TODO: show alert "Please select a client to update"
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/UpdateClientView.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Update Client");
            stage.setScene(new Scene(loader.load()));

            UpdateClientController controller = loader.getController();
            controller.setClient(selected);

            stage.showAndWait();
            loadClients();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void loadClients() {
        List<Client> clients = (List<Client>) clientService.findAll();
        clientTable.getItems().setAll(clients);
    }
}
