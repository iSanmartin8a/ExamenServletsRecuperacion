package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connections.ConnectionManager;
import connections.H2Connection;
import models.Console;

public class ConsolesRepository {
	private static final String jdbcUrl = "jdbc:h2:file:./src/main/resources/test;INIT=RUNSCRIPT FROM 'classpath:scripts/create.sql'";
	ConnectionManager manager = new H2Connection();

	public Console search(Console ConsoleForm) {
		Console ConsoleInDatabase= null;
		ResultSet resultSet = null;
		PreparedStatement prepareStatement = null;
		Connection conn = manager.open(jdbcUrl);
		try {
			prepareStatement = conn.prepareStatement("SELECT * FROM CONSOLE WHERE name = ?");
			prepareStatement.setString(1, ConsoleForm.getName());
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next()){
				ConsoleInDatabase = new Console();
				ConsoleInDatabase.setName(resultSet.getString(0));
				ConsoleInDatabase.setCompany(resultSet.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			close(resultSet);
			close(prepareStatement);
			
		}
		manager.close(conn);
		return ConsoleInDatabase;
	}
	public void insertConsole(Console consoleForm) {
		Connection conn = manager.open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement("INSERT INTO CONSOLE (name, company)" + "VALUES (?, ?)");
			preparedStatement.setString(1, consoleForm.getName());
			preparedStatement.setString(2, consoleForm.getCompany());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			close(preparedStatement);
		}
		manager.close(conn);
	}
	public void update(Console consoleForm) {
		Connection conn = manager.open(jdbcUrl);
		manager.close(conn);
	}
	public List<Console> searchAll() {
		List<Console> listConsoles= new ArrayList<Console>();
		Connection conn = manager.open(jdbcUrl);
		ResultSet resultSet = null;
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = conn.prepareStatement("SELECT * FROM CONSOLE");
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next()){
				Console consoleInDatabase = new Console();
				consoleInDatabase.setName(resultSet.getString(1));
				consoleInDatabase.setCompany(resultSet.getString(2));
				listConsoles.add(consoleInDatabase);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			close(resultSet);
			close(prepareStatement);
		}
		manager.close(conn);
		return listConsoles;
	}
	public List<Console> orderByTitle() {
		List<Console> listConsoles= new ArrayList<Console>();
		Connection conn = manager.open(jdbcUrl);
		ResultSet resultSet = null;
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = conn.prepareStatement("SELECT * FROM CONSOLE ORDER BY name ASC");
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next()){
				Console consoleInDatabase = new Console();
				consoleInDatabase.setName(resultSet.getString(1));
				consoleInDatabase.setCompany(resultSet.getString(2));
				listConsoles.add(consoleInDatabase);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			close(resultSet);
			close(prepareStatement);
		}
		manager.close(conn);
		return listConsoles;
	}
	
	public void delete(Console console) {
		Connection conn = manager.open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement("DELETE FROM CONSOLE WHERE name ='" + console.getName() + "'" );
			preparedStatement.setString(1, console.getName());
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(preparedStatement);
			manager.close(conn);
		}
	}

	
	
	private void close(PreparedStatement prepareStatement) {
		try {
			prepareStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	private void close(ResultSet resultSet) {
		try {
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
