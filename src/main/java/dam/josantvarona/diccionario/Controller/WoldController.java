package dam.josantvarona.diccionario.Controller;

import dam.josantvarona.diccionario.Excepcions.RecordNotFoundException;
import dam.josantvarona.diccionario.Models.Palabra;
import dam.josantvarona.diccionario.Services.WordService;
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
        return new ResponseEntity<Palabra>(palabra, new HttpHeaders(), HttpStatus.OK);
    }
}
