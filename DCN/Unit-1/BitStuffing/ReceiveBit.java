import java.io.*;
import java.net.*;

import java.util.Arrays;



public class ReceiveBit {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(1234);
            Socket clientSocket = serverSocket.accept();
            System.out.println("Connection Established..!");

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        
            String Data = in.readLine();
            System.out.println("Received from client Data: " + Data);
            destuff(Data);
            //String Rdata  = destuff(Data);
            //System.out.println("Received from client RData: " + Rdata);

            in.close();
            clientSocket.close();
            serverSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

      public static void destuff(String s)
         {
            int cnt = 0;
            // Removal of stuffed bits: 
            for (int i = 8; i < s.length()-8 ; i++) {
                char ch = s.charAt(i);
                if (ch == '1') {
                    cnt++;
                    System.out.print(ch);
      
                    if (cnt == 5) {
                        i++;
                        cnt = 0;
                    }
                } else {
                    // Print unstuffed data 
                    System.out.print(ch); 
                    cnt = 0;
                }
            }
            System.out.println();
        }

     
    }

  
    

