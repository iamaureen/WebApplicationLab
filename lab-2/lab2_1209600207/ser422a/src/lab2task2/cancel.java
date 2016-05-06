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
 * Servlet implementation class cancel
 */
@WebServlet(name="cancel",urlPatterns={"/cancel"})
public class cancel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public cancel() {
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
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("pressed cancel-clear cache data");
		
		//delete the cookies
		Cookie[] cookies = request.getCookies();
	    if (cookies != null){
	        for (int i = 0; i < cookies.length; i++) {
	            cookies[i].setValue("");
	            //cookies[i].setPath("/");
	            cookies[i].setMaxAge(0);
	            response.addCookie(cookies[i]);
	        }
	    }
	    out.println("All the data have been cleared.");
	    out.println("<br><a href=\""+response.encodeURL("login.html")+"\">BACK TO LOGIN PAGE</a><br>");
	    /*//check
	    
	    Cookie[] ckie = request.getCookies();
        for (int i = 0; i < ckie.length; i++) {
            Cookie c = ckie[i];
            String name = c.getName();
            String value = c.getValue();
            out.println(name + " = " + value+"<br>");
        }*/
        
        //response.sendRedirect("HomePg");
	}

}
