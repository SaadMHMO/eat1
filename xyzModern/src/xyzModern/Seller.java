package xyzModern;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.management.loading.PrivateClassLoader;

public class Seller extends User {
    double Rate;
    String fill = "pro.txt";
	private UserCollections userCollections;
	List<Item>Product ;
    List<Integer> rate;

	public Seller(){};
	public Seller(String name , String pass , UserCollections Users){
		super(name,pass);
        setSeller(true);
        this.userCollections = Users;
        Product = new ArrayList<>();
        rate = new ArrayList<>();
	}
	
    public void sellerInterface(){
        Scanner in = new Scanner(System.in);
        int i = 0;
        while(i == 10){
            System.out.println("Seller Interface:");
            System.out.println("1) Add Product.");
            System.out.println("2) Remove Product.");
            System.out.println("3) Display All Products.");
            System.out.println("4) Display Selling Statistics.");
            System.out.println("5) Exit.");

            int choise = in.nextInt();
            switch (choise) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    removeProduct();
                    break;
                case 3:
                    displayAllProducts();
                    break;
                case 4:
                    displaySellingStatistics();
                    break;
                case 5:
                    i = 10;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void displaySellingStatistics() {
        System.out.println("Selling Statistics:");

        List<Item> products = getProduct();

        if (products.isEmpty()) {
            System.out.println("No products available for selling.");
        } else {
            int totalProducts = 0;
            double totalRevenue = 0;

            for (Item product : products) {
                totalProducts += product.getQuantity();
                totalRevenue += product.getPrice() * product.getQuantity();
            }

            System.out.println("Total Products Available: " + totalProducts);
            System.out.println("Total Revenue: $" + totalRevenue);
        }
    }

    public void displayAllProducts(){
        if (Product.isEmpty()){
            System.out.println("Sorry! ther is no products so far.");
        }
        else{
            System.out.println(Product);
        }
    }
	private void addProduct() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the product name:");
        String name = scanner.next();

        int price;
        while (true) {
            System.out.println("Enter the product price:");
            if (!(name.isEmpty())) {
                price = scanner.nextInt();
                break;
            } else {
                System.out.println("Invalid input. Please enter a valid integer for price.");
                scanner.next();
            }
        }

        int quantity;
        while (true) {
            System.out.println("Enter the product quantity:");
            if (!(price == 0)) {
                quantity = scanner.nextInt();
                break;
            } else {
                System.out.println("Invalid input. Please enter a valid integer for quantity.");
                scanner.next();
            }
        }

        Item item = new Item(name, price, quantity, false);
        Product.add(item);
        

        System.out.println("Product added successfully. Please wait until the admin approves it.");
    }
    private void removeProduct() {
        Scanner scanner = new Scanner(System.in);

        if (Product.isEmpty()) {
            System.out.println("No products available to remove.");
            return;
        }

        System.out.println("Enter the name of the product to remove:");
        String productName = scanner.next();

        for (int i = 0 ; i < Product.size() ; i++){
            if (Product.get(i).equals(productName)){
                Product.remove(i);
            }
        }
        

        

        System.out.println("Product removed successfully.");
    }

    public void addRating(int rate) {
        this.rate.add(rate);
    }

    public double calculateAverageRating() {
        if (rate.isEmpty()) {
            return 0.0;
        }

        int sum = 0;
        for (int rati : rate) {
            sum += rati;
        }

        return (double) sum / rate.size();
    }

	public String toString(){
		return super.toString();
	}
	@Override
	public String getuserName() {
        return this.userName;
    }

    @Override
    public void setuserName(String name) {
        this.userName = name;
    }


    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }
	
	

	
    public UserCollections getUserCollections() {
        return userCollections;
    }
    public void setUserCollections(UserCollections userCollections) {
        this.userCollections = userCollections;
    }
    public List<Item> getProduct() {
        return Product;
    }
    public void setProduct(List<Item> product) {
        Product = product;
    }
	protected void saveProducts() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("products_" + getuserName() + ".ser"))) {
            oos.writeObject(Product);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    protected void loadProducts() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("products_" + getuserName() + ".ser"))) {
            Product = (List<Item>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No previous products found for seller: " + getuserName());
        }
    }

    	

	
	
}
