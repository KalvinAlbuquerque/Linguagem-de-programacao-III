package UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ClientUDP 
{
    
    private static String STOP_STRING = "##";
    public static int PORT = 5050;
    public static void main(String[] args)
    {
        DatagramPacket sendPacket;
        byte[] sendData;

        try
        {
            /* Criando o UDP socket */
            DatagramSocket clientSocket = new DatagramSocket();
            Scanner scan = new Scanner(System.in);
            String message = "";
            while(!message.equals(STOP_STRING))
            {
                System.out.println("Digite sua mensagem: ");
                message = scan.nextLine();
                sendData = message.getBytes();
                /* Pacote de dados, tamanho do pacote, IP e porta */
                sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("127.0.0.1"), ClientUDP.PORT); 
                clientSocket.send(sendPacket);
            }

            scan.close();
            clientSocket.close();

        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        
    }
}

