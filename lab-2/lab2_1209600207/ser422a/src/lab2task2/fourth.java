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
 * Servlet implementation class fourth
 */
@WebServlet(name="fourth",urlPatterns={"/fourth"})
public class fourth extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public fourth() {
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
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		String button = request.getParameter("button");
		if(button != null && button.equals("Previous")){
			//get value of food from cookie
			out.println("inside previous button");
			Cookie[] cookies = request.getCookies();

			String fo = null;
			if(cookies!=null){
				for(Cookie cookie : cookies){
				    if("Food".equals(cookie.getName())){
				        fo = cookie.getValue();
				        
				    } 
				} 
			}
			
			if(fo.equals("Yes")){
				out.println("<form name='f1' action='fifth' method='post'>");
			    out.println("Do you love to eat?<br>");
			    out.println("<input type='radio' name='food' value='Yes' checked='checked'> Yes<br>");
			    out.println("<input type='radio' name='food' value='No'> No<br>");
			    out.println("<input type='submit' name='button' value='Previous' onclick='f1.action=\"third\"' >");		
				out.println("<input type='submit' name='button' value='Next Page' >");	
				out.println("</form>");
			}
			else{
				out.println("<form name='f1' action='fifth' method='post'>");
			    out.println("Do you love to eat?<br>");
			    out.println("<input type='radio' name='food' value='Yes'> Yes<br>");
			    out.println("<input type='radio' name='food' value='No'checked='checked'> No<br>");
			    out.println("<input type='submit' name='button' value='Previous' onclick='f1.action=\"third\"' >");		
				out.println("<input type='submit' name='button' value='Next Page' >");	
				out.println("</form>");
				
			}
			
		}
		else{
			String d=request.getParameter("days");
			
			if(d.isEmpty()){
				response.sendError(response.SC_NOT_FOUND, "Enter values");
				//response.sendRedirect("first");
			}
			Cookie c1 = new Cookie("Days",d);
		    response.addCookie(c1);
		    	    
		    //set expiration time for cookie
		    c1.setMaxAge(2*60);
		    
		    out.println("<form name='f1' action='fifth' method='post'>");
		    out.println("Do you love to eat?<br>");
		    out.println("<input type='radio' name='food' value='Yes' > Yes<br>");
		    out.println("<input type='radio' name='food' value='No'> No<br>");
		    out.println("<input type='submit' name='button' value='Previous' onclick='f1.action=\"third\"' >");		
			out.println("<input type='submit' name='button' value='Next Page' >");	
			out.println("</form>");
		}
		
	}

}
