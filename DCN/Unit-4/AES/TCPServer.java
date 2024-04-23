import java.io.*;
import java.net.*;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class TCPServer {
    private static final String SECRET_KEY = "ThisIsASecretKey"; // Change this to your secret key

    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Server started. Waiting for a client...");

            // Wait for a client to connect
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected: " + clientSocket);

            // Set up streams for reading from and writing to the client
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            // Read input from client, decrypt it, and echo it back
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Encrypted message received from client: " + inputLine);
                
                // Decrypt the message
                byte[] encryptedBytes = base64StringToBytes(inputLine);

                String decryptedMessage = decrypt(encryptedBytes);
                System.out.println("Decrypted message: " + decryptedMessage);
                
                // Echo back to client (in this case, no need to encrypt again)
                out.println(decryptedMessage); 
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // AES decryption method
    public static String decrypt(byte[] encryptedBytes) {
        try {
     // Create a SecretKeySpec object using the secret key and AES algorithm
            SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
     // Create a Cipher object for AES decryption
        Cipher cipher = Cipher.getInstance("AES");

     // Initialize the cipher for decryption mode with the provided key
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
   // Decrypt the encrypted bytes using the initialized cipher    
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
   // Convert the decrypted bytes to a String
            return new String(decryptedBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Utility method to convert base64 string to byte array
    public static byte[] base64StringToBytes(String base64String) {
        return Base64.getDecoder().decode(base64String);
    }
}
