
package xyzModern;

import java.util.Scanner;

public class xyz {
   public static void main(String[] args) {
        
        xyz xyzShop = new xyz();
        xyzShop.run();
        
        
    }
	private UserCollections userCollections;

    public xyz() {
        this.userCollections = new UserCollections();
    }

    void run() {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("XYZ Shop:");
            System.out.println("1) Log in.");
            System.out.println("2) Log out.");
            System.out.println("3) Register.");
            
            if (scanner.hasNextInt()){
                switch (scanner.nextInt()) {
                    case 1:
                        userCollections.login();
                        break;
                    case 2:
                        isRunning = false;
                        break;
                    case 3:
                        userCollections.registerUser();
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }

            }else{
                System.out.println("Invalid choice. Please select a valid option.");
                scanner.nextLine();
            }
        }
        
        scanner.close();
    }
    

}
