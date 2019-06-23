package com.search.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.search.util.Operations;

@Service
public class StoredService  {
	
	List<String> data = new ArrayList<String>();

	public void saveName(String name) {
		data.add(name);
		saveOperations(Operations.ADD.toString(), name);
	}

	public List<String> findNames(String search) {
		saveOperations(Operations.FIND.toString(), search);
		List<String> results = new ArrayList<String>();
		int counter = 0;
		for (String name : data) {
			if(counter>21)
				break;
			
			if(name.toLowerCase().startsWith(search.toLowerCase())) {
				results.add(name);
			}
			counter++;
		}
		results.add("Results: "+ results.size());
		return results;
	}

	public List<String> getHistory() {
		List<String> history = new ArrayList<String>();
		String file = System.getProperty("user.home") + "/Desktop/history.txt";
		try (BufferedReader br = Files.newBufferedReader(Paths.get(file))) {
			String command;
			while ((command = br.readLine()) != null) {
				history.add(command);
			}
		} catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}
		history.add("Results: "+ history.size());
		return history;
	}

	private void saveOperations(String command, String text) {
		String file = System.getProperty("user.home") + "/Desktop/history.txt";
		try (FileWriter writer = new FileWriter(file, true); BufferedWriter bw = new BufferedWriter(writer)) {
			bw.write(command + " " + text + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
