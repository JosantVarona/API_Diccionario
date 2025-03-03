package dam.josantvarona.diccionario.Controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dam.josantvarona.diccionario.Excepcions.RecordNotFoundException;
import dam.josantvarona.diccionario.Models.Definicion;
import dam.josantvarona.diccionario.Models.Palabra;
import dam.josantvarona.diccionario.Services.DefinicionService;
import dam.josantvarona.diccionario.Services.WordService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/palabras")
public class WoldController {
    @Autowired
    private WordService wordService;
    @Autowired
    private DefinicionService definicionService;

    @CrossOrigin
    @GetMapping
    public ResponseEntity<List<Palabra>> getWords() {
        List<Palabra> list = wordService.getAllWords();
        return new ResponseEntity<List<Palabra>>(list, new HttpHeaders(), HttpStatus.OK);
    }
    @CrossOrigin
    @GetMapping("/definiciones")
    public ResponseEntity<List<Palabra>> getWordsAndDefinicions() {
        List<Palabra> list = wordService.getAllWordAndDefinicion();
        return new ResponseEntity<List<Palabra>>(list, new HttpHeaders(), HttpStatus.OK);
    }
    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<Palabra> getWordById(@PathVariable Long id) throws RecordNotFoundException {
        Palabra palabra = wordService.getWordById(id);
        //Hibernate.initialize(palabra.getDefinicions());
        return new ResponseEntity<>(palabra, new HttpHeaders(), HttpStatus.OK);
    }
    @CrossOrigin
    @PostMapping
    public ResponseEntity<Palabra> addWord(@RequestBody Palabra palabra) {
        Palabra abbWord = wordService.createWord(palabra);
        return ResponseEntity.status(HttpStatus.CREATED).body(abbWord);
    }
    @CrossOrigin
    @PutMapping("/{id}")
    public ResponseEntity<Palabra> updateWord(@PathVariable Long id, @RequestBody Palabra palabra) throws RecordNotFoundException {
        Palabra abbWord = wordService.updateWord(id, palabra);
        return ResponseEntity.status(HttpStatus.OK).body(abbWord);
    }
    @CrossOrigin
    @DeleteMapping("/{id}")
    public HttpStatus deleteWord(@PathVariable Long id) throws RecordNotFoundException {
        wordService.deleteWord(id);
        return HttpStatus.ACCEPTED;
    }
    @CrossOrigin
    @PostMapping("/{id}/definiciones")
    public ResponseEntity<Definicion> addDefinicion(@PathVariable Long id, @RequestBody Definicion definicion) {
        Definicion addDefini = definicionService.createDefinicion(id, definicion);
        return ResponseEntity.status(HttpStatus.CREATED).body(addDefini);
    }
    @CrossOrigin
    @PostMapping("/con-definiciones")
    public ResponseEntity<?> addConDefinicion(@RequestBody Object palabraObj) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> palabraMap = (Map<String, Object>) palabraObj;

        List<Map<String, String>> definicionesMap = (List<Map<String, String>>) palabraMap.get("definicions");

        List<Definicion> definiciones = mapper.convertValue(definicionesMap, new TypeReference<List<Definicion>>(){});

        Palabra palabra = mapper.convertValue(palabraObj, Palabra.class);
        Palabra abbWord = wordService.createWorDefinition(palabra, definiciones);
        return ResponseEntity.status(HttpStatus.CREATED).body(abbWord);
    }
    @CrossOrigin
    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<Palabra>> getPalabrasByCategoria(@PathVariable String categoria) {
        List<Palabra> result = wordService.filterCategori(categoria);
        return new ResponseEntity<>(result, new HttpHeaders(), HttpStatus.OK);
    }
    @CrossOrigin
    @GetMapping("/inicial/{letra}")
    public ResponseEntity<List<Palabra>> getPalabrasByLetra(@PathVariable String letra) {
        List<Palabra> result = wordService.filterChar(letra);
        return new ResponseEntity<>(result, new HttpHeaders(), HttpStatus.OK);
    }

}
