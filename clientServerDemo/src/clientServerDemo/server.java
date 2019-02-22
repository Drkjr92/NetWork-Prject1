package clientServerDemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.Date;
//Server Code
public class server {
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("Listening...");
			int number = 0;//Number for user input, used for switch statement
			String temp = "";//String used to output info from server
			
			//Socket, Scanner & BufferedReader declaration
			ServerSocket serverSocket = new ServerSocket (3011);
			Socket ss = serverSocket.accept();
			Scanner input = new Scanner(ss.getInputStream());
			BufferedReader br;
			
			//While loop runs until it receives "7" from client
			while (input.hasNextInt()) {
				
			System.out.println("Request received");	
			
			//Sets number to user input for switch
			number = input.nextInt();
			
			//Get request based on user input
			switch (number) {
			
			//Date & Time Request
			case 1:
				System.out.println("Request:  Current Date and Time");
				Date date = new Date();
				
				temp = "Current Date & Time: " + date.toString();
				
				System.out.println("Sent\n");
				break;

				//Uptime Request
			case 2:
				System.out.println("Request:  Uptime");
				Process uptime = Runtime.getRuntime().exec("uptime");
				
				br = new BufferedReader (new 
						InputStreamReader(uptime.getInputStream()));
				
				temp = br.readLine();
				
				System.out.println("Sent\n");
				break;
				
				//Memory Usage Request
			case 3:
				System.out.println("Request:  Memory Use");
				
				long totalMemory = Runtime.getRuntime().totalMemory();
				long freeMemory = Runtime.getRuntime().freeMemory();;
				long usedMemory = totalMemory - freeMemory;
				
				temp = ("Current Total Memory: " + totalMemory + 
						" Current Free Memory: " + freeMemory +
						" Current Memory Usage: " + usedMemory);
				System.out.println("Sent\n");
				break;
				
				//Netstat Request
			case 4:
				System.out.println("Request:  Netstat");
				
				Process netstat = Runtime.getRuntime().exec("netstat");
				
				br = new BufferedReader (new 
						InputStreamReader(netstat.getInputStream()));
				
				temp = br.readLine();
				
				//Trying to add lines to "temp"
				temp = temp + br.readLine();
				
				System.out.println("Sent\n");
				break;
				
				//Users Request
			case 5:
				System.out.println("Request: Users");
				
				Process users = Runtime.getRuntime().exec("users");
				
				br = new BufferedReader (new 
						InputStreamReader(users.getInputStream()));
				
				temp = br.readLine();
				
				System.out.println("Sent\n");
				break;
				
				//Running Processes Request
			case 6:
				System.out.println("Request:  Running Processes");
				
				Process ps = Runtime.getRuntime().exec("ps");
				
				br = new BufferedReader (new 
						InputStreamReader(ps.getInputStream()));
				
				temp = br.readLine();
				
				//Trying to add lines to "temp"
				temp = temp + br.readLine();
				
				
				System.out.println("Sent\n");
				break;
				
				//Exit Case
			case 7:
				System.exit(0);
				break;
			}//end switch
			
			//Sends "temp" string to client
			PrintStream ps = new PrintStream(ss.getOutputStream());
			ps.println(temp);
			}//end while
			
			//Closes server
			System.out.println("Request:  Terminate Connection");
			System.out.println("Terminate Connection");
			input.close();
			serverSocket.close();
	}//end main

}//end server

