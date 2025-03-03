package dam.josantvarona.diccionario.Services;

import dam.josantvarona.diccionario.Excepcions.RecordNotFoundException;
import dam.josantvarona.diccionario.Models.Definicion;
import dam.josantvarona.diccionario.Models.Palabra;
import dam.josantvarona.diccionario.Repositories.DefinicionRepository;
import dam.josantvarona.diccionario.Repositories.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DefinicionService {
    @Autowired
    private DefinicionRepository definicionRepository;
    @Autowired
    private WordRepository wordRepository;


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
    public Definicion updateDefinicion(Long palabra, Integer id, Definicion definicion) {
        if (id !=null){
            Optional<Palabra> auxp = wordRepository.findById(palabra);
            Optional<Definicion> aux = definicionRepository.findById(id);
            if (aux.isPresent() && auxp.isPresent()) {
                Definicion updateDefinicion = aux.get();
                updateDefinicion.setDescripcion(definicion.getDescripcion());
                updateDefinicion.setEjemplo(definicion.getEjemplo());
                updateDefinicion = definicionRepository.save(updateDefinicion);
                return updateDefinicion;
            }else {
                throw new RuntimeException("La palabra con ID " + id + " no existe.");
            }
        }else {
            throw new RuntimeException("La palabra con ID " + id + " no existe.");
        }
    }
}
