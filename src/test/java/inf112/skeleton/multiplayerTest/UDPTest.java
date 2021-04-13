package inf112.skeleton.multiplayerTest;


import inf112.skeleton.multiplayer.Client;
import inf112.skeleton.multiplayer.Host;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class UDPTest {
    Client client;

    @Before
    public void setup() throws SocketException, UnknownHostException {
        new Host().start();
        client = new Client();
    }

    @Test
    public void whenCanSendAndReceivePacket_thenCorrect() throws IOException {
        String echo = client.sendEcho("hello server");
        assertEquals("hello server", echo);
        echo = client.sendEcho("server is working");
        assertNotEquals("hello server", echo);
    }

    @After
    public void tearDown() throws IOException {
        client.sendEcho("end");
        client.close();
    }
}