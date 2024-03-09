package participants;

import java.io.IOException;
import java.net.*;
import java.util.Date;

public class MultiCastSenderPegale {

    public static void main(String args[]) {
        Integer n = (int) (Math.random()*(4-1)+0);
        Integer m = (int) (Math.random()*(4-1)+0);

        MulticastSocket socket = null;
        try {
            int serverPort = 49152;
            InetAddress group = InetAddress.getByName("228.5.194.7"); // destination multicast group
            socket = new MulticastSocket(49155);
            socket.joinGroup(group);
            ServerSocket listenSocket = new ServerSocket(serverPort);
            System.out.println("Waiting for messages...");
            Socket clientSocket = listenSocket.accept();  // Listens for a connection to be made to this socket and accepts it. The method blocks until a connection is made.
            Connection c = new Connection(clientSocket,group,socket,n,m);
            c.start();
            while (true) {
                String myMessage = (m + "," + n);
                byte[] men = myMessage.getBytes();
                DatagramPacket messageOut = new DatagramPacket(men, men.length, group, 49155);
                socket.send(messageOut);
                System.out.println("Hola");
                Thread.sleep(200);
            }

        } catch (IOException e) {
            System.out.println("Listen :" + e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
}
