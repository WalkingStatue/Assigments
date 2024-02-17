import java.io.*;
import java.net.*;

public class Q6_Client {
    public static void main(String[] args) {
        String host = "127.0.0.1";
        int port = 65432;
        try (Socket socket = new Socket(host, port)) {
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                System.out.println("Choose a shape to calculate the area:");
                System.out.println("a. Circle");
                System.out.println("b. Square");
                System.out.println("c. Rectangle");
                System.out.println("d. Triangle");
                System.out.println("e. Exit");
                System.out.print("Enter choice: ");
                String choice = reader.readLine();

                output.println(choice);
                if (choice.equals("e")) {
                    System.out.println("Exiting...");
                    break;
                }

                switch (choice) {
                    case "a":
                        System.out.print("Enter the radius of the circle: ");
                        break;
                    case "b":
                        System.out.print("Enter the side length of the square: ");
                        break;
                    case "c":
                        System.out.print("Enter the length of the rectangle: ");
                        output.println(reader.readLine());
                        System.out.print("Enter the width of the rectangle: ");
                        break;
                    case "d":
                        System.out.print("Enter the base of the triangle: ");
                        output.println(reader.readLine());
                        System.out.print("Enter the height of the triangle: ");
                        break;
                }
                output.println(reader.readLine());

                String response = input.readLine();
                System.out.println(response);
            }
        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }
}
