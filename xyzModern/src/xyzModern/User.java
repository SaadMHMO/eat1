package xyzModern;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class User {
		protected String userName;		
		protected String password;
		private boolean Admin;
		private boolean Seller;
		private boolean Customer;
		private boolean  Active;	
		private UserCollections users;
		public User(){}
		public User(String name , String pass){
			this.userName = name;		
			this.password = pass; 
			this.Active = false;
			this.Admin = false;
			this.Seller = false;
			this.Customer = false;
    	}
		public void setActivation(boolean activation) {
			Active = activation;
		}
		public boolean isActiva() {
			return Active;
		}
		public void setUsers(UserCollections users) {
			this.users = users;
		}
		public UserCollections getUsers() {
			return users;
		}
		
		public abstract String getuserName();
		public abstract void setuserName(String name);
		public abstract String getPassword();
		public abstract void setPassword(String password);
		
		
		
		@Override
		public String toString() {
		// TODO Auto-generated method stub
		return "User name: " + userName +"\nUser password: " + password;
		}
		public boolean isAdmin() {
			return Admin;
		}
		public void setAdmin(boolean admin) {
			Admin = admin;
		}
		public boolean isSeller() {
			return Seller;
		}
		public void setSeller(boolean seller) {
			Seller = seller;
		}
		public boolean isCustomer() {
			return Customer;
		}
		public void setCustomer(boolean customer) {
			Customer = customer;
		}
	}
