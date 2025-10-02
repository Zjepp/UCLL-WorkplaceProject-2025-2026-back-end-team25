package be.ucll.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String homeTeam;
    private String awayTeam;
    private LocalDate date;
    private LocalTime startHour;

    public Game() {}

    public Game(String homeTeam, String awayTeam, LocalDate date, LocalTime startHour) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.date = date;
        this.startHour = startHour;
    }

    
    public Long getId() {
        return id;
     }
    public String getHomeTeam() { 
        return homeTeam; 
    }

    
    public void setHomeTeam(String homeTeam) { 
        this.homeTeam = homeTeam; 
    }


    public String getAwayTeam() { 
        return awayTeam; 
    }


    public void setAwayTeam(String awayTeam) { 
        this.awayTeam = awayTeam; 
    }


    public LocalDate getDate() { 
        return date; 
    }


    public void setDate(LocalDate date) { 
        this.date = date; 
    }


    public LocalTime getStartHour() { 
        return startHour; 
    }


    public void setStartHour(LocalTime startHour) { 
        this.startHour = startHour; 
    }
}
