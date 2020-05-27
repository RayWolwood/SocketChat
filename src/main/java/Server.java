import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int PORT = 19000;

    public static void main(String[] args){
        ServerSocket serverSocket = null;
        try{
            serverSocket = new ServerSocket(PORT);
            System.out.println("Started, waiting for connection");
            Socket clientSocket = serverSocket.accept();
            System.out.println("Accepted. " + clientSocket.getInetAddress());

            try(InputStream in = clientSocket.getInputStream();
                OutputStream out = clientSocket.getOutputStream()){

                byte[] buf = new byte[32 * 1024];
                int readBytes = in.read(buf);
                String line = new String(buf, 0, readBytes);
                System.out.printf("Client>%s", line);

                out.write(line.getBytes());
                out.flush();
            }

        } catch (IOException e){
            e.printStackTrace();
        }


    }

    public static int printOlolo(){
        return 10;
    }

}
