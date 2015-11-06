package HKJ;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class loginMgr {
	private DBConnectionMgr pool = null;
	private UserBean param;
	
	public loginMgr(){
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
	    	   String strQuery = "select * from userTable where Email = '" 
	    	   + param.getEmail() + "' AND "
	    	   + "PassWord = " + "'"
	    	   + param.getPassWord()
	    	   + "'";
	    	   
	    	   System.out.println(strQuery);
	    	   
	    	   stmt = conn.createStatement();
	    	   rs = stmt.executeQuery(strQuery);
			 
	    	   if(!rs.next()){}//아무것도 안하고 지나감
	    	   
	    	   else if(param.getEmail().equals(rs.getString("Email")))
	    	   {
	    		   if(param.getPassWord().equals(rs.getString("PassWord")))
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
	
	////////////////////////////////////////////////////////
	public Vector<UserBean>  getList() {
		   Connection conn = null;
		   Statement stmt = null;
		   ResultSet rs = null;
		   boolean flag = false;
		   Vector<UserBean> List = new Vector<UserBean>();
		   try {
	    	   conn = pool.getConnection();
	    	   String strQuery = "select * from userTable";
	    	   
	    	   System.out.println(strQuery);
	    	   
	    	   stmt = conn.createStatement();
	    	   rs = stmt.executeQuery(strQuery);
	    	   
	    	   while(rs.next())
	    	   {
	    		   UserBean bean = new UserBean();
	    		   bean.setUserName(rs.getString("UserName"));
	    		   bean.setEmail(rs.getString("Email"));
	    		   bean.setPassWord(rs.getString("PassWord"));
	    		   List.add(bean);
	    	   }
	    	   
	       } catch (Exception ex) {
	          System.out.println("Exception" + ex);
	       } finally {
		      pool.freeConnection(conn);
	       }
	       return List;
	}
	////////////////////////////////////////////////////////
}
