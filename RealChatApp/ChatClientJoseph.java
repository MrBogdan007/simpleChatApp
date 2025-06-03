import javax.swing.*;
import java.awt.*;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;

public class ChatClientJoseph {
    JTextField textField;
    JButton jButton;
    JLabel jLabel;
    private  PrintWriter clientWriter;
    public static void main(String[] args) {

    ChatClientJoseph chatClient = new ChatClientJoseph();
    chatClient.setupNetworking();
    chatClient.jPanelSetup();


    }
    public void jFrameSetup(JPanel mainPanel){
        JFrame jFrame = new JFrame("Test Chat Header");
        jFrame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        jFrame.setSize(400,200);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    public void jPanelSetup(){
        JPanel jPanel = new JPanel();
        textField = new JTextField(20);
        jButton = new JButton("Send");
        jButton.addActionListener(e -> sendMessage());
        jLabel = new JLabel("Player 1:");
        jPanel.add(jLabel);
        jPanel.add(textField);
        jPanel.add(jButton);
        jFrameSetup(jPanel);
    }
    public void sendMessage(){
    clientWriter.println(textField.getText());
        System.out.println(textField.getText());
    clientWriter.flush();
    textField.setText("");
    textField.requestFocus();
    }
    public void setupNetworking(){
        try {
            SocketChannel clientJosephSocket = SocketChannel.open(new InetSocketAddress("localhost", 5000));
            clientWriter = new PrintWriter(Channels.newOutputStream(clientJosephSocket));
            System.out.println("Network established Joseph user is running at: "+ clientJosephSocket.getLocalAddress());
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
