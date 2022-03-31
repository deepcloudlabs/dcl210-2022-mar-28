package com.example.banking.domain;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Binnur Kurt (binnur.kurt@gmail.com)
 */
@XmlRootElement(name = "VadeliMevduatHesabi")
public class SavingsAccount extends Account {

	private double interestRate;

	public SavingsAccount() {
		super();
	}

	public SavingsAccount(double initBalance, double interestRate) {
		super(initBalance);
		this.interestRate = interestRate;
	}

	public void accumulateInterest() {
		balance += (balance * interestRate);
	}

	@XmlAttribute(name = "faizOrani")
	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	@Override
	public String toString() {
		return "SavingsAccount [balance=" + balance + ", interestRate=" + interestRate + "]";
	}
}
