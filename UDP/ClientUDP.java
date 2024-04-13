package UDP;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClientUDP 
{
    private byte[] buffer;
    private InetAddress inetAddress;
    private DatagramSocket datagramSocket;
    private Scanner scan;
    
    public ClientUDP()
    {
        try
        {
            this.datagramSocket =    
            this.scan = new Scanner(System.in);
            writeMessage();

        }
        catch(IOException e)
        {
            System.out.println("Error: " + e.getMessage());
        }
        finally
        {
            close();
        }


    }

    /* Método responsável por enviar mensagens infinitamente para o servidor. Para tal estou usando o scanner para ler o meu teclado e o DataOutputStream para
     * enviar a mensagem para o servidor.
     */
    private void writeMessage() throws IOException
    {
        String message = "";
        while(!message.equals(ServerUDP.STOP_STRING))
        {
            System.out.println("Enter message: ");
            message = scan.nextLine();
            buffer = message.getBytes();
            DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length, )
        }

    }

    /* 
     * Método responsável por fechar a conexão (socket), scan e o DataOutputStream.
     */
    private void close()
    {
        try
        {
            socket.close();
            scan.close();

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        new ClientUDP();
    }
}

