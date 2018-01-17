package services;

import javax.servlet.http.HttpServletRequest;

public interface Service{
	
	void createNewConsoleFromRequest(HttpServletRequest req);
	void createNewVideogameFromRequest(HttpServletRequest req);

}
