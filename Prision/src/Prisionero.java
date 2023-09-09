public class Prisionero {

    private String nombre;
    private String identificacion;

    public Prisionero(String nombre, String identificacion) {
        this.nombre = nombre;
        this.identificacion = identificacion;
    }

    public Prisionero(String nombre) {
        this.nombre = nombre;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }
}
