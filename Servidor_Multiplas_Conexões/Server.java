package Servidor_Multiplas_Conexões;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
    public static int PORT = 5050;
    public static String STRING_STOP_CONECTION= "##";

    private ServerSocket server;
    private DataInputStream in;
    private int clientID = 0;

    public Server()
    {
        try
        {
            /* Inicializando o servidor */
            this.server = new ServerSocket(PORT);
            System.out.println("Server initialized successfully");
            
            /* Recebendo solicitações de clientes. Uma vez aceita, crio uma thread para ficar lendo as mensagens desse cliente. */
            while(true)
            {
                Socket clientSocket = server.accept();
                if(clientSocket.isConnected())
                {
                    new Thread(()->
                    {
                        int id = clientID++;
                        System.out.println("New connection: Client " + id);
                        readMessage(clientSocket, id);

                    }).start();
                }
            }
            

        }
        catch(IOException e)
        {
            System.out.println("Error in creating a server: " + e.getMessage());
        }
    }

    /* 
     * Método responsável por ler as mensagens recebidas pelo client
     */
    private void readMessage(Socket clientSocket, int clientID)
    {
        try
        {
            String message = "";
            this.in = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream()));
            while(!message.equals(STRING_STOP_CONECTION))
            {
                message = in.readUTF();
                System.out.println("Client " + clientID + " said: " + message);
            }
        }
        catch(IOException e)
        {
            System.out.println("Error in reading a message (Server): " + e.getMessage());
        }

    }
    
    public static void main(String[] args)
    {
        new Server();
    }
}

