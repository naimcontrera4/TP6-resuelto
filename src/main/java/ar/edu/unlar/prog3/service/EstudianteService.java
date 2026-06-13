package ar.edu.unlar.prog3.service;
import org.springframework.stereotype.Service;
import ar.edu.unlar.prog3.clases.Estudiante;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EstudianteService {

    private final Map<String, Comparator<Estudiante>> comparators = new HashMap<>();

    public EstudianteService() {
        comparators.put("promedio", Comparator.comparingDouble(Estudiante::getPromedio));
        comparators.put("edad", Comparator.comparingInt(Estudiante::getEdad));
        comparators.put("nombre", Comparator.comparing(Estudiante::getNombre));
        comparators.put("materiasaprobadas", Comparator.comparingInt(Estudiante::getMateriasAprobadas));
        comparators.put("legajo", Comparator.comparing(Estudiante::getLegajo));
    }

    
    public List<Estudiante> ordenar(List<Estudiante> lista, String sortBy, String order) {
        
       
        String key = sortBy.toLowerCase();

        
        Comparator<Estudiante> comparadorBase = comparators.get(key);

        
      
        if (comparadorBase == null) {
            throw new IllegalArgumentException(sortBy); 
        }
        
        Comparator<Estudiante> comparadorFinal = comparadorBase.thenComparing(Estudiante::getLegajo);

    
        if ("desc".equalsIgnoreCase(order)) {
            comparadorFinal = comparadorFinal.reversed();
        }

        
        lista.sort(comparadorFinal);
        return lista;
    }
}
