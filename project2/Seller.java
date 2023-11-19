package eat1.eat1.project2;

import java.util.ArrayList;

public class Seller {
    ArrayList<Item>product = new ArrayList<Item>();
    public void addProduct(Item item){
        product.add(item);
    }
    public void deleteProduct(Item item){
        product.remove(item);
    }
    public void displayProduct(){
        System.out.println(product);
    }
    
}
