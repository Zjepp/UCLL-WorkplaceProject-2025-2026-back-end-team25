package be.ucll.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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
    @Column(name = "question")
    private String vraag;
    private String category;
    private int tijd;

    @OneToMany(mappedBy = "vragen", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Antwoorden> antwoorden = new ArrayList<>();

    @OneToMany(mappedBy = "vragen", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "vraag-resultaat")
    private List<Resultaat> resultaat = new ArrayList<>();

    // @OneToMany(mappedBy = "antwoorden")
    // private java.util.List<Vragen> vragen;

    public Vragen(String type, String vraag, String category, List<Antwoorden> antwoorden,
            List<Resultaat> resultaat, int tijd) {
        this.type = type;
        this.vraag = vraag;
        this.category = category;
        this.tijd = tijd;
        this.antwoorden = antwoorden;
        if (antwoorden != null) {
            for (Antwoorden a : antwoorden) {
                a.setVragen(this);
            }
        }
        this.resultaat = resultaat;
    }

    public Vragen() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public int getTijd() {
        return tijd;
    }

    public void setTijd(int tijd) {
        this.tijd = tijd;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Antwoorden> getAntwoorden() {
        return antwoorden;
    }

    // public void setAntwoorden() {
    // this.antwoorden = antwoorden;
    // }

    @Override
    public String toString() {
        return "Vragen [id=" + id + ", type=" + type + ", vraag=" + vraag + "]";
    }

    // public void setAntwoorden(List<Antwoorden> antwoorden) {
    // this.antwoorden = antwoorden;
    // }

}
