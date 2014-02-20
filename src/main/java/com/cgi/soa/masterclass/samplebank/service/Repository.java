package com.cgi.soa.masterclass.samplebank.service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.cgi.soa.masterclass.samplebank.model.Account;
import com.cgi.soa.masterclass.samplebank.model.Customer;
import com.cgi.soa.masterclass.samplebank.model.Transaction;

@Stateless
public class Repository {

	@PersistenceContext
	private EntityManager entityManager;
	
	public List<Customer> getCustomers(){
		return (List<Customer>) entityManager.createQuery("SELECT customer FROM "+Customer.class.getName()+" customer ", Customer.class).getResultList();
	}
	
	public Customer findById(Integer customerId){
		Customer customer = entityManager.find(Customer.class, customerId);
		return customer;
	}
	
	public Account findAccountById(Integer accountId){
		Account account = entityManager.find(Account.class, accountId);
		return account;
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
	
	public Account mergeAccount(Account account){
		entityManager.merge(account);
		entityManager.flush();
		return entityManager.find(Account.class, account.getId());
	}
	
	public Account transfer(Account account, Transaction transaction){
		Date time = Calendar.getInstance().getTime();
		BigDecimal amount = transaction.getAmount();
		transaction.setAccount(account);
		transaction.setDate(time);
		transaction.setAmount(transaction.getAmount().multiply(BigDecimal.valueOf(-1)));
		account.setBalance(account.getBalance().add(transaction.getAmount()));
		account.getTransactions().add(transaction);
		Transaction recipient = new Transaction();
		recipient.setAccount(transaction.getAccount());
		recipient.setAmount(amount);
		recipient.setDate(time);
		recipient.setPurpose(transaction.getPurpose());
		recipient.setRecipient(account);
		transaction.getRecipient().getTransactions().add(recipient);
		transaction.getRecipient().setBalance(transaction.getRecipient().getBalance().add(amount));
		entityManager.merge(account);
		entityManager.merge(transaction.getRecipient());
		entityManager.flush();
		return entityManager.find(Account.class, account.getId());
	}
	
	public Account debit(Account account, Transaction transaction){
		transaction.setAccount(account);
		transaction.setDate(Calendar.getInstance().getTime());
		transaction.setRecipient(account);
		account.setBalance(account.getBalance().add(transaction.getAmount()));
		account.getTransactions().add(transaction);
		return mergeAccount(account);
	}
	
	public Boolean isBalanceCovered(Account account, BigDecimal debit){
		return account.getBalance().subtract(debit).compareTo(BigDecimal.ZERO) >= 0;
	}
}
