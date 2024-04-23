import java.io.*;
import java.net.*;
public class Receiver2 {

    public static void main(String[] args) {
        try {
            // Receiver side
            ServerSocket serverSocket = new ServerSocket(12345);
            Socket socket = serverSocket.accept();
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            // Receive data along with checksum
            int[] receivedData = new int[4];
            //byte[] data  =  new byte[4];
            for (int i = 0; i < 4; i++) {
                receivedData[i] = Integer.parseInt(reader.readLine());
            }
            int receivedChecksum = Integer.parseInt(reader.readLine());

            // Calculate checksum for received data
            int calculatedChecksum = calculateChecksum(receivedData,receivedChecksum);

            System.out.println("At receiver side :");
            System.out.println("Data received (along with checksum) is");
            for (int value : receivedData) {
                System.out.println(value +" "+ getBinaryString((byte)value) );
                
            }
            System.out.println(receivedChecksum + "Sum(receivedChecksum) is : " +  getBinaryString((byte)receivedChecksum));
            System.out.println(calculatedChecksum + "Calculated Checksum is : " + getBinaryString((byte)calculatedChecksum));

            // Verify the checksum
            // if (calculatedChecksum == receivedChecksum) {
            //     System.out.println("Hence, it indicates a successful data transfer.");
            // } else {
            //     System.out.println("Checksum verification failed. Data may be corrupted.");
            // }

            // Close the connection
            reader.close();
            writer.close();
            socket.close();
            serverSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int calculateChecksum(int[] data,int receiveChecksum ) {
        int sum = 0;
        for (int value : data) {
            sum += value;
        }
        sum += receiveChecksum;
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
