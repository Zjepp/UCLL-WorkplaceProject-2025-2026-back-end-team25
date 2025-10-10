package be.ucll.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import be.ucll.model.User;


@Repository
public interface UserRepository  extends JpaRepository<User, Long> {

    public User findByEmail(String email);
    
}
