package xyzModern;

import java.util.Scanner;

public class Admin extends User{
    Scanner in;
    public Admin(){
        this.Name = "shm";
        this.ID = 111;
        this.PhoneNumber = 0555;
        this.Email="shm";
        this.password = "shm";
    }
    @Override
	public String getName() {
        return this.Name;
    }

    @Override
    public void setName(String name) {
        this.Name = "SHM team";
    }

    @Override
    public int getID() {
        return this.ID;
    }

    @Override
    public void setID(int iD) {
        this.ID = 1111;
    }

    @Override
    public int getPhoneNumber() {
        return this.PhoneNumber;
    }

    @Override
    public void setPhoneNumber(int phoneNumber) {
        this.PhoneNumber = 0555;
    }

    @Override
    public String getEmail() {
        return this.Email;
    }

    @Override
    public void setEmail(String email) {
        this.Email = "SHM";
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public void setPassword(String password) {
        this.password = "SHM";
    }

    @Override
    public void Login() {
        in = new Scanner(System.in);
        System.out.println("enter your email: ");
        String emailLogIn = in.nextLine();
        System.out.println("Enter your password: ");
        String passLogIn = in.nextLine();
        if (getEmail().equals(emailLogIn)  && getPassword().equals(passLogIn)){
            //call admin interface
            System.out.println("you are Admine");
        }
    }

    @Override
    public void Logout() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Logout'");
    }

    @Override
    public void Register() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Register'");
    }
    
}
