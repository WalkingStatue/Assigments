import java.io.*;
import java.net.*;

public class Q1_Server {
    public static void main(String[] args) {
        int port = 65432;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");

                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

                String text = input.readLine();
                try {
                    int number = Integer.parseInt(text);
                    int squaredNumber = number * number;
                    output.println(squaredNumber);
                } catch (NumberFormatException e) {
                    output.println("Error: Please send a valid number.");
                }
                
                socket.close();
            }
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
