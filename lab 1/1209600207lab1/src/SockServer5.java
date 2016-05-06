import java.net.*;
import java.io.*;
import java.util.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

class SockServer5 {
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
			totals=check(); //function call to restore the server.
				         
			try {
				sock = serv.accept();
				in = sock.getInputStream();
				out = sock.getOutputStream();

				
				if (args.length > 0) Thread.sleep(Long.parseLong(args[0]));				
				
					clientId = in.read();
					System.out.print("ClientId--> "+clientId+"\n");
					int x = in.read();
					System.out.print("X--> "+x+"\n");
				
					Integer total = totals.get(clientId);
					if (total == null) {
						total = 0;
					}
					totals.put(clientId, total + x);
					
					DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
					DocumentBuilder builder = dbf.newDocumentBuilder();
					Document doc = builder.newDocument();
					
					// root element
					Element element = doc.createElement("calculator");
					doc.appendChild(element);
					
					for (Map.Entry<Integer, Integer> entry : totals.entrySet()) {
						//System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
					
					
					
					Element itemElement = doc.createElement("user");
					element.appendChild(itemElement);
					
					
					itemElement.setAttribute("ID", entry.getKey().toString());
					itemElement.setAttribute("Total", entry.getValue().toString());
					
					}
					
					
					TransformerFactory transformerFactory = TransformerFactory.newInstance();
					Transformer transformer = transformerFactory.newTransformer();
					DOMSource source = new DOMSource(doc);
					StreamResult result = new StreamResult(new File("D:\\file.xml"));
					transformer.transform(source, result);
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
	
	public static Map<Integer, Integer> check() throws SAXException, IOException, ParserConfigurationException{
		 Map<Integer, Integer> totals = new HashMap<Integer, Integer>();
		 File inputFile = new File("D:\\file.xml");
		 if(inputFile.exists()==false)return totals ;
		 else{
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();
       
        System.out.println(doc.getDocumentElement().getNodeName());
        NodeList nList = doc.getElementsByTagName("user");
        System.out.println("----------------------------");
        
      //  Map<Integer, Integer> totals = new HashMap<Integer, Integer>();
        for (int temp = 0; temp < nList.getLength(); temp++) {
           Node nNode = nList.item(temp);
           System.out.print("\nCurrent Element :");
           System.out.print(nNode.getNodeName()+"\n");
           if (nNode.getNodeType() == Node.ELEMENT_NODE) {
              Element eElement = (Element) nNode;
              System.out.print("ID: ");
              String x= eElement.getAttribute("ID");
              String y= eElement.getAttribute("Total");
              System.out.println(eElement.getAttribute("ID"));
              System.out.print("Total: ");
              System.out.println(eElement.getAttribute("Total")); 
              totals.put(Integer.parseInt(x), Integer.parseInt(y));
           }
        }
        return totals;
	   }
	 }
}

	
	 
 
