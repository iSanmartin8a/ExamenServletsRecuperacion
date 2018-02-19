package services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import assemblers.GameAssembler;
import connections.ConnectionManager;
import connections.H2Connection;
import models.Game;
import repositories.GamesRepository;

public class GameService {
	GameAssembler assembler = new GameAssembler();

	private GamesRepository repository = new GamesRepository();
	ConnectionManager manager = new H2Connection();
	
	public Game assembleUserFromRequest(HttpServletRequest req) {
		return GameAssembler.assembleGameForm(req);
	}
	public void createNewVideoGameFromRequest(Game gameForm) {
		Game gameDB = repository.search(gameForm);
		if (gameDB== null) {
			repository.insertGame(gameForm);
		} else {
			repository.update(gameForm);
		}
	}
	public List<Game> listAllVideogames() {
		return repository.searchAll();
	}
	public List<Game> OrderByTitle() {
		return repository.orderByTitle();
	}
	public List<Game> OrderByReleaseDate() {
		return repository.orderByReleaseDate();
	}
	public void deleteVideoGame(Game game){
		repository.delete(game);
	}
	public GamesRepository getRepository() {
		return repository;
	}
	public void setRepository(GamesRepository repository) {
		this.repository = repository;
	}
}
