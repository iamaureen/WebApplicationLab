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
 * Servlet implementation class fifth
 */
@WebServlet(name="fifth",urlPatterns={"/fifth"})
public class fifth extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public fifth() {
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
		PrintWriter out = response.getWriter();
		
		String fo=request.getParameter("food");
		
		if(fo.isEmpty()){
			response.sendError(response.SC_NOT_FOUND, "Enter values for Firstname and Lastname");
			//response.sendRedirect("first");
		}
		
		Cookie c1 = new Cookie("Food",fo);
	    response.addCookie(c1);
	    
	    c1.setMaxAge(2*60);
	    
	    Cookie[] cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            Cookie c = cookies[i];
            String name = c.getName();
            if(name.equals("JSESSIONID"))continue;
            String value = c.getValue();
            out.println(name + " = " + value+"<br>");
        }
        out.println("<form name='f1' action='fourth' method='post'>");
        out.println("<input type='submit' name='button' value='Previous' >");
        out.println("</form>");
        out.println("<form name='f1' action='submit' method='post'>");
        out.println("<input type='submit' name='button' value='Submit' >");
        out.println("</form>");
        out.println("<form name='f1' action='cancel' method='post'>");
        out.println("<input type='submit' name='button' value='Cancel' >");
        out.println("</form>");
	}

}
