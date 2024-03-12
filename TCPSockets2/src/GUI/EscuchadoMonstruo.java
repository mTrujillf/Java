package GUI;

import javax.swing.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class EscuchadoMonstruo extends Thread {

    private Integer n;
    private Integer m;
    private InetAddress group;
    private MulticastSocket socket;
    private String data;
    private byte[] buffer;
    private DatagramPacket messageIn;
    private Character chr;
    private int []monstruo;
    private JCheckBox [] arrCheck;
    public EscuchadoMonstruo(int [] monstruo,JCheckBox [] arrCheck,MulticastSocket socket) {
        this.monstruo = monstruo;
        this.arrCheck = arrCheck;

        this.socket = socket;
        buffer = new byte[1000];

    }
    public void run() {
        try {

            while (true) {
                buffer = new byte[1000];
                messageIn = new DatagramPacket(buffer, buffer.length);
                socket.receive(messageIn);
                data = new String(messageIn.getData()).trim();
                //System.out.println(data);
                try{
                    chr = data.charAt(0);
                    monstruo[0] = Integer.parseInt(chr.toString());
                    chr = data.charAt(2);
                    monstruo[1] = Integer.parseInt(chr.toString());
                    int pos = monstruo[0]*4 + monstruo[1];
                    arrCheck[pos].setSelected(true);
                }catch(Exception e){
                    System.out.println(data);
                }
            }
        }catch(Exception e){

        }
    }


}
