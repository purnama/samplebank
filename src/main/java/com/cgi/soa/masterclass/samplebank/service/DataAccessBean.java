package com.cgi.soa.masterclass.samplebank.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.cgi.soa.masterclass.samplebank.model.Customer;

@Stateless
public class DataAccessBean {

	@PersistenceContext
	private EntityManager entityManager;
	
	public List<Customer> getCustomers(){
		
		return (List<Customer>) entityManager.createQuery("SELECT customer FROM "+Customer.class.getName()+" ;", Customer.class).getResultList();
		
	}
}
