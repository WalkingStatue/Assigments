import java.io.*;
import java.net.*;

public class Sender2 {

    public static void main(String[] args) {
        try {
            // Sender side
           // int[] data = {67, 43, 0, 22};
            byte[] data = {(byte)67,(byte) 43,(byte) 0, (byte)22};

            // Calculate checksum
            int checksum = calculateChecksum(data);

            // Establish a connection
            Socket socket = new Socket("localhost", 12345);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Send data along with checksum
            for (int value : data) {
                out.println(value);
                System.out.println("value" + getBinaryString((byte)value));
            }
            out.println(checksum);
            System.out.println("_______________________________________________");
            System.out.println("Checksum....." + getBinaryString((byte)checksum));

            System.out.println("Data being sent along with Checksum.....");
            System.out.println("Thanks for the feedback!!");

            // Close the connection
            out.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int calculateChecksum(byte[] data) {
        int sum = 0;
        for (int value : data) {
            sum += value;
        }
        return ~sum & 0xFF; // One's complement and 8-bit truncation
    }
    private static String getBinaryString(byte b) {
        StringBuilder binaryStringBuilder = new StringBuilder();
        for (int i = 7; i >= 0; i--) {
            int bit = (b >> i) & 1;
            binaryStringBuilder.append(bit);
        }
        return binaryStringBuilder.toString();
    }
   
}
