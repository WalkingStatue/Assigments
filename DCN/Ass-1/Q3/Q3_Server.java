import java.io.*;
import java.net.*;

public class Q3_Server {
    public static void main(String[] args) {
        int port = 65432;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");

                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

                String[] numbers = input.readLine().split(",");
                StringBuilder primes = new StringBuilder();
                for (String numberStr : numbers) {
                    int number = Integer.parseInt(numberStr.trim());
                    if (isPrime(number)) {
                        primes.append(number).append(",");
                    }
                }

                if (primes.toString().isEmpty()) {
                    output.println("0");
                } else {
                    // Remove the last comma
                    output.println(primes.substring(0, primes.length() - 1));
                }
            }
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private static boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
