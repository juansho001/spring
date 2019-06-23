package com.search.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.search.model.Data;
import com.search.service.StoredService;

@RestController
@RequestMapping("/api")
public class SearchController {
	
	@Autowired
	private StoredService StoredService;
	
	@PostMapping("/add")
	@ResponseStatus(HttpStatus.CREATED)
	@CrossOrigin(origins = "http://localhost:4200")
	public void add(@RequestBody Data data) {
		StoredService.saveName(data.getText());
	}
	
	@GetMapping("/find/{search}")
	@ResponseStatus(HttpStatus.OK)
	@CrossOrigin(origins = "http://localhost:4200")
	public List<String> find(@PathVariable(value = "search") String search) {
		return StoredService.findNames(search);
	}
	
	@GetMapping("/history")
	@ResponseStatus(HttpStatus.OK)
	@CrossOrigin(origins = "http://localhost:4200")
	public List<String> getHistory() {
		return StoredService.getHistory();
	}
	
	
}
