import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Base64;

public class TCPServer {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(9999);
        System.out.println("Server is running and waiting for a client to connect...");

        Socket socket = serverSocket.accept();
        System.out.println("Client connected.");

        // Secret key for DES encryption and decryption
        String secretKey = "SecretKe"; // 8 characters for DES

        // Create DES key object
        DESKeySpec desKeySpec = new DESKeySpec(secretKey.getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey key = keyFactory.generateSecret(desKeySpec);

        // Create DES cipher for decryption
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key);

        // Initialize streams
        DataInputStream inputStream = new DataInputStream(socket.getInputStream());
        DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

        // Read and decrypt messages from client
        String encryptedMessage = inputStream.readUTF();
        System.out.println("encryptedMessage sent:- " + encryptedMessage);
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedMessage);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        String decryptedMessage = new String(decryptedBytes);
        System.out.println("Client sent: -" + decryptedMessage);

        // Send response to client
        String response = "Server received: -" + decryptedMessage;
        outputStream.writeUTF(response);

        // Close streams and socket
        inputStream.close();
        outputStream.close();
        socket.close();
        serverSocket.close();
    }
}
