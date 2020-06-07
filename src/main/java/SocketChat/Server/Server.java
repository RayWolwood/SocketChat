package SocketChat.Server;

import SocketChat.Config;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Server {
    public static void main(String[] args){

        try(ServerSocket serverSocket = new ServerSocket(Config.PORT)){

            Socket clientSocket = serverSocket.accept();
            System.out.println("\nAccepted. " + clientSocket.getInetAddress());

            try (DataInputStream in = new DataInputStream(clientSocket.getInputStream());
                 DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream())) {

                while (!clientSocket.isClosed()){
                    String entry = in.readUTF();
                    System.out.println("Client> " + entry);

                    if(entry.equalsIgnoreCase("quit")){
                        System.out.println("Client initialize connections suicide ...");
                        out.writeUTF("Server reply - "+entry + " - OK");
                        out.flush();
                        //Thread.sleep(3000);
                        break;
                    }

                    out.writeUTF("Server reply - "+entry + " - OK");
                    out.flush();
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
