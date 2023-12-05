package xyzModern;

import java.util.ArrayList;


public class Item {
	
	private String itemName;
	private double price;
	private int quantity;
	private boolean Approved;
	
	 
	public Item() {}
	public Item( String itemName, double price, int quantity , boolean approved) {		
		this.itemName = itemName;
		this.price = price;
		this.quantity = quantity;
		this.Approved = approved;
	}
	
	
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	

	
	@Override
	public String toString() {
		return "Item Name: " + itemName + "\nPrice: $" + price + "\nQuantity: " + quantity + "\n";
	}
	public boolean isApproved() {
		return Approved;
	}
	public void setApproved(boolean approved) {
		Approved = approved;
	}
	
}
