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
@WebServlet("/todo_create")
public class ToDo_Create extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ToDo_Create() {
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
		boolean resultBool = false;
		String Email = "";
		String CateName = "";
		String ProjectName = "";
		
		try{
		Email = request.getParameter("Email");
		System.out.println(Email);
		CateName = request.getParameter("CateName");
		ProjectName = request.getParameter("ProjectName");
		bean.setToDoName(request.getParameter("ToDoName"));
		//System.out.println(request.getAttribute("ToDoName"));
		bean.setState(Integer.parseInt(request.getParameter("state")));
		bean.setToDoStartDate(request.getParameter("ToDoStartDate"));
		bean.setToDoEndDate(request.getParameter("ToDoEndDate"));
		//System.out.println(bean.getToDoEndDate());
		}catch(NumberFormatException e)
		{
			System.out.println("fails");
		}
		
		ToDoMgr mgr = new ToDoMgr();
		mgr.setParam(bean);
		mgr.setEmail(Email);
		mgr.setCateName(CateName);
		mgr.setProjectName(ProjectName);
		resultBool = mgr.getCreate();
		
		Gson temp = new Gson();
		PrintWriter out = response.getWriter();
		if(resultBool != false)
		{
			System.out.println("success");
			out.print(temp.toJson(resultBool));
		}
		else
		{
			System.out.println("fail");
			out.print(temp.toJson(null));
		}
		
	}

}