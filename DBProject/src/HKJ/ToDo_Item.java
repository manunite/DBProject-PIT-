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
@WebServlet("/todo_item")
public class ToDo_Item extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ToDo_Item() {
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
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("UTF-8");
		ToDoBean bean = new ToDoBean();
		ToDoBean resultBean = new ToDoBean();
		String Email = "",CateName = "",ProjectName = "";
		
		Email = request.getParameter("Email");
		CateName = request.getParameter("CateName");
		ProjectName = request.getParameter("ProjectName");
		bean.setToDoName(request.getParameter("ToDoName"));
		
		ToDoMgr mgr = new ToDoMgr();
		mgr.setParam(bean);
		mgr.setEmail(Email);
		mgr.setCateName(CateName);
		mgr.setProjectName(ProjectName);
		resultBean = mgr.getItem();
		
		Gson temp = new Gson();
		PrintWriter out = response.getWriter();
		if(resultBean != null)
		{
			System.out.println("success");
			//System.out.println(temp.toJson(resultBean));
			System.out.print("{\"status\":\"OK\",\"num_results\":\"0\",\"results\":"+temp.toJson(resultBean)+"}");
			//out.print(temp.toJson(true));
			out.print("{\"status\":\"OK\",\"num_results\":\"0\",\"results\":["+temp.toJson(resultBean)+"]}");
		}
		else
		{
			System.out.println("fail");
			out.print(temp.toJson(null));
		}
		
	}

}