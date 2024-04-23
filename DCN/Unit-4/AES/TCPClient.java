import java.io.*;
import java.net.*;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class TCPClient {

    private static final String SECRET_KEY = "ThisIsASecretKey"; // Change this to your secret key

    public static void main(String[] args) {

        try  {
            Socket socket = new Socket("localhost", 12345);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Send message to server
            String message = "Hello, server!";
            byte[] encryptedBytes = encrypt(message);
            String encryptedMessage = bytesToBase64String(encryptedBytes);
            out.println(encryptedMessage);

            // Read response from server
            String response = in.readLine();
            System.out.println("Server: " + response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // AES encryption method
    public static byte[] encrypt(String plainText) {
        try {
          // Convert the secret key to a SecretKeySpec object suitable for AES encryption
            SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
        // Create a Cipher object for AES encryption
            //Cipher cipher = Cipher.getInstance("AES");
            Cipher cipher = Cipher.getInstance("AES");

     // Initialize the cipher for encryption mode with the provided key
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
      // Encrypt the plaintext string by converting it to bytes and then encrypting those bytes

            return cipher.doFinal(plainText.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Utility method to convert byte array to base64 string
    public static String bytesToBase64String(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }
}
