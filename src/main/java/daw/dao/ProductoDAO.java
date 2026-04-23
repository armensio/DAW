package daw.dao;

import daw.model.Producto;
import daw.daoInterfaces.ProductoDAOInt;
import jakarta.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementación del DAO (Data Access Object) para la entidad Producto.
 *
 * Esta clase gestiona el almacenamiento y recuperación de productos en memoria,
 * utilizando una estructura Map como repositorio temporal.
 *
 * Se encarga de proporcionar operaciones CRUD básicas:
 * creación, lectura, actualización y eliminación de productos.
 *
 * NOTA: Esta implementación no utiliza base de datos real, por lo que los datos
 * se pierden al reiniciar la aplicación.
 */
@ApplicationScoped
public class ProductoDAO implements ProductoDAOInt, Serializable {

    /**
     * Mapa que almacena los productos, usando como clave su identificador.
     */
    private Map<Integer, Producto> productos;

    /**
     * Contador interno para generar identificadores únicos de productos.
     */
    private int productoId = 1;

    /**
     * Constructor que inicializa la estructura de almacenamiento en memoria.
     */
    public ProductoDAO() {
        productos = new HashMap<>();
    }

    /**
     * Recupera todos los productos almacenados.
     *
     * Se devuelve una copia de cada producto para evitar modificaciones externas
     * sobre los datos almacenados internamente.
     *
     * @return lista de productos; nunca null
     */
    @Override
    public List<Producto> buscaTodos() {
        List<Producto> lista = new ArrayList<>();
        for (Producto p : productos.values()) {
            lista.add(new Producto(p));
        }
        return lista;
    }

    /**
     * Busca un producto por su identificador.
     *
     * @param id identificador del producto
     * @return una copia del producto si existe; null en caso contrario
     */
    @Override
    public Producto buscaId(int id) {
        Producto p = productos.get(id);
        return (p != null) ? new Producto(p) : null;
    }

    /**
     * Recupera todos los productos que pertenecen a una categoría específica.
     *
     * @param idCategoria identificador de la categoría
     * @return lista de productos que coinciden con la categoría; nunca null
     */
    @Override
    public List<Producto> buscaPorCategoria(int idCategoria) {
        List<Producto> lista = new ArrayList<>();
        for (Producto p : productos.values()) {
            if (p.getIdCategoria().equals(idCategoria)) {
                lista.add(new Producto(p));
            }
        }
        return lista;
    }

    /**
     * Crea un nuevo producto en el sistema.
     *
     * Se asigna automáticamente un identificador único al producto antes de almacenarlo.
     *
     * @param p producto a crear
     * @return true si la operación se realiza correctamente
     */
    @Override
    public boolean crea(Producto p) {
        try {
            p.setIdProducto(productoId++);
            productos.put(p.getIdProducto(), new Producto(p));
            return true;

        } catch (Exception e) {
            throw new DAOException("Error al crear producto", e);
        }
    }
    /**
     * Actualiza un producto existente.
     *
     * Solo se realiza la actualización si el producto ya existe en el sistema.
     *
     * @param p producto con los datos actualizados
     * @return true si el producto existe y se actualiza; false en caso contrario
     */
    @Override
    public boolean guarda(Producto p) {
        try {
            if (!productos.containsKey(p.getIdProducto())) {
                throw new DAOException("El producto no existe con id: " + p.getIdProducto());
            }

            productos.put(p.getIdProducto(), new Producto(p));
            return true;

        } catch (Exception e) {
            throw new DAOException("Error al actualizar producto", e);
        }
    }

    /**
     * Elimina un producto del sistema.
     *
     * @param id identificador del producto a eliminar
     * @return true si el producto existía y se elimina; false en caso contrario
     */
    @Override
    public boolean borra(int id) {
        try {
            if (!productos.containsKey(id)) {
                throw new DAOException("No existe producto con id: " + id);
            }

            productos.remove(id);
            return true;

        } catch (Exception e) {
            throw new DAOException("Error al eliminar producto", e);
        }
    }

    /**
     * Obtiene el número total de productos almacenados.
     *
     * @return cantidad de productos en el sistema
     */
    @Override
    public int numProductos() {
        return productos.size();
    }
}