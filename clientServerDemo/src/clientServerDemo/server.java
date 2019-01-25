package clientServerDemo;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class server {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		int number, temp;
		ServerSocket serverSocket = new ServerSocket (3017);
		Socket ss = serverSocket.accept();
		Scanner input = new Scanner(ss.getInputStream());
		
		do {
		
		//Create the server socket to communicate with the client, 
		//using the same port number as the client.  No IP Address 
		//ServerSocket serverSocket = new ServerSocket (3017);
		
		//Another regular socket is still required, this time accepting
		//communication/request to the server socket from the client
		//Socket ss = serverSocket.accept();
		
		//Recieves an input stream through the socket (regular one)
		//Scanner input = new Scanner(ss.getInputStream());
		number = input.nextInt();
		
		temp = number * 2;
		
		PrintStream ps = new PrintStream(ss.getOutputStream());
		ps.println(temp);
		} while (number != 8);
		
		input.close();
		serverSocket.close();
	}

}
