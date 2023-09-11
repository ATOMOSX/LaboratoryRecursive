import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Carcel carcel = new Carcel(5, 6);

        carcel.agregarPrisionero(0, 2, new Prisionero("Prisionero 1"));
        carcel.agregarPrisionero(0, 3, new Prisionero("Prisionero 2"));
        carcel.agregarPrisionero(2, 2, new Prisionero("Prisionero 3"));
        carcel.agregarPrisionero(2, 4, new Prisionero("Prisionero 4"));
        carcel.agregarPrisionero(3, 0, new Prisionero("Prisionero 5"));


        carcel.obtenerCelda(0, 0).setEstado(Celda.Estado.DISPONIBLE);
        carcel.obtenerCelda(0, 4).setEstado(Celda.Estado.DISPONIBLE);
        carcel.obtenerCelda(4, 4).setEstado(Celda.Estado.DISPONIBLE);


        carcel.agregarPrisionero(0, 1, new Prisionero("Prisionero 6"));
        carcel.agregarPrisionero(3, 2, new Prisionero("Prisionero 7"));

        int filaInicio = 4;
        int columnaInicio = 0;
        int filaSalida = 0; // Cambia esto a la fila de la celda de salida
        int columnaSalida = 5; // Cambia esto a la columna de la celda de salida

        // Buscar por filas
        List<String> informeFilas = carcel.buscarPorFilas(filaInicio, columnaInicio, filaSalida, columnaSalida);

        // Buscar por columnas
        List<String> informeColumnas = carcel.buscarPorColumnas(filaInicio, columnaInicio, filaSalida, columnaSalida);

        // Imprimir informes
        System.out.println("Informe de búsqueda por filas:");
        for (String mensaje : informeFilas) {
            System.out.println(mensaje);
        }

        System.out.println("\nInforme de búsqueda por columnas:");
        for (String mensaje : informeColumnas) {
            System.out.println(mensaje);
        }
    }
}
