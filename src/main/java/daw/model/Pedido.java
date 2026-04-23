package daw.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Date;

/**
 * Clase que representa un pedido realizado por un usuario.
 *
 * Un pedido contiene información sobre la fecha, estado, importe total,
 * dirección de envío y el usuario que lo ha realizado.
 *
 * Se relaciona con:
 * - Usuario (mediante idUsuario)
 * - LineaPedido (como conjunto de productos del pedido)
 */
@Entity
public class Pedido {

    /**
     * Identificador único del pedido.
     */
    @Id
    private Integer idPedido = 0;

    /**
     * Fecha en la que se realizó el pedido.
     */
    private Date fecha;

    /**
     * Estado actual del pedido (ej: pendiente, enviado, entregado).
     */
    private String estado = "";

    /**
     * Importe total del pedido.
     */
    private Float total = 0.0f;

    /**
     * Dirección de envío del pedido.
     */
    private String direccion = "";

    /**
     * Identificador del usuario que realizó el pedido.
     */
    private Integer idUsuario = 0;

    /**
     * Constructor vacío.
     */
    public Pedido() {
    }

    /**
     * Constructor con parámetros.
     *
     * @param idPedido identificador del pedido
     * @param fecha fecha del pedido
     * @param estado estado del pedido
     * @param total importe total
     * @param direccion dirección de envío
     * @param idUsuario identificador del usuario
     */
    public Pedido(Integer idPedido, Date fecha, String estado, Float total, String direccion, Integer idUsuario) {
        this.idPedido = idPedido;
        this.fecha = fecha;
        this.estado = estado;
        this.total = total;
        this.direccion = direccion;
        this.idUsuario = idUsuario;
    }

    /**
     * Constructor de copia.
     *
     * Crea una nueva instancia a partir de otro pedido.
     *
     * @param p pedido a copiar
     */
    public Pedido(Pedido p) {
        this(p.idPedido, p.fecha, p.estado, p.total, p.direccion, p.idUsuario);
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
     * Devuelve la fecha del pedido.
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * Establece la fecha del pedido.
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * Devuelve el estado del pedido.
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Establece el estado del pedido.
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Devuelve el importe total del pedido.
     */
    public Float getTotal() {
        return total;
    }

    /**
     * Establece el importe total del pedido.
     */
    public void setTotal(Float total) {
        this.total = total;
    }

    /**
     * Devuelve la dirección de envío.
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Establece la dirección de envío.
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Devuelve el identificador del usuario.
     */
    public Integer getIdUsuario() {
        return idUsuario;
    }

    /**
     * Establece el identificador del usuario.
     */
    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
}