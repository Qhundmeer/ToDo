package controller;

import java.io.IOException;

import dao.TodoDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.TodoService;
@WebServlet("/loginDetail")
public class LoginService extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		TodoService service=new TodoService();
		service.check(req,resp);
	}
}
