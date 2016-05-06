package lab2task2;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Servlet implementation class search
 */
@WebServlet("/search")
public class search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public search() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		String LINE="<BR>";
		response.setContentType("text/html");  
        PrintWriter out=response.getWriter();
        
      //get path of the xml file
      	ServletConfig scfg= getServletConfig();
      	ServletContext scxt = scfg.getServletContext();
      	String webInfPath = scxt.getRealPath("/");
        
      //variables for comparing xml values
      	String fn,ln,lan,ds,fd;
      	int a;;
        
        String lan1=request.getParameter("languages");
       
        try {	
	         File inputFile = new File(webInfPath + "\\lab2.xml");
	         //check if the file exists or not
	         if(!inputFile.exists()){
					//out.println("file exists");
	        	  out.println("No user enlisted in the file"+LINE);
	        	  out.println("<form action='first' method='post'>");
	  			  out.println("<input type='submit' value='Go to Muliple Page Form'/>  ");
	  			  out.println("</form>");
	         }
	         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	         Document doc = dBuilder.parse(inputFile);
	         doc.getDocumentElement().normalize();
	        
	         NodeList userList = doc.getElementsByTagName("User");
	       
	         for (int temp = 0; temp < userList.getLength(); temp++) {
	            Node nNode = userList.item(temp);
	            a=0;
	            //out.println("\nCurrent Element :"  + nNode.getNodeName()+LINE);
	            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	              Element eElement = (Element) nNode;
	              fn = eElement.getElementsByTagName("firstname").item(0).getTextContent();
	              ln = eElement.getElementsByTagName("lastname").item(0).getTextContent();
	              lan = eElement.getElementsByTagName("language").item(0).getTextContent();
	              ds = eElement.getElementsByTagName("days").item(0).getTextContent();
	              fd = eElement.getElementsByTagName("food").item(0).getTextContent();
	             // out.println("----------------------------"+LINE);
	              
	           
				if(lan1.equalsIgnoreCase(lan)){
	            	  a=1;
	            	  }
	             
	              if(a==1){
	            	  out.println("First Name : " +fn +LINE);
			          out.println("Last Name : " + ln+LINE);
			          out.println("Language : " + lan +LINE);
			          out.println("Days Available : "+ds+LINE);
			          out.println("Likes Food : " +fd+LINE);
			          
			          out.println("<form action='first' method='post'>");
	    			  out.println("<input type='submit' value='Go to Muliple Page Form'/>  ");
	    			  out.println("</form>");
	              }
	              else{
	            	  out.println("No entry with this value, search again"+LINE);
	            	  out.println("<form action='search' method='post'>");
	      			  out.println("Input the Language Preference : <input type='text' name='languages'/><br/> " );
	      			  out.println("<input type='submit' value='Search'/>  ");
	      			  out.println("</form>");
	      			  
	      			  out.println("<form action='first' method='post'>");
	    			  out.println("<input type='submit' value='Go to Muliple Page Form'/>  ");
	    			  out.println("</form>");
	              }
	            }
	         }
	      } catch (Exception eet) {
	         eet.printStackTrace();
	      }
        
        
  	  	
		  
	}

}
