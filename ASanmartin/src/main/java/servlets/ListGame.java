package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Game;
import services.GameService;

public class ListGame extends HttpServlet {

	private GameService service= new  GameService();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Game> listAllVideogames = service.listAllVideogames();
		req.setAttribute("listAllVideogames", listAllVideogames);
		redirect(req,resp);
	}


	protected void redirect(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ListVideogame.jsp");
		dispatcher.forward(req,resp);
	}
}