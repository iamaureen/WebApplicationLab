import java.net.*;
import java.util.HashMap;
import java.util.Map;
import java.io.*;

class SockServer1 {
    public static void main (String args[]) throws Exception {
        ServerSocket    serv = null;
        InputStream in = null;
        OutputStream out = null;
        Socket sock = null;
        Map<Integer, Integer> totals = new HashMap<Integer, Integer>();
        
        try {
            serv = new ServerSocket(8888);
        } catch(Exception e) {
	    e.printStackTrace();
	}
        while (serv.isBound() && !serv.isClosed()) {
            System.out.println("Ready...");
            try {
                sock = serv.accept();
                in = sock.getInputStream();
                out = sock.getOutputStream();

                int x = in.read();
                System.out.println("Server received " + x );
                //totals.put(0,0); //initialize
                Integer total=totals.get(0);
                if(total==null){
                	total=0;
                }
                totals.put(0, total+x);
                //Thread.sleep(10000);
                out.write(totals.get(0));
                out.flush();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (out != null)  out.close();
                if (in != null)   in.close();
                if (sock != null) sock.close();
            }
        }
    }
}

