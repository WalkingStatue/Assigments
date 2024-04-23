import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Base64;

public class TCPClient {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 9999);
        System.out.println("Connected to server.");

        // Secret key for DES encryption and decryption
        String secretKey = "SecretKe"; // 8 characters for DES

        // Create DES key object
        DESKeySpec desKeySpec = new DESKeySpec(secretKey.getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey key = keyFactory.generateSecret(desKeySpec);

        // Create DES cipher for encryption
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);

        // Initialize streams
        DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
        DataInputStream inputStream = new DataInputStream(socket.getInputStream());

        // Send encrypted message to server
        String message = "Hello, server!";
        byte[] encryptedBytes = cipher.doFinal(message.getBytes());
        String encryptedMessage = Base64.getEncoder().encodeToString(encryptedBytes);
        outputStream.writeUTF(encryptedMessage);
        System.out.println("message"+message);
        System.out.println("encryptedMessage:-"+encryptedMessage);

        // Receive response from server
        String serverResponse = inputStream.readUTF();
        System.out.println("Server response:- " + serverResponse);
       // System.out.println(encryptedMessage);


        // Close streams and socket
        inputStream.close();
        outputStream.close();
        socket.close();
    }
}
