package xyzModern;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;


public class Customer extends User {
	private UserCollections Users;
	List<Orders> orders ; 
	private double balance;
	UserCollections userCollections;
	
	
	public Customer() {}

	public Customer(String name ,String pass , double balance , UserCollections Users){
		super(name, pass);
		this.orders = new ArrayList<>();
		this.balance = balance;
		this.userCollections = Users;
		setCustomer(true);
	}

	


	
	public String toString() {
		return super.toString();
	}
	
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
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
	
    public void CustInterface() {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("Customer Interface:");
            System.out.println("1) Display Orders.");
            System.out.println("2) Create Order.");
            System.out.println("3) Delete Order.");
            System.out.println("4) Display Balance.");
            System.out.println("5) Rate Seller.");
            System.out.println("6) Exit.");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    displayOrders();
                    break;
                case 2:
                    createOrder();
                    break;
                case 3:
                    deleteOrder();
                    break;
                case 4:
                    displayBalance();
                    break;
                case 5:
                    rateSeller();
                    break;
                case 6:
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
	
	private void displayOrders() {
        loadOrders();

        if (orders.isEmpty()) {
            System.out.println("No orders available.");
        } else {
            System.out.println("Your Orders:");
            for (Orders order : orders) {
                Item product = order.getItem();
                if (product != null) {
                    System.out.println("Product: " + product.getItemName());
                    System.out.println("Quantity: " + order.getQuantity());
                    System.out.println("------------");
                } else {
                    System.out.println("Error: Product information not available.");
                }
            }
        }
    }
	private void createOrder() {
        Scanner scanner = new Scanner(System.in);

        displayAvailableProducts(userCollections);

        System.out.println("Enter the name of the product to order:");
        String productName = scanner.next();

        System.out.println("Enter the quantity to order:");
        int quantity = scanner.nextInt();

        Item selectedProduct = findProduct(productName);

        if (selectedProduct != null && selectedProduct.isApproved() && quantity > 0) {
            if (quantity <= selectedProduct.getQuantity()) {
                Orders order = new Orders(selectedProduct, quantity);
                orders.add(order);

                reduceProductQuantity(selectedProduct, quantity);
                double orderTotal = selectedProduct.getPrice() * quantity;
                reduceBalance(orderTotal);

                System.out.println("Order created successfully.");
            } else {
                System.out.println("Not enough stock available.");
            }
        } else {
            System.out.println("Invalid product or quantity. Please try again.");
        }

        Orders order = new Orders(selectedProduct, quantity);
        orders.add(order);

        saveOrders();
    }
	private void reduceProductQuantity(Item product, int quantity) {
        for (Seller seller : userCollections.getSellers()) {
            List<Item> sellerProducts = seller.getProduct();
            for (Item sellerProduct : sellerProducts) {
                if (sellerProduct.getItemName().equalsIgnoreCase(product.getItemName())) {
                    int remainingQuantity = sellerProduct.getQuantity() - quantity;
                    if (remainingQuantity >= 0) {
                        sellerProduct.setQuantity(remainingQuantity);
                        seller.saveProducts();
                        System.out.println("Quantity reduced successfully.");
                        return;
                    } else {
                        System.out.println("Not enough stock available.");
                        return;
                    }
                }
            }
        }
        System.out.println("Error: Product not found.");
    }
	private void displayAvailableProducts(UserCollections userCollections) {
        System.out.println("Available Products:");

        boolean productsAvailable = false;

        for (Seller seller : userCollections.getSellers()) {
            seller.loadProducts();

            if (seller.isActiva()) {
                System.out.println("Seller: " + seller.getuserName());
                List<Item> sellerProducts = seller.getProduct();

                if (sellerProducts.isEmpty()) {
                    System.out.println("No products available from this seller.");
                } else {
                    for (Item product : sellerProducts) {
                        if (product.isApproved()) {
                            System.out.println("Product: " + product.getItemName());
                            System.out.println("Price: $" + product.getPrice());
                            System.out.println("Quantity: " + product.getQuantity());
                            System.out.println("------------");
                            productsAvailable = true;
                        }
                    }
                }
            } else {
                System.out.println("Seller " + seller.getuserName() + " is not active.");
            }
        }

        if (!productsAvailable) {
            System.out.println("No approved products available.");
        }
    }
	private Item findProduct(String productName) {
        for (Seller seller : userCollections.getSellers()) {
            List<Item> sellerProducts = seller.getProduct();
            for (Item product : sellerProducts) {
                if (product.getItemName().equalsIgnoreCase(productName)) {
                    return product;
                }
            }
        }
        return null;
    }
	private void reduceBalance(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Balance reduced successfully. Remaining balance: $" + balance);
        } else {
            System.out.println("Not enough balance to complete the order.");
        }
    }
	private void deleteOrder() {
        Scanner scanner = new Scanner(System.in);

        displayOrders();

        if (orders.isEmpty()) {
            System.out.println("No orders available to delete.");
            return;
        }

        System.out.println("Enter the name of the product in the order to delete:");
        String productName = scanner.next();

        Iterator<Orders> iterator = orders.iterator();

        while (iterator.hasNext()) {
            Orders order = iterator.next();
            if (order.getItem().getItemName().equalsIgnoreCase(productName)) {

                increaseProductQuantity(order.getItem(), order.getQuantity());

                increaseBalance(order.getItem().getPrice() * order.getQuantity());

                iterator.remove();

                System.out.println("Order deleted successfully.");

                saveOrders();

                return;
            }
        }

        System.out.println("Order not found.");
    }


