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
@WebServlet("/sign_up")
public class sign_up extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public sign_up() {
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
		Gson gson = new Gson();
		
		// 데이터 입력한것을 UserBean에 삽입
		bean.setEmail(request.getParameter("Email"));
		bean.setUserName(request.getParameter("UserName"));
		bean.setPassWord(request.getParameter("PassWord"));
		//
		sign_upMgr mgr = new sign_upMgr();
		mgr.setParam(bean);
		PrintWriter out = response.getWriter();
		
		if(mgr.getResult()){
			System.out.println("success");
			System.out.println(bean.getUserName());
			out.print(gson.toJson(true));//JSON형태로 다시 쏴줌
		}else{
			System.out.println("false");
			out.print(gson.toJson(false));
		}
	}
}
