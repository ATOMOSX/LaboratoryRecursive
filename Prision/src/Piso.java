import java.util.HashSet;
import java.util.Set;

public class Piso {

    private Celda[][] celdas;
    private Set<Prisionero> listaPrisioneros;

    public Piso(int filas, int columnas){
        celdas = new Celda[filas][columnas];
        for (int i = 0; i < filas; i++){
            for (int j = 0; j < columnas; j++){
                celdas[i][j] = new Celda();
            }
        }
        listaPrisioneros = new HashSet<>();
    }

    public boolean agregarPrisionero(int fila, int columna, Prisionero prisionero){
        if (fila >= 0 && fila < celdas.length && columna >=0 & columna < celdas[0].length){
            String nombre = prisionero.getNombre();
            if (!listaPrisioneros.contains(prisionero)){
                celdas[fila][columna].agregarPrisionero(prisionero);
                listaPrisioneros.add(prisionero);
                return true;
            }else {
                System.err.println("Ya existe un prisionero con el mismo nombre.");
            }
        }
        return  false;
    }

    private  boolean verificarColumnas(int fila, int columna){
        if (columna < celdas[0].length){
            Celda celdaActual = celdas[fila][columna];
            if (!celdaActual.vacia()){
                System.out.println("Celda (" + fila + ", " + columna + "): Ocupada por "
                        + celdaActual.getPrisionero().getNombre());
            }
            return verificarColumnas(fila, columna + 1);
        }
        return true;
    }

    private boolean verificarFilas(int filas, int columnas){
        if (filas < celdas.length){
            if (verificarColumnas(filas, 0)){
                return verificarColumnas(filas + 1, 0);
            }
            else {
                return false;
            }
        }
        return true;
    }

    public boolean verificarEstado(){
        return verificarFilas(0,0);
    }
}
