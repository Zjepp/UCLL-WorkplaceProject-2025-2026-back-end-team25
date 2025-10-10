package be.ucll.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "resultaat")
public class Resultaat {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String resultaat;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference(value = "user-resultaat")
    private User user;

    @ManyToOne
    @JoinColumn(name = "vraag_id")
    @JsonManagedReference(value = "vraag-resultaat")
    private Vragen vragen;

    public Resultaat(String resultaat) {
        this.resultaat = resultaat;
    }


    public Resultaat() {
    }

    public Long getId() {
        return id;
    }

    public String getResultaat() {
        return resultaat;
    }

    public void setResultaat(String resultaat) {
        this.resultaat = resultaat;
    }

    @Override
    public String toString() {
        return "resultaaten [id=" + id + ", resultaat=" + resultaat + "]";
    }


    public void setUser(User user) {
        this.user = user;
    }

    public void setVraag(Vragen vragen) {
        this.vragen = vragen;
    }
    
}
