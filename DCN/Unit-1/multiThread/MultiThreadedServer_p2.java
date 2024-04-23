import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

 class MultiThreadedServer_p2 {

    public static void main(String[] args) {
        try {
            // Create a ServerSocket that listens on port 12345
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Multi-Threaded Server is listening on port 12345...");

            // Continuously accept client connections
            while (true) {
                // Accept a connection from a client and obtain a Socket for communication
                Socket clientSocket = serverSocket.accept();
                System.out.println("Accepted connection from " + clientSocket.getInetAddress());

                // Create a new thread (clientHandlerThread) to handle the client
                Thread clientHandlerThread = new Thread(() -> handleClient(clientSocket));

                // Start the thread to handle the client in a separate thread
                clientHandlerThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to handle communication with an individual client
    private static void handleClient(Socket clientSocket) {
        try (
            // Create BufferedReader to read data from the client
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);
        ) {
        
            String message = in.readLine();
            System.out.println("Received from client: " + message);


            System.out.print("Enter a response to send back to the client: ");
            String response = scanner.nextLine();
            out.println("Server response: " + response);
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
