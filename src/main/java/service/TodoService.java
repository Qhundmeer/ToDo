package service;

import java.io.IOException;
import java.util.List;

import dao.TodoDao;
import dto.TodoTask;
//import dto.TodoTask;
import dto.TodoUser;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class TodoService {
	TodoDao dao = new TodoDao();

	public void signUp(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		TodoUser user = new TodoUser();
		user.setName(req.getParameter("name"));
		user.setEmail(req.getParameter("email"));
		user.setMobile(Long.parseLong(req.getParameter("mobile")));
		user.setDob(req.getParameter("dob"));
		user.setGender(req.getParameter("gender"));
		user.setPassword(req.getParameter("password"));

		List<TodoUser> list = dao.findEmailById(user.getEmail());
		if (list.isEmpty()) {
			dao.saveUser(user);
			resp.getWriter().print("<h1>Account is created</h1>");
			req.getRequestDispatcher("login.html").include(req, resp);
			
			List<TodoTask> tasks = dao.fetchTaskByUser(user.getId());
			req.setAttribute("tasks", tasks);
		} else {
			resp.getWriter().print("<h1 align='center' style='color:red'>email should be uniqe</h1>");
			req.getRequestDispatcher("signup.html").include(req, resp);
		}

	}
	public void check(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		List<TodoUser> findEmailByid = dao.findEmailById(email);
		if (!findEmailByid.isEmpty()) {
			if (email.equals(findEmailByid.get(0).getEmail()) && password.equals(findEmailByid.get(0).getPassword())) {
				TodoUser user=findEmailByid.get(0);
				HttpSession session=req.getSession();
				session.setAttribute("user",user);
				session.setAttribute("msg", "Login success");
//				resp.getWriter().print("<h1>login success</h1>");
//				req.getRequestDispatcher("home.jsp").include(req, resp);
				resp.sendRedirect("home.jsp");
				
			}
		} else {
			resp.getWriter().print("<h1>login failed</h1>");
			req.getRequestDispatcher("login.html").include(req, resp);
		}
	}

	public void addtask(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		TodoTask task = new TodoTask();
		task.setTaskName(req.getParameter("taskName"));
		task.setTaskDescription(req.getParameter("description"));
//		task.setDate(LocalDate.now());
		task.setStatus(false);

		TodoUser user = (TodoUser) req.getSession().getAttribute("user");
		task.setTask(user);

		dao.saveTask(task);

		List<TodoTask> tasks = dao.fetchTaskByUser(user.getId());
		req.setAttribute("tasks", tasks);
		req.getRequestDispatcher("home.jsp").include(req, resp);
	}
	public void logOut(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		req.getSession().removeAttribute("user");
		resp.getWriter().print("<h1 align=cneter style='color:green' >logout success</h1>");
		req.getRequestDispatcher("login.html").include(req, resp);
	}
	public void getTaskById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id=Integer.parseInt(req.getParameter("id"));
		dao.completedTask(id);
		
		req.getRequestDispatcher("home.jsp").include(req, resp);
	}
	public void deleteTask(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id=Integer.parseInt(req.getParameter("id"));
		dao.deleteTaskById(id);
		req.getRequestDispatcher("home.jsp").include(req, resp);
	}
	public void editTask(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		dao.editTask(req.getParameter("id"),req.getParameter("taskName"),req.getParameter("description"));
		
		req.getRequestDispatcher("home.jsp").forward(req, resp);
	}

}
