package HKJ;

public class UserBean {
	private int UserId;
	private String UserName;
	private String PassWord;
	private String Email;
	
	public int getUserId(){return UserId;}
	public String getUserName(){return UserName;}
	public String getEmail(){return Email;}
	public String getPassWord(){return PassWord;}
	
	public void setUserId(int val){UserId = val;}
	public void setUserName(String val){UserName = val;}
	public void setEmail(String val){Email = val;}
	public void setPassWord(String val){PassWord = val;}
}
