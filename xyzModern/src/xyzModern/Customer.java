package xyzModern;

import java.util.ArrayList;
import java.util.Scanner;


public class Customer extends User {
	Scanner in ;
	private double balance;
	ArrayList<User>registeredCust = new ArrayList<User>();
	
	public Customer() {}

	public Customer(String name , int id , int phone , String email , String pass){
		super(name, id, phone, email, pass);
	}

	


	/*public void buyItem(Item item, int quantity) {
		if(quantity > 0 && quantity <= item.getQuantity()) {
			double cost = quantity * item.getPrice();
			if(cost <= getBalance()) {
				Item requiredItem = new Item();
				requiredItem.setItemNo(item.getItemNo());
				requiredItem.setItemName(item.getItemName());
				requiredItem.setPrice(item.getPrice());
				requiredItem.setQuantity(quantity);
				setBalance(getBalance() - cost); 
				item.setQuantity(item.getQuantity() - quantity);
				setTotalSpending(item.getQuantity());
				System.out.println("The payment is successfully done!");
				print();
			}else {
				System.out.println("Insufficent balance!");
			}
		}else {
			System.out.println("Sorry, it is out of stock!");
		}
	}*/
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
	public String getName() {
        return this.Name;
    }

    @Override
    public void setName(String name) {
        this.Name = name;
    }

    @Override
    public int getID() {
        return this.ID;
    }

    @Override
    public void setID(int iD) {
        this.ID = iD;
    }

    @Override
    public int getPhoneNumber() {
        return this.PhoneNumber;
    }

    @Override
    public void setPhoneNumber(int phoneNumber) {
        this.PhoneNumber = phoneNumber;
    }

    @Override
    public String getEmail() {
        return this.Email;
    }

    @Override
    public void setEmail(String email) {
        this.Email = email;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }
	@Override
	public void Login() {
		in = new Scanner(System.in);
        System.out.println("enter your email: ");
        String emailLogIn = in.nextLine();
        System.out.println("Enter your password: ");
        String passLogIn = in.nextLine();
		for(int i = 0 ; i < registeredCust.size() ; i++){
            System.out.println("I am Cust");            
            if (registeredCust.get(i).getEmail().equals(emailLogIn) && registeredCust.get(i).getPassword().equals(passLogIn)){
                //call customer interface
                System.out.println("you are cust");
                i = registeredCust.size();
            }
        }
	}
	@Override
	public void Logout() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'Logout'");
	}
	@Override
	public void Register() {
		setUser();
		User CUser = new Customer(getName(), getID(), getPhoneNumber(), getEmail(), getPassword());
		registeredCust.add(CUser);
	}
	
	
	
	
}