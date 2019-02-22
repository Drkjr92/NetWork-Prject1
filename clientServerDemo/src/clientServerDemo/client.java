package clientServerDemo;

import java.io.IOException;
import java.io.PrintStream;
import java.net.*;
import java.util.*;

//Client Code
public class client {

	public static void main(String[] args) throws UnknownHostException, IOException {
		// TODO Auto-generated method stub

		int number;//Number for user input, used for switch statement
		String temp = "";//String used to display info from server
		int request = 0;//Number sent to server for response
		
		//Used to get user input for response
		Scanner input = new Scanner (System.in);
		
		//Socket is needed for communication between a client and a host server
		//requiring the IP address and the port number (>2000)
		Socket s = new Socket("127.0.0.1",3011);
		
		//Used to get the output from the server
		Scanner input1 = new Scanner(s.getInputStream());
		printMenu();
		
		do {
		//Sets number to user input for switch
		number = input.nextInt();
		
		//Sets start time for response time
		Long startTime = System.currentTimeMillis();
		
		//Get request based on user input
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
			
			//Exit case
		case 7:
			System.out.println("GoodBye.");
			System.out.println("Connection ended.");
			System.exit(0);
			break;
			
			//Any input other than #'s 1-7 is invalid. Request another number
		default:
			System.out.println("Invalid Request.  Try Again..");
			break;
		}//end switch
		
		
		//Pass number to the server
		PrintStream ps = new PrintStream(s.getOutputStream());
		
		//Pass number to the server through print stream
		ps.println(request);
		
		temp = input.nextLine();
		
		//get data from server
		if (input1.hasNextLine() == true)
			temp = temp + input1.nextLine();
		
		//Print data from server
		if(temp != null)
			System.out.println(temp);
		
		//Get endtime for response & print total time
		Long endTime = System.currentTimeMillis();
		System.out.println("Response Time: " + (endTime - startTime));

		//Re-print Menu for user
		//printMenu();
		
		//Resets temp String
		temp = "";
		
		//When user inputs 7, exit while loop
		} while (request != 7);
		
		//Close all scanners & sockets before ending the program
		input.close();
		s.close();
		input1.close();
	}//end main
		
	
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
		//System.out.println("\n");
	}//end printMenu

}//end client
