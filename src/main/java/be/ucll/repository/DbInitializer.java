package be.ucll.repository;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import be.ucll.model.Game;
import be.ucll.model.Team;
import be.ucll.model.User;
import jakarta.annotation.PostConstruct;

@Component
public class DbInitializer {

    private UserRepository userRepository;
    private TeamRepository teamRepository;
    private GameRepository gameRepository; 

    @Autowired
    public DbInitializer(UserRepository userRepository, TeamRepository teamRepository, GameRepository gameRepository) {
        this.userRepository = userRepository;
        this.teamRepository = teamRepository;
        this.gameRepository = gameRepository;
    }

    @PostConstruct
    public void initialize() {

        User user1 = new User("Tom Boon", 25, "tom.boon@ucll.be", "tom123");
        User user2 = new User("Loick Luypaert", 30, "loick.luypaert@ucll.be", "loick123");

        userRepository.save(user1);
        userRepository.save(user2);
    
        Team team1 = new Team("Red Lions", "Antwerp", 1920);
        Team team2 = new Team("White Panthers", "Brussels", 1930);

        teamRepository.save(team1);
        teamRepository.save(team2);

        Game game1 = new Game("Braxgata", "Victory", LocalDate.of(2025, 10, 4), LocalTime.of(17, 0));
        Game game2 = new Game("Leopold", "Braxgata", LocalDate.of(2025, 10, 11), LocalTime.of(16, 30));

        gameRepository.save(game1);
        gameRepository.save(game2);
    }
}
