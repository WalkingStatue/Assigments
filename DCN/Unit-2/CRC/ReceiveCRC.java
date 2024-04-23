import java.io.*;
import java.net.*;

public class ReceiveCRC {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(123);
            Socket clientSocket = serverSocket.accept();
            System.out.println("Connection Established..!");

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
              
              

            String Data = in.readLine();
            System.out.println("Received from client Data: " + Data);
            String key = in.readLine();
            System.out.println("Received from client Key: " + key);
            String codeword =  encodeData(Data, key);
            System.out.println("Encoded Data (Data + Remainder): " + codeword);
            if (codeword == "000");
            {
                System.out.println("data received has no error:--" + codeword);
   
            }

            in.close();
            clientSocket.close();
            serverSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    static String encodeData(String data, String key) {
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
        StringBuilder rdata =  encodedData.delete(0, dataLength);
        //StringBuilder rdata =  encodedData.replace(0, dataLength, data);

        //return   encodedData.toString();
        return rdata.toString();
    }

    
}
