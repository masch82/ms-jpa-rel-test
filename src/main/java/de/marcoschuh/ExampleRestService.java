package de.marcoschuh;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.marcoschuh.entities.Parent;

@RestController
public class ExampleRestService {
	
	@Autowired
	ParentRepository repository;

	@RequestMapping(path="/parent/{id}", method=RequestMethod.GET)
	public List<Parent>  productById(@PathVariable String id) {
		List<Parent> products = new ArrayList<>();
		products.add(repository.findOne(Long.parseLong(id)));
		return products;
	}
	
	@RequestMapping(path="/parents", method=RequestMethod.GET)
	public List<Parent> getAll() {
		return repository.findAll();
	}
	
	
}
