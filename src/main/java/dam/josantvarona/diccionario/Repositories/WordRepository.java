package dam.josantvarona.diccionario.Repositories;

import dam.josantvarona.diccionario.Models.Palabra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WordRepository extends JpaRepository<Palabra, Long> {
    @Query(
            value = "SELECT * FROM palabra WHERE categoria_gramatical = ?",
            nativeQuery = true
    )
    List<Palabra> findByCategoriaGramatical(@Param("?") String categoria);
    @Query(
            value = "SELECT * FROM palabra WHERE termino LIKE CONCAT(:termino, '%')",
            nativeQuery = true
    )
    List<Palabra> findByTermino(@Param("termino") String termino);
}
