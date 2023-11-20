package xyzModern;

import java.util.ArrayList;
import java.util.Scanner;

public class Seller extends Item {
    double Rate;
	ArrayList<Item> Product = new ArrayList<Item> (); 
	Scanner in;
	public Seller(){}
	public Seller(String itemNo, String itemName, double price, int quantity){
		super(itemNo, itemName, price, quantity);
	}
	public double getRate() {
		return Rate;
	}

	public void setRate(double rate) {
		Rate = rate;
	}

	
	public ArrayList<Item> getProduct() {
		return Product;
	}
	public void RemoveProduct() {
		in = new Scanner(System.in);
		setProduct();
		Item item = new Seller(getItemNo(), getItemName(), getPrice(),getQuantity());
		Product.remove(item);
	}
	public void addProduct(){
		in = new Scanner(System.in);
		setProduct();
		Item item = new Seller(getItemNo(), getItemName(), getPrice(),getQuantity());
		Product.add(item);
	}
	public void setProduct(){
		in = new Scanner(System.in);
		System.out.println("Enter barcode for the product: ");
		setItemNo(in.nextLine());
		in = new Scanner(System.in);
		System.out.println("Enter the product name: ");
		setItemName(in.nextLine());
		in = new Scanner(System.in);
		System.out.println("Enter the product price: ");
		setPrice(in.nextDouble());
		in = new Scanner(System.in);
		System.out.println("Enter the product quantity: ");
		setQuantity(in.nextInt());
	}
	public void displayProduct(){
		System.out.println(Product);
	}
	public String toString(){
		return super.toString();
	}
	

}
