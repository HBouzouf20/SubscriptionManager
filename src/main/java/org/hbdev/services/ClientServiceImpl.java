package org.hbdev.services;

import org.hbdev.daos.ClientDao;
import org.hbdev.daos.ClientDaoImpl;
import org.hbdev.models.Client;

public class ClientServiceImpl implements ClientService {
    private final ClientDao clientDao = ClientDaoImpl.instance;
    @Override
    public Client save(Client entity) {
        return clientDao.save(entity);
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public Client findById(Integer id) {
        return clientDao.findById(id);
    }

    @Override
    public Iterable<Client> findAll() {
        return clientDao.findAll();
    }

    @Override
    public void delete(Client entity) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Client update(Client entity, Integer integer) {
        return clientDao.update(entity, integer);
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }
}
