package be.ucll.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
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

    @OneToMany(mappedBy = "vragen", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Antwoorden> antwoorden;


    // @OneToMany(mappedBy = "antwoorden")
    // private java.util.List<Vragen> vragen;


    public Vragen(String type, String vraag, List<Antwoorden> antwoorden) {
        this.type = type;
        this.vraag = vraag;
        this.antwoorden = antwoorden;
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

    public List<Antwoorden> getAntwoorden() {
        return antwoorden;
    }

     public void setAntwoorden() {
        this.antwoorden =  antwoorden;
    }


    @Override
    public String toString() {
        return "Vragen [id=" + id + ", type=" + type + ", vraag=" + vraag + "]";
    }


    // public void setAntwoorden(List<Antwoorden> antwoorden) {
    //     this.antwoorden = antwoorden;
    // }

}
