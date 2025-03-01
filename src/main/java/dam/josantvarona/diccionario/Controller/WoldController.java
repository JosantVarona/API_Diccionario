package dam.josantvarona.diccionario.Controller;

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
}
