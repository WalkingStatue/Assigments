import java.io.*;
import java.net.*;

public class Q7_Server {
    public static void main(String[] args) {
        int port = 65432;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);

            while (true) {
                Socket socket = serverSocket.accept();
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

                String sentence = input.readLine();
                String camelCaseSentence = toCamelCase(sentence);
                output.println(camelCaseSentence);
            }
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private static String toCamelCase(String sentence) {
        StringBuilder camelCase = new StringBuilder();
        boolean isStart = true;

        for (char ch : sentence.toCharArray()) {
            if (Character.isSpaceChar(ch)) {
                isStart = true;
                camelCase.append(ch);
            } else if (isStart) {
                camelCase.append(Character.toUpperCase(ch));
                isStart = false;
            } else {
                camelCase.append(Character.toLowerCase(ch));
            }
        }

        return camelCase.toString();
    }
}
