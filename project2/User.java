package eat1.eat1.project2;

public abstract class User {
    String userName;
    String userEmail;
    int phoneNumber;
    public String getName() {
        return userName;
    }
    public void setName(String name) {
        this.userName = name;
    }
    public String getEmail() {
        return userEmail;
    }
    public void setEmail(String email) {
        this.userEmail = email;
    }
    public int getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
}
