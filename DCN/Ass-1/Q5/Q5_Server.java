import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Q5_Server {
    public static void main(String[] args) {
        int port = 65432;
        try (DatagramSocket socket = new DatagramSocket(port)) {
            System.out.println("Server is listening on port " + port);
            byte[] buffer = new byte[1024];

            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                String received = new String(packet.getData(), 0, packet.getLength());
                String[] parts = received.split(",");
                String option = parts[0];
                double temperature = Double.parseDouble(parts[1]);
                double converted;

                if (option.equals("a")) { // Celsius to Fahrenheit
                    converted = (temperature * 9/5) + 32;
                } else { // Fahrenheit to Celsius
                    converted = (temperature - 32) * 5/9;
                }

                String response = String.valueOf(converted);
                byte[] sendBuffer = response.getBytes();
                DatagramPacket responsePacket = new DatagramPacket(sendBuffer, sendBuffer.length, packet.getAddress(), packet.getPort());
                socket.send(responsePacket);
            }
        } catch (SocketException e) {
            System.out.println("SocketException: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }
}
