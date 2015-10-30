

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Update
 */
@WebServlet("/UpdateInfo")
public class UpdateInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static String assign, gradename, assignmentname, assignmentgrade,date,asstype,id;
	static Connection conn;
	static int grade;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//PrintWriter out = response.getWriter();
		assign = request.getParameter("assign");
		gradename = request.getParameter("grade");
		//grade = Integer.parseInt(gradename);
		
		
		id = request.getParameter("id");
		
		date = request.getParameter("date");
		
		asstype = request.getParameter("asstype");
		updateTable(request,response);
		

		
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	public static void updateTable(HttpServletRequest request, HttpServletResponse response) throws IOException{
		try
		{
			 
			//URL of Oracle database server
	        String url = "jdbc:oracle:thin:system/password@localhost"; 
	        Class.forName("oracle.jdbc.driver.OracleDriver");
	      
	        //properties for creating connection to Oracle database
	        Properties props = new Properties();
	        props.setProperty("user", "testuserdb");
	        props.setProperty("password", "password");
	      
	        //creating connection to Oracle database using JDBC
	        Connection conn = DriverManager.getConnection(url,props);

	        String sql ="Insert into gradeinfo (assignment,studentid,asstype,dat,grade) values ('" + assign + "','" + id + "','" + asstype + "','" + date +"'," + gradename +")" ;
	       
			PrintWriter out = response.getWriter();
	      
	    	out.println("<div class = 'container' align='center'>");  	  			  	  			
  			out.println("<a href='FirstPage.html' class='btn btn-info' role='button'>Add More Grades</a>");
  			out.println("<a href='SecondPage.html' class='btn btn-info' role='button'>Search again</a>");
  			out.println("</div>");
	        //creating PreparedStatement object to execute query
	        PreparedStatement preStatement = conn.prepareStatement(sql);
	    
	        preStatement.executeUpdate();
	        //System.out.printf("%15s %15s", "First Name", "Last Name");
	        //System.out.println();
//	        while(result.next()){
//	        	
//	        	CustId = result.getString("CUSTOMER_ID");
//	        	name = result.getString("CUST_FIRST_NAME");
//	            Lname=result.getString("CUST_LAST_NAME");
////	            out.println(name);
////	            out.println(Lname);
//	            name = name + " " + Lname;
//	            out.println("<tr><td><a href=\"Details?CustId=" + CustId + "\">" + name + "</a></td></tr>");
//	            
//	        }
	        conn.close();
		}catch(SQLException | ClassNotFoundException e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	}


