package com.cgi.soa.masterclass.samplebank.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.cgi.soa.masterclass.samplebank.model.Customer;

@Stateless
public class CustomerRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	public List<Customer> getCustomers(){
		return (List<Customer>) entityManager.createQuery("SELECT customer FROM "+Customer.class.getName()+" customer ", Customer.class).getResultList();
	}
	
	public Customer findById(Integer customerId){
		Customer customer = entityManager.find(Customer.class, customerId);
		return customer;
	}
	
	public void persist(Customer customer){
		entityManager.persist(customer);
		entityManager.flush();
	}
	
	public Customer merge(Customer customer){
		entityManager.merge(customer);
		entityManager.flush();
		return entityManager.find(Customer.class, customer.getId());
	}
}
