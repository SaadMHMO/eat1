package xyzModern;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Scanner;

public class Admin extends User{
    private UserCollections userCollections;
    public Admin(){
        super("shm", "shm");
        setAdmin(true);
        

    }
    public void AdminInterface(){
        Scanner scanner = new Scanner(System.in);
        boolean isAdminPanelRunning = true;

        while (isAdminPanelRunning) {
            System.out.println("Admin Interface:");
            System.out.println("1) Activate User.");
            System.out.println("2) Display Unactive Users.");
            System.out.println("3) Display All the products.");
            System.out.println("4) Display All users.");
            System.out.println("5) Display Customers.");
            System.out.println("6) Display Sellers.");
            System.out.println("7) Approve products.");
            System.out.println("8) Remove Users.");
            System.out.println("9) Remove Products.");
            System.out.println("10) Display Statistics.");
            System.out.println("11) Exit.");

            if (scanner.hasNextInt()){
                switch (scanner.nextInt()) {
                    case 1:
                        activateUser();
                        break;
                    case 2:
                        displayUnactiveUsers();
                        break;
                    case 3:
                        displayAllProducts();
                        break;
                    case 4:
                        displayAllUsers();
                        break;
                    case 5:
                        displayCustomers();
                        break;
                    case 6:
                        displaySellers();
                        break;
                    case 7:
                        approveProducts();
                        break;
                    case 8:
                        removeUsers();
                        break;
                    case 9:
                        removeProducts();
                        break;
                    case 10:
                        displayStatistics();
                        break;
                    case 11:
                        isAdminPanelRunning = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }else{
                System.out.println("Invalid choice. Please select a valid option.");
                scanner.nextLine();
            }
        }
    }

    private void displayUnactiveUsers() {
        System.out.println("Inactive Users:");

        List<Seller> sellers = userCollections.getSellers();
        List<Customer> customers = userCollections.getCustomers();

        if (sellers.isEmpty() && customers.isEmpty()) {
            System.out.println("No sellers or customers found.");
        } else {
            for (Seller seller : sellers) {
                if (!seller.isActiva()) {
                    System.out.println("Seller: " + seller.getuserName());
                }
            }

            for (Customer customer : customers) {
                if (!customer.isActiva()) {
                    System.out.println("Customer: " + customer.getuserName());
                }
            }
        }
    }


    private void activateUser() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the username of the user to activate:");
        String usernameToActivate = scanner.next();

        boolean userActivated = false;

        for (Seller seller : userCollections.getSellers()) {
            if (seller.getuserName().equals(usernameToActivate) && !seller.isActiva()) {
                seller.setActivation(true);;
                userActivated = true;
                System.out.println("Seller " + usernameToActivate + " activated successfully.");
                break;
            }
        }

        for (Customer customer : userCollections.getCustomers()) {
            if (customer.getuserName().equals(usernameToActivate) && !customer.isActiva()) {
                customer.setActivation(true);
                userActivated = true;
                System.out.println("Customer " + usernameToActivate + " activated successfully.");
                break;
            }
        }

        if (!userActivated) {
            System.out.println("User not found or already activated.");
        }
        userCollections.saveUsers();
    }


    private void displayAllUsers() {
        System.out.println("Active Users:");

        List<Seller> sellers = userCollections.getSellers();
        List<Customer> customers = userCollections.getCustomers();

        if (sellers.isEmpty() && customers.isEmpty()) {
            System.out.println("No sellers or customers found.");
        } else {
            for (Seller seller : sellers) {
                if (seller.isActiva()) {
                    System.out.println("Seller: " + seller.getuserName());
                }else{
                    System.out.println("Inactive Seller: " + seller.getuserName());
                }
            }

            for (Customer customer : customers) {
                if (customer.isActiva()) {
                    System.out.println("Customer: " + customer.getuserName());
                }else{
                    System.out.println("Inactive Customer: " + customer.getuserName());
                }
            }
        }
    }

    private void displayCustomers() {
        System.out.println("Active Customers:");

        List<Customer> customers = userCollections.getCustomers();

        if (customers.isEmpty()) {
            System.out.println("No customers found.");
        } else {
            for (Customer customer : customers) {
                if (customer.isActiva()) {
                    System.out.println("Customer: " + customer.getuserName());
                } else {
                    System.out.println("Inactive Customer: " + customer.getuserName());
                }
            }
        }
    }


    private void displaySellers() {
        System.out.println("Active Sellers:");

        List<Seller> sellers = userCollections.getSellers();

        if (sellers.isEmpty()) {
            System.out.println("No sellers found.");
        } else {
            for (Seller seller : sellers) {
                if (seller.isActiva()) {
                    System.out.println("Seller: " + seller.getuserName());
                }else{
                    System.out.println("Inactive Customer: " + seller.getuserName());
                }
            }
        }
    }

