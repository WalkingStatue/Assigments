import java.io.*;
import java.net.*;

public class Q1_Client {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 65432;
        try (Socket socket = new Socket(host, port)) {

            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Enter a number to square: ");
            String userInput = stdIn.readLine();
            output.println(userInput);

            String response = input.readLine();
            System.out.println("The square of the number is: " + response);

        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }
}
