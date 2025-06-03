import javax.swing.*;

public class Practice {
    public static void main(String[] args) {
        addGUI();
    }


    public static void addGUI(){
        JTextField textField;
        JButton jButton;
        JFrame frame = new JFrame("This is my box");
        JPanel panel = new JPanel();
        textField= new JTextField(20);
        jButton = new JButton("Send");
        jButton.addActionListener(e -> {
        sendMessage();
        });

        panel.add(textField);
        panel.add(jButton);
        frame.getContentPane().add(panel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    public static void sendMessage(){
        System.out.println("Message sent");
    }
}
