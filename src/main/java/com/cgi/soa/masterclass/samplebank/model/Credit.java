package com.cgi.soa.masterclass.samplebank.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
@DiscriminatorValue("C")
public class Credit extends Transaction {

	private static final long serialVersionUID = -3386264477357394186L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private Account creditor;

	public Account getCreditor() {
		return creditor;
	}

	public void setCreditor(Account creditor) {
		this.creditor = creditor;
	}
}