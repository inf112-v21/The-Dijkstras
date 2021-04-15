package inf112.skeleton.multiplayerTest;

import inf112.skeleton.multiplayer.Client;
import inf112.skeleton.multiplayer.Host;
import inf112.skeleton.multiplayer.Receiver;
import inf112.skeleton.multiplayer.Sender;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


public class TCPTest extends Thread{
    Host server;


    @Before
    public void startServer(){
        this.server = new Host();
    }

    @Test
    public void ServerRespondsByThreading(){
        server.start();

        Client client = new Client();
        client.startConnection("127.0.0.1", 6666);
        String response = client.sendMessage("hello server");
        assertEquals("hello client", response);
        server.close();
    }

    @Test
    public void ServerRespondsToMultipleClients(){
        server.start();

        Client client1 = new Client();
        Client client2 = new Client();
        client1.startConnection("127.0.0.1", 6666);
        client2.startConnection("127.0.0.1", 6666);

        String response1 = client1.sendMessage("hello server");
        String response2 = client2.sendMessage("hello server");
        server.close();

    }
}
