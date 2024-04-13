package SimpleThread;

public class SimpleThread implements Runnable
{
   
    /* 
    * Função que realiza uma conta de 0 a 10
    */
    public void count() 
    {
        for(int i = 0;  i < 10; i++)
        {
            System.out.println(i);
            
            try
            {
                Thread.sleep(1000);
            }
            catch(Exception e)
            {
                System.out.println(e.getMessage());
            }
        }
    }    

    public void run()
    {
        System.out.println("Current Thread is: " + Thread.currentThread().getName());
        count();
    }

    public static void main(String[] args)
    {
        try
        {
            SimpleThread main = new SimpleThread();
            Thread t1 = new Thread(main);
            Thread t2 = new Thread(main);
    
            t1.start();
            t2.start();
        }
        catch(Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }


    }
   
}
