package de.marcoschuh;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.transaction.Transactional;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.marcoschuh.entities.Parent;

@Service
@WebService
@Transactional
public class JpaRelTestSoapWS {
	
	@Autowired
	ParentRepository repository;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@WebMethod
	@WebResult
	@XmlElementWrapper(name="products")
	@XmlElement(name="product")
	public List<Parent> getByName(@WebParam String name) {
		List<Parent> parents = repository.findByName(name);
		logger.info("Found products: {}", parents);
		return parents;
	}
	
	@WebMethod
	public void addProduct(@WebParam(name="parent") Parent parent) {
		repository.save(parent);
	}
	
	@WebMethod
	@Deprecated // FIXME: This is only for development purposes
	@XmlElementWrapper(name="products")
	@XmlElement(name="product")
	public  List<Parent> getAllProducts() {
		return repository.findAll();
	}
	
	@WebMethod
	@Transactional
	public Parent getById(@WebParam(name="id") long id) {
		Parent parent = repository.getOne(id);
		return parent;
	}
}