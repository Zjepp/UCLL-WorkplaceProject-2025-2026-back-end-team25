package be.ucll.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import be.ucll.model.Vragen;


@Repository
public interface VragenRepository extends JpaRepository<Vragen, Long> {

    @Query(value = "SELECT * FROM question ORDER BY RANDOM() LIMIT 20", nativeQuery = true)
    List<Vragen> find20RandomQuestions();

    @Query(value = "SELECT * FROM question WHERE type != 'connect-choice' ORDER BY RANDOM() LIMIT 18", nativeQuery = true)
    List<Vragen> find18RandomQuestions();

    @Query(value = "SELECT * FROM question WHERE type = 'connect-choice' ORDER BY RANDOM() LIMIT 8", nativeQuery = true)
    List<Vragen> find8RandomConnectQuestions();

    @Query(value = "SELECT * FROM question WHERE category = :category AND type = 'connect-choice' ORDER BY RANDOM() LIMIT 8", nativeQuery = true)
    List<Vragen> find8ConnectChoiceQuestionsByCategory(@Param("category") String category);

    @Query(value = "SELECT * FROM question WHERE category = :category AND type != 'connect-choice' ORDER BY RANDOM() LIMIT 18", nativeQuery = true)
    List<Vragen> find18OtherQuestionsByCategory(@Param("category") String category);
    
    @Query("SELECT DISTINCT v.category FROM Vragen v")
    List<String> findAllCategories();
}
