package com.cgi.soa.masterclass.samplebank.faces;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.cgi.soa.masterclass.samplebank.model.Customer;
import com.cgi.soa.masterclass.samplebank.service.Repository;

@Named
@RequestScoped
public class CustomerBean {

	@Inject
	Repository repository;
	
	Customer customer;
	
	public String create(){
		repository.persist(customer);
		return "/customers/index.xhtml?faces-redirect=true";
	}
	
	public List<Customer> getCustomers(){
		return repository.getCustomers();
	}

	public Customer getCustomer() {
		if(customer == null){
			customer = new Customer();
		}
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
}
