package clientServerDemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class server{

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

			int number = 0;
			String temp = null;
			ServerSocket serverSocket = new ServerSocket (3017);
			Socket ss = serverSocket.accept();
			Scanner input = new Scanner(ss.getInputStream());
			
			while (input.hasNextInt()) {
			
			number = input.nextInt();
			
			switch (number) {
			
			case 1:
				System.out.println("Requested:  Host Current Date and Time");
				// run the Unix "ps -ef" command
			    // using the Runtime exec method:
			    Process date = Runtime.getRuntime().exec("date");
			            
			    BufferedReader dateInput = new BufferedReader(new 
			           InputStreamReader(date.getInputStream()));
			                
			    temp = dateInput.readLine();
			    System.out.println("Sent");
				break;

			case 2:
				System.out.println("Requested:  Host Uptime");
				// run the Unix "ps -ef" command
			    // using the Runtime exec method:
			    Process uptime = Runtime.getRuntime().exec("uptime");
			            
			    BufferedReader uptimeInput = new BufferedReader(new 
			           InputStreamReader(uptime.getInputStream()));
			                
			    temp = uptimeInput.readLine();
			    System.out.println("Sent");
				break;
				
			case 3:
				System.out.println("Requested:  Host Memory Use");
				// run the Unix "ps -ef" command
			    // using the Runtime exec method:
			    Process memory = Runtime.getRuntime().exec("vm_stat");
			            
			    BufferedReader memoryInput = new BufferedReader(new 
			           InputStreamReader(memory.getInputStream()));
			                
			    temp = memoryInput.readLine();
			    System.out.println("Sent");
				break;
				
			case 4:
				System.out.println("Requested:  Host Netstat");
				// run the Unix "ps -ef" command
			    // using the Runtime exec method:
			    Process netstat = Runtime.getRuntime().exec("netstat -a");
			            
			    BufferedReader netstatInput = new BufferedReader(new 
			           InputStreamReader(netstat.getInputStream()));
			                
			    temp = netstatInput.readLine();
			    System.out.println("Sent");
				break;
				
			case 5:
				System.out.println("Requested:  Host Current User");
				// run the Unix "ps -ef" command
			    // using the Runtime exec method:
			    Process user = Runtime.getRuntime().exec("users");
			            
			    BufferedReader userInput = new BufferedReader(new 
			           InputStreamReader(user.getInputStream()));
			                
			    temp = userInput.readLine();
			    System.out.println("Sent");
				break;
				
			case 6:
				System.out.println("Requested:  Host Running Process");
				// run the Unix "ps -ef" command
			    // using the Runtime exec method:
			    Process process = Runtime.getRuntime().exec("ps -A");
			            
			    BufferedReader processInput = new BufferedReader(new 
			           InputStreamReader(process.getInputStream()));
			                
			    temp = processInput.readLine();
			    System.out.println("Sent");
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

}

