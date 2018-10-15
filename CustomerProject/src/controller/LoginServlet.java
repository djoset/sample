package controller;
import static utility.Constants.DBMANAGER;
import static utility.Constants.PASSWORD;
import static utility.Constants.USERNAME;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dao.DBConnectionManager;
import dao.LoginDatabase;
import model.Login;
/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=request.getParameter(USERNAME);
		String password=request.getParameter(PASSWORD);
		String role = request.getParameter("role");
		System.out.println("role "+role);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		System.out.println("username "+username.isEmpty());
		if((username != null && !username.trim().isEmpty() ) && (password != null && !password.trim().isEmpty())){
			System.out.println("not empty");
			
			if(role.equalsIgnoreCase("admin")){
				if(username.equals("admin") && password.equals("admin123"))
				{
					response.sendRedirect("admin");
				}
			}
			else{
				Login login = new Login(username, password);
				LoginDatabase database = new LoginDatabase();
				if(database.validate(login))
				{
					System.out.println("validated in servlet");
					RequestDispatcher rd = request.getRequestDispatcher("welcome");
					rd.forward(request, response);
				}
				else
				{
					System.out.println("invalid");
					RequestDispatcher rd = request.getRequestDispatcher("index.html");
					out = response.getWriter();
					out.println("Invalid Credentials");
					rd.include(request, response);
				}
			}
		}
		else{

			RequestDispatcher rd = request.getRequestDispatcher("index.html");
			out = response.getWriter();
			out.println("<h4 style='color:red;margin-left:30px;'>Username or password cannot be empty</h4>");
			rd.include(request, response);
		}
	}

}
