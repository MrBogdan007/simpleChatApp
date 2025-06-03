import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.Reader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.Channels;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class ChatServer {
    public static  void main (String[] args){

        ChatServer chatServer = new ChatServer();
        chatServer.setUpNetworking();

    }

    public String setUpNetworking(){
        try{
            ServerSocketChannel serverListener = ServerSocketChannel.open();
            serverListener.bind(new InetSocketAddress(5000));
            while(serverListener.isOpen()){
                System.out.println("Listening for the port 5000");
                //BufferedReader br = new BufferedReader(Channels.newReader(serverListener, StandardCharsets.UTF_8));
                SocketChannel sc = serverListener.accept();
               Reader serverReader = Channels.newReader( sc, StandardCharsets.UTF_8);
                BufferedReader clientDataRead = new BufferedReader(serverReader);
                String message;
                try {
                    while ((message = clientDataRead.readLine()) != null) {
                        System.out.println("read " + message);

                    }
                } catch (IOException ex) {
                      ex.printStackTrace();
                }
            }
        }catch (IOException ex){
           ex.printStackTrace();

        }
    return "";
    }
}
