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
 * Servlet implementation class sign_up
 */
@WebServlet("/todo_delete")
public class ToDo_Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ToDo_Delete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("AAA");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("UTF-8");
		ToDoBean bean = new ToDoBean();
		String Email = "";
		String CateName = "";
		String ProjectName = "";
		
		Email = request.getParameter("Email");
		CateName = request.getParameter("CateName");
		ProjectName = request.getParameter("ProjectName");
		bean.setToDoName(request.getParameter("ToDoName"));
		
		ToDoMgr mgr = new ToDoMgr();
		mgr.setParam(bean);
		mgr.setEmail(Email);
		mgr.setCateName(CateName);
		mgr.setProjectName(ProjectName);
		
		Gson gson = new Gson();
		PrintWriter out = response.getWriter();
		if(mgr.getDelete()){
			System.out.println("success");
			out.print(gson.toJson(true));
		}else{
			System.out.println("fail");
			out.print(gson.toJson(false));
		}
		
			
	}
}