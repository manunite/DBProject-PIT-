package HKJ;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class project_item
 */
@WebServlet("/project_item")
public class Project_Item extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Project_Item() {
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
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("UTF-8");
		String Email = "", CateName = "";
		ProjectBean bean = new ProjectBean();
		
		Email = request.getParameter("Email");
		CateName = request.getParameter("CateName");
		bean.setProjectName(request.getParameter("ProjectName"));
		
		System.out.println(Email + " " + CateName + " " + request.getParameter("ProjectName"));
		
		ProjectMgr mgr = new ProjectMgr();
		mgr.setEmail(Email);
		mgr.setCateName(CateName);
		mgr.setParam(bean);
		bean = mgr.getItem(); 

		Gson gson = new Gson();
		PrintWriter out = response.getWriter();
		if(bean != null){
	         System.out.println("succese");
	         System.out.println(gson.toJson(bean));
	         //out.print(gson.toJson(bean));
	         out.print("{\"status\":\"OK\",\"num_results\":\"0\",\"results\":["+gson.toJson(bean)+"]}");
	    }else
	    {
	    	System.out.println("fail");
	        out.print(gson.toJson(null));	
	    }
	}

}
