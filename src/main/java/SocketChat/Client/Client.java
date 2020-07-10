package SocketChat.Client;

import SocketChat.Config;

import java.io.*;
import java.net.Socket;

public class Client {
    private static BufferedWriter out;

    public static void main(String[] args) {
        try {
            try (Socket clientSocket = new Socket(Config.HOST, Config.PORT);
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

                out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                System.out.print("> ");
                String word = reader.readLine();
                out.write(word + "\n");
                out.flush();

            } finally {
                System.out.println("Клиент был закрыт...");
                out.close();
            }
        } catch (IOException e) {
            System.err.println(e);
        }

    }
}
