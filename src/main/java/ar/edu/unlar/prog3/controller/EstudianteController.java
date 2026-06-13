package ar.edu.unlar.prog3.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ar.edu.unlar.prog3.clases.Estudiante;
import ar.edu.unlar.prog3.clases.RepoEstudiantes;
import ar.edu.unlar.prog3.service.EstudianteService;

import java.util.List;

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
}