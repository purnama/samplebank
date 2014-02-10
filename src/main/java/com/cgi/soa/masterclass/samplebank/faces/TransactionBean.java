package com.cgi.soa.masterclass.samplebank.faces;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.cgi.soa.masterclass.samplebank.model.Account;
import com.cgi.soa.masterclass.samplebank.model.Transaction;
import com.cgi.soa.masterclass.samplebank.service.Repository;

@Named
@RequestScoped
public class TransactionBean {
	
	@Inject
	Repository repository;
	
	Account account;
	
	Transaction transaction = new Transaction();

	public String debit(){
		account = repository.debit(account, transaction);
		return "/transactions/index.html?faces-redirect=true&account="+account.getId();
	}
	
	public String transfer(){
		account = repository.transfer(account, transaction);
		return "/transactions/index.html?faces-redirect=true&account="+account.getId();
		
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
	
	
	
	
}
