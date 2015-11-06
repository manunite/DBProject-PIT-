package HKJ;

public class CategoryBean {
	private int UserId;
	private int CateId;
	private String CateName;
	private String CateupdateTime;
	
	public int getUserId(){return UserId;}
	public int getCateId(){return CateId;}
	public String getCateName(){return CateName;}
	public String getCateupdateTime(){return CateupdateTime;}

	public void setUserId(int val){UserId = val;}
	public void setCateId(int val){CateId = val;}
	public void setCateName(String val){CateName = val;}
	public void setCateupdateTime(String val){CateupdateTime = val;}
}
