package lab2task2;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class HomePg
 */
@WebServlet(name="HomePg",urlPatterns={"/HomePg"})
public class HomePg extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomePg() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");  
        PrintWriter out=response.getWriter();  
        
        HttpSession session=request.getSession(false);  
        if(session!=null){  
            String name=(String)session.getAttribute("uname");  
              
            out.print("Hello, "+name+" Welcome to Profile");  
         
            //form to progress with search
			out.println("<form action='search' method='post'>");
			out.println("Input the Language Preference : <input type='text' name='languages'/><br/> " );
			out.println("<input type='submit' value='Search'/>  ");
			out.println("</form>");
           
			//form to go to the multiple page form
			out.println("<form action='first' method='post'>");
			out.println("<input type='submit' value='Go to Muliple Page Form'/>  ");
			out.println("</form>");
			
            }  
            else{  
            	
                out.print("Please enter yout name first, you are not logged");  
                out.println("<br><br><a href=\""+response.encodeURL("login.html")+"\">BACK TO LOGIN FORM</a>");
                
            }  
            out.close(); 
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
