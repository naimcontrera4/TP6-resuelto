package ar.edu.unlar.prog3.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ar.edu.unlar.prog3.clases.Estudiante;
import ar.edu.unlar.prog3.clases.RepoEstudiantes;
import ar.edu.unlar.prog3.service.EstudianteService;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {

    private final EstudianteService estudianteService;
    private final RepoEstudiantes repoEstudiantes; 

    public EstudianteController(EstudianteService estudianteService, RepoEstudiantes repoEstudiantes) {
        this.estudianteService = estudianteService;
        this.repoEstudiantes = repoEstudiantes;
    }

    @GetMapping
    public List<Estudiante> obtenerEstudiantes(
            @RequestParam(defaultValue = "promedio") String sortBy,
            @RequestParam(defaultValue = "asc") String order) {

        return estudianteService.ordenar(repoEstudiantes.obtenerEstudiantes(), sortBy, order);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> manejarErrorCriterioInvalido(IllegalArgumentException ex) {
        
        Map<String, Object> jsonError = new HashMap<>();
        jsonError.put("error", "Criterio de ordenamiento no válido");
        jsonError.put("criterioRecibido", ex.getMessage()); 
        jsonError.put("criteriosAceptados", Arrays.asList("promedio", "edad", "nombre", "materiasAprobadas", "legajo"));
        return ResponseEntity.badRequest().body(jsonError);
    }
}