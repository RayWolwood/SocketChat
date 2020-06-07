package SocketChat.Client;

import SocketChat.Config;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {


        try(Socket clientSocket = new Socket(Config.HOST, Config.PORT)){

            try(OutputStream out = clientSocket.getOutputStream()){
                //отправляем message
                String massageString = "Hello, I'm a Client";
                out.write(massageString.getBytes());
                out.flush();
            }

        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
