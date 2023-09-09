import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Carcel {

    private Celda[][] matrizCelda;
    private int filas;
    private int columnas;

    public Carcel(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        matrizCelda = new Celda[filas][columnas];
        inicializarCarcel();
    }

    private void inicializarCarcel() {

        String carcel =
                "cccccs" +
                        "cpppcp" +
                        "cpcpcp" +
                        "cpcpcp" +
                        "epcppp";
        int index = 0;

        for (int fila = 0; fila < filas; fila++) {
            for (int columna = 0; columna < columnas; columna++) {
                char tipoCelda = carcel.charAt(index++);
                Celda celda;
                if (tipoCelda == 'c') {
                    celda = new Celda(Celda.Tipo.CELDA);
                } else if (tipoCelda == 'p') {
                    celda = new Celda(Celda.Tipo.PASILLO);
                } else if (tipoCelda == 's') {
                    celda = new Celda(Celda.Tipo.SALIDA);
                } else if (tipoCelda == 'e') {
                    celda = new Celda(Celda.Tipo.ENTRADA);
                } else {
                    throw new IllegalArgumentException("Descripción de la cárcel no válida.");
                }
                matrizCelda[fila][columna] = celda;
            }
        }
    }

    public Celda obtenerCelda(int fila, int columna) {
        if (fila >= 0 && fila < filas && columna >= 0 && columna < columnas) {
            return matrizCelda[fila][columna];
        } else {
            throw new IllegalArgumentException("Posición fuera de los límites de la carcel.");
        }
    }

    public void agregarPrisionero(int fila, int columna, Prisionero prisionero) {
        Celda celda = obtenerCelda(fila, columna);
        if (celda.getTipo() == Celda.Tipo.CELDA) {
            if (celda.getEstado() == Celda.Estado.DISPONIBLE) {
                if (celda.agregarPrisionero(prisionero)) {
                    celda.setEstado(Celda.Estado.OCUPADO);
                } else {
                    System.err.println("La celda está llena. No se puede agregar más prisioneros.");
                }
            } else {
                System.err.println("La celda está ocupada. No se puede agregar un prisionero.");
            }
        } else {
            throw new IllegalArgumentException("No se puede agregar un prisionero a esta celda.");
        }
    }

    public String obtenerNombresPrisionero(Celda celda) {
        Prisionero[] prisioneros = celda.getPrisioneros();
        List<String> nombres = new ArrayList<>();

        for (Prisionero prisionero : prisioneros) {
            if (prisionero != null) {
                nombres.add(prisionero.getNombre());
            }
        }

        if (nombres.isEmpty()) {
            return "Ningún prisionero";
        } else {
            return String.join(", ", nombres);
        }
    }

    private boolean esValida(int fila, int columna) {
        return fila >= 0 && fila < filas && columna >= 0 && columna < columnas;
    }

    public List<String> realizarMovimiento(int filaInicio, int columnaInicio, int limiteProfundidad) {
        List<String> informe = new ArrayList<>();
        Celda celdaInicio = obtenerCelda(filaInicio, columnaInicio);

        if (celdaInicio.getTipo() == Celda.Tipo.ENTRADA) {
            informe.add("Comenzando en la celda " + celdaInicio.getCodigoCelda());
            realizarMovimientoRecursivo(celdaInicio, informe, 0, limiteProfundidad);
        } else {
            informe.add("El guardia debe comenzar en una celda de entrada.");
        }

        return informe;
    }
    private void realizarMovimientoRecursivo(Celda celdaActual, List<String> informe, int contadorMovimientos, int limiteProfundidad) {
        if (celdaActual == null || contadorMovimientos >= limiteProfundidad) {
            return;
        }

        informe.add("Prisioneros en la celda " + celdaActual.getCodigoCelda() + obtenerNombresPrisionero(celdaActual));
        informe.add("Estado de la celda: " + celdaActual.getEstado());

        int filaActual = celdaActual.getFila();
        int columnaActual = celdaActual.getColumna();

        int[] movimientosFilas = {-1, 1, 0, 0};
        int[] movimientosColumnas = {0, 0, -1, 1};

        for (int i = 0; i < 4; i++) {
            int nuevaFila = filaActual + movimientosFilas[i];
            int nuevaColumna = columnaActual + movimientosColumnas[i];

            if (esValida(nuevaFila, nuevaColumna)) {
                Celda celdaSiguiente = obtenerCelda(nuevaFila, nuevaColumna);
                informe.add("Movimiento " + (i + 1) + ":");
                informe.add("Celda Siguiente: " + celdaSiguiente.getCodigoCelda());
                realizarMovimientoRecursivo(celdaSiguiente, informe, contadorMovimientos + 1, limiteProfundidad);
            }
        }
    }
}