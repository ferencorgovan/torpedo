package hu.nye.progtech.torpedo.configuration;

import hu.nye.progtech.torpedo.service.GameState;
import hu.nye.progtech.torpedo.service.InputReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public GameState gameState() {
        return new GameState();
    }

    @Bean
    public InputReader inputReader() {
        return new InputReader();
    }

    //@Bean
    //Connection connection() throws SQLException {
    //    return DriverManager.getConnection("jdbc:h2:tcp://localhost/./progtech", "sa", "password");
    //}

    //@Bean(destroyMethod = "close")
    //PlayerScoreRepository playerScoreRepository(Connection connection) {
        //return new PlayerScoreRepository(connection);
    //}
}
