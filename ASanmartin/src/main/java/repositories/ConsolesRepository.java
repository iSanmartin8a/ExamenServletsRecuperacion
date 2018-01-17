package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connections.AbstractConnection;
import models.Console;

public class ConsolesRepository {
	
	private static final String jdbcUrl = "jdbc:h2:file:./src/main/resources/test;INIT=RUNSCRIPT FROM 'classpath:scripts/Console.sql'";

	private AbstractConnection connection = new AbstractConnection() {

		@Override
		public String getDatabasePassword() {
			return "";
		}

		@Override
		public String getDatabaseUser() {
			return "sa";
		}

		@Override
		public String getDriver() {
			return "org.h2.Driver";
		}
	};

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

	public void insert(Console consoleForm) {
		Connection conn = connection.open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement("INSERT INTO CONSOLE (name,company)" + "VALUES (?, ?)");
			preparedStatement.setString(1, consoleForm.getName());
			preparedStatement.setString(2, consoleForm.getCompany());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			connection.close(preparedStatement);
		}

		connection.close(conn);
	}

	public Console search(Console consoleForm) {
		Console consoleDB = null;
		ResultSet resultSet = null;
		PreparedStatement prepareStatement = null;
		Connection conn = null;
		try {
			conn = connection.open(jdbcUrl);
			prepareStatement = conn.prepareStatement("SELECT * FROM CONSOLE WHERE name = ?");
			prepareStatement.setString(1, consoleForm.getName());
			resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				consoleDB = new Console();
				consoleDB.setName(resultSet.getString(0));
				consoleDB.setCompany(resultSet.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			close(resultSet);
			close(prepareStatement);

		}
		connection.close(conn);
		return consoleDB;
	}

	public List<Console> searchAll() {
		List<Console> games = new ArrayList<Console>();
		Connection conn = connection.open(jdbcUrl);
		ResultSet resultSet = null;
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = conn.prepareStatement("SELECT * FROM CONSOLE");
			resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				Console consoleDB = new Console();
				consoleDB.setName(resultSet.getString(1));
				consoleDB.setCompany(resultSet.getString(2));

				games.add(consoleDB);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			close(resultSet);
			close(prepareStatement);
		}

		connection.close(conn);
		return games;
	}

	public void update(Console console) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;

		try {
			conn = connection.open(jdbcUrl);
			preparedStatement = conn
					.prepareStatement("UPDATE CONSOLE SET " + "name = ?, company = ? WHERE name = ?");
			preparedStatement.setString(1, console.getName());
			preparedStatement.setString(2, console.getCompany());
			preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			connection.close(preparedStatement);
			connection.close(conn);
		}
	}

}
