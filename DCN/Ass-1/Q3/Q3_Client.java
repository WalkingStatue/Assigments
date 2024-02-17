import java.io.*;
import java.net.*;

public class Q3_Client {
    public static void main(String[] args) {
        String host = "127.0.0.1";
        int port = 65432;
        try (Socket socket = new Socket(host, port)) {

            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Enter a set of numeric values (comma separated): ");
            String userInput = stdIn.readLine();
            output.println(userInput);

            String response = input.readLine();
            System.out.println("Prime numbers from the series: " + response);

        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }
}
