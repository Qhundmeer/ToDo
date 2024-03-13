package controller;

import java.io.IOException;

import dao.TodoDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.TodoService;

@WebServlet("/complete")
public class CompleteTask extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		TodoService service=new TodoService();
		if(req.getSession().getAttribute("user")!=null) {
			
			service.getTaskById(req,resp);
			}
			else {
				resp.getWriter().print("<h1 align='center' style='color:red'> invalid session</h1>");
				req.getRequestDispatcher("login.html").include(req, resp);
			}
	}
}
