public class Bebida extends Producto {
    private int ml;
    private String tipo;

    public Bebida(int id, String nombre, int cantidadDisponible, int cantidadVendidos, String estado, double precio, int ml, String tipo) {
        super(id, nombre, cantidadDisponible, cantidadVendidos, estado, precio, "Bebida");
        this.ml = ml;
        this.tipo = tipo;
    }

    public int getMl() {
        return ml;
    }

    public String getTipo() {
        return tipo;
    }
}
