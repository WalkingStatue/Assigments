import java.io.*;
import java.net.*;

public class Q7_Client {
    public static void main(String[] args) {
        String host = "127.0.0.1";
        int port = 65432;
        try (Socket socket = new Socket(host, port)) {
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Enter a sentence: ");
            String sentence = reader.readLine();
            output.println(sentence);

            String response = input.readLine();
            System.out.println("Camel Case Sentence: " + response);
        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }
}
