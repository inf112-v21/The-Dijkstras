package inf112.skeleton.multiplayerTest;

import inf112.skeleton.multiplayer.Client;
import inf112.skeleton.multiplayer.Host;
import inf112.skeleton.multiplayer.Receiver;
import inf112.skeleton.multiplayer.Sender;
import org.junit.*;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.Assert.*;
import inf112.skeleton.multiplayer.*;

import java.io.IOException;

public class testClient {
    testHost server;


    @Before
    public void setup() {
        this.server = new testHost();
        server.serverStart(5555);
    }

    @Test
    public void givenClient1_whenServerResponds_thenCorrect() {
        EchoClient client1 = new EchoClient();
        client1.startConnection("127.0.0.1", 5555);
        String msg1 = client1.sendMessage("hello");
        String msg2 = client1.sendMessage("world");
        String terminate = client1.sendMessage(".");

        assertEquals(msg1, "hello");
        assertEquals(msg2, "world");
        assertEquals(terminate, "bye");
    }

    @Test
    public void givenClient2_whenServerResponds_thenCorrect() {
        EchoClient client2 = new EchoClient();
        client2.startConnection("127.0.0.1", 5555);
        String msg1 = client2.sendMessage("hello");
        String msg2 = client2.sendMessage("world");
        String terminate = client2.sendMessage(".");

        assertEquals(msg1, "hello");
        assertEquals(msg2, "world");
        assertEquals(terminate, "bye");
    }

    @After
    public void tearDown() {
        server.serverStop();
    }
}