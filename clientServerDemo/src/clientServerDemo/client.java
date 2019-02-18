package clientServerDemo;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;


public class client {

	public static void main(String[] args) throws UnknownHostException, IOException {
		// TODO Auto-generated method stub

		int number;
		String temp;
		int request = 0;
		
		Scanner input = new Scanner (System.in);
		
		//Socket is needed for communication between a client and a host server
		//requiring the IP address and the port number (>2000)
		Socket s = new Socket("127.0.0.1",3017);
		
		//Used to get the output from the server
		Scanner input1 = new Scanner(s.getInputStream());
		printMenu();
		
		do {
				
		number = input.nextInt();
			
		switch (number) {
			
		case 1: 
			request = 1;
			break;
			
		case 2:
			request = 2;
			break;
			
		case 3:
			request = 3;
			break;
			
		case 4:
			request = 4;
			break;
			
		case 5:
			request = 5;
			break;
			
		case 6:
			request = 6;
			break;
			
		case 7:
			System.out.println("GoodBye.");
			System.out.println("Connection ended.");
			System.exit(0);
			break;
			
		default:
			System.out.println("Invalid Request.  Try Again..");
			break;
		}
		
		
		//Pass number to the server
		PrintStream ps = new PrintStream(s.getOutputStream());
		
		//Pass number to the server through print stream
		ps.println(request);
		temp = input1.nextLine();
		System.out.println(temp);
		System.out.println("\n");
		
		} while (request != 7);
		
		input.close();
		s.close();
		input1.close();
	}//end of main
	
	
	//method to check IP validity 
	public static boolean verifyIP(String IP) {  

        String IP_ADDRESS_REGEX = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
                + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
                + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
                + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

        Pattern ipPattern = Pattern.compile(IP_ADDRESS_REGEX);
        if (ipPattern.matcher(IP).matches()) {
            return true;

        }

        return false;

    }
		
	
	public static void printMenu() {
		System.out.println("1.\tHost Current Date and Time");
		System.out.println("2.\tHost Uptime");
		System.out.println("3.\tHost Memory Use");
		System.out.println("4.\tHost Netstat");
		System.out.println("5.\tHost Current User");
		System.out.println("6.\tHost Running Process");
		System.out.println("7.\tQuit");
		System.out.println("\n");
		System.out.println("Enter Request: ");		
		System.out.println("\n");
	}

}
