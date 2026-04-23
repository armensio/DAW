package daw.session;

import daw.model.Producto;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("carro")
@SessionScoped
public class Carrito implements Serializable {

    private List<Producto> productos = new ArrayList<>();

    public List<Producto> getProductos() {
        return productos;
    }

    public void addProducto(Producto p) {
        productos.add(p);
    }

    public void removeProducto(Producto p) {
        productos.remove(p);
    }

    public double getTotal() {
        return productos.stream()
                .mapToDouble(Producto::getPrecio)
                .sum();
    }

    public void vaciar() {
        productos.clear();
    }
}