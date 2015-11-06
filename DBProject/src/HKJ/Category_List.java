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
 * Servlet implementation class category_list
 */
@WebServlet("/category_list")
public class Category_List extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Category_List() {
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
		String Email = "";
		Email = request.getParameter("Email");
		CategoryMgr mgr = new CategoryMgr();
		mgr.setEmail(Email);
		Vector<CategoryBean> result = new Vector<CategoryBean>();
		result = mgr.getList(); 
		Gson gson = new Gson();
		PrintWriter out = response.getWriter();
		
		if(result == null){
			System.out.println("fail");
			out.print(gson.toJson(null));	
			//out.print("{\"status\":\"OK\",\"num_results\":\"$total_record\",\"results\":"+gson.toJson(result));
		}
		else if(result.size() == 0){
			System.out.println("fail");
			out.print(gson.toJson(null));	
	    }else{
	    	System.out.println("succese");
	    	//System.out.println(gson.toJson(result));
	        //out.print(gson.toJson(result));
	    	
	    	/*response.setContentType("application/json");
	    	response.setHeader("Cache-Control", "no-cache");
	    	response.getWriter().print(gson.toJson(result));
	    	PrintWriter outa = response.getWriter();
	    	outa.print(result);*/
	    	out.print("{\"status\":\"OK\",\"num_results\":\"7\",\"results\":"+gson.toJson(result)+"}");
	    }
	         	
	}

}
