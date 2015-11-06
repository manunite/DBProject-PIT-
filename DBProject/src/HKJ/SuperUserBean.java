package HKJ;

public class SuperUserBean {
	private int S_UserId;
	private String S_UserName;
	private String S_PassWord;
	private String S_Email;
	
	public int getUserId(){return S_UserId;}
	public String getUserName(){return S_UserName;}
	public String getEmail(){return S_Email;}
	public String getPassWord(){return S_PassWord;}
	
	public void setUserId(int val){S_UserId = val;}
	public void setUserName(String val){S_UserName = val;}
	public void setEmail(String val){S_Email = val;}
	public void setPassWord(String val){S_PassWord = val;}
}
