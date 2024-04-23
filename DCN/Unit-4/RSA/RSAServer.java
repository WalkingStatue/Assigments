import java.io.*;
import java.math.*;
import java.net.*;

public class RSAServer {
    public static double gcd(double a, double h) {
        double temp;
        while (true) {
            temp = a % h;
            if (temp == 0)
                return h;
            a = h;
            h = temp;
        }
    }

    public static double decrypt(double c, double d, double n) {
        return Math.pow(c, d) % n;
    }

    public static void main(String[] args) throws IOException {
        int port = 1234;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server is listening on port " + port);

        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("Client connected: " + socket);

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Server's RSA key pair
            double p = 7;
            double q = 3;
            double n = p * q;
            double e = 2;
            double phi = (p - 1) * (q - 1);
            while (e < phi) {
                if (gcd(e, phi) == 1)
                    break;
                else
                    e++;
            }
            int k = 2;
            double d = (1 + (k * phi)) / e;  //{ }


            // Receive encrypted message from client
            String encryptedMessageStr = in.readLine();
            double encryptedMessage = Double.parseDouble(encryptedMessageStr);

            // Decrypt the message
            double decryptedMessage = decrypt(encryptedMessage, d, n);
            System.out.println("Decrypted Message: " + decryptedMessage);

            // Send acknowledgment back to client
            out.println("Message received and decrypted successfully!");
            
            socket.close();
        }
    }
}
