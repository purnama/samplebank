package com.cgi.soa.masterclass.samplebank.faces;

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
		transaction.setDate(Calendar.getInstance().getTime());
		transaction.setRecipient(account);
		account.setBalance(account.getBalance().add(transaction.getAmount()));
		account.getTransactions().add(transaction);
		repository.mergeAccount(account);
		return "/transactions/index.html?account="+account.getId();
	}
	
	public String transfer(){
		Date time = Calendar.getInstance().getTime();
		transaction.setDate(time);
		account.setBalance(account.getBalance().subtract(transaction.getAmount()));
		account.getTransactions().add(transaction);
		Transaction recipient = new Transaction();
		recipient.setAccount(transaction.getAccount());
		recipient.setAmount(transaction.getAmount());
		recipient.setDate(time);
		recipient.setPurpose(transaction.getPurpose());
		recipient.setRecipient(account);
		transaction.getRecipient().getTransactions().add(recipient);
		repository.transfer(account, transaction.getRecipient());
		return "/transactions/index.html?account="+account.getId();
		
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