    private void displayBalance() {
        System.out.println("Your Balance: $" + balance);
    }

    private void increaseProductQuantity(Item product, int quantity) {
        Seller seller = findSellerForProduct(product);

        if (seller != null) {
            List<Item> sellerProducts = seller.getProduct();
            for (Item sellerProduct : sellerProducts) {
                if (sellerProduct.getItemName().equalsIgnoreCase(product.getItemName())) {
                    sellerProduct.setQuantity(sellerProduct.getQuantity() + quantity);
                    System.out.println("Product quantity increased successfully.");
                    return;
                }
            }
        } else {
            System.out.println("Seller not found for the product.");
        }
    }

    private Seller findSellerForProduct(Item product) {
        for (Seller seller : userCollections.getSellers()) {
            List<Item> sellerProducts = seller.getProduct();
            for (Item sellerProduct : sellerProducts) {
                if (sellerProduct.getItemName().equalsIgnoreCase(product.getItemName())) {
                    return seller;
                }
            }
        }
        return null;
    }

    private void increaseBalance(double amount) {
        balance += amount;
        System.out.println("Balance increased successfully. Current balance: $" + balance);
        saveCustomerBalance();
    }

    private void saveCustomerBalance() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(userCollections.CUSTOMERS_FILE, true))) {
            List<String> updatedCustomers = new ArrayList<>();

            List<String> customerLines = Files.readAllLines(Paths.get(userCollections.CUSTOMERS_FILE));

            for (String customerLine : customerLines) {
                String[] parts = customerLine.split(",");
                String username = parts[0].trim();

                if (username.equals(getuserName())) {
                    updatedCustomers.add(username + "," + getPassword() + "," + balance);
                } else {
                    updatedCustomers.add(customerLine);
                }
            }
            Files.write(Paths.get(userCollections.CUSTOMERS_FILE), updatedCustomers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private void rateSeller() {
        Scanner scanner = new Scanner(System.in);
        displaySellerRatings();

        System.out.println("Enter the name of the seller to rate:");
        String sellerName = scanner.next();

        Seller sellerToRate = findSellerByName(sellerName);

        if (sellerToRate != null) {
            System.out.println("Enter your rating (1-5):");
            int rating = scanner.nextInt();

            if (rating >= 1 && rating <= 5) {
                sellerToRate.addRating(rating);

                System.out.println("Thank you for rating the seller.");
            } else {
                System.out.println("Invalid rating. Please enter a rating between 1 and 5.");
            }
        } else {
            System.out.println("Seller not found.");
        }
    }

    private void displaySellerRatings() {
        System.out.println("Seller Ratings:");

        for (Seller seller : userCollections.getSellers()) {
            double averageRating = seller.calculateAverageRating();
            if (averageRating > 0.0) {
                System.out.println("Seller: " + seller.getuserName());
                System.out.println("Average Rating: " + averageRating);
                System.out.println("------------");
            }
        }
    }


    private Seller findSellerByName(String sellerName) {
        for (Seller seller : userCollections.getSellers()) {
            if (seller.getuserName().equalsIgnoreCase(sellerName)) {
                return seller;
            }
        }
        return null;
    }

    private void saveOrders() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("orders_" + getuserName() + ".ser"))) {
            oos.writeObject(orders);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadOrders() {
        String fileName = "orders_" + getuserName() + ".ser";

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            orders = (List<Orders>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            if (e instanceof FileNotFoundException) {
                System.out.println("Orders file not found for customer: " + getuserName());
            } else {
                e.printStackTrace();
                System.out.println("Error loading orders for customer: " + getuserName());
            }
        }
    }

	
	
	
	
}