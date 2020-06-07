package SocketChat.Client;

import SocketChat.Config;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {


        try(Socket clientSocket = new Socket(Config.HOST, Config.PORT);
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
            DataInputStream in = new DataInputStream(clientSocket.getInputStream())){

            System.out.println("Client connected to socket.");

            while (!clientSocket.isOutputShutdown()){
                if(br.ready()){
                    String clientMessage = br.readLine();
                    out.writeUTF(clientMessage);
                    out.flush();

                    if(clientMessage.equalsIgnoreCase("quit")){
                        System.out.println("Client kill connection");
                        break;
                    }

                }
            }

//                //отправляем message
//                String massageString = "Hello, I'm a Client";
//                out.write(massageString.getBytes());
//                out.flush();


        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
