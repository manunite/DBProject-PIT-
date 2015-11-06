package HKJ;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class ToDoMgr {
	private DBConnectionMgr pool = null;
	private ToDoBean param;
	private String Email;
	private String ProjectName;
	private String CateName;
	private String newToDoName;
	private int UserId;
	private int ProjectId;
	private int CateId;
	
	
	public ToDoMgr(){
		try{
			pool = DBConnectionMgr.getInstance();
		}catch(Exception e){
			System.out.println("Error : Exception");
		}
	}
	
	public void setParam (ToDoBean val) {param = val;}
	public void setEmail(String val){Email = val;}
	public void setProjectName(String val){ProjectName = val;}
	public void setCateName(String val){CateName = val;}
	public void setNewToDoName(String val){newToDoName = val;}
	
	public Vector<ToDoBean> getList()
	{
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		Vector<ToDoBean> List = new Vector<ToDoBean>();
		 try {
	    	   conn = pool.getConnection();
	    	   String strQuery = "select UserId from usertable where Email = '" + Email + "'";
	    	   stmt = conn.createStatement();
	    	   rs = stmt.executeQuery(strQuery);
	    	   if(rs.next()){
		 	    	UserId = rs.getInt("UserId");
	    	   }else{
		        	pool.freeConnection(conn);
		      	  	return null;
		       }
	    	   strQuery = "select CateId from categorytable where CateName = '" + CateName + "' AND UserId = " + UserId;
	    	   stmt = conn.createStatement();
	    	   rs = stmt.executeQuery(strQuery);
	    	   if(rs.next())
		        {
	    		    CateId = rs.getInt("CateId");
		        }
		        else
		        {
		        	pool.freeConnection(conn);
		      	  	return null;
		       }
	    	   strQuery = "select ProjectId from projecttable where ProjectName = '" + ProjectName + "' AND UserId = " + UserId + " AND CateId = " + CateId;
	    	   stmt = conn.createStatement();
	    	   rs = stmt.executeQuery(strQuery);
	    	   if(rs.next())
		        {
	    		   ProjectId = rs.getInt("ProjectId");
		        }
		        else
		        {
		        	pool.freeConnection(conn);
		      	  	return null;
		       }
	    	
	    	   strQuery = "select * from todotable where UserId = " + UserId + " AND CateId = " + CateId + " AND ProjectId = " + ProjectId;
	    	   stmt = conn.createStatement();
	    	   rs = stmt.executeQuery(strQuery);
	    	   
	    	   while(rs.next())
	    	   {
	    		   ToDoBean bean = new ToDoBean();
	    		   bean.setToDoEndDate(rs.getString("ToDoEndDate"));
	    		   bean.setToDoName(rs.getString("ToDoName"));
	    		   bean.setToDoStartDate(rs.getString("ToDoStartDate"));
	    		   bean.setState(rs.getInt("state"));
	    		   List.add(bean);
	    	   }
	    	   
	       } catch (Exception ex) {
	          System.out.println("Exception" + ex);
	       } finally {
		      pool.freeConnection(conn);
	       }
		 return List;
		   
	}
	
	public boolean getCreate()
	{
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		boolean flag = false;
		try {
			  conn = pool.getConnection();
	   	      String strQuery = "select UserId from usertable where Email = '" + Email + "'";
	   	      stmt = conn.createStatement();
	   	      rs = stmt.executeQuery(strQuery);
	   	      if(rs.next())
	          {
	   	    	  UserId = rs.getInt("UserId");
	          }
	          else
	          {
	        	  pool.freeConnection(conn);
	        	  return false;
	          }
	   	      
	   	      strQuery = "select CateId from categorytable where CateName = '" + CateName + "' AND UserId = " + UserId;
	   	      stmt = conn.createStatement();
	   	      rs = stmt.executeQuery(strQuery);
	   	      
	   	      if(rs.next())
	          {
	   	    	  CateId = rs.getInt("CateId");
	          }
	          else
	          {
	        	  pool.freeConnection(conn);
	        	  return false;
	          }
	   	      
	   	      strQuery = "select ProjectId from projecttable where ProjectName = '" + ProjectName + "' AND CateId = " + CateId + " AND UserId = " + UserId;
	   	      stmt = conn.createStatement();
	   	      rs = stmt.executeQuery(strQuery);
	   	      
	   	      if(rs.next())
	          {
	   	    	  ProjectId = rs.getInt("ProjectId");
	          }
	          else
	          {
	        	  pool.freeConnection(conn);
	        	  return false;
	          }
	   	      
	   	      strQuery = "Insert into todotable (UserId,CateId,ProjectId,ToDoName,state,ToDoStartDate,ToDoEndDate) values" + "(" 
	   	      + UserId + ", "
	   	      + CateId + ", "
	   	      + ProjectId + ", '"
	   	      + param.getToDoName() + "', " 
	   	      + param.getState() + ", '" 
	   	      + param.getToDoStartDate() + "', '" 
	   	      + param.getToDoEndDate() +"')";
	   	      System.out.println(strQuery);
	   	      stmt = conn.createStatement();
	   	      stmt.executeUpdate(strQuery);
	   	      flag = true;
	   	      
	    } catch (Exception ex) {
	      flag = false;
	      System.out.println("Exception" + ex);
	    } finally {
		   pool.freeConnection(conn);
	    }
		
		return flag;
	}
	
	public boolean getDelete()
	{
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		boolean flag = false;
		try {
	    	  conn = pool.getConnection();
	   	      String strQuery = "select UserId from usertable where Email = '" + Email + "'";
	   	      stmt = conn.createStatement();
	   	      rs = stmt.executeQuery(strQuery);
	   	      if(rs.next())
	          {
	        	  UserId = rs.getInt("UserId");
	          }
	          else
	          {
	        	  pool.freeConnection(conn);
	        	  return false;
	          }
	   	      strQuery = "select CateId from categorytable where CateName = '" + CateName + "' AND UserId = " + UserId;
	   	      stmt = conn.createStatement();
	   	      rs = stmt.executeQuery(strQuery);
	   	      if(rs.next())
	          {
	        	  CateId = rs.getInt("CateId");
	          }
	          else
	          {
	        	  pool.freeConnection(conn);
	        	  return false;
	          }
	   	      
	   	      strQuery = "select ToDoName from todotable where ToDoName = '" + param.getToDoName() + "'";
	   	      stmt = conn.createStatement();
	   	      rs = stmt.executeQuery(strQuery);
	   	      if(rs.next())
	          {
	   	    	  
	          }
	          else
	          {
	        	  pool.freeConnection(conn);
	        	  return false;
	          }
	   	      
	   	      strQuery = "select ProjectId from projecttable where ProjectName = '" + ProjectName + "' AND CateId = " + CateId + " AND UserId = " + UserId;
	   	      stmt = conn.createStatement();
	   	      rs = stmt.executeQuery(strQuery);
	   	      if(rs.next())
	          {
	   	    	ProjectId = rs.getInt("ProjectId");
	          }
	          else
	          {
	        	  pool.freeConnection(conn);
	        	  return false;
	          }
	   	      
	   	      strQuery = "Delete from todotable where UserId = " + UserId  + " AND CateId = " + CateId + " AND ProjectId = " + ProjectId + " AND ToDoName = '" + param.getToDoName() + "'";
	    	  stmt = conn.createStatement();
	    	  stmt.executeUpdate(strQuery);
	    	  flag = true;
	    	  
	    } catch (Exception ex) {
	      flag = false;
	      System.out.println("Exception" + ex);
	    } finally {
		   pool.freeConnection(conn);
	    }
		
		return flag;
	}
	
	public boolean getChange()
	{
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		boolean flag = false;
		try {
			  conn = pool.getConnection();
	   	      String strQuery = "select UserId from usertable where Email = '" + Email + "'";
	   	      stmt = conn.createStatement();
	   	      rs = stmt.executeQuery(strQuery);
	   	      if(rs.next())
	          {
	   	    	  UserId = rs.getInt("UserId");
	          }
	          else
	          {
	        	  pool.freeConnection(conn);
	        	  return false;
	          }
	   	      
	   	      strQuery = "select CateId from categorytable where CateName = '" + CateName + "' AND UserId = " + UserId;
	   	      stmt = conn.createStatement();
	   	      rs = stmt.executeQuery(strQuery);
	   	      if(rs.next())
	          {
	   	    	  CateId = rs.getInt("CateId");
	          }
	          else
	          {
	        	  pool.freeConnection(conn);
	        	  return false;
	          }
	   	      strQuery = "select ProjectId from projecttable where UserId = " + UserId + " AND CateId = " + CateId + " AND ProjectName = '" + ProjectName + "'";
	   	      stmt = conn.createStatement();
	   	      rs = stmt.executeQuery(strQuery);
	   	      if(rs.next())
	          {
	   	    	  ProjectId = rs.getInt("ProjectId");
	          }
	          else
	          {
	        	  pool.freeConnection(conn);
	        	  return false;
	          }
	   	      
	   	      strQuery = "Update todotable Set " 
	   	      + "state = " + param.getState() + ", "
	   	      + "ToDoStartDate = '" + param.getToDoStartDate() + "', "
	   	      + "ToDoEndDate = '" + param.getToDoEndDate() + "', "
	   	      + "ToDoName = '" + newToDoName + "' "
	   	      + "where UserId = " + UserId + " AND CateId = " + CateId + " AND ProjectId = " + ProjectId + " AND ToDoName = '" + param.getToDoName() + "'";
	   	      System.out.println(strQuery);
	   	      stmt = conn.createStatement();
	    	  stmt.executeUpdate(strQuery);
	    	  flag = true;
	   	      
	    } catch (Exception ex) {
	      flag = false;
	      System.out.println("Exception" + ex);
	    } finally {
		   pool.freeConnection(conn);
	    }
		return flag;
	}
	
	public ToDoBean getItem()
	{
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		ToDoBean beans = new ToDoBean();
		try {
			  conn = pool.getConnection();
	   	      String strQuery = "select UserId from usertable where Email = '" + Email + "'";
	   	      stmt = conn.createStatement();
	   	      rs = stmt.executeQuery(strQuery);
	   	      if(rs.next())
	        	  UserId = rs.getInt("UserId");
	          else{
	        	  pool.freeConnection(conn);
	        	  return null;
	          }
	   	      strQuery = "select CateId from categorytable where CateName = '" + CateName + "' AND UserId = " + UserId;
	   	      stmt = conn.createStatement();
	   	      rs = stmt.executeQuery(strQuery);
	   	      if(rs.next())
	        	  CateId = rs.getInt("CateId");
	          else{
	        	  pool.freeConnection(conn);
	        	  return null;
	          }
	   	      strQuery = "select ProjectId from projecttable where ProjectName = '" + ProjectName + "' AND CateId = " + CateId + " AND UserId = " + UserId;
	   	      stmt = conn.createStatement();
	   	      rs = stmt.executeQuery(strQuery);
	   	      if(rs.next())
	        	  ProjectId = rs.getInt("ProjectId");
	          else{
	        	  pool.freeConnection(conn);
	        	  return null;
	          }
	   	      //
	   	      strQuery = "select ToDoName from todotable where ToDoName = '" + param.getToDoName() + "'";
	   	      stmt = conn.createStatement();
	   	      rs = stmt.executeQuery(strQuery);
	   	      if(rs.next())
	   	      {
	   	      }
	          else{
	        	  pool.freeConnection(conn);
	        	  return null;
	          }
	   	      //
	   	      strQuery = "select * from todotable where UserId = " + UserId + " AND CateID = " + CateId + " AND ProjectId = " + ProjectId + " AND ToDoName = '" + param.getToDoName() + "'";
	   	      stmt = conn.createStatement();
	    	  rs = stmt.executeQuery(strQuery);
	    	  if(rs.next()){
	    		  beans.setState(rs.getInt("state"));
	    		  beans.setToDoEndDate(rs.getString("ToDoEndDate"));
	    		  beans.setToDoStartDate(rs.getString("ToDoStartDate"));
	    		  beans.setToDoName(rs.getString("ToDoName"));
	    	  }
	    } catch (Exception ex) {
	      System.out.println("Exception" + ex);
	    } finally {
		   pool.freeConnection(conn);
	    }
		return beans;
	}
	
	
}