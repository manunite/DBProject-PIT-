package HKJ;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class create_project
 */
@WebServlet("/project_create")
public class Project_Create extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Project_Create() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String Email = "", CateName = "";
		ProjectBean bean = new ProjectBean();
		
		request.setCharacterEncoding("utf-8");
		try{
			Email = request.getParameter("Email");
			CateName = request.getParameter("CateName");
			bean.setEndDate(request.getParameter("ProjectEndDate"));
			//bean.setendMoney(Integer.parseInt(request.getParameter("endMoney")));
			//bean.setendMoneyDate(request.getParameter("endMoneyDate"));
			bean.setProjectBriefy(request.getParameter("ProjectBriefy"));
			bean.setProjectName(request.getParameter("ProjectName"));
			bean.setStartDate(request.getParameter("ProjectStartDate"));
			//bean.setstartMoney(Integer.parseInt(request.getParameter("startMoney")));
			//bean.setstartMoneyDate(request.getParameter("startMoneyDate"));
		}catch(NumberFormatException e)
		{
			System.out.println("fail");
		}
		
		ProjectMgr mgr = new ProjectMgr();
		mgr.setEmail(Email);
		mgr.setCateName(CateName);
		mgr.setParam(bean);
		
		Gson gson = new Gson();
		PrintWriter out = response.getWriter();
		if(mgr.getCreate()){
	         System.out.println("succese");
	         out.print(gson.toJson(true));
	    }else{
	    	 System.out.println("fail");
	         out.print(gson.toJson(false));	
	    }
	}

}