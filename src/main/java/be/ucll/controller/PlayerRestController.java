package be.ucll.controller;

import be.ucll.model.Player;
import be.ucll.service.PlayerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerRestController {
    private PlayerService playerService;

    public PlayerRestController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/top")
    public List<Player> getTop3Players() {
        List<Player> allPlayers = playerService.getAllPlayers();
        allPlayers.sort((p1, p2) -> Integer.compare(p2.getGoals(), p1.getGoals()));
        return allPlayers.subList(0, Math.min(3, allPlayers.size()));
    }
}
