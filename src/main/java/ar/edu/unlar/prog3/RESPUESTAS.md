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

## Ejercicio 6: El anti-patrón de la resta

**4. Respuesta a la Pregunta 4:**

Un **overflow (desbordamiento) de enteros** ocurre cuando el resultado de una operación matemática supera el límite máximo de memoria que puede guardar el tipo de dato `int` en Java (que es 2.147.483.647). Cuando se pasa de ese límite, el número "da la vuelta" y se transforma en un número negativo enorme.

El **"truco de la resta"** provoca este desbordamiento porque, al hacer `Integer.MAX_VALUE - (-1)`, la regla de los signos convierte la resta en una suma: `Integer.MAX_VALUE + 1`. Al sumarle 1 al límite máximo, se produce el overflow y el resultado da `-2147483648` (negativo).

Esto rompe una parte fundamental del **contrato de Comparator**: el contrato exige que si el primer objeto es mayor que el segundo, debe devolver un número *positivo*. En este caso, el primer estudiante tiene la edad máxima y el segundo tiene `-1`, por lo que debería dar positivo. Sin embargo, por culpa del overflow devuelve un número *negativo*, mintiéndole a Java y haciéndole creer que el Estudiante Máximo es menor, lo que arruina el orden de la lista.

El método **`Integer.compare()`** no sufre este problema porque internamente no realiza ninguna resta ni operación aritmética. Simplemente utiliza los operadores lógicos condicionales (`<` y `>`) para evaluar qué número es más grande y devuelve de forma directa y segura los números `-1`, `0` o `1`.

## Ejercicio 8: Service con patrón Strategy

**5. Respuesta a la Pregunta 5:**

Al utilizar un `Map<String, Comparator<T>>` en lugar de una estructura condicional como `switch` o `if-else`, estamos aplicando una variante del **Patrón de diseño Strategy** (Estrategia), a menudo implementado como un *Strategy Registry* (Registro de Estrategias).

**Relación con el Polimorfismo:**
El polimorfismo nos permite tratar a distintos objetos a través de una interfaz común. En nuestro mapa, los valores son diferentes implementaciones lógicas (lambdas/method references), pero todas comparten la misma interfaz `Comparator`. Nuestro método `ordenar()` no necesita saber qué hace internamente el comparador de "edad" o el de "nombre"; gracias al polimorfismo, simplemente lo extrae del mapa y le dice "ejecutate" usando el contrato de la interfaz. Delega el comportamiento en lugar de interrogar por el tipo.

**Por qué es preferible a la alternativa procedural (switch/if-else):**
La alternativa procedural viola el principio de diseño **Abierto/Cerrado (OCP)** de SOLID. Si el día de mañana agregamos un nuevo atributo al sistema (por ejemplo, `asistencias`) y queremos permitir ordenar por él, un bloque `switch` nos obligaría a modificar el código fuente del método `ordenar()` para añadir un nuevo `case`. 

Al usar el patrón Strategy con un `Map`, el método `ordenar()` queda cerrado a la modificación. Para agregar un nuevo criterio, simplemente registramos una nueva línea de código en el constructor o configuración del servicio (`comparators.put(...)`), manteniendo el núcleo de la lógica intacto, limpio y mucho más escalable.