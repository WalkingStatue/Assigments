import java.io.*;
import java.net.*;

public class Q4_Client {
    public static void main(String[] args) {
        String host = "127.0.0.1";
        int port = 65432;
        try {
            InetAddress address = InetAddress.getByName(host);
            DatagramSocket socket = new DatagramSocket();

            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter a set of numeric values (comma separated): ");
            String userInput = stdIn.readLine();
            byte[] buffer = userInput.getBytes();

            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, port);
            socket.send(packet);

            byte[] responseBuffer = new byte[1024];
            packet = new DatagramPacket(responseBuffer, responseBuffer.length);
            socket.receive(packet);

            String received = new String(packet.getData(), 0, packet.getLength());
            System.out.println("Quartiles: " + received);

            socket.close();
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }
}
