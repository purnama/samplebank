package com.cgi.soa.masterclass.samplebank.faces;

import java.util.List;

import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.cgi.soa.masterclass.samplebank.model.Customer;
import com.cgi.soa.masterclass.samplebank.service.CustomerRepository;

@Named
@RequestScoped
public class CustomerBean {

	@Inject
	CustomerRepository customerRepository;
	
	public List<Customer> getCustomers(){
		return customerRepository.getCustomers();
	}
	
	
}
