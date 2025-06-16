package org.hbdev.daos;

import lombok.Getter;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.hbdev.statics.DatabaseCredentials;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Log
public class DatabaseConnection {
    @Getter
    private Connection connection;
    private static DatabaseConnection instance;

    private DatabaseConnection() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/Database.config"));
            String driver = bufferedReader.readLine();
            String type = bufferedReader.readLine();
            bufferedReader.close();
            Class.forName(driver);
            log.info("Driver loaded");
            connection = DriverManager.getConnection(DatabaseCredentials.URL.formatted(type), DatabaseCredentials.USERNAME, DatabaseCredentials.PASSWORD);
            log.info("Connection established");

        } catch (FileNotFoundException e) {
            log.warning("Configuration not found");
        } catch (IOException | SQLException e) {
            if(e.getMessage().contains("Access denied for user") || e.getMessage().contains("Unknown database")) {
                log.warning("Wrong credentials");
            }

            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            log.warning("Driver not found");
        }

    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }
}
