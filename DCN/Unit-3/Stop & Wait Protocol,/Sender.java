import java.io.*;
import java.net.*;

public class Sender {
    Socket sender;
    PrintWriter out;
    BufferedReader in;
    String packet, ack, str, msg;
    int n, i = 0, sequence = 0;

    // Sender() {}

    public void run() {
        try {
            // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Waiting for Connection....");
            sender = new Socket("localhost", 2004);
            sequence = 0;

            out = new PrintWriter(sender.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(sender.getInputStream()));

            str = in.readLine();
            System.out.println("Receiver > " + str);

            System.out.println("Enter the data to send....");
            // packet = br.readLine();
            packet = "ABCDEFG";
            n = packet.length();

            do {
                try {
                    if (i < n) {
                        msg = "" + sequence;
                        // msg = String.valueOf(sequence);
                        msg += packet.substring(i, i + 1);
                        // msg = msg.concat(packet.substring(i, i + 1));
                    } else if (i == n) {
                        msg = "end";
                        out.println(msg);
                        break;
                    }
                    out.println(msg);
                    // sequence = (sequence == 0) ? 1 : 0;
                    if (sequence == 0) {
                        sequence = 1;
                    } else {
                        sequence = 0;
                    }
                    System.out.println("Data sent: " + msg);

                    ack = in.readLine();
                    System.out.println("Waiting for acknowledgment.....\n");

                    if (ack.equals(String.valueOf(sequence))) {
                        i++;
                        System.out.println("Receiver > Packet received\n");
                    } else {
                        System.out.println("Timeout, resending data....\n");
                        // sequence = (sequence == 0) ? 1 : 0;
                        if (sequence == 0) {
                            sequence = 1;
                        } else {
                            sequence = 0;
                        }
                    }
                } catch (Exception e) {
                }
            } while (i < n + 1);

            System.out.println("All data sent. Exiting.");
        } catch (Exception e) {
        } finally {
            try {
                in.close();
                out.close();
                sender.close();
            } catch (Exception e) {
            }
        }
    }

    public static void main(String args[]) {
        Sender s = new Sender();
        s.run();
    }
}
