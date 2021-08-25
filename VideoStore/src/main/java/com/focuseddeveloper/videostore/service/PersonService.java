package com.focuseddeveloper.videostore.service;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.focuseddeveloper.videostore.model.Person;

@Service
public class PersonService {
	
	List<Person> personList = new ArrayList<Person>();

	public List<Person> getCast(String peopleString){
		
		String queryString = MovieDBHelper.buildPersonQuery(peopleString);
		   
		ObjectMapper objectMapper = new ObjectMapper();
		URL src;
		
		try {
			src = new URL(queryString);
						
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			
			JsonNode jsonNode = objectMapper.readTree(src);
			List<JsonNode> nodeList = jsonNode.findParents("known_for_department");	
			
			personList.clear();
			
			for(int i = 0; i < nodeList.size(); i++) {
				Person	person = new Person();
				
				int id = nodeList.get(i).get("id").asInt();
				String name = nodeList.get(i).get("name").asText();	
				String department = nodeList.get(i).get("known_for_department").asText();		
				
				person.setId(id);
				person.setName(name);
				person.setDepartment(department);
				
				
				personList.add(person);
				
				if(personList.size() == 5) {
					break;
				}
			}
	
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return this.personList;
	}
		
		
}
