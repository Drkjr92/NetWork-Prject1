package clientServerDemo;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;
import java.util.regex.Pattern;


public class Client {


    static Scanner s = new Scanner(System.in);
    private static ArrayList<Thread> threads = new ArrayList<>();
    private static ArrayList<Long> responseTime = new ArrayList<>();
    private static String serverIP;
    static int request = 0;


    public static void main(String[] args) {

        //will set and check IP address
        getIP(s);

        //will get User Request number
        userInput(serverIP);

        System.out.print("Enter how many threads");
        int threadCount = s.nextInt();
        for (int i = 0; i < threadCount; i++) {
            ClientThread thread = new ClientThread(serverIP, responseTime);
            threads.add(new Thread(thread));

        }

        threads.forEach(Thread::run);

        //print response time
        
        threads.clear();


    }//end of main


    //method to get and validate IP address
    public static void getIP(Scanner s) {

        System.out.println("======================================================================");
        System.out.println("========================CLIENT SIDE===================================");

        System.out.println("Type the server IP address: ");
        serverIP = s.nextLine();

        while (!isValidIP(serverIP)) {
            System.out.println("ERROR! INVALID IP address");
            serverIP = s.nextLine();

        }

    }


    //boolean to validate IP address
    public static boolean isValidIP(String serverIP) {
        String IP_REGEX = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
                + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
                + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
                + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

        Pattern p = Pattern.compile(IP_REGEX);
        //if input is a valid IP address
        if (p.matcher(serverIP).matches())
            return true;

        return false;

    }


    public static void userInput(String serverIP) {

        System.out.println("The IP address is " + serverIP);
        System.out.println("==============================================================");
        System.out.println("\n You will be presented with several options: Please enter the number 1-7\n");
        printMenu();

        request = s.nextInt();

        while (!isValidReq(request)) {
            System.out.println("ERROR! Please select numbers 1-7");
            request = s.nextInt();

        }

    }


    public static boolean isValidReq(int request) {

        Integer[] requestNum = new Integer[]{1, 2, 3, 4, 5, 6, 7};
        List<Integer> optionList = Arrays.asList(requestNum);

        return optionList.contains(request);

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

    }


   static class ClientThread implements Runnable {


        private long endTimeStamp;
        String serverIP;
        ArrayList<Long> responseTime = new ArrayList<Long>();


        public ClientThread(String serverIP, ArrayList<Long> responseTime) {
            this.serverIP = serverIP;
            this.responseTime = responseTime;

        }

        //run threads
        @Override
        public void run() {

            clientServerConnection(serverIP, responseTime, endTimeStamp);

        }


        //method to connect to server and gain responses

        private void clientServerConnection(String serverIP, ArrayList<Long> responseTime, long endTimeStamp) {
            //Socket is needed for communication between a client and a host server
            //requiring the IP address and the port number (>2000)
            Socket socket = null;
            BufferedReader in = null;
            DataOutputStream out = null;

            try {
                System.out.println("Your request to the server is: " + request);
                System.out.println("CLIENT: Making connection to Server with IP address of... " + serverIP);
                socket = new Socket(serverIP, 3017);
                System.out.println(" ");


                out = new DataOutputStream(socket.getOutputStream());
                in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));

                System.out.println("CLIENT: Waiting for Server confirmation... ");

                if (socket !=null && in !=null && out !=null)
                {
                    //start time
                    Long startTime = System.currentTimeMillis();

                    //send the response # to server
                    out.writeByte(request);
                    System.out.println("SUCCESSFULLY sent data to Server. Starting time starts now. ");

                    String ServerResponse;

                    while((ServerResponse = in.readLine()) !=null)
                    {

                        System.out.println(ServerResponse);

                        endTimeStamp = System.currentTimeMillis();

                    }

                    responseTime.add(startTime - endTimeStamp);

                    System.out.println();


                }


            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }


    }

}
