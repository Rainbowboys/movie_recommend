package algorithm;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Utils;

/**
 * Servlet implementation class RunALS
 */
@WebServlet("/RunALS")
public class RunALS extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RunALS() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		<input> <output> <train_percent> <ranks> <lambda> <iteration>
		String input = request.getParameter("input");
		String output = Utils.output;
		String train_percent = request.getParameter("train_percent");
		String ranks = request.getParameter("ranks");
		String lambda = request .getParameter("lambda");
		String iteration = request.getParameter("iteration");
		
		boolean flag =true;
		try{
			// 启动线程运行调度任务
			new Thread(new RunSpark(input, output, train_percent, ranks, lambda, iteration)).start();
		}catch(Exception e){
			flag= false;
			e.printStackTrace();
		}
		
		
		// 打印输出
		PrintWriter out = response.getWriter();
		out.write(flag?"true":"false");
		out.flush();
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
