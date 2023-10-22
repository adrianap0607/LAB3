public class Snack extends Producto {
    private int gramos;
    private String sabor;
    private String tamaño;

    public Snack(int id, String nombre, int cantidadDisponible, int cantidadVendidos, String estado, double precio, int gramos, String sabor, String tamaño) {
        super(id, nombre, cantidadDisponible, cantidadVendidos, estado, precio, "Snack");
        this.gramos = gramos;
        this.sabor = sabor;
        this.tamaño = tamaño;
    }

    public int getGramos() {
        return gramos;
    }

    public String getSabor() {
        return sabor;
    }

    public String getTamaño() {
        return tamaño;
    }
}
