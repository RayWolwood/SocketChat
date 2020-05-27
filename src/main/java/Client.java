import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;


public class Client {
    private static final int PORT = 19000;
    private static final String HOST = "localhost";

    public static void main(String[] args) {

        Socket clientSocket = null;
        try{
            clientSocket = new Socket(HOST, PORT);

            try(InputStream in = clientSocket.getInputStream();
                OutputStream out = clientSocket.getOutputStream()){

                String line = "Hello";
                out.write(line.getBytes());
                out.flush();

                byte[] data = new byte[32 * 1024];
                int readBytes = in.read(data);

                System.out.printf("Server> %s", new String(data, 0, readBytes));
            }

        } catch (IOException e){
            e.printStackTrace();
        }


    }



}
