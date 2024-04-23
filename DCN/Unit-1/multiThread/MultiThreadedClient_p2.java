import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class MultiThreadedClient_p2 {

    public static void main(String[] args) {
        try {
            // Connect to the server running on localhost at port 12345
            Socket socket = new Socket("localhost", 12345);

            // Create BufferedReader to read data from the server
            BufferedReader serverIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // Create PrintWriter to send data to the server
            PrintWriter serverOut = new PrintWriter(socket.getOutputStream(), true);
            // Create Scanner to read data from the console
            Scanner scanner = new Scanner(System.in);

            // Read a message from the console and send it to the server
            System.out.print("Enter a message to send to the server: ");
            String message = scanner.nextLine();
            serverOut.println(message);

            // Read the response from the server and print it
            String response = serverIn.readLine();
            System.out.println("Received from server: " + response);

            // Close the socket
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
