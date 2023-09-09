import java.util.List;

public class Main {
    public static void main(String[] args) {
        Carcel carcel = new Carcel(5, 6);

        // Agregar prisioneros
        carcel.agregarPrisionero(0, 2, new Prisionero("Prisionero 1"));
        carcel.agregarPrisionero(0, 3, new Prisionero("Prisionero 2"));
        carcel.agregarPrisionero(2, 2, new Prisionero("Prisionero 3"));
        carcel.agregarPrisionero(2, 4, new Prisionero("Prisionero 4"));
        carcel.agregarPrisionero(3, 0, new Prisionero("Prisionero 5"));

        // Dejar tres celdas desocupadas
        carcel.obtenerCelda(0, 0).setEstado(Celda.Estado.DISPONIBLE);
        carcel.obtenerCelda(0, 4).setEstado(Celda.Estado.DISPONIBLE);
        carcel.obtenerCelda(4, 4).setEstado(Celda.Estado.DISPONIBLE);

        // Dejar dos celdas con un prisionero
        carcel.agregarPrisionero(0, 1, new Prisionero("Prisionero 6"));
        carcel.agregarPrisionero(3, 2, new Prisionero("Prisionero 7"));

        // Iniciar movimiento desde la celda de entrada (E)
        int filaInicio = 4; // Fila de la celda de entrada
        int columnaInicio = 0; // Columna de la celda de entrada
        List<String> informe = carcel.realizarMovimiento(filaInicio, columnaInicio, 5);

        // Imprimir el informe
        for (String mensaje : informe) {
            System.out.println(mensaje);
        }
    }
}
