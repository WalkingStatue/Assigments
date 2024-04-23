import java.io.*;
import java.net.*;

public class ParityClient {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 1234);

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String data = "0111"; // Example data
            String dataWithParity = calculateParityBit(data);
            System.out.println("Sending data to server: " + dataWithParity);
            out.println(dataWithParity);

            String response = in.readLine();
            System.out.println("Server response: " + response);

            out.close();
            in.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String calculateParityBit(String data) {
        int countOnes = 0;
        for (int i = 0; i < data.length(); i++) {
            if (data.charAt(i) == '1') {
                countOnes++;
            }
        }
        char parityBit = (countOnes % 2 == 0) ? '0' : '1';
        return data + parityBit;
    }
}
