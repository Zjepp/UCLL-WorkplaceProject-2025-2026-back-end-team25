package be.ucll.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import be.ucll.model.Faq;

@Repository
public interface FaqRepository extends JpaRepository<Faq, Long> {
    
}
