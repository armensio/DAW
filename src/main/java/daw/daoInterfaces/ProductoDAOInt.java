package daw.daoInterfaces;

import daw.model.Producto;
import java.util.List;

public interface ProductoDAOInt {
    List<Producto> buscaTodos();
    Producto buscaId(int id);
    List<Producto> buscaPorCategoria(int idCategoria);
    boolean crea(Producto p);
    boolean guarda(Producto p);
    boolean borra(int id);
    int numProductos();
}