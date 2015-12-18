package dataimporting;

import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.PreparedStatement;  
import java.sql.SQLException; 
import java.sql.ResultSet; 

public class DBHelper {
	public String dbUrl;//="jdbc:mysql://127.0.0.1/taxitracedots2014";
	public static final String dbDriver="com.mysql.jdbc.Driver";
	public static final String username="root";
	public static final String password="";
	
	public DBHelper(String dbUrl)
	{
		this.dbUrl=dbUrl;
	}
	
	public Connection getConnection()
	{
		Connection conn=null;
		try
		{
			Class.forName(dbDriver);
			if(conn==null)
			{
				conn=DriverManager.getConnection(dbUrl, username, password);
			}			
		}
		catch(ClassNotFoundException e)//the driver can not be found
		{
			e.printStackTrace();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return conn;
	}
	
	//Add\Delete\Update
	public int executeNonQuery(String sql)
	{
		int result=0;
		Connection conn=null;
		PreparedStatement prestat=null;
		
		try{
			conn=getConnection();
			prestat=conn.prepareStatement(sql);
			result=prestat.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			free(null,prestat,conn);
		}
		return result;
	}
	
	public int executeNonQuery(String sql, Object[] obj)
	{
		int result=0;
		Connection conn=null;
		PreparedStatement prestat=null;
		
		try{
			conn=getConnection();
			prestat=conn.prepareStatement(sql);
			for(int i=0;i<obj.length;i++)
			{
				prestat.setObject(i+1, obj[i]);
			}
			result=prestat.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			free(null,prestat,conn);
		}
		return result;
	}
	
	public ResultSet executeQuery(String sql)
	{
		Connection conn=null;
		ResultSet result=null;
		PreparedStatement prestat=null;
		try
		{
			conn=getConnection();
			prestat=conn.prepareStatement(sql);
			result=prestat.executeQuery(sql);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			free(null,prestat,conn);
		}
		return result;
	}
	
	public ResultSet executeQuery(String sql, Object[] obj)
	{
		Connection conn=null;
		ResultSet result=null;
		PreparedStatement prestat=null;
		try
		{
			conn=getConnection();
			prestat=conn.prepareStatement(sql);
			for(int i=0;i<obj.length;i++)
			{
				prestat.setObject(i+1, obj[i]);
			}
			result=prestat.executeQuery(sql);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			free(null,prestat,conn);
		}
		return result;
	}
	
	public void free(Connection conn)
	{
		try
		{
			if(conn!=null)
			{
				conn.close();
			}				
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public void free(PreparedStatement prestat)
	{
		try
		{
			if(prestat!=null)
			{
				prestat.close();
			}				
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public void free(ResultSet result)
	{
		try
		{
			if(result!=null)
			{
				result.close();
			}				
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public void free(ResultSet result, PreparedStatement prestat, Connection conn)
	{
		free(result);
		free(prestat);
		free(conn);
	}
}
