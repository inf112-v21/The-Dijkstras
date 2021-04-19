package inf112.skeleton.multiplayerTest;

import inf112.skeleton.multiplayer.Client;
import inf112.skeleton.multiplayer.Server;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ServerTest {
    Server server;


    @Before
    public void setup() {
        server = new Server(5001);
        server.start();

    }

    @Test
    public void ServerRespondsToMessage() {

        Client client = new Client();
        client.startConnection("127.0.0.1", 5001);
        String terminate = client.sendMessage(".");

        assertEquals("bye", terminate);

    }

    @Test
    public void ServerRespondsToMultipleMessages() {
        Client client = new Client();
        client.startConnection("127.0.0.1", 5001);

        String response1 = client.sendMessage("Hello");
        assertEquals("Hello", response1);

        String response2 = client.sendMessage("World");
        assertEquals("World", response2);

        String response3 = client.sendMessage(".");
        assertEquals("bye", response3);

    }

    @Test
    public void ServerRespondsToMultipleClients() {

        Client client1 = new Client();
        client1.startConnection("127.0.0.1", 5001);
        String response1 = client1.sendMessage(".");


        Client client2 = new Client();
        client2.startConnection("127.0.0.1", 5001);
        String response2 = client2.sendMessage(".");

        assertEquals("bye", response1);
        assertEquals("bye", response2);
    }

    @After
    public void tearDown() {
        server.serverStop();
    }
}
