package xyzModern;
public class Item {
	private String itemNo;
	private String itemName;
	private double price;
	private int quantity;
	public Item() {}
	public Item(String itemNo, String itemName, double price, int quantity) {
		this.itemNo = itemNo;
		this.itemName = itemName;
		this.price = price;
		this.quantity = quantity;
	}
	public Item(String itemNo, String itemName, int quantity) {
		this.itemNo = itemNo;
		this.itemName = itemName;
		this.quantity = quantity;
		this.price = 500.00;
	}
	public Item(String itemNo, String itemName) {
		this.itemNo = itemNo;
		this.itemName = itemName;
		this.price = 500;
		this.quantity = 1;
	}
	public String getItemNo() {
		return itemNo;
	}
	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
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
		return "Item No: " + itemNo + "\nItem Name: " + itemName + "\nPrice: $" + price + "\nQuantity: " + quantity;
	}
	
	public void message() {
		System.out.println("1) Log in  \n 2) Log out  3) Register ");
		
		
		
		
	}
}
