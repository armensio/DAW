package daw.controller;

import daw.dao.LineaPedidoDAO;
import daw.dao.DAOException;
import daw.dao.PedidoDAO;
import daw.model.LineaPedido;
import daw.model.Pedido;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

/**
 * Controlador para la gestión de pedidos.
 *
 * Se encarga de manejar las operaciones relacionadas con pedidos desde la vista:
 * consulta, eliminación y carga de detalles.
 *
 * Actúa como intermediario entre la capa de presentación (JSF) y la capa DAO.
 */
@Named("pedidoCtrl")
@ViewScoped
public class PedidoController implements Serializable {

    /**
     * DAO de pedidos.
     */
    @Inject private PedidoDAO pedidoDAO;

    /**
     * DAO de líneas de pedido.
     */
    @Inject private LineaPedidoDAO lineaPedidoDAO;

    /**
     * Contexto de JSF para mostrar mensajes en la interfaz.
     */
    @Inject private FacesContext facesContext;

    /**
     * Pedido actual en uso (detalle o edición).
     */
    private Pedido pedido;

    /**
     * Lista de pedidos para el listado.
     */
    private List<Pedido> pedidos;

    /**
     * Líneas asociadas al pedido actual.
     */
    private List<LineaPedido> lineasPedidoActual;

    public PedidoController() { }

    /**
     * Inicializa el controlador cargando los pedidos.
     */
    @PostConstruct
    public void init() {
        pedido = new Pedido();
        pedidos = pedidoDAO.buscaTodos();
    }

    /**
     * Recupera un pedido y sus líneas a partir del ID.
     *
     * Se utiliza en vistas de detalle mediante parámetros en la URL.
     */
    public void recupera() {
        try {
            Pedido encontrado = pedidoDAO.buscaId(pedido.getIdPedido());

            if (encontrado == null) {
                throw new DAOException("Pedido no encontrado");
            }

            pedido = encontrado;
            lineasPedidoActual = lineaPedidoDAO.buscaPorPedido(pedido.getIdPedido());

        } catch (DAOException e) {
            facesContext.addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    "Error", e.getMessage()));
        }
    }

    /**
     * Elimina un pedido y todas sus líneas asociadas.
     *
     * @param p pedido a eliminar
     * @return redirección al listado si tiene éxito; permanece en la vista si hay error
     */
    public String borra(Pedido p) {
        try {
            lineaPedidoDAO.borraPorPedido(p.getIdPedido());
            pedidoDAO.borra(p.getIdPedido());

            pedidos = pedidoDAO.buscaTodos();

            facesContext.addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_INFO,
                    "Éxito", "Pedido eliminado correctamente"));

            return "pedidosLista?faces-redirect=true";

        } catch (DAOException e) {
            facesContext.addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    "Error", e.getMessage()));
            return "";
        }
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public List<LineaPedido> getLineasPedidoActual() {
        return lineasPedidoActual;
    }
}