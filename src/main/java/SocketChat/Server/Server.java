package SocketChat.Server;

import SocketChat.Config;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Server {
    public static void main(String[] args){
        ServerSocket serverSocket = null;

        try{
            serverSocket = new ServerSocket(Config.PORT);
            System.out.println("Started, waiting for connection");

            while (true) {
                Socket clientSocket = null;

                while (clientSocket == null) {
                    clientSocket = serverSocket.accept();
                    System.out.println("\nAccepted. " + clientSocket.getInetAddress());

                    try (InputStream in = clientSocket.getInputStream();
                         OutputStream out = clientSocket.getOutputStream()) {

                        byte[] buf = new byte[32 * 1024];
                        int readBytes = in.read(buf);
                        String line = new String(buf, 0, readBytes);
                        System.out.printf("Client> %s", line);

                        out.write(line.getBytes());
                        out.flush();
                    }
                }
            }
        } catch (SocketException e){
            System.err.println("Socket exception");
            e.printStackTrace();
        } catch (IOException e){
            System.err.println("I/O exception");
            e.printStackTrace();
        }
    }
}
