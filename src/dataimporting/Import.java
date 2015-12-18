package dataimporting;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Import {
	public static void main(String args[])
	{
		DBHelper dbhelper=new DBHelper("jdbc:mysql://127.0.0.1/taxitracedots2014");
		String sql="select * from jyj_20140201000100_4054618_ch_db limit 1,100";
		ResultSet result=dbhelper.executeQuery(sql);
		
		try
		{
			while(result.next())
			{
				System.out.println(result.getString(1));
			}
			dbhelper.free(result);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}
}
