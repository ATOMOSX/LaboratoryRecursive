public class Celda {

    private Prisionero prisionero;

    public Celda(Prisionero prisionero) {
        this.prisionero = prisionero;
    }

    public boolean vacia(){
        return prisionero == null;
    }

    public void agregarPrisionero(Prisionero prisionero){
        this.prisionero = prisionero;
    }

    public Prisionero getPrisionero() {
        return prisionero;
    }

    public void setPrisionero(Prisionero prisionero) {
        this.prisionero = prisionero;
    }
}
