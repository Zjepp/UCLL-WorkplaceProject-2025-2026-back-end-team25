package be.ucll.model;

import java.util.List;

import org.hibernate.validator.constraints.br.CNPJ;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
@Table(name = "resultaat")
public class Resultaat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "resultaat")
    private String resultaat;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference(value = "user-resultaat")
    private User user;

    @ManyToOne
    @JoinColumn(name = "question_id")
    @JsonBackReference(value = "vraag-resultaat")
    private Vragen vragen;

    public Resultaat(String resultaat) {
        this.setResultaat(resultaat);
    }

    public void setResultaat(String resultaat) {
        if (resultaat == null) {
            this.resultaat = "";
            return;
        } else {
            if (!resultaat.endsWith("%")) {
                throw new IllegalArgumentException();
            }

            String numberPart = resultaat.replace("%", "");
            try {
                int percentage = Integer.parseInt(numberPart);
                if (percentage > 100) {
                    throw new IllegalArgumentException();
                }
                this.resultaat = resultaat;
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException();
            }
        }
    }

    public Resultaat() {
    }

    public Long getId() {
        return id;
    }

    public String getResultaat() {
        return resultaat;
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
