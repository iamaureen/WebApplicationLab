import java.net.*;
import java.io.*;

class SockClient6 {
     public static void main (String args[]) throws Exception {
        Socket sock = null;
	   int i1=0,i2=0;

	try {
	    i1 = Integer.parseInt(args[0]);
	   
	
	} catch (NumberFormatException nfe) {
	    System.out.println("Command line args must be integers");
	    System.exit(2);
	}
        try {
            sock = new Socket("localhost", 8888);
            PrintWriter out = new PrintWriter(sock.getOutputStream(), true);
    		BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
    		
    		
            
            out.println(Integer.toString(i1)+'\n');
         //   out.println(Integer.toString(i2)+'\n');
           //System.out.println("here in client");
           // out.write(i2);
            String result = in.readLine();
            System.out.println("Result is " + result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sock != null) sock.close();
        }
      	
		

    }
}
