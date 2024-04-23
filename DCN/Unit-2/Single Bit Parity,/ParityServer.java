import java.io.*;
import java.net.*;

public class ParityServer {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(1234);
            System.out.println("Server started. Waiting for client...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                String receivedData = in.readLine();
                System.out.println("Received data from client: " + receivedData);

                boolean parityCheckResult = checkParity(receivedData);
                if (parityCheckResult) {
                    System.out.println("Received message has even parity.");
                    out.println("Parity check passed. Message received successfully.");
                } else {
                    System.out.println("Received message does not have even parity. Error detected.");
                    out.println("Parity check failed. Message may be corrupted.");
                }

                in.close();
                out.close();
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean checkParity(String receivedMessage) {
        int countOnes = 0;
        for (int i = 0; i < receivedMessage.length(); i++) {
            if (receivedMessage.charAt(i) == '1') {
                countOnes++;
            }
        }
        return countOnes % 2 == 0;
    }
}
