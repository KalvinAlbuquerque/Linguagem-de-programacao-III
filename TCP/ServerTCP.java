package TCP;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTCP 
{
    public static int PORT = 5050;
    public static String STOP_STRING = "##";

    private ServerSocket server;
    private DataInputStream input;

    public ServerTCP()
    {
        try
        {
            /* Criando servidor TCP e passando a porta */
            this.server = new ServerSocket(PORT);
            System.out.println("Server initialized sucessfully");

            /* Aguardando conexão. O método accept trava a aplicação enquanto o servidor não receber nenhuma solicitação do client */
            Socket clientSocket = server.accept();
            /* Uma vez recebida a conexão, irei entrar em um looping infinito, em que o servidor ficará lendo todas as mensagens vindas do servidor
             * até que a mensagem que indica a finalizaçaõ da conexão seja enviada.
             */
            if(clientSocket.isConnected())
            {
                readMessage(clientSocket);
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
    private void readMessage(Socket clientSocket)
    {
        try
        {
            String message = "";
            this.input = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream()));
            while(!message.equals(STOP_STRING))
            {   
                message = input.readUTF();
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
        input.close();
    }

    public static void main(String[] args)
    {
        new ServerTCP();
    }
}
