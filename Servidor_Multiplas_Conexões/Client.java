package Servidor_Multiplas_Conexões;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client
{
    private Socket socket;
    private DataOutputStream out;
    private Scanner scan;
    
    /* 
     * Método responsável por conectar o client ao servidor e enviar mensagens
     */
    private void Connecting()
    {
        try
        {
            /* 
             * 1.Criando o socket
             * 2.Conectando o client ao servidor passando a porta e o IP
             * 3.Escrevendo as mensagens e enviando para o servidor até que digite a string de parada.
             * 4.Fechando o client e o socket
             */
            this.socket = new Socket("localhost", Server.PORT);
            this.out = new DataOutputStream(socket.getOutputStream());
            this.scan = new Scanner(System.in);

            writeMessage();
        }
        catch(IOException e)
        {
            System.out.println("Error in connecting function (Client): " + e.getMessage());

        }
        finally
        {
            close();
        }

    }

    /* 
     * Método responsável por escrever mensagens ao servidor
     */
    private void writeMessage() throws IOException
    {
        String message = "";
        while(!message.equals(Server.STRING_STOP_CONECTION))
        {
            message = scan.nextLine();
            out.writeUTF(message);
        }

    }

    private void close()
    {
        try
        {
            this.socket.close();
            this.out.close();
            this.scan.close();
        }
        catch(IOException e)
        {
            System.out.println("Error in close functioin (Client): " + e.getMessage());
 
        }
    }

    public static void main(String[] args)
    {
        Client client = new Client();
        
        client.Connecting();
    }
}
