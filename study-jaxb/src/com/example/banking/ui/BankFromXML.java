package com.example.banking.ui;

import java.io.File;
import java.util.function.Consumer;

import com.example.banking.domain.Bank;
import com.example.banking.domain.Customer;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

/**
 *
 * @author Binnur Kurt (binnur.kurt@gmail.com)
 */
public class BankFromXML {
	private static final Consumer<Customer> PRINT_CUSTOMER = System.err::println;
	private static final Consumer<Customer> PRINT_CUSTOMER_ACCOUNTS = customer -> customer.getAccounts().forEach(System.err::println);
	
	public static void main(String[] args) {
		try {
			JAXBContext jc = JAXBContext.newInstance(Bank.class);
			Bank bank = Bank.getBank();
			Unmarshaller u = jc.createUnmarshaller();
			bank = (Bank) u.unmarshal(new File("resources", "bank.xml"));
			bank.getCustomers().forEach( PRINT_CUSTOMER.andThen(PRINT_CUSTOMER_ACCOUNTS) );
		} catch (JAXBException ex) {
			ex.printStackTrace(System.err);
		}
	}

}
