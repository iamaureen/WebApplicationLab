import java.net.*;
import java.io.*;

class SockClient4 {
     public static void main (String args[]) throws Exception {
        Socket sock = null;
	   int i1=0;

	try {
	    i1 = Integer.parseInt(args[0]);
	} catch (NumberFormatException nfe) {
	    System.out.println("Command line args must be integers");
	    System.exit(2);
	}
        try {
            sock = new Socket("localhost", 8888);
            BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            DataOutputStream out = new DataOutputStream(sock.getOutputStream());
            
            out.writeBytes(Integer.toString(i1)+'\n');
            System.out.println("here");
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
