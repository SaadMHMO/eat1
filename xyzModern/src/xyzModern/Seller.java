package xyzModern;

import java.util.ArrayList;

public class Seller extends User {
      double Rate;
      ArrayList<Item> Product = new ArrayList<Item> (); 
	public double getRate() {
		return Rate;
	}

	public void setRate(double rate) {
		Rate = rate;
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

	public ArrayList<Item> getProduct() {
		return Product;
	}

	public void setProduct(Item product) {
		Product.add(product);
	}
	public void RemoveProduct(Item product) {
		Product.remove(product);
	}

}
