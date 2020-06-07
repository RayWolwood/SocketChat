package SocketChat.Client;

import SocketChat.Config;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {

        Socket clientSocket = null;
        try{
            clientSocket = new Socket(Config.HOST, Config.PORT);

            try(InputStream in = clientSocket.getInputStream();
                OutputStream out = clientSocket.getOutputStream()){

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
