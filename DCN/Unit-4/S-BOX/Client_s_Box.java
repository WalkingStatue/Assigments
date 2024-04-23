import java.io.*;
import java.net.Socket;

public class Client_s_Box {
    private static final String SERVER_ADDRESS = "127.0.0.1";
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) {
        try {
            // Establish connection with the server
            Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);

            // Get output stream to send data to server
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter out = new PrintWriter(outputStream, true);

            // Encrypt the message using S-box substitution
            String plaintext = "1010111110001010"; // Example plaintext
            String ciphertext = encrypt(plaintext);

            // Send encrypted message to server
            out.println(ciphertext);

            // Close resources
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Define the S-box lookup table as a 2D array
    private static final String[][] sBox = {
            {"1110", "0111", "1010", "0011"},
            {"1100", "0101", "1001", "0000"},
            {"0110", "1111", "0010", "1011"},
            {"0001", "1000", "0100", "1101"}
    };

    // Method to perform S-box substitution
    private static String sBoxSubstitution(String input) {
        // Parse the input binary string to get row and column indices
        int row = Integer.parseInt(input.substring(0, 2), 2);
        int col = Integer.parseInt(input.substring(2), 2);
        // Retrieve the output value from the S-box table
        return sBox[row][col];
    }

    // Method to encrypt plaintext using S-box substitution
    private static String encrypt(String plaintext) {
        StringBuilder ciphertext = new StringBuilder();
        for (int i = 0; i < plaintext.length(); i += 4) {
            String nibble = plaintext.substring(i, i + 4); // Extract each nibble (4 bits)
            String encryptedNibble = sBoxSubstitution(nibble); // Encrypt the nibble
            ciphertext.append(encryptedNibble); // Append the encrypted nibble to ciphertext
        }
        return ciphertext.toString();
    }
}
