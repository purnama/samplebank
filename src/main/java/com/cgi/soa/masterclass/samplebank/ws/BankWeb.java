package com.cgi.soa.masterclass.samplebank.ws;

import java.math.BigDecimal;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;

import com.cgi.soa.masterclass.samplebank.model.Account;
import com.cgi.soa.masterclass.samplebank.model.Transaction;
import com.cgi.soa.masterclass.samplebank.service.Repository;

@WebService
public class BankWeb {
	
	@Inject
	private Repository repository;

	@WebMethod
	public boolean isBalanceCovered(Integer accountNumber, BigDecimal debit){
		Account account = repository.findAccountById(accountNumber);
		return repository.isBalanceCovered(account, debit);
	}
	
	@WebMethod
	public boolean isAccountExist(Integer accountNumber){
		return repository.findAccountById(accountNumber) != null;
	}
	
	@WebMethod
	public void transfer(Integer accountSender, Integer accountRecipient, String purpose, BigDecimal amount){
		Account sender = repository.findAccountById(accountSender);
		Account recipient = repository.findAccountById(accountRecipient);
		Transaction transaction = new Transaction();
		transaction.setAmount(amount);
		transaction.setPurpose(purpose);
		transaction.setRecipient(recipient);
		repository.transfer(sender, transaction);
	}
}
