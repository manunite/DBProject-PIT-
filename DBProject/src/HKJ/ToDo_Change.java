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
@WebServlet("/todo_change")
public class ToDo_Change extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ToDo_Change() {
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
		boolean resultBool  = false;
		String Email = "";
		String CateName = "";
		String ProjectName = "";
		String newToDoName = "";
		
		try{
		Email = request.getParameter("Email");
		System.out.println("A");
		CateName = request.getParameter("CateName");
		System.out.println("B");
		ProjectName = request.getParameter("ProjectName");
		System.out.println("C");
		newToDoName = request.getParameter("newToDoName");
		System.out.println("D");
		bean.setToDoName(request.getParameter("oldToDoName"));
		System.out.println("E");
		bean.setState(Integer.parseInt(request.getParameter("state")));
		System.out.println("F");
		bean.setToDoStartDate(request.getParameter("ToDoStartDate"));
		System.out.println("G");
		bean.setToDoEndDate(request.getParameter("ToDoEndDate"));
		System.out.println(request.getParameter("ToDoStartDate") + "," + request.getParameter("ToDoEndDate"));
		}catch(NumberFormatException e)
		{
			System.out.println("fail");
			return;
		}
		
		ToDoMgr mgr = new ToDoMgr();
		mgr.setParam(bean);
		mgr.setEmail(Email);
		mgr.setCateName(CateName);
		mgr.setProjectName(ProjectName);
		mgr.setNewToDoName(newToDoName);
		resultBool = mgr.getChange();
		
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
			out.print(temp.toJson(false));
		}	
	}

}