import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.Channels;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChatServer {
    private final List<PrintWriter> clientSenders = new ArrayList<>();
    public static  void main (String[] args){
        setUpGUI();
        ChatServer chatServer = new ChatServer();
        chatServer.setUpNetworking();


    }

    private static void setUpGUI() {
        JFrame frame = new JFrame("No smoking");
        JButton button = new JButton("Click me");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(button);
       frame.setSize(300,300);
        frame.setVisible(true);
        System.out.println("heelo");
    }

    public String setUpNetworking(){
        ExecutorService threadPool = Executors.newCachedThreadPool();
        try{
            ServerSocketChannel serverListener = ServerSocketChannel.open();//Opening client listener
            serverListener.bind(new InetSocketAddress(5000));
            while(serverListener.isOpen()){
                System.out.println("Listening for the port 5000");
                //BufferedReader br = new BufferedReader(Channels.newReader(serverListener, StandardCharsets.UTF_8));
                SocketChannel clientSocketData = serverListener.accept();// Server is listening for the client request to the 5000 port
                //Putting what data i've got from the client to the sender ( writer)
                PrintWriter sender = new PrintWriter(Channels.newWriter(clientSocketData,StandardCharsets.UTF_8));
                clientSenders.add(sender);
                threadPool.submit(new ClientHandler(clientSocketData));
                System.out.println("Network established , client running at this address : "+ clientSocketData.getLocalAddress());
            }
        }catch (IOException ex){
           ex.printStackTrace();

        }
    return "";
    }



    public class ClientHandler implements Runnable{
        BufferedReader reader;
        SocketChannel socket;

        public ClientHandler(SocketChannel clientSocketData) {
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
            for(PrintWriter sender: clientSenders){
                sender.println(message);
                sender.flush();
            }
        }
    }
}
