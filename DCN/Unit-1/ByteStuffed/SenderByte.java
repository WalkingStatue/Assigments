import java.io.*;
import java.net.*;

public class SenderByte {

    public static void main(String[] args) {
        try {
            
            // Establish a connection
            Socket socket = new Socket("localhost", 123);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String data = "#@KS@DCN#"; 
            String result = performBitStuffing(data);
        System.out.println(" data:- " + data); 
        System.out.println(" data_result:- " + result); 
        out.println(result);
           
            out.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String performBitStuffing(String data) {
        String res = "";
        for (int i = 0; i < data.length(); i++) {
            if (data.charAt(i) == '#')
                res = res + '@' + data.charAt(i);
            else if (data.charAt(i) == '@')
                res = res + '@' + data.charAt(i);
            else
                res = res + data.charAt(i);
        }

        // Stuffing of the starting and end of the data with 'F'
        res = '#' + res + '#';

        return res;
    }
    
   
}