    private void displayAllProducts() {
        System.out.println("All Products:");

        List<Seller> sellers = userCollections.getSellers();

        if (sellers.isEmpty()) {
            System.out.println("No sellers found.");
        } else {
            for (Seller seller : sellers) {
                if (seller.isActiva()) {
                    System.out.println("Seller: " + seller.getuserName());

                    loadProductsForSeller(seller);

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
                            } else {
                                System.out.println("Product " + product.getItemName() + " is not approved.");
                            }
                        }
                    }
                } else {
                    System.out.println("Seller " + seller.getuserName() + " is not active.");
                }
            }
        }
    }

    private void loadProductsForSeller(Seller seller) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("products_" + seller.getuserName() + ".ser"))) {
            List<Item> loadedProducts = (List<Item>) ois.readObject();
            seller.setProduct(loadedProducts);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading products for seller: " + seller.getuserName());
        }
    }




    private void approveProducts() {
        System.out.println("Products Pending Approval:");

        List<Seller> sellers = userCollections.getSellers();

        boolean productsToApprove = false;

        for (Seller seller : sellers) {
            if (seller.isActiva()) {
                List<Item> sellerProducts = seller.getProduct();

                for (Item product : sellerProducts) {
                    if (!product.isApproved()) {
                        productsToApprove = true;
                        System.out.println("Seller: " + seller.getuserName());
                        System.out.println("Product: " + product.getItemName());
                        System.out.println("Price: $" + product.getPrice());
                        System.out.println("Quantity: " + product.getQuantity());
                        System.out.println("------------");
                    }
                }
            }
        }

        if (!productsToApprove) {
            System.out.println("No products pending approval.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name of the product to approve:");

        String productName = scanner.next();

        for (Seller seller : sellers) {
            if (seller.isActiva()) {
                List<Item> sellerProducts = seller.getProduct();

                for (Item product : sellerProducts) {
                    if (product.getItemName().equalsIgnoreCase(productName) && !product.isApproved()) {
                        product.setApproved(true);
                        System.out.println("Product " + productName + " approved successfully.");
                        saveProducts();  // Save the changes to the file
                        return;
                    }
                }
            }
        }

        System.out.println("Product not found or already approved.");
    }

    private void saveProducts() {
        List<Seller> sellers = userCollections.getSellers();

        for (Seller seller : sellers) {
            if (seller.isActiva()) {
                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("products_" + seller.getuserName() + ".ser"))) {
                    oos.writeObject(seller.getProduct());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void removeUsers() {
        System.out.println("Remove Users:");

        List<Seller> sellers = userCollections.getSellers();
        List<Customer> customers = userCollections.getCustomers();

        boolean usersToRemove = false;

        for (Seller seller : sellers) {
            if (seller.isActiva()) {
                System.out.println("Seller: " + seller.getuserName());
                usersToRemove = true;
            }
        }

        for (Customer customer : customers) {
            if (customer.isActiva()) {
                System.out.println("Customer: " + customer.getuserName());
                usersToRemove = true;
            }
        }

        if (!usersToRemove) {
            System.out.println("No active users to remove.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the username of the user to remove:");

        String usernameToRemove = scanner.next();

        for (Seller seller : sellers) {
            if (seller.getuserName().equalsIgnoreCase(usernameToRemove) && seller.isActiva()) {
                seller.setActivation(false);
                System.out.println("Seller " + usernameToRemove + " removed successfully.");
                userCollections.saveUsers();
                return;
            }
        }

        for (Customer customer : customers) {
            if (customer.getuserName().equalsIgnoreCase(usernameToRemove) && customer.isActiva()) {
                customer.setActivation(false);
                System.out.println("Customer " + usernameToRemove + " removed successfully.");
                userCollections.saveUsers();
                return;
            }
        }

        System.out.println("User not found or already removed.");
    }


    private void removeProducts() {
        System.out.println("Remove Products:");

        List<Seller> sellers = userCollections.getSellers();
        boolean productsToRemove = false;

        for (Seller seller : sellers) {
            if (seller.isActiva()) {
                System.out.println("Seller: " + seller.getuserName());
                List<Item> sellerProducts = seller.getProduct();

                if (!sellerProducts.isEmpty()) {
                    productsToRemove = true;
                    for (Item product : sellerProducts) {
                        System.out.println("Product: " + product.getItemName());
                    }
                }
            }
        }

        if (!productsToRemove) {
            System.out.println("No products to remove.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name of the product to remove:");

        String productNameToRemove = scanner.next();

        for (Seller seller : sellers) {
            if (seller.isActiva()) {
                List<Item> sellerProducts = seller.getProduct();
                for (Item product : sellerProducts) {
                    if (product.getItemName().equalsIgnoreCase(productNameToRemove)) {
                        sellerProducts.remove(product);
                        System.out.println("Product " + productNameToRemove + " removed successfully.");
                        return;
                    }
                }
            }
        }

        System.out.println("Product not found or already removed.");
    }


    private void displayStatistics() {
        System.out.println("Statistics:");

        List<Seller> sellers = userCollections.getSellers();
        List<Customer> customers = userCollections.getCustomers();

        int totalSellers = 0;
        int totalCustomers = 0;
        int totalProducts = 0;

        for (Seller seller : sellers) {
            if (seller.isActiva()) {
                totalSellers++;
                totalProducts += seller.getProduct().size();
            }
        }

        for (Customer customer : customers) {
            if (customer.isActiva()) {
                totalCustomers++;
            }
        }

        System.out.println("Total Sellers: " + totalSellers);
        System.out.println("Total Customers: " + totalCustomers);
        System.out.println("Total Products: " + totalProducts);
    }

    
    

    @Override
	public String getuserName() {
        return "shm";
    }

    @Override
    public void setuserName(String name) {
        this.userName = "shm";
    }


    @Override
    public String getPassword() {
        return "shm";
    }

    @Override
    public void setPassword(String password) {
        this.password = "shm";
    }

        
}
