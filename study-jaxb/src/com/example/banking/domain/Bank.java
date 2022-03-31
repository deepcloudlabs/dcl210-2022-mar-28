package com.example.banking.domain;

import java.util.LinkedList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlElementRefs;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author Binnur Kurt (binnur.kurt@gmail.com)
 */
@XmlRootElement(name = "banka")
public class Bank {
	private List<Customer> customers;
	private volatile static Bank instance = null;

	public static Bank getBank() {
		if (instance == null) {
			synchronized (Bank.class) {
				if (instance == null)
					instance = new Bank();
			}
		}
		return instance;
	}

	private Bank() {
		customers = new LinkedList<Customer>();
	}

	public void addCustomer(String f, String l) {
		customers.add(new Customer(f, l));
	}

	public Customer getCustomer(int index) {
		return customers.get(index);
	}

	public int getNumOfCustomers() {
		return customers.size();
	}

	@XmlElementWrapper
	@XmlElementRefs({ @XmlElementRef(type = Customer.class), }
	// Above are the concrete impl'ns of SourceBrightness
	)
	public List<Customer> getCustomers() {
		return customers;
	}

}
