package SocketChat.Server;

import SocketChat.Config;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
    private static BufferedReader in;
    private static BufferedWriter out;

    public static void main(String[] args) {

        try (ServerSocket server = new ServerSocket(Config.PORT)) {
            try (Socket clientSocket = server.accept()) {

                System.out.println("Подключен клиент: " + clientSocket.getInetAddress());

                try {
                    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                    String word = in.readLine();
                    System.out.print("> ");
                    System.out.println(word);

                    //out.write("Привет, это Сервер! Подтверждаю, вы написали : " + word + "\n");
                    out.flush();

                } finally {
                    in.close();
                    out.close();
                }
            } finally {
                System.out.println("Сервер закрыт!");
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}

