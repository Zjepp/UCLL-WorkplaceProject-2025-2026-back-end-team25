package be.ucll.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import be.ucll.model.Team;
import be.ucll.model.User;
import jakarta.annotation.PostConstruct;

@Component
public class DbInitializer {

    private UserRepository userRepository;
    private TeamRepository teamRepository;

    @Autowired
    public DbInitializer(UserRepository userRepository, TeamRepository teamRepository) {
        this.userRepository = userRepository;
        this.teamRepository = teamRepository;
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
    }
}
