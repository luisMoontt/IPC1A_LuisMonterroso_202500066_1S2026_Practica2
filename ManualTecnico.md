# Manual Tecnico - Algoritmos de Ordenamiento

## IPC1 - Practica 2

---

## 1. Descripcion General

Aplicacion de escritorio desarrollada en Java con Swing que permite visualizar en tiempo real el proceso de ordenamiento de una serie de numeros mediante tres algoritmos: Bubble Sort, Shell Sort y Quick Sort.

La aplicacion muestra graficamente cada paso del algoritmo mediante una grafica de barras interactiva, registra estadisticas de ejecucion como comparaciones, intercambios e iteraciones, y genera un reporte HTML con informacion detallada del sistema y los resultados de cada corrida.

---

## 2. Estructura del Proyecto

El proyecto esta organizado bajo el grupo `com.ipc1_practica2` y dividido en cuatro paquetes principales:

```
com.ipc1_practica2
├── controllers
│   ├── ReportController.java
│   └── SerieController.java
├── main
│   └── AlgoritmosdeOrdenamiento.java
├── models
│   └── Serie.java
└── views
    ├── InformationModule.java
    ├── MainModule.java
    ├── NumberRange.java
    └── StartFrame.java
```

### 2.1 Clases del Proyecto

| Clase | Responsabilidad |
|-------|----------------|
| AlgoritmosdeOrdenamiento.java | Clase principal. Inicializa el perfil del sistema y lanza la interfaz grafica. |
| Serie.java | Modelo de datos que representa la serie de numeros y sus estadisticas. |
| SerieController.java | Controlador principal. Ejecuta los algoritmos y actualiza la grafica. |
| ReportController.java | Obtiene informacion del sistema y genera el reporte HTML. |
| StartFrame.java | Ventana de bienvenida con boton de inicio. |
| MainModule.java | Ventana principal con controles, grafica y estadisticas. |
| NumberRange.java | Ventana para generar series aleatorias mediante un JSpinner. |
| InformationModule.java | Ventana informativa con imagen de guia de uso. |

---

## 3. Librerias Implementadas

### 3.1 JFreeChart 1.5.4

Libreria para la generacion de graficas en Java. Se utiliza para visualizar el estado del arreglo en cada paso del algoritmo mediante un grafico de barras dinamico con colores que indican el estado de cada elemento.

| Clase | Uso |
|-------|-----|
| ChartFactory | Fabrica para crear graficos de barras |
| ChartPanel | Panel embebible en Swing para mostrar el grafico |
| JFreeChart | Objeto principal del grafico |
| CategoryPlot | Area de trazado con configuracion de ejes y fondo |
| BarRenderer | Renderizador personalizado para colorear barras individualmente |
| DefaultCategoryDataset | Dataset que almacena los valores del arreglo |

### 3.2 OSHI 6.4.0

Libreria para obtener informacion detallada del hardware del sistema operativo. Se utiliza en ReportController para capturar los datos tecnicos de la maquina donde corre el programa.

| Clase | Uso |
|-------|-----|
| SystemInfo | Punto de entrada principal de OSHI |
| HardwareAbstractionLayer | Capa de abstraccion del hardware del sistema |
| CentralProcessor | Informacion del procesador: modelo y frecuencia |

---

## 4. Descripcion de Clases y Metodos Importantes

### 4.1 Serie.java

Modelo de datos principal de la aplicacion. Representa una serie de numeros con todos sus metadatos de configuracion y estadisticas.

| Atributo | Descripcion |
|----------|-------------|
| int[] numbers | Arreglo de enteros con la serie de numeros a ordenar |
| int t_algoritm | Tipo de algoritmo: 0=Bubble Sort, 1=Shell Sort, 2=Quick Sort |
| boolean t_order | Orden: true=ascendente, false=descendente |
| int speed | Velocidad de visualizacion en ms: 20, 100 o 500 |
| int n_comparison | Contador de comparaciones realizadas |
| int n_exchange | Contador de intercambios realizados |
| int n_iteration | Contador de iteraciones realizadas |

### 4.2 SerieController.java

Controlador principal de la logica de ordenamiento. Gestiona la serie de datos, ejecuta los algoritmos y coordina la actualizacion de la interfaz grafica.

| Metodo | Descripcion |
|--------|-------------|
| loadSerie() | Configura los parametros de la serie antes de ordenar |
| ordenar() | Ejecuta el algoritmo seleccionado segun t_algoritm |
| bubbleSort() | Implementacion iterativa de Bubble Sort con visualizacion paso a paso |
| shellSort() | Implementacion iterativa de Shell Sort con reduccion de gap |
| quickSort() | Implementacion recursiva de Quick Sort con pivote al final |
| calculateGraph() | Genera el ChartPanel con colores segun el estado de cada barra |
| updateGraph() | Actualiza la grafica en el EDT mediante SwingUtilities.invokeAndWait |
| generateSerie() | Convierte un String de numeros separados por comas a int[] |
| generateNumbers() | Genera una serie aleatoria de n numeros sin repeticion entre 0 y 100 |
| withoutZeros() | Filtra los ceros del arreglo retornando solo valores validos |

### 4.3 ReportController.java

Controlador encargado de obtener informacion del sistema y generar el reporte HTML de resultados.

