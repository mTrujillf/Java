package participants;

import java.net.*;
import java.io.*;

public class TCPServer {

    public static void main(String args[]) {

        try {
            int serverPort = 49152;
            ServerSocket listenSocket = new ServerSocket(serverPort);
            while (true) {
                System.out.println("Waiting for messages...");
                Socket clientSocket = listenSocket.accept();  // Listens for a connection to be made to this socket and accepts it. The method blocks until a connection is made.
                Connection c = new Connection(clientSocket,null,null,null,null);
                c.start();
            }
        } catch (IOException e) {
            System.out.println("Listen :" + e.getMessage());
        }
    }

}

class Connection extends Thread {
    private DataInputStream in;
    private DataOutputStream out;
    private Socket clientSocket;
    private InetAddress group;
    private MulticastSocket socket;
    boolean paso;
    String user;
    String data;
    String ganador;
    Character chr;

    Integer nHit;
    Integer mHit;

    Integer n;
    Integer m;
    public Connection(Socket aClientSocket,InetAddress group,MulticastSocket socket, Integer n, Integer m) {
        this.group = group;
        this.socket = socket;
        this.n = n;
        this.m = m;
        try {
            clientSocket = aClientSocket;
            in = new DataInputStream(clientSocket.getInputStream());
            out = new DataOutputStream(clientSocket.getOutputStream());
        } catch (IOException e) {
            System.out.println("Connection:" + e.getMessage());
        }
    }

    @Override
    public void run() {
        try {

            while(true) {
                // an echo server
                user = in.readUTF();
                System.out.println("Hit" + user);
                data = in.readUTF();
                paso = false;
                while(!paso) {
                    try {
                        chr = data.charAt(0);
                        nHit = Integer.getInteger(chr.toString());

                        chr = data.charAt(1);
                        mHit = Integer.getInteger(chr.toString());
                        paso = true;
                    }catch(Exception e){
                        data = in.readUTF();
                    }
                }
                ganador = "El Jugardor " + user + " es el ganador de la ronda";
                byte[] men = data.getBytes();
                System.out.println(data);

                DatagramPacket messageOut = new DatagramPacket(men, men.length, group, 49155);
                try {
                    socket.send(messageOut);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            //}// envio respuesta

        } catch (EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO:" + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}