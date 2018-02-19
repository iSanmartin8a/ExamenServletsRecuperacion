package repositories;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connections.ConnectionManager;
import connections.H2Connection;
import models.Game;

public class GamesRepository {
	private static final String jdbcUrl = "jdbc:h2:file:./src/main/resources/test";
	ConnectionManager manager = new H2Connection();
	
	public Game search(Game GameForm) {
		Game gameDB= null;
		ResultSet resultSet = null;
		PreparedStatement prepareStatement = null;
		Connection conn = manager.open(jdbcUrl);
		try {
			prepareStatement = conn.prepareStatement("SELECT * FROM Game WHERE title = ?");
			prepareStatement.setString(1, GameForm.getTitle());
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next()){
				gameDB = new Game();
				gameDB.setTitle(resultSet.getString(1));
				gameDB.setAge(resultSet.getString(2));
				gameDB.setReleaseDate(resultSet.getDate(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			close(resultSet);
			close(prepareStatement);
		}
		manager.close(conn);
		return gameDB;
	}
	public void insertGame(Game gameForm) {
		Connection conn = manager.open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement("INSERT INTO Game (title, age, releaseDate)" + "VALUES (?, ?, ?)");
			preparedStatement.setString(1, gameForm.getTitle());
			preparedStatement.setString(2, gameForm.getAge());
			preparedStatement.setDate(3,(Date) gameForm.getReleaseDate());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			close(preparedStatement);
		}
		manager.close(conn);
	}
	
	public List<Game> searchAll() {
		List<Game> listOfGames= new ArrayList<Game>();
		Connection conn = manager.open(jdbcUrl);
		ResultSet resultSet = null;
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = conn.prepareStatement("SELECT * FROM VIDEOGAME");
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next()){
				Game gameDB = new Game();
				gameDB.setTitle(resultSet.getString(1));
				gameDB.setAge(resultSet.getString(2));
				gameDB.setReleaseDate(resultSet.getDate(3));;
				listOfGames.add(gameDB);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			close(resultSet);
			close(prepareStatement);
		}
		manager.close(conn);
		return listOfGames;
	}
	public List<Game> orderByTitle() {
		List<Game> listOfGames= new ArrayList<Game>();
		Connection conn = manager.open(jdbcUrl);
		ResultSet resultSet = null;
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = conn.prepareStatement("SELECT * FROM Game ORDER BY name ASC");
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next()){
				Game gameDB = new Game();
				gameDB.setTitle(resultSet.getString(1));
				gameDB.setAge(resultSet.getString(2));
				gameDB.setReleaseDate(resultSet.getDate(3));;
				listOfGames.add(gameDB);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			close(resultSet);
			close(prepareStatement);
		}
		manager.close(conn);
		return listOfGames;
	}
	public List<Game> orderByReleaseDate() {
		List<Game> listOfGames= new ArrayList<Game>();
		Connection conn = manager.open(jdbcUrl);
		ResultSet resultSet = null;
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = conn.prepareStatement("SELECT * FROM Game ORDER BY releaseDate ASC");
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next()){
				Game gameDB = new Game();
				gameDB.setTitle(resultSet.getString(1));
				gameDB.setAge(resultSet.getString(2));
				gameDB.setReleaseDate(resultSet.getDate(3));;
				listOfGames.add(gameDB);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			close(resultSet);
			close(prepareStatement);
		}
		manager.close(conn);
		return listOfGames;
	}
	public void delete(Game gameForm) {
		Connection conn = manager.open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement("DELETE FROM Game WHERE title = ?");
			preparedStatement.setString(1, gameForm.getTitle());
			preparedStatement.executeUpdate();
			System.out.println("DELETE FROM Game WHERE title = ?");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(preparedStatement);
			manager.close(conn);
		}
	}
	public void update(Game consoleForm) {
		Connection conn = manager.open(jdbcUrl);
		manager.close(conn);
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
