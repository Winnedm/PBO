package uas;

import java.sql.*;                                   
public class koneksi
{	
	public static final String USERNAME = "winned";
	public static final String PASSWORD = "win123";
	public static final String URL = "jdbc:mckoi://localhost:9157//";
	public static final String DATABASE_DRIVER = "com.mckoi.JDBCDriver";	
	public static void main(String args[])
	{
		Connection con = null;
			try
				{
					//Driver DatabaseMySQL
					Class.forName(DATABASE_DRIVER);
					con = DriverManager.getConnection(URL,USERNAME,PASSWORD);
					Statement st = con.createStatement();
				}
			catch (ClassNotFoundException e) 
				{
					e.printStackTrace();
				}
			catch(SQLException e)
				{
					con =null;
				}
			if(con != null)
				{
					System.out.println("Koneksi Berhasil");
				}
			else
				{
					System.out.println("Koneksi TIdak Berhasil");
				}
	}
}

