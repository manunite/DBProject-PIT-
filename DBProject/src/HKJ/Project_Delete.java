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
 * Servlet implementation class delete_project
 */
@WebServlet("/project_delete")
public class Project_Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Project_Delete() {
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
		String Email = "", CateName = "";
		ProjectBean bean = new ProjectBean();
		Email = request.getParameter("Email");
		CateName = request.getParameter("CateName");
		bean.setProjectName(request.getParameter("ProjectName"));
		
		ProjectMgr mgr = new ProjectMgr();
		mgr.setEmail(Email);
		mgr.setCateName(CateName);
		mgr.setParam(bean);
		
		Gson gson = new Gson();
		PrintWriter out = response.getWriter();
		if(mgr.getDelete()){
	         System.out.println("succese");
	         out.print(gson.toJson(true));
	    }else
	    {
	    	System.out.println("fail");
	         out.print(gson.toJson(false));	
	    }
	}

}

