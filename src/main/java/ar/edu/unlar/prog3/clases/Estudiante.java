package ar.edu.unlar.prog3.clases;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Estudiante {

    private String legajo;
    private String nombre;
    private double promedio;
    private int edad;
    private int materiasAprobadas;

    @Override
    public String toString(){
        return "Legajo:"+legajo+" | Nombre: "+nombre+" | Promedio:"+promedio+" | Edad:"+edad+" | MateriasAprobadas:"+materiasAprobadas;
    }
  
}