import java.io.*;
import java.net.*;

public class Q2_Server {
    public static void main(String[] args) {
        int port = 65432;
        byte[] buffer = new byte[1024];

        try (DatagramSocket socket = new DatagramSocket(port)) {
            System.out.println("Server is listening on port " + port);

            while (true) {
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                socket.receive(request);

                String received = new String(request.getData(), 0, request.getLength());
                int number = Integer.parseInt(received.trim());
                int cubedNumber = number * number * number;

                byte[] sendBuffer = String.valueOf(cubedNumber).getBytes();
                InetAddress clientAddress = request.getAddress();
                int clientPort = request.getPort();

                DatagramPacket response = new DatagramPacket(sendBuffer, sendBuffer.length, clientAddress, clientPort);
                socket.send(response);
            }
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }
}
