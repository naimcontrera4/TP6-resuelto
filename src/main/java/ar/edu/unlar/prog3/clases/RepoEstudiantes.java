package ar.edu.unlar.prog3.clases;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;

@Component  
public class RepoEstudiantes {
    private List<Estudiante>estudiantes= new ArrayList<>();

    @PostConstruct
    public void cargarDatos(){
        estudiantes.add(new Estudiante("LU-2024-001", "Martín Quiroga",8.5, 22, 18));
        estudiantes.add(new Estudiante("LU-2024-002", "Valeria Díaz",       8.5, 20, 15));
        estudiantes.add(new Estudiante("LU-2024-003", "Facundo Castro",     7.2, 24, 22));
        estudiantes.add(new Estudiante("LU-2024-004", "Camila Torres",      9.1, 21, 24));
        estudiantes.add(new Estudiante("LU-2024-005", "Lucas González",     9.1, 23, 24));
        estudiantes.add(new Estudiante("LU-2024-006", "Agustina López",     6.8, 19, 10));
        estudiantes.add(new Estudiante("LU-2024-007", "Nahuel Herrera",     7.5, 22, 14));
        estudiantes.add(new Estudiante("LU-2024-008", "Florencia Ríos",     8.9, 25, 20));
        estudiantes.add(new Estudiante("LU-2024-009", "Tomás Sosa",         6.5, 20, 12));
        estudiantes.add(new Estudiante("LU-2024-010", "Lucía Fernández",    7.8, 21, 16));
    }
    public List<Estudiante>obEstudiantes(){
        return estudiantes;
    }
}
