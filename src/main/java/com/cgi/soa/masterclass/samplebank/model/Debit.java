package com.cgi.soa.masterclass.samplebank.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
@DiscriminatorValue("D")
public class Debit extends Transaction {

	private static final long serialVersionUID = 262561846930510884L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private Account debtor;

	public Account getDebtor() {
		return debtor;
	}

	public void setDebtor(Account debtor) {
		this.debtor = debtor;
	}
}
