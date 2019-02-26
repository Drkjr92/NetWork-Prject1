package clientServerDemo;

import java.io.IOException;
import java.io.PrintStream;
import java.net.*;
import java.util.*;
import java.util.regex.Pattern;

//Client Code
public class client {

	public static void main(String[] args) throws UnknownHostException, IOException {
		// TODO Auto-generated method stub

		int number = 0;//Number for user input, used for switch statement
		int clientCount = 1;//Number for client count
		String temp = "";//String used to display info from server
		int request = 0;//Number sent to server for response
		
		//Used to get user input for response
		Scanner input = new Scanner (System.in);
		
		//Get user input for IP Address
		System.out.println("Enter IP Address:");
		String serverIP;
		serverIP = input.nextLine();
		
		while (!verifyIP(serverIP)) {
            System.out.println("INVALID IP ADDRESS, RE-ENTER IP ADDRESS");
            serverIP = input.nextLine();
        }
		
		//Socket is needed for communication between a client and a host server
		//requiring the IP address and the port number (>2000)
		Socket s = new Socket();
		
		try{
		s = new Socket(serverIP,3011);
		} 
		catch (Exception e) {
		System.out.println("INCORRECT IP ADDRESS\n\nPROGRAM TERMINATED");
		System.exit(1);
		}
		
		
		//Used to get the output from the server
		Scanner input1 = new Scanner(s.getInputStream());
		printMenu();
		
		do {
		//Reset request to 0
			request = 0;
			
		//Sets number to user input for switch
		while(!input.hasNextInt()){
			System.out.println("INVALID CHARACTER, RE-ENTER REQUEST #");
			input.nextLine();
		}
		//Sets number to user input for switch
		number = input.nextInt();
		
		//Check if number is between 1 & 6. If not, skip client count request.
		//Set client count to 1 for invalid response
		if(number < 7 && number > 0){
			System.out.println("Enter # of clients:");
		
			//Check if client count is valid (clientCount >= 1)
			while (clientCount < 1 || !input.hasNextInt()){
				System.out.println("INVALID NUMBER, RE-ENTER # OF CLIENTS");
				input.nextLine();
				}//end short while
		
			clientCount = input.nextInt();
	
			//Set to 1 for invalid number
		}else
			clientCount = 1;
		
		//Sets start time for response time
		Long startTime = System.currentTimeMillis();
		
		//For loop for multiple clients
		int i;
		for (i = 0; i < clientCount; i++){
		
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
			
			//Any input other than 1 through 7 is invalid. Request another number
		default:
			System.out.println("Invalid Request.  Try Again..");
			break;
		}//end switch
		
		
		//Pass number to the server
		PrintStream ps = new PrintStream(s.getOutputStream());
		
		//Pass number to the server through print stream
		ps.println(request);
		
		//get data from server
		temp = temp + input1.nextLine();
		
		//Format memory usage text
		if(number == 3){
			temp = temp + "\n" + input1.nextLine();
			temp = temp + "\n" + input1.nextLine();
		}
		
		//If number request is not invalid, print data from server
		if(number < 7 && number > 0)
			System.out.println(temp);
		
		//Resets temp String
		temp = "";
		
		}//end for loop for multiple clients
		
		//Get endtime for response & print total time
		Long endTime = System.currentTimeMillis();
		System.out.println("Response Time: " + (endTime - startTime) + " ms\n");

		//Re-print Menu for user, comment out to maintain sanity!!
		printMenu();
		
		//When user inputs 7, exit while loop
		} while (request != 7);
		
		//Close all scanners & sockets before ending the program
		input.close();
		s.close();
		input1.close();
	}//end main
		
	//Prints Menu for user input
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
	
    //check IP  using regex
    public static boolean verifyIP(String IP)
    {

        String IP_ADDRESS_REGEX = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
                + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
                + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
                + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

        Pattern ipPattern = Pattern.compile(IP_ADDRESS_REGEX);
        if (ipPattern.matcher(IP).matches()) {
            return true;

        }//end if

        return false;

    }//end verifyIP

}//end client
