package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Game;
import services.GameService;

public class DeleteGame extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private GameService service = new GameService();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Game game = service.assembleUserFromRequest(req);
		service.deleteVideoGame(game);
		loginRedirect(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("title", req.getParameter("title"));
		confirmationRedirect(req, resp);
	}


	protected void confirmationRedirect(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("ConfirmationDeleteGame.jsp");
		dispatcher.forward(req, resp);
	}

	protected void loginRedirect(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("ListGame.jsp");
		dispatcher.forward(req, resp);
	}
}