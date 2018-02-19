package connections;

import java.sql.Connection;

public interface ConnectionManager {
	public Connection open(String jdbcUrl);
	
	public void close(Connection conn);

	public Connection executeSql(Connection conn, String sql);
	
}
