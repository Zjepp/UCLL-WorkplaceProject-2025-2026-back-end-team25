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
@Table(name = "question")
public class Vragen {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private String vraag;

    @ManyToOne()
    @JoinColumn(name = "answer_id")
    private Antwoorden antwoorden;


    public Vragen(String type, String vraag) {
        this.type = type;
        this.vraag = vraag;
    }

    public Vragen() {
    }

    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVraag() {
        return vraag;
    }

    public void setVraag(String vraag) {
        this.vraag = vraag;
    }

    @Override
    public String toString() {
        return "Vragen [id=" + id + ", type=" + type + ", vraag=" + vraag + "]";
    }


}
