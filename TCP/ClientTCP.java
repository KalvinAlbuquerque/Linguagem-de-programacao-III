package TCP;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientTCP 
{
    private DataOutputStream output;
    private Scanner scan;
    private Socket socket;
    
    public ClientTCP()
    {
        try
        {   
            /* Criando um socket e conectando-o ao servidor através de um IP e uma porta. */
            this.socket = new Socket("localhost", ServerTCP.PORT);
            this.scan = new Scanner(System.in);
            this.output = new DataOutputStream(this.socket.getOutputStream());
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
        while(!message.equals(ServerTCP.STOP_STRING))
        {
            System.out.println("Enter message: ");
            message = scan.nextLine();
            output.writeUTF(message);
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
            output.close();

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        new ClientTCP();
    }
}

