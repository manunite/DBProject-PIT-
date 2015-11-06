package HKJ;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class sign_upMgr {
	private DBConnectionMgr pool = null;
	private UserBean param;
	
	public sign_upMgr(){
		try{
			pool = DBConnectionMgr.getInstance();
		}catch(Exception e){
			System.out.println("Error : Exception");
		}
	}
	
	public void setParam (UserBean val) {param = val;}
	
	public boolean getResult() {
		   Connection conn = null;
		   Statement stmt = null;
		   ResultSet rs = null;
		   boolean flag = false;
	       try {
	    	  conn = pool.getConnection();
	    	  
	    	  String strQuery = "select * from usertable where Email = '"
	    	  + param.getEmail() + "'";
	    	  stmt = conn.createStatement();
	    	  rs = stmt.executeQuery(strQuery);
	    	  if(rs.next())
	    	  {
	    		 System.out.println("Alreay Exist");
	    		 flag = false;
	    		 return flag;
	    	  }
	    	  System.out.println("AAA");
	    	  //DB에 삽입할 쿼리문 생성
	    	  strQuery = "insert into usertable (UserName,Email,PassWord) "
	    			  + "VALUES('" + param.getUserName()
	    			  + "', '" + param.getEmail()
	    			  + "', '" + param.getPassWord()
	    			  + "')";
	    	  //
	    	  System.out.println(strQuery);
	          stmt = conn.createStatement();
	          stmt.executeUpdate(strQuery);
	          flag = true;
	       } catch (Exception ex) {
	          System.out.println("Exception" + ex);
	       } finally {
		      pool.freeConnection(conn);
	       }
	       return flag;
	}
}
