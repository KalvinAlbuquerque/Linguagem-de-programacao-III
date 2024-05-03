import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class InterfaceRMIImpl extends UnicastRemoteObject implements InterfaceRMI
{
    /* Serializando o objeto */
    private static final long servialVersionUID = 1L;

    protected InterfaceRMIImpl() throws RemoteException
    {
        super();
    }


    /* 
     * Toda classe que implementa uma interface remota, darão origem a objetos remotos.
     */

    @Override
    public String sayHello() throws RemoteException 
    {
        return "Hello World";
    }
    
}
