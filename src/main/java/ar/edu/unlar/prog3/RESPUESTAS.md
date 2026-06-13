# Respuestas del Trabajo Práctico

# Ejercicio 1: El error que dispara todo

**3. Error de compilación capturado:**
`The method sort(List<T>) in the type Collections is not applicable for the arguments (ArrayList<Estudiante>)`

**4. Respuesta a la Pregunta 1:**
El método `Collections.sort()` no compila porque le estamos pasando una lista de objetos de la clase `Estudiante`, y Java no tiene forma de saber bajo qué criterio debe ordenarlos (por ejemplo, si debe hacerlo por legajo, por promedio o por edad). Al no existir un criterio de ordenamiento natural definido para estos objetos, el compilador se confunde y arroja un error.

El "contrato" que Java exige y que nuestra clase no está cumpliendo es la interfaz `Comparable`. Para que el ordenamiento funcione, la clase `Estudiante` debe declarar que implementa dicha interfaz (`implements Comparable<Estudiante>`) y sobrescribir obligatoriamente el método `compareTo()`. Es dentro de ese método donde debemos definir la regla exacta para decidir cuándo un estudiante va antes o después que otro.

