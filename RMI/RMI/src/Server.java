import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server 
{
    public static void main(String[] args)
    {
        /* Criando o RMI registry
         * É um servidor que fica rodando no host do servidor, responsável por guardar os objetos remotos.
         * O cliente irá buscar os objetos remotos nesse Registry
         */
        try
        {   
            /* Setando o endereço IP/HOST do servidor no registry */
            System.setProperty("java.rmi.server.hostname", "192.168.100.39");
            Registry registry = LocateRegistry.createRegistry(1099);
            /* Conectando/vinculando um objeto remoto ao servidor registry, dando um nome(em URL) e a instância de um objeto remoto */
            Naming.rebind("rmi://192.168.100.39:1099/ola", new InterfaceRMIImpl());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }    
}
