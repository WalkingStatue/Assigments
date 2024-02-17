import java.io.*;
import java.net.*;

public class Q6_Server {
    public static void main(String[] args) {
        int port = 65432;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);

            while (true) {
                Socket socket = serverSocket.accept();
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

                String shape = input.readLine();
                double area = 0;
                switch (shape) {
                    case "a": // Circle
                        double radius = Double.parseDouble(input.readLine());
                        area = Math.PI * radius * radius;
                        break;
                    case "b": // Square
                        double side = Double.parseDouble(input.readLine());
                        area = side * side;
                        break;
                    case "c": // Rectangle
                        double length = Double.parseDouble(input.readLine());
                        double width = Double.parseDouble(input.readLine());
                        area = length * width;
                        break;
                    case "d": // Triangle
                        double base = Double.parseDouble(input.readLine());
                        double height = Double.parseDouble(input.readLine());
                        area = (base * height) / 2;
                        break;
                    case "e": // Exit
                        output.println("Exit");
                        System.exit(0);
                }
                output.println("The area is: " + area);
            }
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
