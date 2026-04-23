package daw.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
/**
 * Clase que representa un producto del sistema.
 *
 * Un producto contiene información básica como nombre, precio, stock disponible
 * y la categoría a la que pertenece.
 *
 * Se relaciona con:
 * - Categoria (mediante idCategoria)
 * - LineaPedido (como producto dentro de pedidos)
 */
@Entity
public class Producto {

    /**
     * Identificador único del producto.
     */
    @Id
    private Integer idProducto = 0;

    /**
     * Nombre del producto.
     */
    private String nombre = "";

    /**
     * Precio unitario del producto.
     */
    private Float precio = 0.0f;

    /**
     * Cantidad disponible en stock.
     */
    private Integer stock = 0;

    /**
     * Identificador de la categoría a la que pertenece el producto.
     */
    private Integer idCategoria = 0;

    /**
     * Constructor vacío.
     */
    public Producto() {
    }

    /**
     * Constructor con parámetros.
     *
     * @param idProducto identificador del producto
     * @param nombre nombre del producto
     * @param precio precio unitario
     * @param stock cantidad disponible
     * @param idCategoria identificador de la categoría
     */
    public Producto(Integer idProducto, String nombre, Float precio, Integer stock, Integer idCategoria) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.idCategoria = idCategoria;
    }

    /**
     * Constructor de copia.
     *
     * Crea una nueva instancia a partir de otro producto.
     *
     * @param p producto a copiar
     */
    public Producto(Producto p) {
        this(p.idProducto, p.nombre, p.precio, p.stock, p.idCategoria);
    }

    /**
     * Devuelve el identificador del producto.
     */
    public Integer getIdProducto() {
        return idProducto;
    }

    /**
     * Establece el identificador del producto.
     */
    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    /**
     * Devuelve el nombre del producto.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del producto.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve el precio del producto.
     */
    public Float getPrecio() {
        return precio;
    }

    /**
     * Establece el precio del producto.
     */
    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    /**
     * Devuelve el stock disponible.
     */
    public Integer getStock() {
        return stock;
    }

    /**
     * Establece el stock disponible.
     */
    public void setStock(Integer stock) {
        this.stock = stock;
    }

    /**
     * Devuelve el identificador de la categoría.
     */
    public Integer getIdCategoria() {
        return idCategoria;
    }

    /**
     * Establece el identificador de la categoría.
     */
    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }
}