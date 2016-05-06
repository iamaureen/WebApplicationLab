import java.net.*;
import java.io.*;
import java.util.*;

class SockServer3 {
	public static void main (String args[]) throws Exception {
		ServerSocket    serv = null;
		InputStream in = null;
		OutputStream out = null;
		Socket sock = null;
		int clientId = 0;
		Map<Integer, Integer> totals = new HashMap<Integer, Integer>();

		try {
			serv = new ServerSocket(8888);
		} catch(Exception e) {
			e.printStackTrace();
		}
		while (serv.isBound() && !serv.isClosed()) {
			System.out.println("SockServerState Ready...");
			try {
				sock = serv.accept();
				in = sock.getInputStream();
				out = sock.getOutputStream();

				
				if (args.length > 0) Thread.sleep(Long.parseLong(args[0]));
				
				
					clientId = in.read();
					System.out.print("ClientId--> "+clientId+"\n");
					int x = in.read();
					System.out.print("X--> "+x+"\n");
					//System.out.print(" for client " + clientId + " " + x);
					Integer total = totals.get(clientId);
					if (total == null) {
						total = 0;
					}
					totals.put(clientId, total + x);
					
					
					out.write(totals.get(clientId));
					
				
				
				System.out.println("");
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

