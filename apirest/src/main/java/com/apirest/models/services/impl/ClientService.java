package com.apirest.models.services.impl;

import com.apirest.models.dao.IClientDAO;
import com.apirest.models.entities.Client;
import com.apirest.models.services.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClientService implements IClientService {

    @Autowired
    private IClientDAO clientDAO;

    @Override
    @Transactional(readOnly = true)
    public List<Client> findAll() {
        return (List<Client>) clientDAO.findAll();
    }

    @Override
    public Client save(Client client) {
        return clientDAO.save(client);
    }

    @Override
    public void delete(Integer id) {
        clientDAO.deleteById(id);
    }

    @Override
    public Client findById(Integer id) {
        return clientDAO.findById(id).orElse(null);
    }
}
