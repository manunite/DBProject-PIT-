package HKJ;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class CategoryMgr {
	private DBConnectionMgr pool = null;
	private CategoryBean param;
	private String Email;
	private String oldCateName;
	private String newCateName;
	private int UserId;
	
	public CategoryMgr(){
		try{
			pool = DBConnectionMgr.getInstance();
		}catch(Exception e){
			System.out.println("Error : Exception");
		}
	}
	
	public void setParam (CategoryBean val) {param = val;}
	public void setEmail (String val){Email = val;}
	public void setNewCateName(String val) {newCateName = val;}
	public void setOldCateName(String val) {oldCateName = val;}
	
	public Vector<CategoryBean> getList() {
		   Connection conn = null;
		   Statement stmt = null;
		   ResultSet rs = null;
		   Vector<CategoryBean> vecList = new Vector<CategoryBean>();
		   try {
	    	   conn = pool.getConnection();
	    	   //1.현재 사용자의 Email에 해당하는 UserId를 찾음
	    	   String strQuery = "select UserId from UserTable where Email = '" 
	    	   + Email + "'";
	    	   //System.out.println(strQuery);
	    	   stmt = conn.createStatement();
	    	   rs = stmt.executeQuery(strQuery);
			 
	    	   if(rs.next())
	    	   {
	    		   UserId = rs.getInt("UserId");
	    	   }
	    	   else // 존재하지않을시 (있을리가 없겠지만)
	    	   {
	    		   pool.freeConnection(conn);
	    		   return null;
	    	   }
	    	   
	    	   //2. 찾은 UserId로 해당 유저의 Category List들을 불러옴
	    	   strQuery = "select * from CategoryTable where UserId = "
	    	   + UserId + "";
	    	   stmt = conn.createStatement();
	    	   rs = stmt.executeQuery(strQuery);
	    	   
	    	   while(rs.next())
	    	   {//4개의 데이터를 모두 삽입
	    		   CategoryBean bean = new CategoryBean();
	    		   bean.setCateId(rs.getInt("CateId"));
	    		   bean.setCateName(rs.getString("CateName"));
	    		   bean.setCateupdateTime(rs.getString("CateupdateTime"));
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
	
	public CategoryBean getItem(){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		CategoryBean bean = new CategoryBean(); //<- 확인한 Category의 정보를 받아올 Bean
		try{
			conn = pool.getConnection();
			String strQuery = "select UserId from UserTable where Email = '" + Email + "'";
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
			
			//확인된 UserId중에서 원하는 CateName에 속하는 카테고리의 전부를 가져온다.
			strQuery = "select * from CategoryTable where UserId = " + UserId + " AND CateName = '" + param.getCateName() + "'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(strQuery);
			
			if(!rs.next()) bean = null; //만일 아무것도 확인된것이 없으면 Bean에 Null반
			else//rs에서 얻어온 정보들을 bean에 삽입
			{
				bean.setCateId(rs.getInt("CateId"));
				bean.setCateName(rs.getString("CateName"));
				bean.setUserId(rs.getInt("UserId"));
				bean.setCateupdateTime(rs.getString("CateupdateTime"));
			}
			
		} catch(Exception ex){
			System.out.println("Exception" + ex);
		} finally {
			pool.freeConnection(conn);
		}
		return bean;
	}
	
	public boolean getCreate()
	{
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		boolean flag = false;
		
		try{
			conn = pool.getConnection();
			String strQuery = "select UserId from UserTable where Email = '" + Email + "'";
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
			
			String time = CurTime.getTime(); // 현재시간을 String형으로 생성
			strQuery = "insert into CategoryTable (UserId,CateName,CateUpdateTime) values (" + UserId + ", "
			+ "'" + param.getCateName() + "', "
			+ "'" + time + "'"
			+ ")"; // 쿼리문 생성
			
			stmt = conn.createStatement();
		    stmt.executeUpdate(strQuery);
		    flag = true;
		} catch(Exception ex){
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
		
		try{
			conn = pool.getConnection();
			//1. 넘어온 Email값으로 UserId를 찾음.
			String strQuery = "select UserId from UserTable where Email = '" + Email + "'";
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
			//2. UserId로 넘어온 CateName을 찾음
			strQuery = "Select CateName from CategoryTable where CateName = '" + param.getCateName() + "' AND UserId = " + UserId + "";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(strQuery);
			if(rs.next()){} //정상적으로 해당 CateName이 있음을 확인
			else
			{
				pool.freeConnection(conn);
				return false;
			}
			//3. CateName이 있음을 확인했으니 삭제
			strQuery = "delete from CategoryTable where UserId = "
			+ UserId + " AND CateName = '"
			+  param.getCateName() + "'";
			stmt = conn.createStatement();
			stmt.executeUpdate(strQuery);
			flag = true;
			
		} catch(Exception ex){
			System.out.println("Exception" + ex);
		} finally{
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
		String time = CurTime.getTime(); // 현재시간을 String형으로 생성
		//String time = new CurTime().getTime();
		try{
			conn = pool.getConnection();
			//1.현재 유저의 Email로 UserId를 찾아냄
			String strQuery = "Select UserId from UserTable where Email = '" + Email + "'";
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
			param.setUserId(UserId);
			
			//2.찾은 UserId에서 해당하는 CateName을 찾는다.
			strQuery = "Select CateName from CategoryTable where UserId = " + UserId + " AND CateName = '"
			+ oldCateName + "'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(strQuery);
			if(rs.next()){} //찾으려는 CateName이 있음ㅇ르 확인
			else
			{
				pool.freeConnection(conn);
				return false;
			}
			
			//3.CateName을 수정(update시간 반영 안함)
			strQuery = "update CategoryTable set CateName = '"
			+ newCateName + "' , CateupdateTime = '"
			+ time + "'"
			+ " where UserId = "
			+ UserId + " AND CateName = '"
			+ param.getCateName() + "'";
			stmt = conn.createStatement();
			stmt.execute(strQuery);
			flag = true;
			
		} catch(Exception ex){
			System.out.println("Exception" + ex);
		} finally{
			pool.freeConnection(conn);
		}
		return flag;
	}
	
}