import com.google.gson.*;
import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
/**
 * Servlet implementation class GetUserServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/GetStates" })
public class GetStates extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection con;
	private Statement stm;
	private List<DataList> dl;
	private String json;
	private ResultSet rs;
	private int id;
	protected String GetState() throws SQLException
	{
		dl = null;
		json = null;
		stm = con.createStatement();
		rs = stm.executeQuery("select stateid, statename from states");
		dl = new ArrayList<DataList>();
		while(rs.next())
		{
			dl.add(new DataList(rs.getString(1), rs.getString(2)));
		}
		json = new Gson().toJson(dl);
		return json;
	}
	protected String GetCity() throws SQLException
	{
		dl = null;
		json = null;
		stm = con.createStatement();
		rs = stm.executeQuery("select districtid, districtName from districts where stateid = "+id);
		dl = new ArrayList<DataList>();
		while(rs.next())
		{
			dl.add(new DataList(rs.getString(1), rs.getString(2)));
		}
		json = new Gson().toJson(dl);
		return json;
	}
 	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 		try{
 			Class.forName("oracle.jdbc.OracleDriver");
 			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "payroll", "2611798");
 			response.setContentType("text/html");
 			response.setCharacterEncoding("UTF-8");
 			if(request.getParameter("get_param").equals("New"))
 			{
 				response.getWriter().write(GetState());
 			}
 			else
 			{
 				id = Integer.parseInt(request.getParameter("get_param"));
 				response.getWriter().write(GetCity());
 			}
 			
		}
 		catch(ClassNotFoundException CNFE)
 		{
 			response.getWriter().write("CNF Error:Try After SOmeTime");
 		}
 		catch(SQLException SE)
 		{
 			response.getWriter().write(SE.getMessage()); 			
 		}
	}
}
 		

class DataList
{
	private String id;
	private String Name;
	public DataList(String id, String Name)
	{
		this.id = id;
		this.Name = Name;
	}
	public String toString() {
	    return "id : " +id+ " stateName : " +Name;
//	    return "{ \"id\" : \"" +id+ "\", \"stateName\" : \"" +StateName+"\" }";
	}
}

/* 		String country = request.getParameter("countryID").trim();
	int val = Integer.parseInt(country);
if(val	==	-1){
	country = "Invalid Select Country";
}
List<stateList> sl = new ArrayList<stateList>();
sl.add(new stateList("1","India"));
sl.add(new stateList("2","pak"));
sl.add(new stateList("3","sti"));
String js = null;
js = new Gson().toJson(sl);
response.setContentType("application/json");
response.setCharacterEncoding("UTF-8");
response.getWriter().write(js);*/
