# Respuestas del Trabajo Práctico

# Ejercicio 1: El error que dispara todo

**3. Error de compilación capturado:**
`The method sort(List<T>) in the type Collections is not applicable for the arguments (ArrayList<Estudiante>)`

**4. Respuesta a la Pregunta 1:**
El método `Collections.sort()` no compila porque le estamos pasando una lista de objetos de la clase `Estudiante`, y Java no tiene forma de saber bajo qué criterio debe ordenarlos (por ejemplo, si debe hacerlo por legajo, por promedio o por edad). Al no existir un criterio de ordenamiento natural definido para estos objetos, el compilador se confunde y arroja un error.

El "contrato" que Java exige y que nuestra clase no está cumpliendo es la interfaz `Comparable`. Para que el ordenamiento funcione, la clase `Estudiante` debe declarar que implementa dicha interfaz (`implements Comparable<Estudiante>`) y sobrescribir obligatoriamente el método `compareTo()`. Es dentro de ese método donde debemos definir la regla exacta para decidir cuándo un estudiante va antes o después que otro.

## Ejercicio 2: Implementar Comparable<Estudiante>

**3. Respuesta a la Pregunta 2:**
Elegí el atributo `promedio` como orden natural porque el requerimiento del sistema (el enunciado) lo define como el criterio principal de "mérito académico". El "orden natural" definido por `Comparable` debe representar la forma más lógica e inherente de ordenar los objetos de esa clase por defecto.

Si mañana surge un nuevo requisito para ordenar por `cantidadMateriasAprobadas`, **NO** modificaría el método `compareTo()`. 

La consecuencia de modificar el `compareTo()` sería alterar el orden natural de la clase `Estudiante` a nivel global. Esto rompería o generaría bugs en todas las otras partes del sistema (pantallas, reportes, etc.) que ya dependían de que la lista se ordenara por promedio. En Java, una clase solo puede tener **un único orden natural**. Para cumplir con un nuevo requisito de ordenamiento sin afectar el código existente, la solución correcta es dejar el `compareTo()` intacto y crear un `Comparator` externo específico para las materias aprobadas.

## Ejercicio 3: Reflexión sobre la limitación de Comparable

**Respuesta a la Pregunta 3:**
El principal problema de diseño que introduce `Comparable` cuando necesitamos múltiples formas de ordenamiento es que nos limita a un único orden natural. Si intentamos forzar que la clase maneje 4 contextos distintos de ordenamiento, estaríamos violando gravemente las buenas prácticas de diseño orientado a objetos.

Relacionándolo con los principios SOLID:

* **Principio de Responsabilidad Única (SRP):** La clase `Estudiante` debería tener como única responsabilidad representar los datos y el estado de un alumno. Si intentamos meter múltiples lógicas de ordenamiento dentro de su método `compareTo` (por ejemplo, usando condicionales complejos tipo `if/else` dependiendo del contexto), la clase asume la responsabilidad adicional de saber "cómo debe ser ordenada por el sistema".
* **Principio de Abierto/Cerrado (OCP):** Una clase debe estar abierta a la extensión pero cerrada a la modificación. Si estamos atados solo a `Comparable` y el día de mañana nos piden un quinto criterio de ordenamiento, nos veríamos obligados a modificar el código fuente del método `compareTo` de la clase `Estudiante`. Esto viola el OCP, ya que el código existente debe ser tocado para agregar nueva funcionalidad.

**Conclusión:** Para resolver esto sin violar los principios, el diseño correcto es mantener a `Comparable` solo para el orden natural y utilizar la interfaz externa `Comparator` para los otros 3 criterios. Esto nos permite crear nuevos comparadores (extender) sin tener que modificar la clase `Estudiante` (cerrada a la modificación).