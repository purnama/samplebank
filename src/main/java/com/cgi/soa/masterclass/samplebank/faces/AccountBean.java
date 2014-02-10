package com.cgi.soa.masterclass.samplebank.faces;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.cgi.soa.masterclass.samplebank.model.Account;
import com.cgi.soa.masterclass.samplebank.model.Customer;
import com.cgi.soa.masterclass.samplebank.service.Repository;

@Named
@RequestScoped
public class AccountBean {
	
	@Inject
	Repository repository;
	
	Customer customer;
	
	Account account = new Account();

	public String create(){
		account.setBalance(BigDecimal.ZERO);
		account.setCreateDate(Calendar.getInstance().getTime());
		account.setCustomer(customer);
		customer.getAccounts().add(account);
		customer = repository.merge(customer);
		return "/accounts/index.html?customer="+customer.getId();
	}
	
	public Customer getCustomer(){
		return customer;
	}
	
	public void setCustomer(Customer customer){
		this.customer = customer;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
	
}
