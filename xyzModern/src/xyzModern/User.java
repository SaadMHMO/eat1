package xyzModern;

import java.util.Scanner;

public abstract class User {
		String Name;
		int ID;
		int PhoneNumber;
		String Email;
		String password;
		public User(){}
		public User(String name , int id , int phone , String email , String pass){
			this.Name = name;
			this.ID = id;
			this.PhoneNumber = phone;
			this.Email = email;
			this.password = pass;        
    	}
		public abstract String getName();
		public abstract void setName(String name);
		public abstract int getID();
		public abstract void setID(int iD);
		public abstract int getPhoneNumber() ;
		public abstract void setPhoneNumber(int phoneNumber);
		public abstract String getEmail();
		public abstract void setEmail(String email);
		public abstract String getPassword();
		public abstract void setPassword(String password);
		public abstract void Login();
		public abstract void Logout();
		public abstract void Register();
		public void setUser() {		
			
			Scanner in = new Scanner(System.in);
			System.out.println("Enter your name: ");
			setName(in.nextLine());
			in = new Scanner(System.in);
			System.out.println("Enter your ID: ");
			setID(in.nextInt());
			in = new Scanner(System.in);
			System.out.println("Enter your Phone number: ");
			setPhoneNumber(in.nextInt()); 
			in = new Scanner(System.in);     
			System.out.println("Enter your Email: ");
			setEmail(in.nextLine());
			in = new Scanner(System.in);
			System.out.println("Enter your Password: ");
			setPassword(in.nextLine());        
			
		}
		@Override
		public String toString() {
		// TODO Auto-generated method stub
		return "User name: " + Name + "\nUser ID: " + ID + "\nUser Phone Nuumber: " + PhoneNumber + "\nUser email: " + Email + "\nUser password: " + password;
		}
	}
