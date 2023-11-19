package eat1.eat1.project2;

import java.util.ArrayList;

public class Customer {
    ArrayList<Item>orders = new ArrayList<Item>();
    double balance;
    public void displayOrders(){
        System.out.println(orders);
    }
    public void addOrder(Item item){
        orders.add(item);
    }
    public void delOrder(){
        orders.clear();
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public double getBalance() {
        return balance;
    }
    public void invoice(){

    }
}
