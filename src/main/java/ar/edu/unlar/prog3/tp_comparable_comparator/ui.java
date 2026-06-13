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

        ArrayList<Estudiante> listaDePrueba = new ArrayList<>();
        
        listaDePrueba.add(new Estudiante("LU-MAX", "Estudiante Máximo", 8.0, Integer.MAX_VALUE, 10));
        listaDePrueba.add(new Estudiante("LU-MIN", "Estudiante Negativo", 8.0, -1, 10));

        Comparator<Estudiante> restaTramposa = (e1, e2) -> e1.getEdad() - e2.getEdad();

        System.out.println("--- ORDEN CON LA RESTA TRAMPOSA ---");
        listaDePrueba.sort(restaTramposa);
        for (Estudiante e : listaDePrueba) {
            System.out.println(e.getNombre() + " - Edad: " + e.getEdad());
        }

        Comparator<Estudiante> comparadorSeguro = Comparator.comparingInt(Estudiante::getEdad);
    
        System.out.println("\n--- ORDEN CORREGIDO CON Integer.compare() ---");
        listaDePrueba.sort(comparadorSeguro);
        for (Estudiante e : listaDePrueba) {
            System.out.println(e.getNombre() + " - Edad: " + e.getEdad());
        }
    }
}
