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
 * Servlet implementation class project_list
 */
@WebServlet("/project_list")
public class Project_List extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Project_List() {
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
		Email = request.getParameter("Email");
		CateName = request.getParameter("CateName");
		
		ProjectMgr mgr = new ProjectMgr();
		mgr.setEmail(Email);
		mgr.setCateName(CateName);
		Vector<ProjectBean> result = mgr.getList(); 
		
		Gson gson = new Gson();
		PrintWriter out = response.getWriter();
		if(result != null){
			System.out.println("success");
			System.out.println(gson.toJson(result));
			out.print("{\"status\":\"OK\",\"num_results\":\"0\",\"results\":"+gson.toJson(result)+"}");
			//out.print(gson.toJson(result));
		}else
		{
			System.out.println("fail");
			out.print(gson.toJson(null));
		}	
	}

}