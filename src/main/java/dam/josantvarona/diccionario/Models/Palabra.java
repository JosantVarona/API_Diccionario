package dam.josantvarona.diccionario.Models;

import com.fasterxml.jackson.annotation.*;
import dam.josantvarona.diccionario.Excepcions.RecordNotFoundException;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;

import java.util.List;


@Entity
@Table(name = "palabra")
public class Palabra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "termino", nullable = false)
    private String termino;

    @Size(max = 50)
    @NotNull
    @Column(name = "categoria_gramatical", nullable = false, length = 50)
    private String categoriaGramatical;

    @JsonIgnore
    @OneToMany(mappedBy = "palabra", fetch = FetchType.LAZY)
    private List<Definicion> definicions = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTermino() {
        return termino;
    }

    public void setTermino(String termino) {
        this.termino = termino;
    }

    public String getCategoriaGramatical() {
        return categoriaGramatical;
    }

    public void setCategoriaGramatical(String categoriaGramatical) {
        this.categoriaGramatical = categoriaGramatical;
    }

    public List<Definicion> getDefinicions() {
        return definicions;
    }
    public void setDefinicions(List<Definicion> definicions) {
        this.definicions = definicions;
    }
}