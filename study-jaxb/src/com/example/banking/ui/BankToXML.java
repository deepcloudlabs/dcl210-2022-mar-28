package com.example.banking.ui;

import java.io.File;

import com.example.banking.domain.Bank;
import com.example.banking.domain.CheckingAccount;
import com.example.banking.domain.Customer;
import com.example.banking.domain.SavingsAccount;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;

/**
 *
 * @author Binnur Kurt (binnur.kurt@gmail.com)
 */
public class BankToXML {

	public static void main(String[] args) throws Exception {
		try {
			Bank bank = Bank.getBank();
			Customer customer;

			// Create several customers and their accounts
			bank.addCustomer("Jane", "Simms");
			customer = bank.getCustomer(0);
			customer.addAccount(new SavingsAccount(500.00, 0.05));
			customer.addAccount(new CheckingAccount(200.00, 400.00));

			bank.addCustomer("Owen", "Bryant");
			customer = bank.getCustomer(1);
			customer.addAccount(new CheckingAccount(200.00));

			bank.addCustomer("Tim", "Soley");
			customer = bank.getCustomer(2);
			customer.addAccount(new SavingsAccount(1500.00, 0.05));
			customer.addAccount(new CheckingAccount(200.00));

			bank.addCustomer("Maria", "Soley");
			customer = bank.getCustomer(3);
			// Maria and Tim have a shared checking account
			customer.addAccount(bank.getCustomer(2).getAccount(1));
			customer.addAccount(new SavingsAccount(150.00, 0.05));

			JAXBContext jc = JAXBContext.newInstance(Bank.class);
			Marshaller m = jc.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			m.marshal(bank, new File("resources", "bank.xml"));
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

}
