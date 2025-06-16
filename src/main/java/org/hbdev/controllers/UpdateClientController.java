package org.hbdev.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.hbdev.enums.Gender;
import org.hbdev.models.Client;
import org.hbdev.services.ClientService;
import org.hbdev.services.ClientServiceImpl;

import java.time.LocalDate;

public class UpdateClientController {

    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField birthDateField;
    @FXML private TextField cinField;
    @FXML private TextField phoneField;
    @FXML private TextField emailField;
    @FXML private TextField genderField;

    private final ClientService clientService = new ClientServiceImpl();

    private Client client;

    public void setClient(Client client) {
        this.client = client;
        // populate fields
        firstNameField.setText(client.getFirstName());
        lastNameField.setText(client.getLastName());
        birthDateField.setText(String.valueOf(client.getBirthDate()));
        cinField.setText(client.getCin());
        phoneField.setText(client.getPhone());
        emailField.setText(client.getEmail());
        genderField.setText(String.valueOf(client.getGender()));
    }

    @FXML
    private void handleSave() {
        try {
            client.setFirstName(firstNameField.getText());
            client.setLastName(lastNameField.getText());
            client.setBirthDate(LocalDate.parse(birthDateField.getText()));
            client.setCin(cinField.getText());
            client.setPhone(phoneField.getText());
            client.setEmail(emailField.getText());
            client.setGender(Gender.valueOf(genderField.getText()));

            clientService.update(client, client.getId());

            closeWindow();

        } catch (Exception e) {
            // TODO: Show validation error alert
            e.printStackTrace();
        }
    }

    @FXML
    private void handleCancel() {
        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) firstNameField.getScene().getWindow();
        stage.close();
    }
}
