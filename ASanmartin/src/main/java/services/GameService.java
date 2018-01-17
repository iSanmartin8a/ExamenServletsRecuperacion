package services;

import javax.servlet.http.HttpServletRequest;

import assemblers.GameAssembler;
import connections.ConnectionManager;
import models.Game;

public class GameService implements Service{
	
	GameAssembler assembler = new GameAssembler();
	private ConnectionManager manager = new ConnectionManager();

	public void createNewGameFromRequest(HttpServletRequest req) {
		Game game = assembler.createGameFromRequest(req);

		if (!getManager().search(game).isPresent()) {
			getManager().insert(game);
		} else {
			getManager().update(game);
		}
	}

	public ConnectionManager getManager() {
		return manager;
	}

	public void createNewConsoleFromRequest(HttpServletRequest req) {
		// TODO Auto-generated method stub
		
	}

}
