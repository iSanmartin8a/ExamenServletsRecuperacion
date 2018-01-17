package repositories;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connections.AbstractConnection;
import models.Game;

public class GamesRepository {
	
	private static final String jdbcUrl = "jdbc:h2:file:./src/main/resources/test;INIT=RUNSCRIPT FROM 'classpath:scripts/VideoGame.sql'";

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

	public void insert(Game gameForm) {
		Connection conn = connection.open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement("INSERT INTO GAME (title,pegi,releaseDate)" + "VALUES (?, ?, ?)");
			preparedStatement.setString(1, gameForm.getTitle());
			preparedStatement.setString(2, gameForm.getAge());
			preparedStatement.setDate(3, (Date) gameForm.getReleaseDate());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			connection.close(preparedStatement);
		}

		connection.close(conn);
	}

	public Game search(Game gameForm) {
		Game gameDB = null;
		ResultSet resultSet = null;
		PreparedStatement prepareStatement = null;
		Connection conn = connection.open(jdbcUrl);
		try {
			prepareStatement = conn.prepareStatement("SELECT * FROM GAME WHERE title = ?");
			prepareStatement.setString(1, gameForm.getTitle());
			resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				gameDB = new Game();
				gameDB.setTitle(resultSet.getString(0));
				gameDB.setAge(resultSet.getString(2));
				gameDB.setReleaseDate(resultSet.getDate(3));

			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			close(resultSet);
			close(prepareStatement);

		}
		connection.close(conn);
		return gameDB;
	}

	public List<Game> searchAll() {
		List<Game> listGames = new ArrayList<Game>();
		Connection conn = connection.open(jdbcUrl);
		ResultSet resultSet = null;
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = conn.prepareStatement("SELECT * FROM GAME");
			resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				Game gameDB = new Game();
				gameDB.setTitle(resultSet.getString(1));
				gameDB.setAge(resultSet.getString(2));
				gameDB.setReleaseDate(resultSet.getDate(3));

				listGames.add(gameDB);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			close(resultSet);
			close(prepareStatement);
		}

		connection.close(conn);
		return listGames;
	}

	public void update(Game game) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;

		try {
			conn = connection.open(jdbcUrl);
			preparedStatement = conn
					.prepareStatement("UPDATE GAME SET " + "title = ?, age = ?, releaseDate = ? WHERE title = ?");
			preparedStatement.setString(1, game.getTitle());
			preparedStatement.setString(2, game.getAge());
			preparedStatement.setDate(3, (Date) game.getReleaseDate());
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
