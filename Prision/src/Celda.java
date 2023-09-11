public class Celda {

    private Tipo tipo;
    private Estado estado;
    private String codigoCelda;
    private Prisionero[] prisioneros;
    private int fila;
    private int columna;
    private boolean visitada;

    public Celda(Tipo tipo) {
        this.tipo = tipo;
        this.estado = Estado.DISPONIBLE;
        this.codigoCelda = generarCodigoCelda();
        this.prisioneros = new Prisionero[2]; //Hasta 2 prisioneros en una celda
        this.visitada = false;
    }

    public void setUbicacion(int fila, int columna){
        this.fila = fila;
        this.columna = columna;
    }

    private String generarCodigoCelda(){
        return tipo.name() + "-" + hashCode();
    }

    public Tipo getTipo(){
        return tipo;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public Estado getEstado(){
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getCodigoCelda(){
        return codigoCelda;
    }

    public Prisionero[] getPrisioneros() {
        return prisioneros;
    }

    public boolean esVisitada() {
        return visitada;
    }

    public void setVisitada(boolean visitada) {
        this.visitada = visitada;
    }

    public boolean agregarPrisionero(Prisionero prisionero){
        return agregarPrisionero(prisionero, 0);
    }

    private boolean agregarPrisionero(Prisionero prisionero, int index) {
        if (index < prisioneros.length) {
            if (prisioneros[index] == null) {
                prisioneros[index] = prisionero;
                return true;
            } else {
                return agregarPrisionero(prisionero, index + 1);
            }
        }
        return false;
    }

        public enum Tipo {
        CELDA,
        PASILLO,
        SALIDA,
        ENTRADA
    }

    public enum Estado{
        DISPONIBLE,
        OCUPADO
    }
}
