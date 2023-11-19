package xyzModern;

	public abstract class User {
		String Name;
		int ID;
		int PhoneNumber;
		String Email;
		public abstract String getName();
		public abstract void setName(String name);
		public abstract int getID();
		public abstract void setID(int iD);
		public abstract int getPhoneNumber() ;
		public abstract void setPhoneNumber(int phoneNumber);
		public abstract String getEmail();
		public abstract void setEmail(String email);
	
	}
