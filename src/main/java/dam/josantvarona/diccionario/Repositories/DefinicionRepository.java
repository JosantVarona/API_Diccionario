package dam.josantvarona.diccionario.Repositories;

import dam.josantvarona.diccionario.Models.Definicion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DefinicionRepository extends JpaRepository<Definicion, Integer> {

    @Query(
            value = "SELECT * FROM definicion WHERE palabra_id = :palabraId",
            nativeQuery = true
    )
    List<Definicion> findByPalabra(@Param("palabraId") Long palabra);
}
