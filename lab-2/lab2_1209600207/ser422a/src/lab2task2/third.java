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
 * Servlet implementation class third
 */
@WebServlet(name="third",urlPatterns={"/third"})
public class third extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public third() {
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
			
			out.println("<form name='f1' action='fourth' method='post'>");
			out.println("Days to Meet:<br>");
			out.println("<input type='checkbox' name='days' value='Sunday'/> Sunday");
			out.println("<input type='checkbox' name='days' value='Monday'/> Monday");
			out.println("<input type='checkbox' name='days' value='Tuesday'/> Tuesday");
			out.println("<input type='checkbox' name='days' value='Wednesday'/> Wednesday");
			out.println("<input type='checkbox' name='days' value='Thursday'/> Thursday");
			out.println("<input type='checkbox' name='days' value='Friday'/> Friday");
			out.println("<input type='checkbox' name='days' value='Saturday'/> Saturday<br>");		
			out.println("<input type='submit' name='button' value='Previous' onclick='f1.action=\"second\"' >");		
			out.println("<input type='submit' name='button' value='Next Page' >");		  
			out.println("</form>");
		}
		else{
			//get the inputs
			String lan_name = request.getParameter("language");
			
			if(lan_name.isEmpty()){
				response.sendError(response.SC_NOT_FOUND, "Enter value for Language");
				//response.sendRedirect("first");
			}
			
			
			//create and set the cookie
			Cookie c1 = new Cookie("Language",lan_name);
		    response.addCookie(c1);
		    	    
		    //set expiration time for cookie
		    c1.setMaxAge(2*60);
		    
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
		    out.println("<form name='f1' action='fourth' method='post'>");
			out.println("Days to Meet:<br>");
			out.println("<input type='checkbox' name='days' value='Sunday'/> Sunday");
			out.println("<input type='checkbox' name='days' value='Monday'/> Monday");
			out.println("<input type='checkbox' name='days' value='Tuesday'/> Tuesday");
			out.println("<input type='checkbox' name='days' value='Wednesday'/> Wednesday");
			out.println("<input type='checkbox' name='days' value='Thursday'/> Thursday");
			out.println("<input type='checkbox' name='days' value='Friday'/> Friday");
			out.println("<input type='checkbox' name='days' value='Saturday'/> Saturday<br>");		
			out.println("<input type='submit' name='button' value='Previous' onclick='f1.action=\"second\"' >");		
			out.println("<input type='submit' name='button' value='Next Page' >");		  
			out.println("</form>");
		}
		
	}

}
