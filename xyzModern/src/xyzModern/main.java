
package xyzModern;



public class main {
   
	public static void main(String[] args){
		
		
		Seller s = new Seller();
		Orders i = new Orders();
		i.addProduct();
		i.addProduct();
		i.addProduct();
				
		System.out.println("------------------------");		
		i.creatOrder();
		i.creatOrder();
		i.creatOrder();
		System.out.println(i.salla);

		System.out.println("------------------------");		
		i.invoice();
		/*switch("1) Log in  \n 2) Log out  3) Register " + y ) {
		
		case 1:
			//login
			
			break;
		case 2:
			
			
			break;
			
		case 3:
			
			break;
		default:
			System.out.println("Choose from menu!");
		}
*/
	}

}
