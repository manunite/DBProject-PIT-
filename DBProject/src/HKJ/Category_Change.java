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
 * Servlet implementation class change_category
 */
@WebServlet("/category_change")
public class Category_Change extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Category_Change() {
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
		CategoryBean bean = new CategoryBean();
		String Email = "", newCateName = "", oldCateName = "";
		bean.setCateName(request.getParameter("oldCateName"));
		Email = request.getParameter("Email");
		newCateName = request.getParameter("newCateName");
		oldCateName = request.getParameter("oldCateName");
		
		CategoryMgr mgr = new CategoryMgr();
		mgr.setEmail(Email);
		mgr.setNewCateName(newCateName);
		mgr.setOldCateName(oldCateName);
		mgr.setParam(bean);
		
		Gson gson = new Gson();
		PrintWriter out = response.getWriter();
		if(mgr.getChange()){
	         System.out.println("succese");
	         out.print(gson.toJson(true));
	    }else
	    {
	    	System.out.println("fail");
	         out.print(gson.toJson(false));		
	    }
		
	}

}
