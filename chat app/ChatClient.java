import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ChatClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345);
            System.out.println("Connected to server.");

            Scanner scanner = new Scanner(socket.getInputStream());
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            String serverMessage, clientMessage;

            while (true) {
                System.out.print("Client: ");
                clientMessage = new Scanner(System.in).nextLine();
                out.println(clientMessage);

                serverMessage = scanner.nextLine();
                if (serverMessage == null) {
                    break;
                }

                // Check if the received message is not the client's own message
                if (!serverMessage.equals(clientMessage)) {
                    System.out.println("Server: " + serverMessage);
                }
            }

            socket.close();
            System.out.println("Client closed.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
