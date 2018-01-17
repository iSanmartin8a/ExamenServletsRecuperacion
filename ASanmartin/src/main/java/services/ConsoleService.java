package services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import assemblers.ConsoleAssembler;
import connections.ConnectionManager;
import connections.H2Connection;
import models.Console;
import repositories.ConsolesRepository;

public class ConsoleService implements Service{
	
	private ConsolesRepository repository = new ConsolesRepository();

	public void createNewConsoleFromRequest(HttpServletRequest req) {
		Console console = ConsoleAssembler.assembleConsoleFrom(req);
		insertOrUpdate(console);
	}

	public void insertOrUpdate(Console consoleFrom) {
		Console consoleInDatabase = repository.search(consoleFrom);
		if (null == consoleInDatabase) {
			repository.insert(consoleFrom);
		} else {
			repository.update(consoleFrom);
		}
	}

	public List<Console> listAllConsoles(){
		return repository.searchAll();
		
	}
	
	public ConsolesRepository getRepository() {
		return repository;
	}

	public void setRepository(ConsolesRepository repository) {
		this.repository = repository;
	}

}
