import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Emprendimiento {
    private ArrayList<Producto> productos = new ArrayList<>();

    public void cargarProductosDesdeCSV(String archivo) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            String linea;
            boolean primeraLinea = true;  
    
            while ((linea = br.readLine()) != null) {
                if (primeraLinea) {
                    primeraLinea = false;
                    continue;  // Saltar la primera línea (encabezado)
                }
    
                String[] datos = linea.split(",");
                int id = Integer.parseInt(datos[0]);
                String nombre = datos[1];
                int cantidadDisponible = Integer.parseInt(datos[2]);
                int cantidadVendidos = Integer.parseInt(datos[3]);
                String estado = datos[4];
                double precio = Double.parseDouble(datos[5]);
                String categoria = datos[6];
    
                if (categoria.equals("Bebida")) {
                    int ml = Integer.parseInt(datos[7]);
                    String tipo = datos[8];
                    productos.add(new Bebida(id, nombre, cantidadDisponible, cantidadVendidos, estado, precio, ml, tipo));
                } else if (categoria.equals("Snack")) {
                    int gramos = Integer.parseInt(datos[9]);
                    String sabor = datos[10];
                    String tamaño = datos[11];
                    productos.add(new Snack(id, nombre, cantidadDisponible, cantidadVendidos, estado, precio, gramos, sabor, tamaño));
                } else if (categoria.equals("Postres")) {
                    int gramos = Integer.parseInt(datos[7]);
                    String sabor = datos[8];
                    String tamaño = datos[9];
                    productos.add(new Postre(id, nombre, cantidadDisponible, cantidadVendidos, estado, precio, gramos, sabor, tamaño));
                }

            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Producto buscarProductoPorId(int id) {
        for (Producto producto : productos) {
            if (producto.getId() == id) {
                return producto;
            }
        }
        return null;
    }

    public void listarProductosPorCategoria(String categoria) {
        System.out.println("Listado de productos de la categoría " + categoria + ":");
        for (Producto producto : productos) {
            if (producto.getCategoria().equalsIgnoreCase(categoria)) {
                System.out.println(producto.getNombre());
            }
        }
    }

    public double calcularVentasTotales() {
        double ventasTotales = 0.0;
        for (Producto producto : productos) {
            ventasTotales += producto.getPrecio() * producto.getCantidadVendidos();
        }
        return ventasTotales;
    }

    public HashMap<String, Integer> calcularComisionesPorCategoria() {
        HashMap<String, Integer> comisionesPorCategoria = new HashMap<>();
        for (Producto producto : productos) {
            String categoria = producto.getCategoria();
            double comision;
            if (categoria.equals("Postres")) {
                comision = producto.getPrecio() * producto.getCantidadVendidos() * 0.2;
            } else {
                comision = producto.getPrecio() * producto.getCantidadVendidos() * 0.1; // 10% para otras categorías
            }
            comisionesPorCategoria.put(categoria, comisionesPorCategoria.getOrDefault(categoria, 0) + (int) comision);
        }
        return comisionesPorCategoria;
    }

    public void mostrarInforme() {
        System.out.println("Informes:");
        // Listado de categorías con el total de productos
        HashMap<String, Integer> totalPorCategoria = new HashMap<>();
        for (Producto producto : productos) {
            String categoria = producto.getCategoria();
            totalPorCategoria.put(categoria, totalPorCategoria.getOrDefault(categoria, 0) + 1);
        }
        System.out.println("Listado de categorías con el total de productos:");
        for (String categoria : totalPorCategoria.keySet()) {
            System.out.println(categoria + " - " + totalPorCategoria.get(categoria));
        }

        // Listado de productos por categoría
        System.out.println("\nListado de productos por categoría:");
        for (Producto producto : productos) {
            System.out.println(producto.getCategoria() + " - " + producto.getNombre());
        }

        // Total de ventas
        double ventasTotales = calcularVentasTotales();
        System.out.println("\nTotal de ventas: Q" + ventasTotales);

        // Comisiones por categoría
        System.out.println("Porcentaje de comisión por categoría:");
        HashMap<String, Integer> comisionesPorCategoria = calcularComisionesPorCategoria();
        for (String categoria : comisionesPorCategoria.keySet()) {
            System.out.println(categoria + ": Q" + comisionesPorCategoria.get(categoria));
        }
    }

    public static void main(String[] args) {
        Emprendimiento emprendimiento = new Emprendimiento();
        emprendimiento.cargarProductosDesdeCSV("productos.csv");

        while (true) {
            System.out.println("\nMenú de opciones:");
            System.out.println("1. Buscar producto por ID");
            System.out.println("2. Listar productos por categoría");
            System.out.println("3. Mostrar informe");
            System.out.println("4. Salir");

            int opcion = 0;
            try {
                opcion = Integer.parseInt(System.console().readLine("Seleccione una opción: "));
            } catch (NumberFormatException e) {
                System.out.println("Opción no válida. Ingrese un número.");
                continue;
            }

            switch (opcion) {
                case 1:
                    int id = Integer.parseInt(System.console().readLine("Ingrese el ID del producto: "));
                    Producto producto = emprendimiento.buscarProductoPorId(id);
                    if (producto != null) {
                        System.out.println("Producto encontrado: " + producto.getNombre());
                    } else {
                        System.out.println("Producto no encontrado.");
                    }
                    break;
                case 2:
                    String categoria = System.console().readLine("Ingrese la categoría de productos(Bebida,Snack): ");
                    emprendimiento.listarProductosPorCategoria(categoria);
                    break;
                case 3:
                    emprendimiento.mostrarInforme();
                    break;
                case 4:
                    System.out.println("Saliendo del programa.");
                    System.exit(0);
                default:
                    System.out.println("Opción no válida. Por favor, elija una opción válida.");
                    break;
            }
        }
    }
}
