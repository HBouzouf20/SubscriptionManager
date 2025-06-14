package org.hbdev.services;

import lombok.extern.java.Log;
import org.hbdev.daos.ClientDaoImpl;
import org.hbdev.models.Client;

import java.util.List;

@Log
public class ClientServiceImpl implements ClientService {

    private final ClientDaoImpl clientDao = ClientDaoImpl.instance;

    @Override
    public Client save(Client client) {
        log.info("Saving client: " + client);
        return clientDao.save(client);
    }

    @Override
    public Client update(Client client, Integer id) {
        log.info("Updating client with ID " + id);
        return clientDao.update(client, id);
    }

    @Override
    public void deleteById(Integer id) {
        log.info("Deleting client by ID: " + id);
        clientDao.deleteById(id);
    }

    @Override
    public void delete(Client client) {
        log.info("Deleting client: " + client);
        clientDao.delete(client);
    }

    @Override
    public void deleteAll() {
        log.warning("Deleting all clients.");
        clientDao.deleteAll();
    }

    @Override
    public Client findById(Integer id) {
        log.info("Finding client by ID: " + id);
        return clientDao.findById(id);
    }

    @Override
    public List<Client> findAll() {
        log.info("Fetching all clients.");
        return (List<Client>) clientDao.findAll();
    }

    @Override
    public long count() {
        return findAll().size();
    }

    @Override
    public boolean existsById(Integer id) {
        return findById(id)!=null;
    }
}
