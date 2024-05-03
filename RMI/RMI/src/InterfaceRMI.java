import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceRMI extends Remote 
{
    /* Interface (classe abstrata) para disponibilizar os métodos que poderão ser requisitados remotamente 
     * Todos os métodos da interface remota devem lançar a exceção do RMI 
     * */
    public String sayHello() throws RemoteException;
    
} 
