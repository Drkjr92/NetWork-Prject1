package ServerClient;

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
			int number = 0;
			String temp = null;
			ServerSocket serverSocket = new ServerSocket (3011);
			Socket ss = serverSocket.accept();
			Scanner input = new Scanner(ss.getInputStream());
			BufferedReader br;
			
			
			while (input.hasNextInt()) {
				
			System.out.println("Request received");	
			
			number = input.nextInt();
			
			switch (number) {
			
			case 1:
				System.out.println("Request:  Current Date and Time");
				Date date = new Date();
				temp = "Current Date & Time: " + date.toString();
				//temp = "Host Current Date and Time";
				System.out.println("Sent\n");
				break;
				
			case 2:
				System.out.println("Request:  Uptime");
				Process uptime = Runtime.getRuntime().exec("uptime");
				
				br = new BufferedReader (new 
						InputStreamReader(uptime.getInputStream()));
				
				temp = br.readLine();
				
				System.out.println("Sent\n");
				break;
				
			case 3:
				System.out.println("Request:  Memory Use");
				
				long totalMemory = Runtime.getRuntime().totalMemory();
				long freeMemory = Runtime.getRuntime().freeMemory();;
				long usedMemory = totalMemory - freeMemory;
				
				//temp = "Host Memory Use";
				
				temp = ("Current Total Memory: " + totalMemory + 
						" Current Free Memory: " + freeMemory +
						" Current Memory Usage: " + usedMemory);
				System.out.println("Sent\n");
				break;
				
			case 4:
				System.out.println("Request:  Netstat");
				temp = "Host Netstat";
				System.out.println("Sent\n");
				break;
				
			case 5:
				System.out.println("Request:  Users");
				
				Process users = Runtime.getRuntime().exec("users");
				
				br = new BufferedReader (new 
						InputStreamReader(users.getInputStream()));
				
				temp = br.readLine();
				
				//temp = "Host Current User";
				System.out.println("Sent\n");
				break;
				
			case 6:
				System.out.println("Request:  Running Process");
				temp = "Host Running Process";
				System.out.println("Sent\n");
				break;
				
			case 7:
				System.exit(0);
				break;
			}
			
			PrintStream ps = new PrintStream(ss.getOutputStream());
			ps.println(temp);
			}
			
			System.out.println("Request:  Terminate Connection");
			System.out.println("Terminate Connection");
			input.close();
			serverSocket.close();
	}

}//

