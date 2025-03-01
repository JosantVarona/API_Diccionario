package dam.josantvarona.diccionario.Controller;


import dam.josantvarona.diccionario.Excepcions.RecordNotFoundException;
import dam.josantvarona.diccionario.Services.DefinicionService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
}
