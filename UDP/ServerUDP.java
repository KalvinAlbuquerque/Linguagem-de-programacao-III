package UDP;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ServerUDP 
{
    public static int PORT = 5050;
    public static String STOP_STRING = "##";

    private DatagramSocket server;

    private byte[] buffer = new byte[256];

    public ServerUDP()
    {
        try
        {
            /* Criando servidor UDP e passando a porta */
            this.server = new DatagramSocket(PORT);
            System.out.println("Server initialized sucessfully");

      

            /* Uma vez recebida a conexão, irei entrar em um looping infinito, em que o servidor ficará lendo todas as mensagens vindas do servidor
             * até que a mensagem que indica a finalizaçaõ da conexão seja enviada.
             */
            if(server.isConnected())
            {
                readMessage();
                close();
            }

        }
        catch(IOException e)
        {
            System.out.println("Error: " + e.getMessage());
        }

    }

    /* 
     * Método responsável por ler todas as mensagens enviadas pelo client.
     * OBS.:
     * Estou utilizando o DataInputStream e o DataOutputStream, há o ObjectOutputStream/ObjectInputStream e são, sem dúvidas, bugados. Se possível não os utilize,
     * deu muito trabalho fazer uma aplicação rodar com eles.
     * 
     * Aqui estou acessando o buffer de input, para onde as mensagens do client estão sendo enviadas, lendo os caracteres e armazenando-os em "message".
     */
    private void readMessage()
    {
        try
        {
            DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
            this.server.receive(datagramPacket);
            String message = "";
            while(!message.equals(STOP_STRING))
            {   
                message = new String(datagramPacket.getData(),0, datagramPacket.getLength());
                System.out.println("Client said: " + message);
            }
        }
        catch(IOException e)
        {
            System.out.println("Error: " + e.getMessage());
        }
    }


    /* 
     * Método responsável por fechar o servidor e o canal de input.
     */
    private void close() throws IOException
    {
        server.close();
    }

    public static void main(String[] args)
    {
        new ServerUDP();
    }
}
