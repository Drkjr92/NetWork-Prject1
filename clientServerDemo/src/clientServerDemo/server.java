package clientServerDemo;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class server {

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
				System.out.println("Request:  Current Date and Time");
				temp = "Host Current Date and Time";
				System.out.println("Sent");
				break;
				
			case 2:
				System.out.println("Request:  Uptime");
				temp = "Host Uptime";
				System.out.println("Sent");
				break;
				
			case 3:
				System.out.println("Request:  Memory Use");
				temp = "Host Memory Use";
				System.out.println("Sent");
				break;
				
			case 4:
				System.out.println("Request:  Netstat");
				temp = "Host Netstat";
				System.out.println("Sent");
				break;
				
			case 5:
				System.out.println("Request:  Current User");
				temp = "Host Current User";
				System.out.println("Sent");
				break;
				
			case 6:
				System.out.println("Request:  Running Process");
				temp = "Host Running Process";
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

