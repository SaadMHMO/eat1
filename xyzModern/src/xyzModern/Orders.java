package xyzModern;

import java.util.ArrayList;
import java.util.Scanner;

public class Orders extends ItemCollections{
    ArrayList<ArrayList<Item>>orders = new ArrayList<>();
    ArrayList<Item> salla = new ArrayList<Item>();	

    public void creatOrder(){		
		displayProduct();
		in = new Scanner(System.in);
		System.out.println("enter the num of item to add to salla: ");
		String o = in.nextLine();
		for (int i = 0 ; i < Product.size() ; i++ ){
			if (Product.get(i).getItemNo().equals(o)){
				salla.add(Product.get(i));
			}
		}
	}
    public void deleteOrder(){
        displaySalla();
        System.out.println("enter the num of item to remove from salla: ");
		String o = in.nextLine();
		for (int i = 0 ; i < salla.size() ; i++ ){
			if (salla.get(i).getItemNo().equals(o)){
				salla.remove(Product.get(i));
            }
        }
    }
    public void displayAllOrders(){
        //add to orders Array aftar payment
        orders.add(salla);
    }
    public void invoice(){
        System.out.println(salla);
        double totalPrice = 0 ;
        for (int i = 0 ; i < salla.size() ; i++){
            totalPrice += salla.get(i).getPrice();
        }
        System.out.println("The Total Price: " + totalPrice);
    }
    
    public void displaySalla(){
        System.out.println(salla);
    }
}
