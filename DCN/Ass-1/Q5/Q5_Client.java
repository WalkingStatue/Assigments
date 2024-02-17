import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Q5_Client {
    public static void main(String[] args) {
        String host = "127.0.0.1";
        int port = 65432;
        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress address = InetAddress.getByName(host);
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                System.out.println("Choose an option:");
                System.out.println("a. Celsius to Fahrenheit");
                System.out.println("b. Fahrenheit to Celsius");
                System.out.println("c. Exit");
                System.out.print("Enter choice: ");
                String choice = reader.readLine();

                if (choice.equals("c")) {
                    System.out.println("Exiting...");
                    break;
                }

                System.out.print("Enter temperature: ");
                String temperature = reader.readLine();
                String message = choice + "," + temperature;
                byte[] buffer = message.getBytes();

                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, port);
                socket.send(packet);

                byte[] responseBuffer = new byte[1024];
                DatagramPacket responsePacket = new DatagramPacket(responseBuffer, responseBuffer.length);
                socket.receive(responsePacket);

                String received = new String(responsePacket.getData(), 0, responsePacket.getLength());
                System.out.println("Converted temperature: " + received + "\n");
            }
        } catch (UnknownHostException e) {
            System.out.println("Host could not be determined: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }
}
