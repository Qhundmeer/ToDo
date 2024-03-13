package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.TodoService;
@WebServlet("/addTask")
public class AddTaskServlet extends HttpServlet{
	TodoService service=new TodoService();
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getSession().getAttribute("user")!=null) {
		
		service.addtask(req,resp);
		}
		else {
			resp.getWriter().print("<h1 align='center' style='color:red'> invalid session</h1>");
			req.getRequestDispatcher("login.html").include(req, resp);
		}
	}
}
