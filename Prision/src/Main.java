// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Piso piso = new Piso(2, 3);

        agregarPrisionerosAlPiso(piso);
//        verificarEstadoPiso(piso);

        if (piso.verificarEstado()) {
            System.out.println("El estado de las celdas es correcto.");
        } else {
            System.out.println("Hay celdas ocupadas incorrectamente.");
        }
    }

    private static void agregarPrisionerosAlPiso(Piso piso) {

        piso.agregarPrisionero(0, 0, new Prisionero("Prisionero 1"));
        piso.agregarPrisionero(0, 1, new Prisionero("Prisionero 2"));
        piso.agregarPrisionero(1, 1, new Prisionero("Prisionero 3"));
        piso.agregarPrisionero(1, 1, new Prisionero("Prisionero 4"));
    }



    private static void verificarEstadoPiso(Piso piso) {

        boolean estadoCorrecto = piso.verificarEstado();

        if (estadoCorrecto){
            System.out.println("El estado de las celdas es correcto.");
        }
        else {
            System.out.println("Hay celdas ocupadas incorrectamente");
        }
    }
}