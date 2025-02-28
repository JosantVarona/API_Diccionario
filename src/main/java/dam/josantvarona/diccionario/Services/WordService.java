package dam.josantvarona.diccionario.Services;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import dam.josantvarona.diccionario.Excepcions.RecordNotFoundException;
import dam.josantvarona.diccionario.Models.Definicion;
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
            Palabra p = palabra.get();
            WordLazy wordLazy = new WordLazy();
            wordLazy.setId(p.getId());
            wordLazy.setTermino(p.getTermino());
            wordLazy.setCategoriaGramatical(p.getCategoriaGramatical());
            wordLazy.setDefinicions(p.getDefinicions());
            return wordLazy;
        }else {
            throw new RecordNotFoundException("No esta registrada la palabra",id);
        }
    }
}
class WordLazy extends Palabra {

    @JsonProperty("definicions")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Override
    public List<Definicion> getDefinicions() {
        return super.getDefinicions();
    }
}
