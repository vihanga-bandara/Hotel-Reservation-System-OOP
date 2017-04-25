import java.io.*;
import java.util.*;
public class Main {


	static String Cust_Name;
	static int Room_No;
	static String ans;
	static int check;

	static Scanner sc = new Scanner(System.in);
	static Customer [] hotel = new Customer[10];
	static Queueobj q= new Queueobj(10);

	public static void main(String[] args) {

		initialise(hotel);
		while (ans!="Exit"){
			System.out.println("\n------------Welcome to Galle Face Hotel------------\n");
			System.out.println("\tSelect Your Choice and Enter Below :\n");
			System.out.println("\tA    : Add a customer to a room");
			System.out.println("\tV    : To view all rooms");
			System.out.println("\tE    : Display Empty Rooms");
			System.out.println("\tD    : Delete Customer From Room");
			System.out.println("\tF    : Find Room From Customer Name");
			System.out.println("\tS    : Store Program array data into a plain text");
			System.out.println("\tL    : Load Program Back From the file into the Array");
			System.out.println("\tO    : View Rooms Ordered Alphabetically By Name");
			System.out.println("\t3    : To Display the names of the first 3 customers ");
			System.out.println("\tExit : To Exit The Program");
			System.out.print("\n|Choice|: ");
			ans=sc.next();
			if (ans.equalsIgnoreCase("A")){
				Cust_add(hotel);
			} else if (ans.equalsIgnoreCase("V")){
				View_Room(hotel);
			} else if (ans.equalsIgnoreCase("E")){
				Empty_Room(hotel);
			} else if (ans.equalsIgnoreCase("D")){
				Del_Cust(hotel);
			}else if (ans.equalsIgnoreCase("F")){
				Find_Room(hotel);

			}else if (ans.equalsIgnoreCase("S")){
				Store_Array(hotel);		
			} else if (ans.equalsIgnoreCase("L")){
				Load_Array();
			}else if(ans.equalsIgnoreCase("3")){
				Queue(q);
			}else if(ans.equalsIgnoreCase("Exit")){
				Leave();
			}
		}

	}
	private static void initialise(Customer []hotelRef){
		for(int i=0;i<hotelRef.length;i++){
			hotelRef[i]= new Customer();
			hotelRef[i].setCustName("empty");
			int j=i+1;
			hotelRef[i].setRoomNo(j);
		}
	}

	private static void View_Room(Customer[] hotel){
		for (int i=0;i<hotel.length;i++){
			if (hotel[i].getCustName().equals("empty")){
				System.out.println("Room "+hotel[i].getRoomNo()+" is empty");
			} else {
				System.out.println("Room "+hotel[i].getRoomNo()+" is occupied by "+hotel[i].getCustName());


			}
		}
		Leave();
	}
	private static void Empty_Room(Customer [] hotel){
		for (int i=0;i<hotel.length;i++){
			if (hotel[i].getCustName().equals("empty")){
				System.out.println("Room "+hotel[i].getRoomNo()+" is empty");
			}
		}
	}
	private static void Cust_add(Customer [] hotel){
		int check;
		int content;
		do {
			check=0;	
			System.out.print("Enter Room No : ");
			Room_No=sc.nextInt();
			if (hotel[Room_No].getCustName().equals("empty")||hotel[Room_No].getCustName().equals("")){
				check=1;
				System.out.println("Room is available!");
			} else 
				System.out.println("Room not available..Try another Room");
		}while (check!=1);

		System.out.print("Enter Customer Name : ");
		Cust_Name=sc.next();
		if(hotel[Room_No].getCustName().equals("empty")){
			hotel[Room_No].setCustName(Cust_Name);
			System.out.println("Customer has been added to Room");
			q.addQueue(Cust_Name);
		}
	}
	private static void Del_Cust(Customer [] hotel){
		System.out.print("Enter Customer Name : ");
		Cust_Name=sc.next();
		for (int i=0;i<hotel.length;i++){
			if (hotel[i].getCustName().equals(Cust_Name)){
				hotel[i].setCustName("empty");
				System.out.println("  Customer has been deleted from the room!!");
				q.removeQueue(Cust_Name);				
			}
		}
	}
	private static void Queue(Queueobj queue){
		for (int i = 0; i < 3; i++) {
			queue.removeQueue();
		}
	}
	private static void Find_Room (Customer [] hotel){
		System.out.print("Enter Customer Name to Find : ");
		String customer=sc.next();
		check=0;
		for (int i=0;i<hotel.length;i++){
			if (hotel[i].getCustName().equalsIgnoreCase(customer)){
				System.out.println("The Room Number of "+hotel[i].getCustName()+" is " +hotel[i].getRoomNo());
				check=1;
			}
		}
		if (check==0){
			System.out.println("Customer has not booked any room");
		}
		Leave();
	}
	private static void Store_Array (Customer [] hotel) {
		String line=null;
		try {
			File bit= new File("Arraydata.txt");
			if (!bit.exists()) {
				bit.createNewFile();
			}
			FileWriter b=new FileWriter(bit);
			BufferedWriter lol=new BufferedWriter(b);
			for (int i=0;i<hotel.length;i++){
				if(hotel[i].getCustName().equals("empty")){
					line="Room "+hotel[i].getRoomNo()+" is empty";
					lol.write(line);
					lol.newLine();
				}else {
					line="Room "+hotel[i].getRoomNo()+" is occupied by "+hotel[i].getCustName();
					lol.write(line);
					lol.newLine();
				}
			}
			lol.close();

		}catch (Exception e){
			System.out.println("There seems to be an error. Please Try Again");
		} 
		System.out.println("Data saved to a plain text file");
	}
	private static void Load_Array (){
		File c=new File("Arraydata.txt");
		Scanner z=null;
		try {
			z=new Scanner(c);
			int count=0;
			while (z.hasNextLine()){
				String arr=z.nextLine();
				count++;
			}
		}
		catch(FileNotFoundException e) {
			System.out.println("File not Found. Please Try Again");
		}finally {
			System.out.println("The Program data has been successfully loaded");
		}
	}
	private static void Leave(){
		System.out.println("Do you want to Exit the Program ? [Yes] or [No]");
		String ans=sc.next();
		if (ans.equalsIgnoreCase("Yes")){
			System.out.println("Program is Closing.....");
			System.exit(0);
		}

	}

}
