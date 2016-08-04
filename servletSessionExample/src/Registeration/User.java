package Registeration;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class User
 */
//@WebServlet("/User")
public class User extends HttpServlet {
	private static final long serialVersionUID = 1L;
//	private HttpSession ses;
	private RequestDispatcher rd;
	public void init(ServletConfig config) throws ServletException {
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession ses = request.getSession();
		if(ses.getAttribute("User") != null)
		{
			response.getWriter().append("Hello " + ses.getAttribute("User"));
		}
		else
		{
			response.setContentType("text/html");
			response.getWriter().append("you need to login to access user area");
			response.setHeader("Refresh", "3; URL=/servletTutorial/Login/Login.html");
		}
	}
	
}
