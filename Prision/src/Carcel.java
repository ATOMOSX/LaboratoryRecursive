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

    public boolean verificarEstadoCeldas() {
        boolean celdasFaltantes = false;

        for (int fila = 0; fila < filas; fila++) {
            for (int columna = 0; columna < columnas; columna++) {
                Celda celda = matrizCelda[fila][columna];
                if (celda.getTipo() == Celda.Tipo.CELDA && celda.getEstado() != Celda.Estado.OCUPADO) {
                    celdasFaltantes = true;
                    System.out.println("La celda en la fila " + fila + ", columna " + columna + " está vacía.");
                }
            }
        }

        return celdasFaltantes;
    }

    public void encontrarPrisionerosFaltantes() {
        Set<Prisionero> prisionerosEnCeldas = new HashSet<>();

        for (int fila = 0; fila < filas; fila++) {
            for (int columna = 0; columna < columnas; columna++) {
                Celda celda = matrizCelda[fila][columna];
                Prisionero[] prisioneros = celda.getPrisioneros();
                for (Prisionero prisionero : prisioneros) {
                    if (prisionero != null) {
                        prisionerosEnCeldas.add(prisionero);
                    }
                }
            }
        }

        for (int fila = 0; fila < filas; fila++) {
            for (int columna = 0; columna < columnas; columna++) {
                Celda celda = matrizCelda[fila][columna];
                Prisionero[] prisioneros = celda.getPrisioneros();
                for (Prisionero prisionero : prisioneros) {
                    if (prisionero != null && !prisionerosEnCeldas.contains(prisionero)) {
                        System.out.println("El prisionero " + prisionero.getNombre() + " está en una celda incorrecta en la fila " + fila + ", columna " + columna + ".");
                    }
                }
            }
        }
    }
