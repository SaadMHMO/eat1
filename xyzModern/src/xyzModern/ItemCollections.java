/*package xyzModern;


import java.util.Scanner;

public class ItemCollections extends Item {
    Scanner in;
    Item item;
    
    public ItemCollections(){}
    public void RemoveProduct() {
		in = new Scanner(System.in);
        System.out.println("enter barcode for the product you want to remove it: ");
        String s = in.nextLine();
		for (int i = 0 ; i < Product.size()  ; i++){
            if (Product.get(i).getItemNo().equals(s)){
                Product.remove(i);
            }
        }
		
	}
	public void addProduct(){
		
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
		item = new Item(getItemNo(), getItemName(),getPrice(),getQuantity());
		
	    Product.add(item);		
	}
	public void displayProduct(){
		
		System.out.println(Product);
	}
	
}*/
