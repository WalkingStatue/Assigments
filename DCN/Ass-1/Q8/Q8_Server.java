import java.io.*;
import java.net.*;

public class Q8_Server {
    public static void main(String[] args) {
        int port = 65432;
        try (DatagramSocket socket = new DatagramSocket(port)) {
            System.out.println("Server is listening on port " + port);
            byte[] buffer = new byte[1024];

            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                String sentence = new String(packet.getData(), 0, packet.getLength());
                String reverseCamelCaseSentence = toReverseCamelCase(sentence);
                buffer = reverseCamelCaseSentence.getBytes();
                InetAddress address = packet.getAddress();
                int clientPort = packet.getPort();
                packet = new DatagramPacket(buffer, buffer.length, address, clientPort);
                socket.send(packet);
            }
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private static String toReverseCamelCase(String sentence) {
        StringBuilder result = new StringBuilder();
        boolean isStartOfWord = true;
        for (char ch : sentence.toCharArray()) {
            if (isStartOfWord && !Character.isWhitespace(ch)) {
                result.append(Character.toLowerCase(ch));
                isStartOfWord = false;
            } else if (Character.isWhitespace(ch)) {
                isStartOfWord = true;
                result.append(ch);
            } else {
                result.append(Character.toUpperCase(ch));
            }
        }
        return result.toString();
    }
}