//
//    public void buscarPorFilas(Celda[][] matriz, int fila, int columna, List<String> informe, Set<String> celdasVisitadas) {
//        if (!esValida(fila, columna) || matriz[fila][columna].getTipo() == Celda.Tipo.SALIDA || celdasVisitadas.contains(matriz[fila][columna].getCodigoCelda())) {
//            return;
//        }
//
//        Celda celdaActual = matriz[fila][columna];
//        informe.add("Celda Actual: " + celdaActual.getCodigoCelda());
//        informe.add("Prisioneros en la celda: " + obtenerNombresPrisionero(celdaActual));
//        informe.add("Estado de la celda: " + celdaActual.getEstado());
//
//        celdasVisitadas.add(celdaActual.getCodigoCelda());
//
//        int[] movimientosFilas = {-1, 1, 0, 0};
//        int[] movimientosColumnas = {0, 0, -1, 1};
//
//        for (int i = 0; i < 4; i++) {
//            int nuevaFila = fila + movimientosFilas[i];
//            int nuevaColumna = columna + movimientosColumnas[i];
//
//            if (esValida(nuevaFila, nuevaColumna)) {
//                Celda celdaSiguiente = matriz[nuevaFila][nuevaColumna];
//                informe.add("Movimiento en fila " + (i + 1) + ":");
//                buscarPorFilas(matriz, nuevaFila, nuevaColumna, informe, celdasVisitadas);
//            }
//        }
//    }
//    public void buscarPorColumnas(Celda[][] matriz, int fila, int columna, List<String> informe, Set<String> celdasVisitadas) {
//        if (!esValida(fila, columna) || matriz[fila][columna].getTipo() == Celda.Tipo.SALIDA || celdasVisitadas.contains(matriz[fila][columna].getCodigoCelda())) {
//            return;
//        }
//
//        Celda celdaActual = matriz[fila][columna];
//        informe.add("Celda Actual: " + celdaActual.getCodigoCelda());
//        informe.add("Prisioneros en la celda: " + obtenerNombresPrisionero(celdaActual));
//        informe.add("Estado de la celda: " + celdaActual.getEstado());
//
//        celdasVisitadas.add(celdaActual.getCodigoCelda());
//
//        int[] movimientosFilas = {-1, 1, 0, 0};
//        int[] movimientosColumnas = {0, 0, -1, 1};
//
//        for (int i = 0; i < 4; i++) {
//            int nuevaFila = fila + movimientosFilas[i];
//            int nuevaColumna = columna + movimientosColumnas[i];
//
//            if (esValida(nuevaFila, nuevaColumna)) {
//                Celda celdaSiguiente = matriz[nuevaFila][nuevaColumna];
//                informe.add("Movimiento en columna " + (i + 1) + ":");
//                buscarPorColumnas(matriz, nuevaFila, nuevaColumna, informe, celdasVisitadas);
//            }
//        }
//    }

    public List<String> buscarPorFilas(int filaInicio, int columnaInicio, int filaSalida, int columnaSalida) {
        List<String> informe = new ArrayList<>();
        Celda celdaInicio = obtenerCelda(filaInicio, columnaInicio);
        Celda celdaSalida = obtenerCelda(filaSalida, columnaSalida);

        if (celdaInicio.getTipo() == Celda.Tipo.ENTRADA && celdaSalida.getTipo() == Celda.Tipo.SALIDA) {
            buscarPorFilasRecursivo(celdaInicio, celdaSalida, informe);
        } else {
            informe.add("La celda de inicio debe ser de entrada y la celda de salida debe ser de salida.");
        }

        return informe;
    }

    private void buscarPorFilasRecursivo(Celda celdaActual, Celda celdaSalida, List<String> informe) {
        int filaActual = celdaActual.getFila();
        int columnaActual = celdaActual.getColumna();

        // Agregar informe solo si es una celda
        if (celdaActual.getTipo() == Celda.Tipo.CELDA) {
            informe.add("Celda: " + celdaActual.getCodigoCelda());
            informe.add("Prisioneros: " + obtenerNombresPrisionero(celdaActual));
            informe.add("Estado: " + celdaActual.getEstado());
        }

        // Marcamos la celda actual como visitada para evitar bucles infinitos
        celdaActual.setVisitada(true);

        // Comprobar si hemos llegado a la salida
        if (celdaActual.equals(celdaSalida)) {
            informe.add("Fin del camino");
            return;
        }

        // Moverse a la izquierda
        if (columnaActual > 0 && !obtenerCelda(filaActual, columnaActual - 1).esVisitada()) {
            buscarPorFilasRecursivo(obtenerCelda(filaActual, columnaActual - 1), celdaSalida, informe);
        }

        if (columnaActual < columnas - 1 && !obtenerCelda(filaActual, columnaActual + 1).esVisitada()) {
            buscarPorFilasRecursivo(obtenerCelda(filaActual, columnaActual + 1), celdaSalida, informe);
        }

        // Marcar la celda actual como no visitada para permitir otros caminos
        celdaActual.setVisitada(false);
    }

    public List<String> buscarPorColumnas(int filaInicio, int columnaInicio, int filaSalida, int columnaSalida) {
        List<String> informe = new ArrayList<>();
        Celda celdaInicio = obtenerCelda(filaInicio, columnaInicio);
        Celda celdaSalida = obtenerCelda(filaSalida, columnaSalida);

        if (celdaInicio.getTipo() == Celda.Tipo.ENTRADA && celdaSalida.getTipo() == Celda.Tipo.SALIDA) {
            buscarPorColumnasRecursivo(celdaInicio, celdaSalida, informe);
        } else {
            informe.add("La celda de inicio debe ser de entrada y la celda de salida debe ser de salida.");
        }

        return informe;
    }

    private void buscarPorColumnasRecursivo(Celda celdaActual, Celda celdaSalida, List<String> informe) {
        int filaActual = celdaActual.getFila();
        int columnaActual = celdaActual.getColumna();

        // Agregar informe solo si es una celda
        if (celdaActual.getTipo() == Celda.Tipo.CELDA) {
            informe.add("Celda: " + celdaActual.getCodigoCelda());
            informe.add("Prisioneros: " + obtenerNombresPrisionero(celdaActual));
            informe.add("Estado: " + celdaActual.getEstado());
        }

        // Marcamos la celda actual como visitada para evitar bucles infinitos
        celdaActual.setVisitada(true);

        // Comprobar si hemos llegado a la salida
        if (celdaActual.equals(celdaSalida)) {
            informe.add("Fin del camino");
            return;
        }

        // Moverse hacia arriba
        if (filaActual > 0 && !obtenerCelda(filaActual - 1, columnaActual).esVisitada()) {
            buscarPorColumnasRecursivo(obtenerCelda(filaActual - 1, columnaActual), celdaSalida, informe);
        }

        // Moverse hacia abajo
        if (filaActual < filas - 1 && !obtenerCelda(filaActual + 1, columnaActual).esVisitada()) {
            buscarPorColumnasRecursivo(obtenerCelda(filaActual + 1, columnaActual), celdaSalida, informe);
        }

        // Marcar la celda actual como no visitada para permitir otros caminos
        celdaActual.setVisitada(false);
    }


        public void verificarPrisioneros(Celda celda) {
        Prisionero[] prisioneros = celda.getPrisioneros();
        boolean celdaVacia = true;

        for (Prisionero prisionero : prisioneros) {
            if (prisionero != null) {
                celdaVacia = false;
                break;
            }
        }

        if (celdaVacia) {
            System.out.println("Falta un prisionero en la celda " + celda.getCodigoCelda());
        }
    }

    public Celda[][] getMatrizCelda() {
        return matrizCelda;
    }
}