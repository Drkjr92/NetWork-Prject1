package clientServerDemo;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;
import java.util.regex.Pattern;


public class client {

	//Variable Declarations
    private static ArrayList<Long> responseTimes = new ArrayList<>();
    private static ArrayList<Thread> threads = new ArrayList<>();
    private static Scanner scanning = new Scanner(System.in);
    private static String serverIP;
    private static int request = 0;

    public static void main(String[] args) throws UnknownHostException, IOException {
        // TODO Auto-generated method stub
    	
    	//Thread Count initialized
    	int threadCount = 0;

    	//Requests IP Address
        getIP(scanning); //sets the IP address

        while (true){
        	
        	getRequest(serverIP); // sets the request number

        	//If requesting exit, exit immediately
        	if(request == 7){
        		System.out.println("Program Terminated\n");
        		System.exit(1);
        		}//end if
        	else{//Prompt user for thread count
        		System.out.println("IP ADDRESS AND REQUEST NUMBER SET."
        				+ "\nBEFORE ATTEMPTING TO CONNECT, HOW MANY "
        				+ "THREADS WOULD YOU LIKE? ");
        		threadCount = scanning.nextInt();
        		}//end else
        
        	//Create threads, then run them
        	for (int i = 0; i < threadCount; i++)
        	{

        		ClientP1Thread createThread = new ClientP1Thread(serverIP,responseTimes, request);
        		threads.add(new Thread(createThread));
            
        	}
       
       threads.forEach(Thread::run);
       System.out.println("Requests Complete...\n");
       threads.clear();
       
       int j;
       double y = 0;
       for(j = 0; j < responseTimes.size(); j++){
    	   System.out.println(responseTimes.get(j));
    	   y = y + responseTimes.get(j);
       }
       System.out.println("Response Time Average: " + y/threadCount + " \n");
       responseTimes.clear();
     
        }//end while
        
    }//end main

    //USER inputs IP address
    public static void getIP(Scanner scan)
    {
        System.out.println("====================WELCOME TO CLIENT SIDE=============================");
        System.out.println();
        
        System.out.println("Please type in IP address");
        serverIP = scan.nextLine();


        while (!verifyIP(serverIP)) {
            System.out.println("INVALID IP ADDRESS\nPROGRAM TERMINATED");
            System.exit(2);
            //serverIP = scan.nextLine();
        }

        System.out.println("Valid IP address accepted. One moment while we connect to the address of "
        +  serverIP);


    }

    private static void getRequest(String serverIP)
    {
        System.out.println("==========================REQUEST TO SERVER===========================================");
        System.out.println();
        System.out.println("Enter your request to the Server with address--> " + serverIP);
        printMenu();
        request = scanning.nextInt();

        while (!isValidRequest(request))
        {
            System.out.println("Request option invalid!");
		printMenu();
            request = scanning.nextInt();


        }

    }

    //method to check if request is valid
    private static boolean isValidRequest(int request)
    {
        Integer[] validRequest = new Integer[]{1,2,3,4,5,6,7};
        List<Integer> list = Arrays.asList(validRequest);

        return list.contains(request);
    }


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

        }

        return false;

    }

    //prints menu option for user
    public static void printMenu() {
        System.out.println("1.\tHost Current Date and Time");
        System.out.println("2.\tHost Uptime");
        System.out.println("3.\tHost Memory Use");
        System.out.println("4.\tHost Netstat");
        System.out.println("5.\tHost Current Users");
        System.out.println("6.\tHost Running Process");
        System.out.println("7.\tQuit");
        System.out.println("\n");
        System.out.println("Enter Request: ");
    }

}

class ClientP1Thread implements Runnable
{

    String serverIP;
    ArrayList<Long> responseTimes = new ArrayList<>();
    int request;

    public ClientP1Thread(String serverIP, ArrayList<Long> responseTimes, int request)
    {
        this.serverIP = serverIP;
        this.responseTimes = responseTimes;
        this.request = request;

    }

    //override method to connect to server
    //clienttoserver connection
    @Override
    public void run()
    {
    	
        clientToServerConnection(serverIP, responseTimes);

    }


    public void clientToServerConnection(String serverIP, ArrayList<Long> responseTimes)
    {
        Socket socket = null;
        DataOutputStream out = null;
        PrintStream sendit = null;
        String temp = "";
        Long endTime;
        Scanner scanned;

        try{
            System.out.println("Attempting to connect to Server with IP: " +serverIP + " with the request " + request );
            socket = new Socket(serverIP, 3010);
            sendit = new PrintStream(socket.getOutputStream());
            System.out.println("Connected");
            System.out.println("==================================================================================");
            System.out.println("=============================CONNECTED TO SERVER==================================");
            System.out.println();

            Long startTime = System.currentTimeMillis();
            
            sendit.println(request);
            System.out.println(request);
               
                System.out.println("Your request has been sent and the timer has started");
                System.out.println();
                
                scanned = new Scanner(socket.getInputStream());
                
                int i;
                //Format output
                if(request == 3)
                	for(i = 0; i < 3; i++)
                		temp = temp + scanned.nextLine() + "\n";
                else if (request != 7)
                	temp = scanned.nextLine();
              
                endTime = System.currentTimeMillis();
                
                System.out.println(temp);
                
                responseTimes.add(endTime - startTime);

                if (!(request!=7)) {
                    System.out.println("============================CLOSING CONNECTIONS===================================");
                    socket.close();
                    sendit.close();
                }









        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
