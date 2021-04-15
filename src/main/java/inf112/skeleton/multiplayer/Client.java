package inf112.skeleton.multiplayer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import org.json.*;

// TODO: Implement Client methods
// TODO: Use Reciever.java to listen to host
// TODO: Use Sender.java to send data to host
public class Client{
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void startConnection(String ip, int port){
        try{
            clientSocket = new Socket(ip, port);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String sendMessage(String msg) {
        try{
            out.println(msg);
            System.out.println("Client says: " + msg);
            String resp = in.readLine();
            System.out.println("Server says: " + msg);
            return resp;}

        catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

//    public void sendJSONobject(JSONObject packet) {
//    }

    public void stopConnection() throws IOException {
        try{
            in.close();
            out.close();
            clientSocket.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}

