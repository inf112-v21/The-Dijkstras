package inf112.skeleton.multiplayer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

// TODO: Implement Host methods
// TODO: Use Reciever.java to listen to clients
// TODO: Use Sender.java to send data to clients
public class Host extends Thread{

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private int port = 6666;

    public Host() {
    }

    public void run() {
        try {
            serverSocket = new ServerSocket(port);

            clientSocket = serverSocket.accept();
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String greeting = in.readLine();

            if ("hello server".equals(greeting)) {
                out.println("hello client");
            }
            else {
                out.println("unrecognised greeting");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close(){
        try{
            in.close();
            out.close();
            clientSocket.close();
            serverSocket.close();
        }catch (IOException e) {
        e.printStackTrace();
        }
    }
}