import java.math.BigInteger;
import java.net.*;
import java.util.HashMap;
import java.util.Map;
import java.io.*;

class SockServer4 {
    public static void main (String args[]) throws Exception {
        ServerSocket    serv = null;
        Socket sock = null;
        Map<Integer, Integer> totals = new HashMap<Integer, Integer>();
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();


        try {
            serv = new ServerSocket(8888);
        } catch(Exception e) {
	    e.printStackTrace();
	}
        while (serv.isBound() && !serv.isClosed()) {
            System.out.println("Ready...");
            try {
                sock = serv.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
                DataOutputStream out = new DataOutputStream(sock.getOutputStream());

                
                String dataInString= in.readLine();
                System.out.println("Server received "+dataInString);
                int x= Integer.parseInt(dataInString);
                System.out.println("converted int "+x);

                Integer total=totals.get(0);
                if(total==null){
                	total=0;
                }

                totals.put(0, total+x);
                //Thread.sleep(10000);
                int ans=totals.get(0);
                out.writeBytes(Integer.toString(ans));
                out.flush();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (sock != null) sock.close();
            }
        }
    }

}

