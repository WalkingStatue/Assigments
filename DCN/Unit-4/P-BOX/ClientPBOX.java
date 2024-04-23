import java.io.*;
import java.net.*;

public class ClientPBOX {
    public static void main(String[] args) {
        String plaintext = "1010"; // Original plaintext
        
        try {
            Socket socket = new Socket("localhost", 01234);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            // Encrypt the plaintext using P-box permutation
            String ciphertext = encrypt(plaintext);
            
            // Send the ciphertext to the server
            out.println(ciphertext);
            
            // Receive and print the decrypted plaintext from the server
            String decryptedPlaintext = in.readLine();
            System.out.println("Decrypted plaintext from server: " + decryptedPlaintext);
            
            // Close resources
            out.close();
            in.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // Encrypt function using P-box permutation
    public static String encrypt(String plaintext) {
        
        int[] pBoxPattern = {2, 0, 3, 1}; // P-box permutation pattern
        StringBuilder ciphertext = new StringBuilder();
        
        // Permute the plaintext using the P-box pattern
        for (int i = 0; i < pBoxPattern.length; i++) {
            ciphertext.append(plaintext.charAt(pBoxPattern[i]));
        }
        
        return ciphertext.toString();
    }
}
