package xyzModern;

import java.util.ArrayList;
import java.util.Scanner;

public class Seller extends User {
    double Rate;
	ArrayList<User>registeredSeller = new ArrayList<User>();
	Scanner in;
	
	public Seller(){};
	public Seller(String name , int id , int phone , String email , String pass){
		super(name, id, phone, email, pass);
	}
	
	public double getRate() {
		return Rate;
	}

	public void setRate(double rate) {
		Rate = rate;
	}

	public String toString(){
		return super.toString();
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
		for(int i = 0 ; i < registeredSeller.size() ; i++){
            System.out.println("I am Seller");
            if(registeredSeller.get(i).getEmail().equals(emailLogIn) && registeredSeller.get(i).getPassword().equals(passLogIn)){
                //call seller interface
                System.out.println("you are seller");
                i = registeredSeller.size();
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
		User sUser = new Seller(getName(), getID(), getPhoneNumber(), getEmail(), getEmail());
		registeredSeller.add(sUser);
	}

	
	

	

	
	
}
