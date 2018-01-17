package services;

import javax.servlet.http.HttpServletRequest;

import assemblers.GameAssembler;
import models.Game;

public class GameService implements Service{
	
	GameAssembler assembler = new GameAssembler();
	private ConnectionManager manager = new ConnectionH2();

	public void createNewGameFromRequest(HttpServletRequest req) {
		Game game = assembler.createGameFromRequest(req);

		if (!getManager().search(videogame).isPresent()) {
			getManager().insert(videogame);
		} else {
			getManager().update(videogame);
		}
	}

	public ConnectionManager getManager() {
		return manager;
	}

	public void createNewConsoleFromRequest(HttpServletRequest req) {
		// TODO Auto-generated method stub
		
	}

}
