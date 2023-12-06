package xyzModern;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserCollections  {
    protected static final String CUSTOMERS_FILE = "customers.txt";
    protected static final String SELLERS_FILE = "sellers.txt";
    protected static final String ADMINS_FILE = "admins.txt";

    private List<Customer> customers;
    private List<Seller> sellers;
    private Admin admin ;

    public UserCollections() {
        this.customers = new ArrayList<>();
        this.sellers = new ArrayList<>(); 
        this.admin = new Admin(this);    
        loadUsers();
    }

    private void loadUsers() {
        loadCustomers();
        loadSellers();
        loadAdmins();
    }

    private void loadCustomers() {
        try (Scanner scanner = new Scanner(new File(CUSTOMERS_FILE))) {
            while (scanner.hasNext()) {
                String username = scanner.next();
                String password = scanner.next();
                int balance = scanner.nextInt();
                boolean active = scanner.nextBoolean();

                Customer customer = new Customer(username, password, balance, this);
                customer.setActivation(active);
                customers.add(customer);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Customers file not found. Creating a new one.");
        }
    }
    private void loadAdmins() {
        try (Scanner scanner = new Scanner(new File(ADMINS_FILE))) {
            while (scanner.hasNext()) {
                String username = scanner.next();
                String password = scanner.next();
                boolean active = scanner.nextBoolean();

                Admin admin = new Admin(this);
                admin.setActivation(active);
                
            }
        } catch (FileNotFoundException e) {
            System.out.println("Admins file not found. Creating a new one.");
        }
    }

    private void loadSellers() {
        try (Scanner scanner = new Scanner(new File(SELLERS_FILE))) {
            while (scanner.hasNext()) {
                String username = scanner.next();
                String password = scanner.next();
                boolean active = scanner.nextBoolean();

                Seller seller = new Seller(username, password, this);
                seller.setActivation(active);
                sellers.add(seller);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Sellers file not found. Creating a new one.");
        }
    }
    

    protected void saveUsers() {
        saveCustomers();
        saveSellers();
        saveAdmins();
    }

    private void saveCustomers() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(CUSTOMERS_FILE))) {
            for (Customer customer : customers) {
                writer.println(customer.getuserName() + " " +
                        customer.getPassword() + " " +
                        customer.getBalance() + " " +
                        customer.isActiva());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveSellers() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(SELLERS_FILE))) {
            for (Seller seller : sellers) {
                writer.println(seller.getuserName() + " " +
                        seller.getPassword() + " " +
                        seller.isActiva());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void saveAdmins() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ADMINS_FILE))) {
                writer.println(admin.getuserName() + " " +
                admin.getPassword() + " " +
                admin.isActiva());
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    public List<Customer> getCustomers() {
        return customers;
    }

    public List<Seller> getSellers() {
        return sellers;
    }

    public Admin getAdmins() {
        return admin;
    }

    public void setCustomers(List<Customer> customer) {
        this.customers.addAll(customer);
    }

    public void setSellers(List<Seller> seller) {
        this.sellers.addAll(seller);
    }
    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
    
    public void login() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Username:");
        String username = scanner.next();

        System.out.println("Enter Password:");
        String password = scanner.next();

        User user = authenticateUser(username, password);

        if (user != null) {
            if (user.isActiva()) {
                
                if (user instanceof Customer) {
                    Customer customer = (Customer) user;
                    customer.CustInterface();;
                } else if (user instanceof Seller) {
                    
                    Seller seller = (Seller) user;
                    seller.sellerInterface();
                    
                } 
            }
            else if (user instanceof Admin) {
                    Admin admin = (Admin) user;
                    setAdmin(admin);
                    saveUsers();
                    admin.AdminInterface();
            } else {
                System.out.println("Wait until admin gives you the permission.");
            }
        } else {
            System.out.println("Login failed. Invalid credentials.");
        }

    }
    private User authenticateUser(String username, String password) {

        for (Customer customer : getCustomers()) {
            if (customer.getuserName().equals(username) && customer.getPassword().equals(password)) {
                return customer;
            }
        }

        for (Seller seller : getSellers()) {
            
            if (seller.getuserName().equals(username) && seller.getPassword().equals(password)) {
                return seller;
            }
        }
        
        if (admin.getuserName().equals(username) && admin.getPassword().equals(password)) {
            
            return admin;

        }
        
        

        return null;
    }


    public void registerUser() {
        Scanner scanner = new Scanner(System.in);

        int userType;
        while (true) {
            try {
                System.out.println("Select User Type to Register:");
                System.out.println("1) Seller.");
                System.out.println("2) Customer.");
                

                userType = Integer.parseInt(scanner.next());

                if (userType >= 1 && userType <= 2) {
                    break;
                } else {
                    System.out.println("Invalid input. Please enter a valid user type (1, 2, or 3).");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }

        System.out.println("Enter Username:");
        String username = scanner.next();

        System.out.println("Enter Password:");
        String password = scanner.next();

        switch (userType) {
            case 1:
                Seller seller = new Seller(username, password, this);
                List<Seller> sellerList = new ArrayList<>();
                sellerList.add(seller);
                setSellers(sellerList);
                System.out.println("Registration successful as Seller, please wait until the admin give to you permission");
                saveUsers();
                break;
            case 2:
                System.out.println("Enter Balance:");
                int balance = scanner.nextInt();
                Customer customer = new Customer(username, password, balance, this);
                List<Customer> customerList = new ArrayList<>();
                customerList.add(customer);
                setCustomers(customerList);
                System.out.println("Registration successful as Customer, please wait until the admin give to you permission");
                saveUsers();
                break;
            
            default:
                System.out.println("Invalid user type. Registration failed.");
        }
    }

    
    
    
    
}
