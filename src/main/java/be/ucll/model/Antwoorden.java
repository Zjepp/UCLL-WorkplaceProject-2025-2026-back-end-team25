package be.ucll.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "answer")
public class Antwoorden {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean correct;
    private String antwoord;

    @OneToMany(mappedBy = "antwoorden")
    private java.util.List<Vragen> vragen;


    public Antwoorden(Boolean correct, String antwoord) {
        this.correct = correct;
        this.antwoord = antwoord;
    }


    public Antwoorden() {
    }

    public Long getId() {
        return id;
    }

    public Boolean getCorrect() {
        return correct;
    }

    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }

    public String getAntwoord() {
        return antwoord;
    }

    public void setAntwoord(String antwoord) {
        this.antwoord = antwoord;
    }

    @Override
    public String toString() {
        return "Antwoorden [id=" + id + ", correct=" + correct + ", antwoord=" + antwoord + "]";
    }
    
}
