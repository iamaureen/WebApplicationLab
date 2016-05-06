package lab2task2;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class second
 */
@WebServlet(name="second",urlPatterns={"/second"})
public class second extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public second() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendError(response.SC_SERVICE_UNAVAILABLE, "Doesnt Accept Get Request, go to 'http://localhost:8080/ser422a/'");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		String button = request.getParameter("button");
		if(button != null && button.equals("Previous")){
			//get cookies
			Cookie[] cookies = request.getCookies();

			String lan = null;
			if(cookies!=null){
				for(Cookie cookie : cookies){
				    if("Language".equals(cookie.getName())){
				        lan = cookie.getValue();
				        
				    } 
				} 
			}
			
			out.println("<form name='f1' action='third' method='post'>");
			out.println("Language:<br>");
			out.println("<input type='text' name='language' value="+lan+"><br>");
			
			out.println("<input type='submit' name='button' value='Previous' onclick='f1.action=\"first\"' >");		
			out.println("<input type='submit' name='button' value='Next Page' >");		  
			out.println("</form>");
		}
			else{
				//get the inputs
				String fname = request.getParameter("firstname");
				String lname = request.getParameter("lastname");
				
				if(fname.isEmpty()||lname.isEmpty()){
					response.sendError(response.SC_NOT_FOUND, "Enter values for Firstname and Lastname");
					//response.sendRedirect("first");
				}
				//create and set the cookie
				Cookie c1 = new Cookie("Firstname",fname);
			    response.addCookie(c1);
			    Cookie c2 = new Cookie("Lastname",lname);
			    response.addCookie(c2);
			    
			    //set expiration time for cookie
			    c1.setMaxAge(2*60);
			    c2.setMaxAge(2*60);
			    
			    Cookie[] cookies = request.getCookies();

				String lan = "";
				if(cookies!=null){
					for(Cookie cookie : cookies){
					    if("Language".equals(cookie.getName())){
					        lan = cookie.getValue();
					        
					    } 
					}
				}
			    
			    //form
			    out.println("<form name='f1' action='third' method='post'>");
				out.println("Language:<br>");
				out.println("<input type='text' name='language' value="+lan+"><br>");
				out.println("<input type='submit' name='button' value='Previous' onclick='f1.action=\"first\"' >");		
				out.println("<input type='submit' name='button' value='Next Page' >");		  
				out.println("</form>");
			}
		}
		
	    
	
}
