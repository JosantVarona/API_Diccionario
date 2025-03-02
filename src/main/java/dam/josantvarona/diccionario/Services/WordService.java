package dam.josantvarona.diccionario.Services;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class WordService {
    @Autowired
    private WordRepository wordRepository;
    @Autowired
    private DefinicionService DefinicionService;

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
    public Palabra createWord(Palabra palabra) {
        Palabra p = null;
        if (palabra != null) {
            p = palabra;
            p = wordRepository.save(p);

        }
        return p;
    }
    public Palabra createWorDefinition(Palabra palabra, List<Definicion> definicions) {
        Palabra p = null;
        if (palabra != null) {
            p = wordRepository.save(palabra);

            for (Definicion definicion : definicions) {
                definicion.setPalabra(p);
                System.out.println(definicion);
                Long id = Long.valueOf(p.getId());
                DefinicionService.createDefinicion(id,definicion);
            }

            p.setDefinicions(definicions);

        }
        return p;
    }

    public Palabra updateWord(Long id, Palabra palabra) throws RecordNotFoundException {
        if (id != null) {
            Optional<Palabra> aux = wordRepository.findById(id);
            if (aux.isPresent()) {
                Palabra wordUpdate = aux.get();
                wordUpdate.setTermino(palabra.getTermino());
                wordUpdate.setCategoriaGramatical(palabra.getCategoriaGramatical());
                wordUpdate =wordRepository.save(wordUpdate);
                return wordUpdate;
            }else {
                throw new RecordNotFoundException("No esta registrada la palabra",id);
            }
        }else {
            throw new RecordNotFoundException("No esta registrada la palabra",id);
        }
    }
    public void deleteWord(Long id) throws RecordNotFoundException {
        Optional<Palabra> aux = wordRepository.findById(id);
        if (aux.isPresent()) {
            wordRepository.delete(aux.get());
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
