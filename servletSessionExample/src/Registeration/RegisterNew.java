package Registeration;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;

/**
 * Servlet implementation class RegisterNew
 */
//@WebServlet("/Register")
public class RegisterNew extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection con;
	private String ErrorMessage;
	private String SuccessMessage;
	public void init(ServletConfig config) throws ServletException
	{
		try{
			String DBUser = config.getInitParameter("dbuser");
			String DBPass = config.getInitParameter("dbpass");
			ServletContext scon = config.getServletContext();
			String driver = scon.getInitParameter("driver");
			Class.forName(driver);
			con = DriverManager.getConnection(scon.getInitParameter("url"),DBUser,DBPass);
		}
		catch(ClassNotFoundException cnfe)
		{
			ErrorMessage = "Error in establishing connection \n"+cnfe.getMessage();			
		}
		catch(SQLException se)
		{
			ErrorMessage = "Error in establishing connection \n"+se.getMessage();
		}
	}
	
	protected int btnSubmitClick(HttpServletRequest req) throws SQLException
	{
		PreparedStatement pst = con.prepareStatement("insert into userdata (userid, uname, uemail, username, upassword)values(uids.nextval,?,?,?,?)");
		pst.setString(1, req.getParameter("uname"));
		pst.setString(2, req.getParameter("uemail"));
		pst.setString(3, req.getParameter("username"));
		pst.setString(4, req.getParameter("upass"));
		int i = pst.executeUpdate();
		return i;
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{	
		String uname = request.getParameter("username");
		if(con!=null)
		{
			try
			{
				int i = 0;
				i = btnSubmitClick(request);
				if(i==1)
				{
					response.getWriter().append("Succesfully added");
					HttpSession session = request.getSession(true);
					response.getWriter().println(session.getId());
					session.setAttribute("User", uname);
					RequestDispatcher rd = request.getRequestDispatcher("/user");
					rd.forward(request, response);
				}
				else
				{
					throw new SQLException();
				}
			}
			catch(SQLException se)
			{
				ErrorMessage = "Error in SQLException \n" + se.getMessage();
				response.getWriter().append(ErrorMessage);
			}
			catch(Exception e)
			{
				ErrorMessage = "Error in Exception" + e.getMessage();
				response.getWriter().append(ErrorMessage);				
			}
		}
		else
		{
			response.getWriter().append(ErrorMessage);
		}
	}

}
