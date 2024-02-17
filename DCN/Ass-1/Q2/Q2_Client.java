import java.io.*;
import java.net.*;

public class Q2_Client {
    public static void main(String[] args) {
        String host = "127.0.0.1";
        int port = 65432;
        try {
            InetAddress address = InetAddress.getByName(host);
            DatagramSocket socket = new DatagramSocket();

            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter a number to cube: ");
            String userInput = stdIn.readLine();
            byte[] buffer = userInput.getBytes();

            DatagramPacket request = new DatagramPacket(buffer, buffer.length, address, port);
            socket.send(request);

            byte[] responseBuffer = new byte[1024];
            DatagramPacket response = new DatagramPacket(responseBuffer, responseBuffer.length);
            socket.receive(response);

            String received = new String(response.getData(), 0, response.getLength());
            System.out.println("The cube of the number is: " + received);

            socket.close();
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }
}
