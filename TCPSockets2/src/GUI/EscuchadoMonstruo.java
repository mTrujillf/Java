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
    public EscuchadoMonstruo(int [] monstruo) {
        this.monstruo = monstruo;
        try {
            group = InetAddress.getByName("228.5.194.7"); // destination multicast group
            socket = new MulticastSocket(49155);
            socket.joinGroup(group);
            buffer = new byte[1000];
        } catch (IOException ex) {

        }
    }
    public void run() {
        try {

            while (true) {
                messageIn = new DatagramPacket(buffer, buffer.length);
                socket.receive(messageIn);
                data = new String(messageIn.getData()).trim();
                System.out.println(data);
                try{
                    chr = data.charAt(0);
                    monstruo[0] = Integer.parseInt(chr.toString());
                    chr = data.charAt(2);
                    monstruo[1] = Integer.parseInt(chr.toString());
                }catch(Exception e){
                    System.out.println(data);
                }
            }
        }catch(Exception e){

        }
    }


}
