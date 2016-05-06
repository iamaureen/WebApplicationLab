package lab2task2;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 * Servlet implementation class submiit
 */
@WebServlet(name="submit",urlPatterns={"/submit"})
public class submit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public submit() {
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
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("All the information written in to xml file");

		out.println("<form action='search' method='post'>");
		  out.println("Input the Language Preference : <input type='text' name='languages'/><br/> " );
		  out.println("<input type='submit' value='Search'/>  ");
		  out.println("</form>");
		  
		out.println("<br><a href=\""+response.encodeURL("login.html")+"\">BACK TO LOGIN PAGE</a><br>");
		
		//get the cookies
		Cookie[] cookies = request.getCookies();
		String fn = null;
		String ln = null;
		String lan=null;
		String d=null;
		String f=null;
		for(Cookie cookie : cookies){
		    if("Firstname".equals(cookie.getName())){
		        fn = cookie.getValue();
		    }
		}
		for(Cookie cookie : cookies){
		    if("Lastname".equals(cookie.getName())){
		        ln = cookie.getValue();
		    }
		}
		for(Cookie cookie : cookies){
		    if("Language".equals(cookie.getName())){
		        lan = cookie.getValue();
		    }
		}
		for(Cookie cookie : cookies){
		    if("Days".equals(cookie.getName())){
		        d = cookie.getValue();
		    }
		}
		for(Cookie cookie : cookies){
		    if("Food".equals(cookie.getName())){
		        f = cookie.getValue();
		    }
		}
		
		//get path of the xml file
		ServletConfig scfg= getServletConfig();
		ServletContext scxt = scfg.getServletContext();
		String webInfPath = scxt.getRealPath("/");
		
		//create xml or look for existing 
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		DocumentBuilder builder=null;
		try {
			builder = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		File file = new File(webInfPath + "\\lab2.xml");
		Document doc=null;
		if(file.exists()){
			out.println("file exists");
			 try {
				  doc = builder.parse(webInfPath + "\\lab2.xml");
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Element element = doc.getDocumentElement(); //root
			Element user = doc.createElement("User");
			element.appendChild(user);
			
			Element firstname = doc.createElement("firstname");
			firstname.appendChild(doc.createTextNode(fn));
			user.appendChild(firstname);
			
			Element lastname = doc.createElement("lastname");
			lastname.appendChild(doc.createTextNode(ln));
			user.appendChild(lastname);
			
			Element language = doc.createElement("language");
			language.appendChild(doc.createTextNode(lan));
			user.appendChild(language);
			
			Element days = doc.createElement("days");
			days.appendChild(doc.createTextNode(d));
			user.appendChild(days);
			
			Element fd = doc.createElement("food");
			fd.appendChild(doc.createTextNode(f));
			user.appendChild(fd);
			
			/*String[] days1 = request.getParameterValues("days");
			 for(String s: days1) {
				// out.println(s);
			days.appendChild(doc.createTextNode(s+" "));
			user.appendChild(days);
			}
			if(request.getParameter("food").equals("Yes")) {
				Element food = doc.createElement("food");
				food.appendChild(doc.createTextNode("Yes"));
				user.appendChild(food);
	         }
	         else {
	        	Element food = doc.createElement("food");
				food.appendChild(doc.createTextNode("No"));
				user.appendChild(food);
	         }*/
			 
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = null;
			try {
				transformer = transformerFactory.newTransformer();
			} catch (TransformerConfigurationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(webInfPath + "\\lab2.xml"));
			try {
				transformer.transform(source, result);
			} catch (TransformerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			 
		}
		else{
			   doc = builder.newDocument();
			// root element
			Element element = doc.createElement("weblab2");
			doc.appendChild(element);
			
			Element user = doc.createElement("User");
			element.appendChild(user);
			
			Element firstname = doc.createElement("firstname");
			firstname.appendChild(doc.createTextNode(fn));
			user.appendChild(firstname);
			
			Element lastname = doc.createElement("lastname");
			lastname.appendChild(doc.createTextNode(ln));
			user.appendChild(lastname);
			
			Element language = doc.createElement("language");
			language.appendChild(doc.createTextNode(lan));
			user.appendChild(language);
			
			Element days = doc.createElement("days");
			days.appendChild(doc.createTextNode(d));
			user.appendChild(days);
			
			Element fd = doc.createElement("food");
			fd.appendChild(doc.createTextNode(f));
			user.appendChild(fd);
			 
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = null;
			try {
				transformer = transformerFactory.newTransformer();
			} catch (TransformerConfigurationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(webInfPath + "\\lab2.xml"));
			try {
				transformer.transform(source, result);
			} catch (TransformerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}//end of inner else-checking whether the file exists
		
		Cookie[] co = request.getCookies();
	    if (co != null){
	        for (int i = 0; i < co.length; i++) {
	            co[i].setValue("");
	            co[i].setPath("/");
	            co[i].setMaxAge(0);
	            response.addCookie(co[i]);
	        }
	    }
	}

}
