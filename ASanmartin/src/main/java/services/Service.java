package services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
	
	public interface Service<Object> {
		
		public Object assembleObjectFromRequest(HttpServletRequest req);
		public List<Object> listAll();
		public void insert(Object formularyObject);
		public void delete(Object formularyObject);

}
