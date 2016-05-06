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
 * Servlet implementation class login_submit
 */
@WebServlet("/login_submit")
public class login_submit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login_submit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		response.setContentType("text/html");  
	     PrintWriter out = response.getWriter();  
	     
		 String n1=request.getParameter("fname");  
		 String n2=request.getParameter("lname"); 
		 
		 //check whether first and last name is empty or not, all provided, add to session, else redirect to the login page
		 if((n1!=null && n1.length()>0) && (n2!=null && n2.length()>0)){
			 String n=n1+" "+n2;
		     out.print("Welcome "+n);  
		          
		     HttpSession session=request.getSession();  
		     session.setAttribute("uname",n);  
		     response.sendRedirect("HomePg");
		 }
		 else{
			 
			 out.println("Enter your firstname and Lastname");
			 request.getRequestDispatcher("login.html").include(request, response);  
		 }
	}

}
