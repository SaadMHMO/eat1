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
		@Override
		public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
		}
	}
