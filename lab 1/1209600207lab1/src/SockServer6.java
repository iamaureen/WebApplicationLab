import java.math.BigInteger;
import java.net.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.*;

class SockServer6 {
	
    public static void main (String args[]) throws Exception {
    	ServerSocket    serv = null;
        Socket sock = null;  
        ExecutorService executor = null;
        executor = Executors.newFixedThreadPool(5);
        
        try {
            serv = new ServerSocket(8888);
        } 
        catch(Exception e) {
        	e.printStackTrace();
        }
        
        while (serv.isBound() && !serv.isClosed()) {
            System.out.println("Ready...");
            
            try {
            	while(true){
	                sock = serv.accept();
	                Runnable t=new ClientServiceThread(sock);
	                executor.execute(t);
            	}
                
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (sock != null) sock.close();
                if (executor != null) executor.shutdown();
    			
            }
        }
    }
    

}

class ClientServiceThread implements Runnable
{ 
    Socket myClientSocket;
    ServerSocket serverSocket = null;
    Map<Integer, Integer> totals = new HashMap<Integer, Integer>();
    ByteArrayOutputStream buffer = new ByteArrayOutputStream();
    BufferedReader in;DataOutputStream out;
  

    ClientServiceThread(Socket s) 
    { 
        myClientSocket = s; 

    } 

    public void run() 
    {            

       System.out.println("Accepted Client Address - " + myClientSocket.getInetAddress().getHostName()); 
       System.out.println(myClientSocket.isConnected());

        try 
        {                                
        	BufferedReader in = new BufferedReader(new InputStreamReader(myClientSocket.getInputStream()));
        	BufferedWriter out = new BufferedWriter(new OutputStreamWriter(myClientSocket.getOutputStream()));
           // DataOutputStream out = new DataOutputStream(myClientSocket.getOutputStream());
        	
            
            String dataInString1= in.readLine();
            System.out.println("Server received "+dataInString1);
            int x= Integer.parseInt(dataInString1);
            System.out.println("converted int "+x);
            
          

            Integer total=totals.get(0);
            if(total==null){
            	total=0;
            }

            totals.put(0, total+x);
            //Thread.sleep(10000);
            int ans=totals.get(0);
            out.write(Integer.toString(ans)+'\n');
            out.flush();

            
        } 
        catch(Exception e) 
        { 
            e.printStackTrace(); 
        } 
        /*finally 
        { 
            // Clean up 
            try 
            {                    
                in.close(); 
                out.close(); 
                myClientSocket.close(); 
                System.out.println("...Stopped"); 
            } 
            catch(IOException ioe) 
            { 
                ioe.printStackTrace(); 
            } 
        }*/ 
    }

}