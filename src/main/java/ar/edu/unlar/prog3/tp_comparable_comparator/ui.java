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
        Comparator<Estudiante>compMateriasAprobadas=(e1, e2) -> Integer.compare(e1.getMateriasAprobadas(), e2.getMateriasAprobadas());
        Comparator<Estudiante>compNombre=Comparator.comparing(Estudiante::getNombre);
        Comparator<Estudiante>compEdad=Comparator.comparing(Estudiante::getEdad);
        System.out.println("--- ORDEN POR MATERIAS APROBADAS (Ascendente) ---");
        estudiantes.sort(compMateriasAprobadas);
        for (Estudiante e : estudiantes) {
            System.out.println(e); 
        }

        System.out.println("\n--- ORDEN POR NOMBRE (Alfabético A-Z) ---");
        estudiantes.sort(compNombre);
        for (Estudiante e : estudiantes) {
            System.out.println(e);
        }

        System.out.println("\n--- ORDEN POR EDAD (Ascendente) ---");
        estudiantes.sort(compEdad);
        for (Estudiante e : estudiantes) {
            System.out.println(e);
        }
    }
}
