package services;

import javax.servlet.http.HttpServletRequest;

import assemblers.ConsoleAssembler;
import models.Console;

public class ConsoleService implements Service{
	
	ConsoleAssembler assembler = new ConsoleAssembler();
	private ConnectionManager manager = new ConnectionH2();

	public void createNewConsoleFromRequest(HttpServletRequest req) {
		Console console = assembler.createConsoleFromRequest(req);

		if (!getManager().search(console).isPresent()) {
			getManager().insert(console);
		} else {
			getManager().update(console);
		}
	}

	public ConnectionManager getManager() {
		return manager;
	}


	public void createNewVideogameFromRequest(HttpServletRequest req) {
		// TODO Auto-generated method stub
		
	}

}
