package dam.josantvarona.diccionario.Services;

import dam.josantvarona.diccionario.Excepcions.RecordNotFoundException;
import dam.josantvarona.diccionario.Models.Definicion;
import dam.josantvarona.diccionario.Models.Palabra;
import dam.josantvarona.diccionario.Repositories.DefinicionRepository;
import dam.josantvarona.diccionario.Repositories.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DefinicionService {
    @Autowired
    private DefinicionRepository definicionRepository;
    @Autowired
    private WordRepository wordRepository;

    public List<Definicion> getDefinicionesByWord(Long word) {
        List<Definicion> definiciones = definicionRepository.findByPalabra(word);
        if (!definiciones.isEmpty()) {
            return definiciones;
        }else {
            return new ArrayList<Definicion>();
        }
    }
    public Definicion createDefinicion(Long id, Definicion definicion) {
        Optional<Palabra> aux = wordRepository.findById(id);
        if (aux.isPresent()) {
            Definicion newDefinicion = new Definicion();
            newDefinicion.setPalabra(aux.get());
            newDefinicion.setDescripcion(definicion.getDescripcion());
            newDefinicion.setEjemplo(definicion.getEjemplo());
            definicionRepository.save(newDefinicion);
            return newDefinicion;
        }
        throw new RuntimeException("La palabra con ID " + id + " no existe.");
    }
    public void deleteDefinicion(Integer id) throws RecordNotFoundException {
        Optional<Definicion> aux = definicionRepository.findById(id);
        if (aux.isPresent()) {
            definicionRepository.delete(aux.get());
        }else {
            throw new RecordNotFoundException("Deficincion no encontrada por id",id);
        }
    }
}
