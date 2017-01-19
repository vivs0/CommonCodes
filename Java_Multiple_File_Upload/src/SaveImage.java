package org.shopping.servlet.Admin;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.shopping.JavaCode.Connect;

@WebServlet("/SaveImage")
@MultipartConfig
public class SaveImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PreparedStatement statement = null;
	private Connection connection = null;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("check1");
		String SQL = "insert into Demo(img_name, photo) values (?, ?)";
		try {
			connection = Connect.Connect().getConnection();
			statement = connection.prepareStatement(SQL);

			for(Part part : request.getParts())
			{
				String names = part.getSubmittedFileName();
				InputStream stream = part.getInputStream();
				statement.setString(1, names);
				statement.setBlob(2, stream);
				statement.addBatch();
			}
			statement.executeBatch();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
}
