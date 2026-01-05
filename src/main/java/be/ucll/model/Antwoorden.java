package be.ucll.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "answer")
public class Antwoorden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean correct;
    @Column(name = "answer")
    private String antwoord;

    // @OneToMany(mappedBy = "antwoorden")
    // private java.util.List<Vragen> vragen;

    @ManyToOne
    @JoinColumn(name = "vragen_id")
    @JsonIgnore // blokkeert vraag in JSON
    private Vragen vragen;

    public Antwoorden(Boolean correct, String antwoord) {
        setCorrect(correct);
        setAntwoord(antwoord);
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
        if (antwoord == null || antwoord.trim().isEmpty()) {
            throw new IllegalArgumentException("Leeg antwoord moet niet toegestaan zijn");
        }
        this.antwoord = antwoord;
    }

    @Override
    public String toString() {
        return "Antwoorden [id=" + id + ", correct=" + correct + ", antwoord=" + antwoord + "]";
    }

    public void setVragen(Vragen vraag) {
        this.vragen = vraag;
    }

}
