package hu.nye.progtech.torpedo.persistence;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import hu.nye.progtech.torpedo.model.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PlayerScoreRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerScoreRepository.class);

    public static final String INSERT_STATEMENT = "INSERT INTO players (name, wins) VALUES (?, ?);";
    public static final String SELECT_STATEMENT = "SELECT FROM players;";

    private final Connection connection;

    public PlayerScoreRepository(Connection connection) {
        this.connection = connection;
    }

    public List<Player> players() {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_STATEMENT);) {
            List<Player> playerList = new ArrayList<Player>();
            while (resultSet.next()) {
                playerList.add(new Player(resultSet.getString("name"), resultSet.getInt("wins")));
            }
            return playerList;
        } catch (SQLException throwables) {
            throw new RuntimeException("Failed to load scoreboard");
        }
    }

    public void update(String name, Integer wins) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STATEMENT)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, wins.toString());
            preparedStatement.executeUpdate();
        }
    }

    public void close() throws Exception {
        connection.close();
    }
}
