

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class WorkerPage
 */
@WebServlet("/WorkerPage")
public class WorkerPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WorkerPage() {
        super();
        // TODO Auto-generated constructor stub
    }


    public Connection getConnection()
  	{
  		Connection conn = null;
  		try
  		{
  			//URL of Oracle database server
  			String url = "jdbc:oracle:thin:system/password@localhost"; 
  			 try {
  					Class.forName("oracle.jdbc.driver.OracleDriver");
  				} catch (ClassNotFoundException e1) {
  					// TODO Auto-generated catch block
  					e1.printStackTrace();
  				}
  			//properties for creating connection to Oracle database
  			Properties props = new Properties();
  			props.setProperty("user", "testuserdb");
  			props.setProperty("password", "password");

  			//creating connection to Oracle database using JDBC
  			conn = DriverManager.getConnection(url,props);
  			
  		}
  		catch(SQLException e)
  		{
  			System.out.println(e.getMessage());
  			e.printStackTrace();
  			return null;
  		}
  		
  		return conn;
  	}
      
      
  	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  		// TODO Auto-generated method stub
  		// Set response content type
  	      response.setContentType("text/html");
  	      
  	      // Actual logic goes here.
  	      
  	    	//URL of Oracle database server
  	      
  	      String isStudent =request.getParameter("studentid");
  	      String isType = request.getParameter("asstype");
  	      if(isStudent!=null&&isType.equals("All"))
  	    	  {studentPage(request, response);}
  	      
  	      else if(isStudent.equals("") && !isType.equals("All"))
	    	  {typePage(request, response);}
  	      
  	      else if(isStudent!=null&&!isType.equals("All"))
  	    	  {studenttypePage(request, response);}
  	      
  	   
	
  	     
  	}

  	/**
  	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
  	 */
  	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  		doGet(request,response);
  		
  		// TODO Auto-generated method stub
  	}
  	
  	
  	public void studenttypePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
  	{
  		
  	//URL of Oracle database server
	        String url = "jdbc:oracle:thin:system/password@localhost"; 
	        try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        //properties for creating connection to Oracle database
	        Properties props = new Properties();
	        props.setProperty("user", "testuserdb");
	        props.setProperty("password", "password");
	      
	        //creating connection to Oracle database using JDBC
	        Connection conn = null;
			try {
				conn = DriverManager.getConnection(url,props);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			PrintWriter out = response.getWriter();
			ResultSet result= null;
	      	String ass = null,asstype =null;
	      	int grade=0;
	      	int sum=0, i=0, avg=0,id;
	      	String dat=null;
	    
				String sql2 ="select * from gradeinfo where studentid = '" + request.getParameter("studentid") +"' and asstype = '"+ request.getParameter("asstype")+"'";
	  	        //creating PreparedStatement object to execute query
	  	        PreparedStatement preStatement2 = null;
	  	      
	  	       
	  				try {
						preStatement2 = conn.prepareStatement(sql2);
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	  				
	  				try {
						result = preStatement2.executeQuery();
						
					
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.getMessage();
					}
	  				 
				 
	  			out.println("<!DOCTYPE html>");
	  			out.println("<html lang='en'>");
	  			out.println("<head>");
	  			out.println("<title>" + "Bloody Servlets" + "</title>");
	  			out.println(" <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css\" integrity=\"sha512-dTfge/zgoMYpP7QbHy4gWMEGsbsdZeCXz7irItjcC3sPUFtf0kuFbDz/ixG7ArTxmDjLXDmezHubeNikyKGVyQ==\" crossorigin=\"anonymous\">");
	  			out.println("<body>");
	  			
	  			out.println(" <div class=\"container\">");
	  			out.println("<h2>Database dog</h2>");
	  			out.println("<table class=\"table table-striped\">");
	  			out.println("<tr><th>Assignment</th><th>Date</th><th>Assignment Type</th><th>Grade</th><tr>");
	  			try {
				while(result.next())
				{
					ass = result.getString(1);
					id=result.getInt(2);
					asstype = result.getString(3);
					dat=result.getString(4);
					grade=result.getInt(5);
					i++;
					sum=sum+grade;
					out.println("<tr>"+"<td>"+ass+"</td>"+"<td>"+dat+"</td>"+"<td>"+asstype+"</td>"+"<td>"+grade+"</td>"+"<tr>");
					avg=(sum/i);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	  			out.println("</table>");
	  			out.println("<h4 style='text-align:left'>Average Grade: " + avg + "</h4>");
	  			out.println("<div class = 'container' align='center'>");  	  			  	  			
	  			out.println("<a href='FirstPage.html' class='btn btn-info' role='button'>Add More Grades</a>");
	  			out.println("<a href='SecondPage.html' class='btn btn-info' role='button'>Search again</a>");
	  			out.println("</div>");
	  			out.println("</div>");
	  			out.println("</div>");
	  			out.println("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js\"></script>");
	  			out.println("<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js\" integrity=\"sha512-K1qjQ+NcF2TYO/eI3M6v8EiNYZfA95pQumfvcVrTHtwQVDG+aHRqLi/ETn2uB+1JqwYqVG3LIvdm9lj6imS/pQ==\" crossorigin=\"anonymous\"></script>");
	  			out.println("</body>");
	  			out.println("</html>");	
  	}
  	
  	
  	public void typePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
  	{
  	//URL of Oracle database server
	        String url = "jdbc:oracle:thin:system/password@localhost"; 
	        try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        //properties for creating connection to Oracle database
	        Properties props = new Properties();
	        props.setProperty("user", "testuserdb");
	        props.setProperty("password", "password");
	      
	        //creating connection to Oracle database using JDBC
	        Connection conn = null;
			try {
				conn = DriverManager.getConnection(url,props);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			PrintWriter out = response.getWriter();
			ResultSet result= null;
	      	String ass = null,asstype =null;
	      	int grade=0;
	      	int sum=0, i=0, avg=0,id;
	      	String dat=null;
	      	String studid = null;
	      
				String sql2 ="select * from gradeinfo where asstype = '" + request.getParameter("asstype")+"'";
	  	        //creating PreparedStatement object to execute query
	  	        PreparedStatement preStatement2 = null;
	  	      
	  	       
	  				try {
						preStatement2 = conn.prepareStatement(sql2);
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	  				
	  				try {
						result = preStatement2.executeQuery();
						
					
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.getMessage();
					}
	  				 
				 
	  			out.println("<!DOCTYPE html>");
	  			out.println("<html lang='en'>");
	  			out.println("<head>");
	  			out.println("<title>" + "Bloody Servlets" + "</title>");
	  			out.println(" <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css\" integrity=\"sha512-dTfge/zgoMYpP7QbHy4gWMEGsbsdZeCXz7irItjcC3sPUFtf0kuFbDz/ixG7ArTxmDjLXDmezHubeNikyKGVyQ==\" crossorigin=\"anonymous\">");
	  			out.println("<body>");
	  			
	  			out.println(" <div class=\"container\">");
	  			//out.println("<h2>Database Updated</h2>");
	  			out.println("<table class=\"table table-striped\">");
	  			//out.println("<tr><th>Student ID</th><th>Assignment</th><th>Date</th><th>Assignment Type</th><th>Grade</th><tr>");
	  			try {
				while(result.next())
				{
					ass = result.getString(1);
					studid = result.getString(2);
					asstype = result.getString(3);
					dat=result.getString(4);
					grade=result.getInt(5);
					i++;
					sum=sum+grade;
					out.println("<tr>"+"<td>"+studid+"</td>"+"<td>"+ass+"</td>"+"<td>"+dat+"</td>"+"<td>"+asstype+"</td>"+"<td>"+grade+"</td>"+"<tr>");
					avg=(sum/i);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	  			out.println("</table>");
	  			out.println("<h4 style='text-align:left'>Average Grade: " + avg + "</h4>");
	  			out.println("<div class = 'container' align='center'>");  	  			  	  			
	  			out.println("<a href='FirstPage.html' class='btn btn-info' role='button'>Add More Grades</a>");
	  			out.println("<a href='SecondPage.html' class='btn btn-info' role='button'>Search again</a>");
	  			out.println("</div>");
	  			out.println("</div>");
	  			out.println("</div>");
	  			out.println("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js\"></script>");
	  			out.println("<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js\" integrity=\"sha512-K1qjQ+NcF2TYO/eI3M6v8EiNYZfA95pQumfvcVrTHtwQVDG+aHRqLi/ETn2uB+1JqwYqVG3LIvdm9lj6imS/pQ==\" crossorigin=\"anonymous\"></script>");
	  			out.println("</body>");
	  			out.println("</html>");	
	    	  
  	}
  	public void studentPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  	  String url = "jdbc:oracle:thin:system/password@localhost"; 
        try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        //properties for creating connection to Oracle database
        Properties props = new Properties();
        props.setProperty("user", "testuserdb");
        props.setProperty("password", "password");
      
        //creating connection to Oracle database using JDBC
        Connection conn = null;
		try {
			conn = DriverManager.getConnection(url,props);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PrintWriter out = response.getWriter();
		ResultSet result= null;
      	String ass = null,asstype =null;
      	int grade=0;
      	int sum=0, i=0, avg=0,id;
      	String dat=null;
      	
      
			String sql2 ="select * from gradeinfo where studentid = '" + request.getParameter("studentid") + "'";
  	        //creating PreparedStatement object to execute query
  	        PreparedStatement preStatement2 = null;
  	      
  	       
  				try {
					preStatement2 = conn.prepareStatement(sql2);
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
  				
  				try {
					result = preStatement2.executeQuery();
					
					
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.getMessage();
				}
  				 
			 
  			out.println("<!DOCTYPE html>");
  			out.println("<html lang='en'>");
  			out.println("<head>");
  			out.println("<title>" + "Bloody Servlets" + "</title>");
  			out.println(" <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css\" integrity=\"sha512-dTfge/zgoMYpP7QbHy4gWMEGsbsdZeCXz7irItjcC3sPUFtf0kuFbDz/ixG7ArTxmDjLXDmezHubeNikyKGVyQ==\" crossorigin=\"anonymous\">");
  			out.println("<body>");
  			
  			out.println(" <div class=\"container\">");
  			out.println("<h2>Student ID: " + request.getParameter("studentid") +"</h2>");
  			out.println("<table class=\"table table-striped\">");
  			out.println("<tr><th>Assignment</th><th>Date</th><th>Assignment Type</th><th>Grade</th><tr>");
  			try {
				while(result.next())
				{
					ass = result.getString(1);
					id=result.getInt(2);
					asstype = result.getString(3);
					dat=result.getString(4);
					grade=result.getInt(5);
					i++;
					sum=sum+grade;
					out.println("<tr>"+"<td>"+ass+"</td>"+"<td>"+dat+"</td>"+"<td>"+asstype+"</td>"+"<td>"+grade+"</td>"+"<tr>");
					avg=(sum/i);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
  			out.println("</table>");
  			out.println("<h4 style='text-align:left'>Average Grade: " + avg + "</h4>");
  			out.println("<div class = 'container' align='center'>");  	  			  	  			
  			out.println("<a href='FirstPage.html' class='btn btn-info' role='button'>Add More Grades</a>");
  			out.println("<a href='SecondPage.html' class='btn btn-info' role='button'>Search again</a>");
  			out.println("</div>");
  			out.println("</div>");
  			out.println("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js\"></script>");
  			out.println("<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js\" integrity=\"sha512-K1qjQ+NcF2TYO/eI3M6v8EiNYZfA95pQumfvcVrTHtwQVDG+aHRqLi/ETn2uB+1JqwYqVG3LIvdm9lj6imS/pQ==\" crossorigin=\"anonymous\"></script>");
  			out.println("</body>");
  			out.println("</html>");	
    	  
			
		
		
     
  		
  	}

}