| Metodo | Descripcion |
|--------|-------------|
| printProfileSystem() | Obtiene y almacena datos del SO, procesador, nucleos y RAM mediante OSHI y Runtime |
| generateReport() | Genera o actualiza el archivo reporte.html con los datos de cada ejecucion |
| printSerie() | Convierte un int[] a String con valores separados por comas |

---

## 5. Logica General de Ejecucion

| Paso | Descripcion |
|------|-------------|
| 1 | AlgoritmosdeOrdenamiento ejecuta printProfileSystem() para capturar datos del sistema al inicio |
| 2 | Se lanza StartFrame como ventana de bienvenida |
| 3 | El usuario accede a MainModule y configura algoritmo, orden, velocidad y serie de numeros |
| 4 | Al presionar INICIAR, MainModule lanza un hilo separado con new Thread() para no bloquear la UI |
| 5 | SerieController ejecuta el algoritmo seleccionado con Thread.sleep() para controlar la velocidad |
| 6 | En cada paso se llama a updateGraph() mediante SwingUtilities.invokeAndWait() para actualizar la grafica de forma sincronizada |
| 7 | Al finalizar el ordenamiento, se llama a generateReport() para registrar los resultados en el HTML |

---

## 6. Algoritmos Implementados

### 6.1 Bubble Sort

Algoritmo iterativo que compara pares de elementos adyacentes y los intercambia si estan en el orden incorrecto. Repite el proceso hasta que no haya mas intercambios necesarios.

| Caso | Complejidad |
|------|-------------|
| Mejor caso | O(n) |
| Caso promedio | O(n²) |
| Peor caso | O(n²) |

### 6.2 Shell Sort

Mejora del Bubble Sort que compara elementos separados por un intervalo llamado gap. El gap comienza en n/2 y se reduce a la mitad en cada iteracion hasta llegar a 1, lo que permite mover elementos lejanos a su posicion correcta de forma eficiente.

| Caso | Complejidad |
|------|-------------|
| Mejor caso | O(n log n) |
| Caso promedio | O(n log n) |
| Peor caso | O(n²) |

### 6.3 Quick Sort

Algoritmo recursivo que selecciona un elemento pivote (ultimo elemento del subarreglo) y divide el arreglo en dos partes: los menores al pivote y los mayores. Ordena cada parte recursivamente hasta que todos los elementos esten en su posicion final.

| Caso | Complejidad |
|------|-------------|
| Mejor caso | O(n log n) |
| Caso promedio | O(n log n) |
| Peor caso | O(n²) |

---

## 7. Manejo de Hilos

La ejecucion de los algoritmos se realiza en un hilo separado del EDT (Event Dispatch Thread) de Swing para evitar que la interfaz grafica se congele durante el ordenamiento.

| Elemento | Funcion |
|----------|---------|
| new Thread() | Lanza el algoritmo en un hilo separado del hilo principal de la UI |
| Thread.sleep(speed) | Pausa la ejecucion el tiempo configurado para visualizar cada paso |
| SwingUtilities.invokeAndWait() | Actualiza la grafica en el EDT de forma sincronizada, esperando que se dibuje antes de continuar |
| SwingUtilities.invokeLater() | Encola actualizaciones de labels y estadisticas en el EDT sin bloquear el hilo de ordenamiento |

---

## 8. Visualizacion de la Grafica

La grafica de barras utiliza un BarRenderer personalizado que colorea cada barra segun su estado en el algoritmo:

| Color | Estado |
|-------|--------|
| Azul (0, 191, 255) | Estado normal, barra sin accion |
| Amarillo (255, 183, 3) | Barras siendo comparadas en ese momento |
| Rojo (255, 90, 109) | Barras siendo intercambiadas |
| Verde (26, 188, 140) | Barra en su posicion final correcta |

---

## 9. Reporte HTML

El sistema genera automaticamente un archivo `reporte.html` en el directorio raiz del proyecto con los resultados de cada ejecucion.

| Dato registrado | Fuente |
|----------------|--------|
| Sistema operativo y arquitectura | System.getProperty() |
| Nucleos y hilos del procesador | Runtime.getRuntime().availableProcessors() |
| RAM total y disponible | Runtime.getRuntime() |
| Modelo del procesador y frecuencia | OSHI - CentralProcessor |
| Numero de elementos procesados | Serie.getNumbers().length |
| Tiempo total de ejecucion | System.nanoTime() |
| Comparaciones, intercambios e iteraciones | Contadores de Serie |
| Arreglo inicial y arreglo ordenado | Serie.getNumbers() antes y despues |

La variable estatica `firstTime` controla si es la primera ejecucion del programa. Si es la primera vez, el archivo se sobreescribe desde cero con la estructura HTML base. En ejecuciones posteriores dentro de la misma sesion, las nuevas entradas se agregan al final del archivo sin sobreescribir las anteriores.

---

## 10. Dependencias Maven

El proyecto utiliza Maven como gestor de dependencias. Las librerias externas se declaran en el archivo `pom.xml`:

| GroupId | ArtifactId | Version |
|---------|-----------|---------|
| org.jfree | jfreechart | 1.5.4 |
| com.github.oshi | oshi-core | 6.4.0 |
