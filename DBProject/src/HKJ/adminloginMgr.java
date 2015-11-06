package HKJ;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class adminloginMgr {
	private DBConnectionMgr pool = null;
	private SuperUserBean param;
	
	public adminloginMgr(){
		try{
			pool = DBConnectionMgr.getInstance();
		}catch(Exception e){
			System.out.println("Error : Exception");
		}
	}
	
	public void setParam (SuperUserBean val) {param = val;}
	
	public boolean getResult() {
		   Connection conn = null;
		   Statement stmt = null;
		   ResultSet rs = null;
		   boolean flag = false;
		   
		   try {
	    	   conn = pool.getConnection();
	    	   String strQuery = "select * from SuperUserTable where S_Email = '" 
	    	   + param.getEmail() + "' AND "
	    	   + "S_PassWord = " + "'"
	    	   + param.getPassWord()
	    	   + "'";
	    	   
	    	   System.out.println(strQuery);
	    	   
	    	   stmt = conn.createStatement();
	    	   rs = stmt.executeQuery(strQuery);
			 
	    	   if(!rs.next()){}//아무것도 안하고 지나감
	    	   
	    	   else if(param.getEmail().equals(rs.getString("S_Email")))
	    	   {
	    		   if(param.getPassWord().equals(rs.getString("S_PassWord")))
	    		   {
	    			   //같은정보가 있음을 확인
	    			   flag = true;
	    		   }
	    	   }
	    	   
	       } catch (Exception ex) {
	          System.out.println("Exception" + ex);
	       } finally {
		      pool.freeConnection(conn);
	       }
	       return flag;
	}
}
