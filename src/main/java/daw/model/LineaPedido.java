package daw.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/**
 * Clase que representa una línea de pedido.
 *
 * Cada línea de pedido corresponde a un producto incluido en un pedido,
 * indicando la cantidad y el precio unitario en el momento de la compra.
 *
 * Sirve como relación entre Pedido y Producto.
 */
@Entity
public class LineaPedido {

    /**
     * Identificador único de la línea de pedido.
     */
    @Id
    private Integer idLinea = 0;

    /**
     * Cantidad de unidades del producto.
     */
    private Integer cantidad = 0;

    /**
     * Precio unitario del producto en esta línea.
     */
    private Double precioUnitario;    /**
     * Identificador del pedido al que pertenece esta línea.
     */
    private Integer idPedido = 0;

    /**
     * Identificador del producto asociado a esta línea.
     */
    private Integer idProducto = 0;

    /**
     * Constructor vacío.
     */
    public LineaPedido() {
    }

    /**
     * Constructor con parámetros.
     *
     * @param idLinea identificador de la línea
     * @param cantidad cantidad del producto
     * @param precioUnitario precio unitario
     * @param idPedido identificador del pedido
     * @param idProducto identificador del producto
     */
    public LineaPedido(Integer idLinea, Integer cantidad, Double precioUnitario, Integer idPedido, Integer idProducto) {
        this.idLinea = idLinea;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.idPedido = idPedido;
        this.idProducto = idProducto;
    }

    /**
     * Constructor de copia.
     *
     * Crea una nueva instancia a partir de otra línea de pedido.
     *
     * @param l línea de pedido a copiar
     */
    public LineaPedido(LineaPedido l) {
        this(l.idLinea, l.cantidad, l.precioUnitario, l.idPedido, l.idProducto);
    }

    /**
     * Devuelve el identificador de la línea.
     */
    public Integer getIdLinea() {
        return idLinea;
    }

    /**
     * Establece el identificador de la línea.
     */
    public void setIdLinea(Integer idLinea) {
        this.idLinea = idLinea;
    }

    /**
     * Devuelve la cantidad del producto.
     */
    public Integer getCantidad() {
        return cantidad;
    }

    /**
     * Establece la cantidad del producto.
     */
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Devuelve el precio unitario del producto.
     */
    public Double getPrecioUnitario() {
        return precioUnitario;
    }
    /**
     * Establece el precio unitario del producto.
     */
    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    /**
     * Devuelve el identificador del pedido.
     */
    public Integer getIdPedido() {
        return idPedido;
    }

    /**
     * Establece el identificador del pedido.
     */
    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
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
}