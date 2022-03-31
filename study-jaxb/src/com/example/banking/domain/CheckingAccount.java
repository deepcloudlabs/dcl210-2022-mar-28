package com.example.banking.domain;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
*
* @author Binnur Kurt (binnur.kurt@gmail.com)
*/
@XmlRootElement(name = "KrediliMevduatHesabi")
public class CheckingAccount extends Account {

	private double overdraftAmount;

	public CheckingAccount() {
		super();
	}

	public CheckingAccount(double initBalance, double overdraftAmount) {
		super(initBalance);
		this.overdraftAmount = overdraftAmount;
	}

	public CheckingAccount(double initBalance) {
		this(initBalance, 0.0);
	}

	public void withdraw(double amount) {
		if (balance < amount) {
			// Not enough balance to cover the amount requested to withdraw
			// Check if there is enough in the overdraft protection (if any)
			double overdraftNeeded = amount - balance;
			if (overdraftAmount >= overdraftNeeded) {
				// Yes, there is overdraft protection and enough to cover the
				// amount
				balance = 0.0;
				overdraftAmount -= overdraftNeeded;
			}
		} else {
			// Yes, there is enough balance to cover the amount
			// Proceed as usual
			balance = balance - amount;
		}
	}

	@XmlAttribute(name = "krediMiktari")
	public double getOverdraftAmount() {
		return overdraftAmount;
	}

	public void setOverdraftAmount(double overdraftAmount) {
		this.overdraftAmount = overdraftAmount;
	}

	@Override
	public String toString() {
		return "CheckingAccount [balance=" + balance + ", overdraftAmount="
				+ overdraftAmount + "]";
	}

}
