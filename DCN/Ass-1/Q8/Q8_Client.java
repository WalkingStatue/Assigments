import java.io.*;
import java.net.*;

public class Q8_Client {
    public static void main(String[] args) {
        String host = "127.0.0.1";
        int port = 65432;
        try {
            InetAddress address = InetAddress.getByName(host);
            DatagramSocket socket = new DatagramSocket();
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Enter a sentence: ");
            String sentence = reader.readLine();
            byte[] buffer = sentence.getBytes();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, port);
            socket.send(packet);

            buffer = new byte[1024];
            packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);
            String received = new String(packet.getData(), 0, packet.getLength());
            System.out.println("Reverse Camel Case Sentence: " + received);

            socket.close();
        } catch (IOException ex) {
            System.out.println("Client exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
