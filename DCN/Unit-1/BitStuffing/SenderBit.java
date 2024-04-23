import java.io.*;
import java.net.*;

public class SenderBit {
    public static void main(String[] args) {
        try {
            // Establish a connection
            Socket socket = new Socket("localhost", 1234);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String data = "011111110";
            String result = encodeData(data);
            System.out.println(" data:- " + data);
            System.out.println(" data_result:- " + result); //
            // Output: 1111100000111111100011111100001111
            out.println(result);
            // out.println(key);
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String encodeData(String data) {
        int cnt = 0;
        String s = "";
        for (int i = 0; i < data.length(); i++) {
            char ch = data.charAt(i);
            if (ch == '1') {
                cnt++;
                if (cnt < 5)
                    s += ch;

                else {
                    s = s + ch + '0';
                    cnt = 0;
                }
            } else {
                s += ch;
                cnt = 0;
            }
        }
        return "01111110" + s + "01111110";
    }

}