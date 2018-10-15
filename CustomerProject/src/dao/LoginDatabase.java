package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Login;

public class LoginDatabase {

	public boolean validate(Login login)
	{
		try {
			ResultSet rs = DBConnectionManager.runSql("select username,cpassword from customer");
			while(rs.next())
			{
				System.out.println("rs validate");
				if(rs.getString(1).equals(login.getUsername()))
				{
					if(rs.getString(2).equals(login.getPassword()))
					{
						System.out.println("validated");
						return true;
					}
				}
			}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return false;
	}
}
