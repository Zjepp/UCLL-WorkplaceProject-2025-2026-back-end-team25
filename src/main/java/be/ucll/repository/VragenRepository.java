package be.ucll.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import be.ucll.model.Vragen;


@Repository
public interface VragenRepository extends JpaRepository<Vragen, Long> {

    @Query(value = "SELECT * FROM question ORDER BY RANDOM() LIMIT 20", nativeQuery = true)
    List<Vragen> find20RandomQuestions();
}
