package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Console;
import services.ConsoleService;

public class ListConsole extends HttpServlet {

	private ConsoleService service= new  ConsoleService();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Console> listAllConsoles = service.listAllConsoles();
		req.setAttribute("listAllConsoles", listAllConsoles);
		redirect(req,resp);
	}


	protected void redirect(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ListConsole.jsp");
		dispatcher.forward(req,resp);
	}
}