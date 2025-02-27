package dam.josantvarona.diccionario.Services;

import dam.josantvarona.diccionario.Excepcions.RecordNotFoundException;
import dam.josantvarona.diccionario.Models.Palabra;
import dam.josantvarona.diccionario.Repositories.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WordService {
    @Autowired
    private WordRepository wordRepository;

    public List<Palabra> getAllWords() {
        List<Palabra> palabras = wordRepository.findAll();
        if( palabras.size() > 0 ){
            return palabras;
        }else {
            return new ArrayList<Palabra>();
        }
    }
    public Palabra getWordById(Long id) throws RecordNotFoundException {
        Optional<Palabra> palabra = wordRepository.findById(id);
        if (palabra.isPresent()) {
            return palabra.get();
        }else {
            throw new RecordNotFoundException("No esta registrada la palabra",id);
        }
    }
}
