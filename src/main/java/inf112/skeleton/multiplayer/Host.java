package inf112.skeleton.multiplayer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

// TODO: Implement Host methods

public class Host extends Thread{

    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public Host(ServerSocket socket) throws IOException {
        clientSocket = socket.accept();
    }

    public void run() {
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);

            in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));

            String inputLine = null;

            while (true) {
                if ((inputLine = in.readLine()) == null) break;

                if (".".equals(inputLine)) {
                    out.println("bye");
                    break;
                }
                out.println(inputLine);
            }
            close();
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
        }catch (IOException e) {
        e.printStackTrace();
        }
    }
}