package com.apirest.models.dao;

import com.apirest.models.entities.Client;
import org.springframework.data.repository.CrudRepository;

public interface IClientDAO extends CrudRepository<Client, Integer> {
}
