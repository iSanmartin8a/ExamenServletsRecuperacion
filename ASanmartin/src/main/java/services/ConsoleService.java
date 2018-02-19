package services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import assemblers.ConsoleAssembler;
import connections.ConnectionManager;
import connections.H2Connection;
import models.Console;
import repositories.ConsolesRepository;

public class ConsoleService {
	ConsoleAssembler assembler = new ConsoleAssembler();
	ConnectionManager manager = new H2Connection();
	private ConsolesRepository repository = new ConsolesRepository();
	
	public Console assembleUserFromRequest(HttpServletRequest req) {
		return ConsoleAssembler.assembleConsoleFrom(req);
	}
	public void createNewConsoleFromRequest(Console consoleForm) {
		Console consoleInDatabase = repository.search(consoleForm);
		if (consoleInDatabase== null) {
			repository.insertConsole(consoleForm);
		} else {
			repository.update(consoleForm);
		}
	}
	public List<Console> listAllConsoles() {
		return repository.searchAll();
	}
	public List<Console> listOrderByTitle() {
		return repository.orderByTitle();
	}
	public void deleteConsole(Console console){
		repository.delete(console);
	}
	public ConsolesRepository getRepository() {
		return repository;
	}
	public void setRepository(ConsolesRepository repository) {
		this.repository = repository;
	}

}
