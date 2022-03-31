package com.example.banking.domain;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Binnur Kurt (binnur.kurt@gmail.com)
 */
@XmlRootElement(name = "hesap")
public class Account {
	protected double balance;

	public Account() {
	}

	protected Account(double balance) {
		this.balance = balance;
	}

	@XmlElement(name = "bakiye")
	public double getBalance() {
		return balance;
	}

	public void deposit(double amt) {
		balance += amt;
	}

	public void setBalance(double amt) {
		balance += amt;
	}

	public void withdraw(double amt) {
		if (balance >= amt)
			balance -= amt;
	}
}
