package be.ucll.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import be.ucll.model.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {
    
}
