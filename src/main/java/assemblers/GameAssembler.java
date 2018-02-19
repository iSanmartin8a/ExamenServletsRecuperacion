package assemblers;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import models.Game;

public class GameAssembler {
	
	public static Game assembleGameForm(HttpServletRequest request) {

		Game game = new Game();
		game.setTitle(request.getParameter("title"));
		game.setAge(request.getParameter("age"));
		game.setReleaseDate2((request.getParameter("releaseDate")));
		return game;
	}

}
