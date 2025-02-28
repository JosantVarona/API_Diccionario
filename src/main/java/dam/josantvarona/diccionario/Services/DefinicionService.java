package dam.josantvarona.diccionario.Services;

import dam.josantvarona.diccionario.Models.Definicion;
import dam.josantvarona.diccionario.Repositories.DefinicionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DefinicionService {
    @Autowired
    private DefinicionRepository definicionRepository;

    public List<Definicion> getDefinicionesByWord(Long word) {
        List<Definicion> definiciones = definicionRepository.findByPalabra(word);
        if (!definiciones.isEmpty()) {
            return definiciones;
        }else {
            return new ArrayList<Definicion>();
        }
    }

}
