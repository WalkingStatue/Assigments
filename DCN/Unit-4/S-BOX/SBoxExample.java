public class SBoxExample {
    // Define the S-box lookup table as a 2D array
    private static final String[][] sBox = {
            {"1110", "0111", "1010", "0011"},
            {"1100", "0101", "1001", "0000"},
            {"0110", "1111", "0010", "1011"},
            {"0001", "1000", "0100", "1101"}
    };

    // Method to perform S-box substitution
    public static String sBoxSubstitution(String input) {
        // Parse the input binary string to get row and column indices
        int row = Integer.parseInt(input.substring(0, 2), 2);
        int col = Integer.parseInt(input.substring(2), 2);
        // Retrieve the output value from the S-box table
        String output = sBox[row][col];
        return output;
    }

    // Method to encrypt plaintext using S-box substitution
    public static String encrypt(String plaintext) {
        StringBuilder ciphertext = new StringBuilder();
        for (int i = 0; i < plaintext.length(); i += 4) {
            String nibble = plaintext.substring(i, i + 4); // Extract each nibble (4 bits)
            String encryptedNibble = sBoxSubstitution(nibble); // Encrypt the nibble
            ciphertext.append(encryptedNibble); // Append the encrypted nibble to ciphertext
        }
        return ciphertext.toString();
    }

    // Method to decrypt ciphertext using reverse S-box substitution
    public static String decrypt(String ciphertext) {
        StringBuilder plaintext = new StringBuilder();
        for (int i = 0; i < ciphertext.length(); i += 4) {
            String nibble = ciphertext.substring(i, i + 4); // Extract each nibble (4 bits)
            String decryptedNibble = reverseSBoxSubstitution(nibble); // Decrypt the nibble
            plaintext.append(decryptedNibble); // Append the decrypted nibble to plaintext
        }
        return plaintext.toString();
    }

    // Method to perform reverse S-box substitution (for decryption)
    public static String reverseSBoxSubstitution(String input) {
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

    public static void main(String[] args) {
       // String plaintext = "1010_111110001010"; // Example plaintext 0010 1101 0110 0010
        String plaintext = "1010"; // Example plaintext

        System.out.println("Plaintext: " + plaintext);
        
        // Encrypt the plaintext
        String ciphertext = encrypt(plaintext);
        System.out.println("Ciphertext: " + ciphertext);
        
        // Decrypt the ciphertext
        String decryptedPlaintext = decrypt(ciphertext);
        System.out.println("Decrypted plaintext: " + decryptedPlaintext);
    }
}
