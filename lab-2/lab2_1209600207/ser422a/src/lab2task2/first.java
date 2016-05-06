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
 * Servlet implementation class first
 */
@WebServlet(name="first",urlPatterns={"/first"})
public class first extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public first() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//doPost(request,response);
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
			
		//from second page to first page using back button
		String button = request.getParameter("button");
		if(button != null && button.equals("Previous")){
			//get cookies
			Cookie[] cookies = request.getCookies();

			String fn = null;
			String ln = null;
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
			 //form with the pre-populated value
			out.println("<form name='f1' action='second' method='post'>");
			out.println("First name:<br>");
			out.println("<input type='text' name='firstname' value="+fn+"><br>");
			out.println("Last name:<br>");
			out.println("<input type='text' name='lastname' value="+ln+"><br>") ; 
			out.println("<input type='submit' name='button' value='Next Page' >");		  
			out.println("</form>");
			
			
		}
		else{
		
			Cookie[] cookies = request.getCookies();
		    if (cookies != null){
		        for (int i = 0; i < cookies.length; i++) {
		            cookies[i].setValue("");
		           // cookies[i].setPath("/");
		            cookies[i].setMaxAge(0);
		            response.addCookie(cookies[i]);
		        }
		    }
		    
			//original form
			out.println("<form name='f1' action='second' method='post'>");
			out.println("First name:<br>");		
			out.println("<input type='text' name='firstname' ><br>");
			out.println("Last name:<br>");
			out.println("<input type='text' name='lastname' ><br>") ; 
			out.println("<input type='submit' name='button' value='Next Page' >");		  
			out.println("</form>");
		}
	}

}
