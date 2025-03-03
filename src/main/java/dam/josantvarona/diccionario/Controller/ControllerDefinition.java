package dam.josantvarona.diccionario.Controller;


import dam.josantvarona.diccionario.Excepcions.RecordNotFoundException;
import dam.josantvarona.diccionario.Models.Definicion;
import dam.josantvarona.diccionario.Services.DefinicionService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("definiciones")
public class ControllerDefinition {

    @Autowired
    private DefinicionService definicionService;

    @CrossOrigin
    @DeleteMapping("/{id}")
    public HttpStatus eliminarDefinicion(@PathVariable Integer id) throws RecordNotFoundException {
        definicionService.deleteDefinicion(id);
        return HttpStatus.ACCEPTED;
    }
    @CrossOrigin
    @PutMapping("/{palabra}/{id}")
    public ResponseEntity<Definicion> updateDefinicion(@PathVariable Long palabra, @PathVariable Integer id, @RequestBody Definicion definicion) throws RecordNotFoundException {
        Definicion newDefinicion = definicionService.updateDefinicion(palabra, id, definicion);
        return ResponseEntity.status(HttpStatus.OK).body(newDefinicion);
    }
}
