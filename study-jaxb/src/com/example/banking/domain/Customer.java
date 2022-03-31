package com.example.banking.domain;

import java.util.LinkedList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlElementRefs;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;



/**
 *
 * @author Binnur Kurt (binnur.kurt@gmail.com)
 */
@XmlRootElement(name = "musteri")
public class Customer {
	private String firstName;
	private String lastName;
	private List<Account> accounts;

	public Customer() {
		accounts = new LinkedList<Account>();
	}

	public Customer(String firstName, String lastName) {
		this();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	@XmlAttribute(name = "ad")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@XmlAttribute(name = "soyad")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Account getAccount(int index) {
		return accounts.get(index);
	}

	public void addAccount(Account acc) {
		accounts.add(acc);
	}

	public int getNumOfAccounts() {
		return accounts.size();
	}

	@XmlElementWrapper
	@XmlElementRefs({ @XmlElementRef(type = CheckingAccount.class), @XmlElementRef(type = SavingsAccount.class) })
	public List<Account> getAccounts() {
		return accounts;
	}

	@Override
	public String toString() {
		return "Customer [firstName=" + firstName + ", lastName=" + lastName + "]";
	}

}
