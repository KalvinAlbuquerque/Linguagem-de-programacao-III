package UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ServerUDP 
{
    public static int PORT = 5050;
    public static String STOP_STRING = "##";

    public static void main(String[] args)
    {
        try
        {
            /* Criando o servidor UDP */
            DatagramSocket serverSocket = new DatagramSocket(ServerUDP.PORT);
            System.out.println("Server initialized sucessfully");

            /* Criando buffer para receber os bytes (mensagens) do client */
            byte[] receivedData = new byte[1024];
            DatagramPacket receivPacket;

            String message = "";
            while(!message.equals(ServerUDP.STOP_STRING))
            {
                /* Esperando pela mensagem do client */
                receivPacket = new DatagramPacket(receivedData, receivedData.length);
                serverSocket.receive(receivPacket);

                message = new String(receivPacket.getData(), 0, receivPacket.getLength());

                System.out.println("Client said: " + message);
                
            }

            serverSocket.close();
        }   
        catch(IOException e)
        {
            e.printStackTrace();
        }

    }

  
}
