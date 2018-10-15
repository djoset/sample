package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CustomerDatabase;
import dao.DBConnectionManager;
import model.Customer;
import static utility.Constants.*;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class CustomerRegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name=request.getParameter(NAME);
		String username=request.getParameter(USERNAME);
		String passwrod=request.getParameter(PASSWORD);
		String email=request.getParameter(EMAIL);
		String phone=request.getParameter(PHONE);
		String gender=request.getParameter(GENDER);
		String city=request.getParameter(CITY);
		response.setContentType("text/html");
		if( (name != null && !name.trim().isEmpty())&& (username !=null && !username.trim().isEmpty() ) &&
				(!!email.isEmpty() || email != null) && (!phone.isEmpty() && phone != null) && 
				(!gender.isEmpty() || gender != "") && (!city.isEmpty() || city != null))
		{
			System.out.println("if not null");
			Customer c1 = new Customer(name,phone,email,city,gender,username,passwrod);
			CustomerDatabase database = new CustomerDatabase();		
			if(database.insert(c1))
			{				
				System.out.println("insert true");
				RequestDispatcher rd = request.getRequestDispatcher("index.html");
				PrintWriter out = response.getWriter();
				out.println("Successfully Registered. Please Login");
				rd.include(request, response);
			}
			else{
				System.out.println("insert false");
				RequestDispatcher rd = request.getRequestDispatcher("index.html");
				PrintWriter out = response.getWriter();
				out.println("Please contact administrator");
				rd.include(request, response);
			}
		}
		else{
			RequestDispatcher rd = request.getRequestDispatcher("customer.html");
			request.setAttribute("error","Please fill in all details");
			rd.include(request, response);
		}
	}

}
