import java.io.*;
import java.net.*;
import java.math.*;

public class RSAClient {
    // RSA encryption method
    public static double encrypt(double msg, double e, double n) {
        return Math.pow(msg, e) % n;
    }

    public static void main(String[] args) throws IOException {
        String serverAddress = "localhost";
        int port = 1234;

        // Server's RSA public key (for demonstration)
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


        //int k = 2;
        //double d = (1 + (k * phi)) / e;
        double msg = 5;

        // Encrypt the message using RSA
        double encryptedMsg = encrypt(msg, e, n);
         

        // Connect to the server
        Socket socket = new Socket(serverAddress, port);
        System.out.println("Connected to server: " + serverAddress);

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        // Send encrypted message to server
        //System.out.println(msg);


        System.out.println(encryptedMsg);
        out.println(Double.toString(encryptedMsg)); // Convert to string for easier handling

        // Receive acknowledgment from server
        String ack = in.readLine();
        System.out.println("Server Response: " + ack);

        socket.close();
    }

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
}
