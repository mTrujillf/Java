package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Login {
    MulticastSocket socket = null;
    Socket s = null;
    int serverPort = 49152;
    private JButton loginButton;
    private JPanel panelLogin;
    private JTextPane textPane1;
    private JLabel userName;
    private JFrame framePegale ;
    int val;
    InetAddress group;
    public Login(int val){
        this.val = val;


        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = textPane1.getText();
                if (! user.isEmpty()){
                    framePegale = new JFrame("Pegale");
                    framePegale.setContentPane(new Pegale(user).getPanelMain());
                    framePegale.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    framePegale.pack();
                    framePegale.setLocation(50 + (275*val),350);
                    framePegale.setVisible(true);
                }

            }
        });
    }

    public JPanel getPanelLogin(){
        return panelLogin;
    }


    public static void main(String[] args){

        JFrame frame = new JFrame("Login");
        frame.setContentPane(new Login(0).panelLogin);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocation(700,350);
        frame.setVisible(true);

    }
}
