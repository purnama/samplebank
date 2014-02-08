package com.cgi.soa.masterclass.samplebank.faces;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.cgi.soa.masterclass.samplebank.model.Customer;
import com.cgi.soa.masterclass.samplebank.service.CustomerRepository;

@Named
@RequestScoped
public class CustomerBean {

	@Inject
	CustomerRepository customerRepository;
	
	Customer customer;
	
	public String create(){
		customerRepository.persist(customer);
		return "/customers/index.xhtml";
	}
	
	public List<Customer> getCustomers(){
		return customerRepository.getCustomers();
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
