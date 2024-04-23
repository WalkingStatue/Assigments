import java.io.*;
import java.net.*;

public class SenderCRC {

    public static void main(String[] args) {
        try {
            
            // Establish a connection
            Socket socket = new Socket("localhost", 123);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String data = "100100"; // Replace this with your actual data
            String key = "1101";    // Replace this with your actual key
            

            String codeword =  CRCData(data, key);
             System.out.println("CRC Data (Data + Remainder): " + codeword);
           // String message = "hello";
            out.println(codeword);
            out.println(key);
           
            // Close the connection
            out.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static String CRCData(String data, String key) {
        int dataLength = data.length();
        int keyLength = key.length();

        // Append zeros to the data
        StringBuilder encodedData = new StringBuilder(data);
        encodedData.append("0000", 0, keyLength - 1);
        

        for (int i = 0; i < dataLength; i++) {
            // XOR operation
            if (encodedData.charAt(i) == '1') {
                for (int j = 0; j < keyLength; j++) {
                   // System.out.println("I;- " + i + "j;-"+ j);
                 //encodedData.setCharAt(i + j, (encodedData.charAt(i + j) == key.charAt(j)) ? '0' : '1');
                
                     if (encodedData.charAt(i + j) == key.charAt(j)) {
                        encodedData.setCharAt(i + j, '0');
                    } else {
                        encodedData.setCharAt(i + j, '1');
                    }

                }
            }
        }
        //StringBuilder rdata =  encodedData.delete(0, dataLength);
        System.out.println("(encod- dData): " + encodedData);

        StringBuilder rdata =  encodedData.replace(0, dataLength, data);
        System.out.println("(Data + Remainder): " + rdata);


        //return   encodedData.toString();
        return rdata.toString();
    }
   
}
