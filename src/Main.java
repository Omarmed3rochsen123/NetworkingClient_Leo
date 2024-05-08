import java.io.*;
import java.net.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        Socket client = null;

        //Default port number we are going to use
        int portnumber = 52800;
        if(args.length >= 1){
            portnumber = Integer.parseInt(args[0]);
        }

        for( int i = 0; i < 10; i++){
            try{
                String msg = "";

                //Create a client socket
                client = new Socket(InetAddress.getLocalHost(), portnumber);
                System.out.println("Client socket is created " + client);

                //Create an output stream of the client socket
                OutputStream clientOut = client.getOutputStream();
                PrintWriter pw = new PrintWriter(clientOut, true);

                //Create an input stream of the client socket
                InputStream clientIN = client.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(clientIN));

                //Create Bufferedreader for a standard input
                BufferedReader stdIN = new BufferedReader(new InputStreamReader(System.in));


                System.out.println("Enter your name. Type Bye to exit. ");
                System.out.print(": ");

                //read date form standard input device and write it
                //to the output stream of the client socket
                msg = stdIN.readLine().trim();
                pw.println(msg);

                //Read data from the input stream of the client socket
                System.out.println("Message returned from the server = " + br.readLine());

                pw.close();
                br.close();
                client.close();

                //stop the operation
                if (msg.equalsIgnoreCase("bye")){
                    break;
                }


            } catch (IOException ie){
                System.out.println("I/O error " + ie);
            }
        }
    }
}