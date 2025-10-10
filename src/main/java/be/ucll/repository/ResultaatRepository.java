package be.ucll.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import be.ucll.model.Resultaat;


@Repository
public interface ResultaatRepository extends JpaRepository<Resultaat, Long> {
    
}