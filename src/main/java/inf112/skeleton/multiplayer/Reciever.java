package inf112.skeleton.multiplayer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Reciever extends Thread {
    private DatagramSocket socket;
    private String Host = "localhost";
    private int port = 5001;
    private byte[] buffer = new byte[512];
    private boolean running = true;

    public Reciever() throws SocketException {
        socket = new DatagramSocket(port);
    }

    public void run() {
        while (running) {
            DatagramPacket packet
                    = new DatagramPacket(buffer, buffer.length);
            try {
                socket.receive(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }

            InetAddress address = packet.getAddress();
            int port = packet.getPort();
            packet = new DatagramPacket(buffer, buffer.length, address, port);
            String received
                    = new String(packet.getData(), 0, packet.getLength());

            if (received.equals("end")) {
                running = false;
                continue;
            }
            try {
                socket.send(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        socket.close();
    }

    public void close(){
        running = false;
    }
}
