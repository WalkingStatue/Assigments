import java.io.*;
import java.net.*;

public class ServerPBOX {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(01234);
            System.out.println("Server is running...");
            
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected.");
            
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            
            // Receive ciphertext from client
            String ciphertext = in.readLine();
            System.out.println("Received ciphertext from client: " + ciphertext);
            
            // Decrypt the ciphertext
            String decryptedPlaintext = decrypt(ciphertext);
            System.out.println("Decrypted plaintext: " + decryptedPlaintext);
            
            // Send the decrypted plaintext back to the client
            out.println(decryptedPlaintext);
            
            // Close resources
            out.close();
            in.close();
            clientSocket.close();
            serverSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // Decrypt function (inverse P-box permutation)
    public static String decrypt(String ciphertext) {
        int[] pBoxPattern = {2, 0, 3, 1}; // P-box permutation pattern
        
        StringBuilder plaintext = new StringBuilder();
        
        // Inverse permutation (inverse P-box pattern)
        for (int i = 0; i < pBoxPattern.length; i++) {
            plaintext.append(ciphertext.charAt(findIndex(pBoxPattern, i)));
        }
        
        return plaintext.toString();
    }
    
    // Helper function to find the index of a value in an array
    private static int findIndex(int[] arr, int value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return -1; // Not found
    }
}
