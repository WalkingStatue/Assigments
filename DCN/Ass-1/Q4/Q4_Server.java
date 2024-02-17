import java.io.*;
import java.net.*;
import java.util.Arrays;

public class Q4_Server {
    public static void main(String[] args) {
        int port = 65432;
        try (DatagramSocket socket = new DatagramSocket(port)) {
            System.out.println("Server is listening on port " + port);
            byte[] buffer = new byte[1024];

            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                String received = new String(packet.getData(), 0, packet.getLength());
                int[] numbers = Arrays.stream(received.split(",")).mapToInt(Integer::parseInt).toArray();
                Arrays.sort(numbers);

                double q1 = calculateQuartile(numbers, 25);
                double q2 = calculateQuartile(numbers, 50);
                double q3 = calculateQuartile(numbers, 75);

                String quartiles = q1 + "," + q2 + "," + q3;
                byte[] sendData = quartiles.getBytes();
                InetAddress address = packet.getAddress();
                int portToSend = packet.getPort();
                packet = new DatagramPacket(sendData, sendData.length, address, portToSend);
                socket.send(packet);
            }
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private static double calculateQuartile(int[] values, double quartile) {
        double n = (quartile / 100.0) * (values.length - 1) + 1;
        if (n == 1.0) return values[0];
        if (n == values.length) return values[values.length - 1];
        int index = (int) n;
        double diff = n - index;
        return values[index - 1] + diff * (values[index] - values[index - 1]);
    }
}
