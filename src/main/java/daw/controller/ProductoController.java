package daw.controller;

import daw.JData.ProductoDAOJData;
import daw.JData.CategoriaDAOJData;
import daw.dao.DAOException;
import daw.model.Categoria;
import daw.model.Producto;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import daw.DAOJData;

import java.io.Serializable;
import java.util.List;

/**
 * Controlador para la gestión de productos.
 *
 * Se encarga de manejar las operaciones relacionadas con productos desde la vista:
 * creación, edición, eliminación y consulta.
 *
 * Actúa como intermediario entre la capa de presentación (JSF) y la capa DAO.
 */
@Named("productoCtrl")
@ViewScoped
public class ProductoController implements Serializable {

    /**
     * DAO de productos para acceso a datos.
     */
    @Inject
    @DAOJData
    private ProductoDAOJData productoDAO;
    /**
     * DAO de categorías para obtener listas de categorías.
     */
    @Inject
    @DAOJData
    private CategoriaDAOJData categoriaDAO;
    /**
     * Contexto de JSF para mostrar mensajes en la interfaz.
     */
    @Inject private FacesContext facesContext;

    /**
     * Producto actual en uso (alta, edición o detalle).
     */
    private Producto producto;

    /**
     * Lista de productos para mostrar en la vista.
     */
    private List<Producto> productos;

    public ProductoController() { }

    /**
     * Método de inicialización del controlador.
     *
     * Se ejecuta tras la construcción del bean y carga los datos iniciales.
     */
    @PostConstruct
    public void init() {
        producto = new Producto();
        productos = productoDAO.buscaTodos();
    }

    /**
     * Crea un nuevo producto a partir de los datos introducidos en la vista.
     *
     * @return redirección al listado si tiene éxito; permanece en la vista si hay error
     */
    public String crea() {
        try {
            productoDAO.crea(producto);

            facesContext.addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_INFO,
                    "Éxito", "Producto creado correctamente"));

            return "listado?faces-redirect=true";

        } catch (DAOException e) {
            facesContext.addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    "Error", e.getMessage()));
            return "";
        }
    }

    /**
     * Guarda los cambios de un producto existente.
     *
     * @return redirección al listado si tiene éxito; permanece en la vista si hay error
     */
    public String guarda() {
        try {
            productoDAO.guarda(producto);

            facesContext.addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_INFO,
                    "Éxito", "Producto actualizado correctamente"));

            return "listado?faces-redirect=true";

        } catch (DAOException e) {
            facesContext.addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    "Error", e.getMessage()));
            return "";
        }
    }

    /**
     * Elimina un producto seleccionado desde la vista.
     *
     * @param p producto a eliminar
     * @return redirección al listado si tiene éxito; permanece en la vista si hay error
     */
    public String borra(Producto p) {
        try {
            productoDAO.borra(p.getIdProducto());

            productos = productoDAO.buscaTodos();

            facesContext.addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_INFO,
                    "Éxito", "Producto eliminado correctamente"));

            return "listado?faces-redirect=true";

        } catch (DAOException e) {
            facesContext.addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    "Error", e.getMessage()));
            return "";
        }
    }

    /**
     * Recupera un producto a partir del identificador recibido por parámetro.
     *
     * Se utiliza en vistas de detalle o edición mediante parámetros en la URL.
     */
    public void recupera() {
        try {
            Producto encontrado = productoDAO.buscaId(producto.getIdProducto());

            if (encontrado == null) {
                throw new DAOException("No existe un producto con ese ID");
            }

            producto = encontrado;

        } catch (DAOException e) {
            facesContext.addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    "Error", e.getMessage()));
        }
    }

    /**
     * Devuelve el producto actual.
     */
    public Producto getProducto() {
        return producto;
    }

    /**
     * Establece el producto actual.
     */
    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    /**
     * Devuelve la lista de productos.
     */
    public List<Producto> getProductos() {
        return productos;
    }

    /**
     * Devuelve la lista de categorías disponibles.
     */
    public List<Categoria> getCategorias() {
        return categoriaDAO.buscaTodos();
    }
}