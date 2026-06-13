package ar.edu.unlar.prog3.tp_comparable_comparator;

import java.util.ArrayList;
import java.util.Comparator;
import ar.edu.unlar.prog3.clases.Estudiante;

public class ui {
    public static void main(String[] args){
        ArrayList<Estudiante>estudiantes=new ArrayList<>();
        estudiantes.add(new Estudiante("LU-2024-001", "Martín Quiroga",8.5, 22, 18));
        estudiantes.add(new Estudiante("LU-2024-002", "Valeria Díaz",       8.5, 20, 15));
        estudiantes.add(new Estudiante("LU-2024-003", "Facundo Castro",     7.2, 24, 22));
        estudiantes.add(new Estudiante("LU-2024-004", "Camila Torres",      9.1, 21, 24));
        estudiantes.add(new Estudiante("LU-2024-010", "Lucía Fernández",    7.8, 21, 16));
        Comparator<Estudiante>comPromedio=Comparator.comparing(Estudiante::getPromedio);
        Comparator<Estudiante>compNombre=comPromedio.thenComparing(Estudiante::getNombre);
        Comparator<Estudiante>compEdad=Comparator.comparing(Estudiante::getEdad);
         Comparator<Estudiante>compPromedioAsc=comPromedio.reversed();
         Comparator<Estudiante> compMateriasYNombre = Comparator.comparing(Estudiante::getMateriasAprobadas).reversed().thenComparing(Estudiante::getNombre);
       System.out.println("--- ORDEN POR MATERIAS APROBADAS DESCENDENTE (Desempate por nombre) ---");
        estudiantes.sort(compMateriasYNombre); 
        for (Estudiante e : estudiantes) {
            System.out.println(e);
        }

        
        System.out.println("\n--- ORDEN POR PROMEDIO Y NOMBRE ---");
        estudiantes.sort(compNombre);
        for (Estudiante e : estudiantes) {
            System.out.println(e);
        }

        
        System.out.println("\n--- ORDEN POR EDAD (Ascendente) ---");
        estudiantes.sort(compEdad);
        for (Estudiante e : estudiantes) {
            System.out.println(e);
        }

        
        System.out.println("\n--- ORDEN POR PROMEDIO INVERTIDO ---");
        estudiantes.sort(compPromedioAsc);
        for (Estudiante e : estudiantes) {
            System.out.println(e);
        }
    }
}
