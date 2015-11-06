package HKJ;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class ProjectMgr {
private DBConnectionMgr pool = null;
	
	private String Email;
	private String CateName;
	private int UserId;
	private int CateId;
	private ProjectBean param;
	private String newProjectName;
	private String oldProjectName;
	
	public ProjectMgr(){
		try{
			pool = DBConnectionMgr.getInstance();
		}catch(Exception e){
			System.out.println("Error : Exception");
		}
	}
	
	public void setParam (ProjectBean val) {param = val;}
	public void setEmail (String val) {Email = val;}
	public void setCateName(String val) {CateName = val;}
	public void setnewProjectName(String val) {newProjectName = val;}
	public void setoldProjectName(String val) {oldProjectName = val;}
	
	public Vector<ProjectBean> getList() {
		   Connection conn = null;
		   Statement stmt = null;
		   ResultSet rs = null;
		   Vector<ProjectBean> vecList = null;
		   
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
		      	  	return null;
		      }
	          strQuery = "select CateId from categorytable where UserId = '" + UserId + "' AND CateName = '" + CateName + "'";
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
	    	  strQuery = "select * from projecttable where UserId = " + UserId + " AND CateId = " + CateId;// AND ProjectName = '" + ProjectName +"'";;
	          stmt = conn.createStatement();
	          rs = stmt.executeQuery(strQuery);
	          vecList = new Vector<ProjectBean>();
			  while (rs.next()) {
	             ProjectBean bean = new ProjectBean();
	             bean.setCateId(rs.getInt("CateId"));
	             bean.setEndDate(rs.getDate("ProjectEndDate").toString());
	             //bean.setendMoney(rs.getInt("endMoney"));
	             //bean.setendMoneyDate(rs.getDate("endMoneydate").toString());
	             bean.setProjectBriefy(rs.getString("ProjectBriefy"));
	             bean.setProjectId(rs.getInt("ProjectId"));
	             bean.setProjectName(rs.getString("ProjectName"));
	             //bean.setProjectPercent(rs.getInt("ProjectPercent"));/////
	             bean.setProjectUpdateTime(rs.getString("ProjectUpdateTime"));
	             bean.setStartDate(rs.getDate("ProjectStartDate").toString());
	             //bean.setstartMoney(rs.getInt("startMoney"));
	             //bean.setstartMoneyDate(rs.getDate("startMoneyDate").toString());
	             bean.setUserId(rs.getInt("UserId"));
	             vecList.add(bean);
	          }
	       } catch (Exception ex) {
	          System.out.println("Exception" + ex);
	       } finally {
		      pool.freeConnection(conn);
	       }
	       return vecList;
	}
	
	public ProjectBean getItem() {
		   Connection conn = null;
		   Statement stmt = null;
		   ResultSet rs = null;
		   ProjectBean bean = null;	   
		   
	       try {
	    	  conn = pool.getConnection();
	    	  //1. �̸��Ͽ� �ش��ϴ� userID ����
	    	  String strQuery = "select UserId from usertable where Email = '" + Email + "'";
	          stmt = conn.createStatement();
	          rs = stmt.executeQuery(strQuery);
	          if(rs.next())
	        	  UserId = rs.getInt("UserId");
	          else{
	        	  pool.freeConnection(conn);
	        	  return null;
	          }
	          //2. ī�װ� ���̵� ����
	          strQuery = "select CateId from categorytable where UserId = " + UserId + " AND CateName = '" + CateName + "'";
	          stmt = conn.createStatement();
	          rs = stmt.executeQuery(strQuery);
	          if(rs.next())
	        	  CateId = rs.getInt("CateId");
	          else{
	        	  pool.freeConnection(conn);
	        	  return null;
	          }
	          //3. �ش� project ������
	    	  strQuery = "select * from projecttable where UserId = " + UserId + " AND CateId = " + CateId + " AND ProjectName = '" + param.getProjectName() +"'";
	          stmt = conn.createStatement();
	          rs = stmt.executeQuery(strQuery);
	          
	          if(rs.next()){
	        	bean = new ProjectBean();
	          	bean.setCateId(rs.getInt("CateId"));
		      	bean.setEndDate(rs.getDate("ProjectEndDate").toString());
		      	//bean.setendMoney(rs.getInt("endMoney"));
		      	//bean.setendMoneyDate(rs.getDate("endMoneydate").toString());
		      	bean.setProjectBriefy(rs.getString("ProjectBriefy"));
		      	bean.setProjectId(rs.getInt("ProjectId"));
		      	bean.setProjectName(rs.getString("ProjectName"));
		      	//bean.setProjectPercent(rs.getInt("ProjectPercent"));
		      	bean.setProjectUpdateTime(rs.getDate("ProjectUpdateTime").toString());
		      	bean.setStartDate(rs.getDate("ProjectStartDate").toString());
		      	//bean.setstartMoney(rs.getInt("startMoney"));
		      	//bean.setstartMoneyDate(rs.getDate("startMoneyDate").toString());
		      	bean.setUserId(rs.getInt("UserId"));
	          }
	       } catch (Exception ex) {
	          System.out.println("Exception" + ex);
	       } finally {
		      pool.freeConnection(conn);
	       }
	       return bean;
	}

	public boolean getCreate() {
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

		          strQuery = "select CateId from categorytable where UserId = " + UserId + " AND CateName = '" + CateName + "'";
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

		          String time = CurTime.getTime();
		    	  strQuery = "insert into projecttable (ProjectUpdateTime, CateId, UserId, ProjectName, ProjectBriefy, "
		    	  		  + "ProjectStartDate, ProjectEndDate) "
		    			  + "values ('"
		    	  		  + time + "', "
		    			  + CateId + ", "
		    			  + UserId + ", '"
		    			  + param.getProjectName() +"', '"
		    			  + param.getProjectBriefy() + "', '"
		    			  + param.getStartDate() + "', '"
		    			  + param.getEndDate() + "') ";
		    			  //+ param.getstartMoney() + ", "
		    			  //+ param.getendMoney() + ", '"
		    			  //+ param.getstartMoneyDate() + "', '"
		    			  //+ param.getendMoneyDate() + "')";
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
	
	public boolean getDelete() {
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
		          
		          strQuery = "select CateId from categorytable where UserId = " + UserId + " AND CateName = '" + CateName + "'";
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
		          
		          strQuery = "select ProjectName from projecttable where ProjectName = '" + param.getProjectName() + "'";
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
		          
		          try{
		    	  strQuery = "delete from projecttable where UserId = " + UserId + " AND CateId = " + CateId + " AND ProjectName = '" + param.getProjectName() +"'";
		          System.out.println(UserId + " " + CateId + " " + param.getProjectName());
		    	  stmt = conn.createStatement();
		          stmt.executeUpdate(strQuery);
		          }catch(Exception e)
		          {
		        	  return false;
		          }
		          
		          flag = true;
	       } catch (Exception ex) {
	          System.out.println("Exception" + ex);
	       } finally {
		      pool.freeConnection(conn);
	       }
	      return flag;
	}
	
	public boolean getChange() {
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

		          strQuery = "select CateId from categorytable where UserId = " + UserId + " AND CateName = '" + CateName + "'";
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
		          
		          strQuery = "select ProjectName from projecttable where ProjectName = '" + oldProjectName + "'";
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
		          
		          String time = CurTime.getTime();
		    	  strQuery = "update projecttable set "
		    	  + "ProjectUpdateTime = '" + time + "', "
			      + "ProjectEndDate = '" + param.getEndDate() + "', " 
			      //+ "endMoney = " + param.getendMoney() + ", "
			      //+ "endMoneyDate = '" + param.getendMoneyDate() + "', "
			      + "ProjectBriefy = '" + param.getProjectBriefy() + "', "
			      + "ProjectName = '" + newProjectName + "', "
			      //+ "ProjectPercent = " + param.getProjectPercent() + ", "
			      //+ "ProjectUpdateTime = '" + param.getProjectUpdateTime() +"'"
			      //CateId
			      //UserId
			      + "ProjectStartDate = '" + param.getStartDate() + "' "
			      //+ "startMoney = " + param.getstartMoney() + ", "
			      //+ "startMoneyDate = '" + param.getstartMoneyDate() + "' "
			      + "where UserId = " + UserId + " AND CateId = " + CateId + " AND ProjectName = '" + param.getProjectName() +"'";
		          //System.out.println(strQuery);
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
}
