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
    public void setup() throws InterruptedException {
        server = new Server(5001);
        server.start();

        Thread.sleep(100); // Waiting for server thread to setup
    }

    @Test
    public void ServerRespondsToMessage() {

        Client client = new Client();
        client.startConnection("127.0.0.1", 5001);
        String terminate = client.sendMessage(".");

        assertEquals("bye", terminate);

        client.stopConnection();

    }

    @Test
    public void ServerRespondsToMultipleMessages() throws InterruptedException {
        Client client = new Client();
        client.startConnection("127.0.0.1", 5001);

        String response1 = client.sendMessage("Hello");
        assertEquals("Hello", response1);

        String response2 = client.sendMessage("World");
        assertEquals("World", response2);


        String response3 = client.sendMessage(".");
        assertEquals("bye", response3);

        client.stopConnection();

    }

    @Test
    public void ServerRespondsToMultipleClients() {

        Client client1 = new Client();
        Client client2 = new Client();
        client1.startConnection("127.0.0.1", 5001);
        client2.startConnection("127.0.0.1", 5001);

        String response1 = client1.sendMessage("Hello");
        String response2 = client2.sendMessage("World");
        String response3 = client2.sendMessage(".");
        String response4 = client1.sendMessage(".");

        assertEquals("Hello", response1);
        assertEquals("World", response2);
        assertEquals("bye", response3);
        assertEquals("bye", response4);

        client1.stopConnection();
        client2.stopConnection();
    }


    @Test
    public void ServerRecievesJsonHelloWorld() {

        Client client1 = new Client();
        client1.startConnection("127.0.0.1", 5001);


        String response1 = client1.sendMessage("Hello");

        assertEquals("Hello", response1);
    }

    @Test
    public void ServerWaitsForTwoClients() throws InterruptedException {

        Client client1 = new Client();
        Client client2 = new Client();
        client1.startConnection("127.0.0.1", 5001);
        client2.startConnection("127.0.0.1", 5001);

        String response1 = client1.sendMessage("waitfortwo");

        Thread.sleep(1000);
        String response2 = client2.sendMessage("Hello");

        assertEquals("Hello", response2);
        assertEquals("Two hosts connected", response1);

    }


    @After
    public void tearDown(){
        server.serverStop();
    }
}
