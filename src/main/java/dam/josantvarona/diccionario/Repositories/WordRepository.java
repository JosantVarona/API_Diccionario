package dam.josantvarona.diccionario.Repositories;

import dam.josantvarona.diccionario.Models.Palabra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordRepository extends JpaRepository<Palabra, Long> {
}
