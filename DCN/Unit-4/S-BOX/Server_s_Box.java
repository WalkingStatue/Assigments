import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server_s_Box {
    private static final int SERVER_PORT = 12345;
    private static final String[][] sBox = {
        {"1110", "0111", "1010", "0011"},
        {"1100", "0101", "1001", "0000"},
        {"0110", "1111", "0010", "1011"},
        {"0001", "1000", "0100", "1101"}
};
    public static void main(String[] args) {
        try {
            // Create server socket
            ServerSocket serverSocket = new ServerSocket(SERVER_PORT);

            // Wait for client connection
            System.out.println("Waiting for client connection...");
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected.");

            // Get input stream to receive data from client
            InputStream inputStream = clientSocket.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));

            // Get output stream to send data to client
            OutputStream outputStream = clientSocket.getOutputStream();
            PrintWriter out = new PrintWriter(outputStream, true);

            // Receive encrypted message from client
            String encryptedMessage = in.readLine();
            System.out.println("Encrypted message received from client: " + encryptedMessage);

            // Decrypt the message using reverse S-box substitution
            String decryptedMessage = decrypt(encryptedMessage);
            System.out.println("Decrypted message: " + decryptedMessage);

            // Send decrypted message back to client
            out.println(decryptedMessage);

            // Close resources
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Define the S-box lookup table as a 2D array
    

    // Method to perform reverse S-box substitution (for decryption)
    private static String decrypt(String ciphertext) {
        StringBuilder plaintext = new StringBuilder();
        for (int i = 0; i < ciphertext.length(); i += 4) {
            String nibble = ciphertext.substring(i, i + 4); // Extract each nibble (4 bits)
            String decryptedNibble = reverseSBoxSubstitution(nibble); // Decrypt the nibble
            plaintext.append(decryptedNibble); // Append the decrypted nibble to plaintext
        }
        return plaintext.toString();
    }

    // Method to perform reverse S-box substitution (for decryption)
    private static String reverseSBoxSubstitution(String input) {
        for (int i = 0; i < sBox.length; i++) {
            for (int j = 0; j < sBox[i].length; j++) {
                if (sBox[i][j].equals(input)) {
                    // Build binary representation of row and column indices
                    String row = Integer.toBinaryString(i);
                    String col = Integer.toBinaryString(j);
                    // Pad row and column with leading zeros if necessary
                    row = String.format("%2s", row).replace(' ', '0');
                    col = String.format("%2s", col).replace(' ', '0');
                    // Concatenate row and column to get decrypted nibble
                    return row + col;
                }
            }
        }
        return null; // Should not reach here if input is valid
    }
}
