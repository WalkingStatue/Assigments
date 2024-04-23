import java.io.*;
import java.net.*;

public class ReceiveByte {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(123);
            Socket clientSocket = serverSocket.accept();
            System.out.println("Connection Established..!");

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        
            String Data = in.readLine();
            System.out.println("Received from client Data: " + Data);
            String result = performBitDestuffing(Data);
            System.out.println(" result:-- " + result);

            in.close();
            clientSocket.close();
            serverSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String performBitDestuffing(String stuffedData) {
        String out = "";
        for (int i = 1; i < stuffedData.length() - 1; i++) {
            
             if (stuffedData.charAt(i) == '@' && stuffedData.charAt(i + 1) =='#')  {
                out = out + '#';
                i++;
            }
            else if (stuffedData.charAt(i) == '@' && stuffedData.charAt(i + 1) =='@')  {
                out = out + '@';
                i++;
            }
               
            else{
                out = out + stuffedData.charAt(i); 

            }
        }

        return out;
    }
    }

  
    

