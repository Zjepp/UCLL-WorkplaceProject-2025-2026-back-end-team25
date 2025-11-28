package be.ucll.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import be.ucll.model.Vragen;


@Repository
public interface VragenRepository extends JpaRepository<Vragen, Long> {

    @Query(value = "SELECT * FROM question WHERE type != 'connect-choice' ORDER BY RANDOM() LIMIT 18", nativeQuery = true)
    List<Vragen> find18RandomQuestions();

    @Query(value = "SELECT * FROM question WHERE type = 'connect-choice' ORDER BY RANDOM() LIMIT 8", nativeQuery = true)
    List<Vragen> find8RandomQuestions();
}
