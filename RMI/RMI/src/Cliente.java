import java.rmi.Naming;

public class Cliente
{

    public static void main(String[] args)
    {
        try
        {
            /* Procurando pelo objeto remoto no registry - passo a URL */
            /* STUB é um objeto que faz o papel de servidor na máquina do cliente */
            InterfaceRMI stub = (InterfaceRMI) Naming.lookup("rmi://192.168.100.39:1099/ola");
            System.out.println(stub.sayHello());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
