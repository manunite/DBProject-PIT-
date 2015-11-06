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
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			doPost(request, response);
		}
		catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		UserBean bean = new UserBean();
		//Data삽입
		bean.setEmail(request.getParameter("Email"));
		bean.setPassWord(request.getParameter("PassWord"));
		//
		Gson gson = new Gson();
		loginMgr mgr = new loginMgr();
		mgr.setParam(bean);
		
		PrintWriter out = response.getWriter();
		
		if(mgr.getResult()){
			System.out.println("success");
			out.print(gson.toJson(true));
			//out.print("success");
		}else{
			System.out.println("false");
			out.print(gson.toJson(false));
			//out.print("false");
		}
	}
}
