package participants;

import java.io.IOException;
import java.net.*;
import java.util.Date;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;

public class MultiCastSenderPegale {

    public static void main(String args[]) {

        int [] monstruo = {0,0};

        MulticastSocket socket = null;
        try {
            Dictionary<String, Integer> dictionary = new Hashtable<>();
            int serverPort = 49152;
            InetAddress group = InetAddress.getByName("228.5.194.7"); // destination multicast group
            socket = new MulticastSocket(49155);
            socket.joinGroup(group);
            System.out.println("Waiting for messages...");
            EscuchadorTCP eTCP = new EscuchadorTCP(serverPort,group,socket,monstruo,dictionary);
            eTCP.start();
            while (true) {
                String myMessage = (0 + "," + 0);
                byte[] men = myMessage.getBytes();
                DatagramPacket messageOut = new DatagramPacket(men, men.length, group, 49155);
                socket.send(messageOut);
                Thread.sleep(200);
            }

        } catch (IOException e) {
            System.out.println("Listen :" + e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
}
