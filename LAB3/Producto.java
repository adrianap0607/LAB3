public class Producto {
    private int id;
    private String nombre;
    private int cantidadDisponible;
    private int cantidadVendidos;
    private String estado;
    private double precio;
    private String categoria;

    public Producto(int id, String nombre, int cantidadDisponible, int cantidadVendidos, String estado, double precio, String categoria) {
        this.id = id;
        this.nombre = nombre;
        this.cantidadDisponible = cantidadDisponible;
        this.cantidadVendidos = cantidadVendidos;
        this.estado = estado;
        this.precio = precio;
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public int getCantidadVendidos() {
        return cantidadVendidos;
    }

    public String getEstado() {
        return estado;
    }

    public double getPrecio() {
        return precio;
    }

    public String getCategoria() {
        return categoria;
    }
}
