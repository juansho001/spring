package com.apirest.models.services;

import com.apirest.models.entities.Client;

import java.util.List;

public interface IClientService {

    List<Client> findAll();

    Client save(Client client);

    void delete(Integer id);

    Client findById(Integer id);



}
