import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class ClientHandlerAbstract implements Runnable{
    BufferedReader reader;
    SocketChannel socket;

    public ClientHandlerAbstract(SocketChannel clientSocketData) {
        socket = clientSocketData;
        reader = new BufferedReader(Channels.newReader(socket, StandardCharsets.UTF_8));
    }

    @Override
    public void run() {

        String message;
        try {
            while ((message = reader.readLine()) != null) {
                System.out.println("read " + message);
                tellAllClients(message);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void tellAllClients(String message) {
//    for(PrintWriter sender: clientSenders){
//        sender.println(message);
//        sender.flush();
//    }
    }
}
