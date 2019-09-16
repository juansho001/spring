package com.apirest.controllers;

import com.apirest.models.entities.Client;
import com.apirest.models.services.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api")
public class ClientRestController {

    @Autowired
    private IClientService clientService;

    @GetMapping("/clients")
    public List<Client> getAll(){
        return clientService.findAll();
    }

    @GetMapping("/clients/{id}")
    public ResponseEntity<?> getClient(@PathVariable Integer id){
        Map<String, Object> response = new HashMap<>();
        Client client = null;
        try {
            client = clientService.findById(id);
        }catch (DataAccessException e){
            response.put("message", "Error query on database");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(client==null){
            response.put("message", "The client with id: "+id+ " no exist");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Client>(client, HttpStatus.OK);
    }

    @PostMapping("/clients")
    public ResponseEntity<?> create(@Valid @RequestBody Client client, BindingResult bindingResult){
        Map<String, Object> response = new HashMap<>();
        if(bindingResult.hasErrors()){
            List<String> errors = bindingResult.getFieldErrors().stream()
                    .map(fieldError -> "Field '"+fieldError.getField()+"' "+fieldError.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("error", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        Client clientNew = null;
        try {
            client.setCreatedAt(new Date());
            client = clientService.save(client);
        }catch (DataAccessException e){
            response.put("message", "Error insert on database");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("message", "User created succsess");
        response.put("client", client);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/clients/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Client client, BindingResult bindingResult, @PathVariable Integer id){
        Map<String, Object> response = new HashMap<>();
        if(bindingResult.hasErrors()){
            List<String> errors = bindingResult.getFieldErrors().stream()
                    .map(fieldError -> "Field '"+fieldError.getField()+"' "+fieldError.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("error", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        Client currentClient = clientService.findById(id);
        Client clientUpdated = null;

        if(currentClient==null){
            response.put("message", "The client with id: "+id+ " no exist");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        try{
            currentClient.setName(client.getName());
            currentClient.setLastName(client.getLastName());
            currentClient.setEmail(client.getEmail());
            clientUpdated = clientService.save(currentClient);
        }catch (DataAccessException e){
            response.put("message", "Error update on database");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("message", "User updated succsess");
        response.put("client", client);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/clients/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        Map<String, Object> response = new HashMap<>();
        try{
            clientService.delete(id);
        }catch (DataAccessException e){
            response.put("message", "Error delete on database");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message", "User deleted succsess");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
