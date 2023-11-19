package xyzModern;

import java.util.ArrayList;


public class Customer extends User {
	private String customerId;
	private String name;
	private double balance;
	private double spending;
	ArrayList<Item> orders = new ArrayList<Item>();
	
	
	public Customer() {}
	public Customer(String custId, String name, double balance) {
		this.customerId = custId;
		this.name = name;
		this.balance = balance;
	}
	public Customer(String custId, String name) {
		this.customerId = custId;
		this.name = name;
		this.balance = 500;
	}


	public void buyItem(Item item, int quantity) {
		if(quantity > 0 && quantity <= item.getQuantity()) {
			double cost = quantity * item.getPrice();
			if(cost <= getBalance()) {
				Item requiredItem = new Item();
				requiredItem.setItemNo(item.getItemNo());
				requiredItem.setItemName(item.getItemName());
				requiredItem.setPrice(item.getPrice());
				requiredItem.setQuantity(quantity);
				setBalance(getBalance() - cost); 
				item.setQuantity(item.getQuantity() - quantity);
				setTotalSpending(item.getQuantity());
				System.out.println("The payment is successfully done!");
				print();
			}else {
				System.out.println("Insufficent balance!");
			}
		}else {
			System.out.println("Sorry, it is out of stock!");
		}
	}
	public void print() {
		System.out.println("Customer Details:");
		System.out.println("-----------------");
		System.out.println(toString());
		System.out.println("Payment History");
		
	}
	public String toString() {
		return "Customer Id: " + getID() + " Customer Name: " + getName() + " Balance: $" +getBalance() + "\n";
	}
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void setID(int iD) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public int getPhoneNumber() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void setPhoneNumber(int phoneNumber) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public String getEmail() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setEmail(String email) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getPassword'");
	}
	@Override
	public void setPassword(String password) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'setPassword'");
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public ArrayList<Item> getOrders() {
		return orders;
	}
	public void setOrders(Item item) {
		orders.add(item);
	}
	public void Invoices() {
		System.out.println(orders);
		System.out.println();
		
	}
	public double getTotalSpending() {
		return spending;
	}
	public void setTotalSpending(double spending) {
		this.spending =+ spending;
	}
	
	
	
}