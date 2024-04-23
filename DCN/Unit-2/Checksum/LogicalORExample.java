public class LogicalORExample {
    public static void main(String[] args) {
       
        byte byte1 = (byte) 11001100;
        byte byte2 = (byte) 10101010;
        byte byte3 = (byte) 11110000;
        byte byte4 = (byte) 11000011;


        byte result = performLogicalOR(byte1, byte2, byte3, byte4);
        System.out.println("Binary representation of the result: " + Integer.toBinaryString(result & 0xFF));

        System.out.println("Binary representation of the result: " + Integer.toBinaryString(~result & 0xFF));

        
    }

    private static byte performLogicalOR(byte... bytes) {
        byte result = 0;
        for (byte b : bytes) {
            result |= b;
        }
        return result;

    }
}
