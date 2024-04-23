import java.io.*;
import java.net.*;

 public class Reciever {
    ServerSocket receiver;
    Socket connection = null;
    PrintWriter out;
    BufferedReader in;
    String packet, ack, data = "";
    int i = 0, sequence = 0;

   // Reciever() {}

    public static void main(String args[]) {
        Reciever s = new Reciever();
        while (true) {
            s.run();
        }
    }

    public void run() {
        try {
            receiver = new ServerSocket(2004);
            System.out.println("Waiting for connection...");
            connection = receiver.accept();
            sequence = 0;
            System.out.println("Connection established:");
            out = new PrintWriter(connection.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            out.println("Connected.");
            do {
                try {
                    packet = in.readLine();
                    if (Integer.valueOf(packet.substring(0, 1)) == sequence) {
                        data += packet.substring(1);
                        
                       // sequence = (sequence == 0) ? 1 : 0;
                            if (sequence == 0) {
                                sequence = 1;
                            } else {
                                sequence = 0;
                            }
                        System.out.println("\n\nReceiver > " + packet);
                    } else {
                        System.out.println("\n\nReceiver > " + packet + "   Duplicate data");
                    }

                    
                    if (i < 3) {
                        out.println(String.valueOf(sequence));
                       // System.out.println("\n\nsequence > " + sequence + "   Duplicate data");
                        i++;
                    } else {
                        out.println(String.valueOf((sequence + 1) % 2));
                        i = 0;
                    }
                } catch (Exception e) {
                }
            } while (!packet.equals("end"));
            System.out.println("Data received: " + data);
            out.println("Connection ended.");
        } catch (Exception e) {
        } finally {
            try {
                in.close();
                out.close();
                receiver.close();
            } catch (Exception e) {
            }
        }
    }

   
}
