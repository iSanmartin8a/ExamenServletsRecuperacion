package assemblers;

import javax.servlet.http.HttpServletRequest;

import models.Console;

public class ConsoleAssembler {
	
	public Console createConsoleFromRequest(HttpServletRequest request) {

		Console console = new Console();
		console.setName(request.getParameter("name"));
		console.setCompany(request.getParameter("company"));
		return console;
	}

}
