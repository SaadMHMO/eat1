package xyzModern;

import java.util.ArrayList;
import java.util.Scanner;

public class UserCollections extends User {
    public UserCollections(){}
    public UserCollections(String name, int id, int phone, String email, String pass) {
        super(name, id, phone, email, pass);
        //TODO Auto-generated constructor stub
    }
    ArrayList<User>registeredCust = new ArrayList<User>();
    ArrayList<User>registeredSeller = new ArrayList<User>();
    ArrayList<User>registeredAdmin = new ArrayList<User>();
    Scanner in ;
    @Override
    public String getName() {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'getName'");
        return this.Name;
    }

    @Override
    public void setName(String name) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'setName'");
        this.Name = name;
    }

    @Override
    public int getID() {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'getID'");
        return this.ID;
    }

    @Override
    public void setID(int iD) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'setID'");
        this.ID = iD;
    }

    @Override
    public int getPhoneNumber() {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'getPhoneNumber'");
        return this.PhoneNumber;
    }

    @Override
    public void setPhoneNumber(int phoneNumber) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'setPhoneNumber'");
        this.PhoneNumber = phoneNumber;
    }

    @Override
    public String getEmail() {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'getEmail'");
        return this.Email;
    }

    @Override
    public void setEmail(String email) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'setEmail'");
        this.Email = email;
    }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'getPassword'");
        return this.password;
    }

    @Override
    public void setPassword(String password) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'setPassword'");
        this.password = password;
    }
    public void logIn(){
        in = new Scanner(System.in);
        System.out.println("enter your email: ");
        String emailLogIn = in.nextLine();
        System.out.println("Enter your password: ");
        String passLogIn = in.nextLine();
        for (int i = 0 ; i < registeredCust.size() && i < registeredAdmin.size() && i <registeredSeller.size()  ; i++){
            if (registeredCust.get(i).getEmail() == emailLogIn && registeredCust.get(i).getPassword() == passLogIn){
                //call customer interface
                i = registeredCust.size();
            }
            else if (registeredAdmin.get(i).getEmail() == emailLogIn && registeredAdmin.get(i).getPassword() == passLogIn){
                //call admin interface
                i = registeredCust.size();
            }
            else if(registeredSeller.get(i).getEmail() == emailLogIn && registeredSeller.get(i).getPassword() == passLogIn){
                //call seller interface
                i = registeredCust.size();
            }
            else {
                System.out.println("You should to register!");
            }
        }
    }
    public void logOut(){
        System.exit(0);
    }
    public void register(){
        in = new Scanner(System.in);
        System.out.println("Are you Coustomer or Seller or Admin? \n1) Admin \n2)Seller \n3)Customer");
        
        int userChoose = in.nextInt();
        if (userChoose == 1){
            setUser();
            User uAdmin = new UserCollections(getName(), getID(), getPhoneNumber(), getEmail(), getPassword());
            registeredAdmin.add(uAdmin);
        }
        else if (userChoose == 2){
            setUser();
            User uSeller = new UserCollections(getName(), getID(), getPhoneNumber(), getEmail(), getPassword());
            registeredSeller.add(uSeller);
        }
        else if (userChoose == 3){
            setUser();
            User uCust = new UserCollections(getName(), getID(), getPhoneNumber(), getEmail(), getPassword());
            registeredCust.add(uCust);
        }
    }
    public void setUser(){
        in = new Scanner(System.in);
        System.out.println("Enter your Password: ");
        setPassword(in.nextLine());
        System.out.println("Enter your Phone number: ");
        setPhoneNumber(in.nextInt());        
        System.out.println("Enter your Email: ");
        
        //setEmail(in.nextLine());
        System.out.println("Enter your name: ");
        setName(in.nextLine());
        System.out.println("Enter your ID: ");
        setID(in.nextInt());
        
        
        
    }
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }
    
}
