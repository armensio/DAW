package daw.app;

import daw.dao.*;
import daw.model.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.event.Startup;
import jakarta.enterprise.inject.Default;
import jakarta.faces.annotation.FacesConfig;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.Date;
import java.util.logging.Logger;

import static java.util.logging.Logger.*;

/**
 * Configuración principal de la aplicación.
 *
 * Esta clase se encarga de:
 * - Inicializar el contexto JSF.
 * - Cargar datos de prueba al arrancar la aplicación.
 * - Servir como bean global accesible desde la vista.
 *
 * Se ejecuta automáticamente al iniciar la aplicación.
 */
@FacesConfig
@Named("app")
@Default
@ApplicationScoped
public class AppConfig {

    /**
     * Logger para registrar eventos de inicio y carga de datos.
     */
    private final Logger log = getLogger(AppConfig.class.getName());

    /**
     * Mensaje de bienvenida accesible desde la vista.
     */
    private final String message = "Welcome DAW!";

    // -------------------------------------------------------------------------
    // Inyección de DAOs
    // -------------------------------------------------------------------------

    /**
     * DAO de categorías.
     */
    @Inject private CategoriaDAO categoriaDAO;

    /**
     * DAO de productos.
     */
    @Inject private ProductoDAO productoDAO;

    /**
     * DAO de usuarios.
     */
    @Inject private UsuarioDAO usuarioDAO;

    /**
     * DAO de pedidos.
     */
    @Inject private PedidoDAO pedidoDAO;

    /**
     * DAO de líneas de pedido.
     */
    @Inject private LineaPedidoDAO lineaPedidoDAO;

    /**
     * Constructor de la aplicación.
     *
     * Se ejecuta al instanciar el bean y registra el inicio.
     */
    public AppConfig() {
        log.info(">>> Application starting...");
    }

    /**
     * Método ejecutado automáticamente cuando la aplicación está lista.
     *
     * Se encarga de cargar los datos de prueba en el sistema.
     *
     * @param event evento de inicio de la aplicación
     */
    public void onStartup(@Observes Startup event) {
        log.info(">>> Application ready, cargando datos de prueba...");

        cargarCategorias();
        cargarProductos();
        cargarUsuarios();
        cargarPedidos();
        cargarLineas();

        log.info(">>> Datos de prueba cargados: "
                + categoriaDAO.numCategorias() + " categorías, "
                + productoDAO.numProductos()   + " productos, "
                + usuarioDAO.numUsuarios()     + " usuarios, "
                + pedidoDAO.numPedidos()       + " pedidos, "
                + lineaPedidoDAO.numLineas()   + " líneas de pedido.");
    }

    // -------------------------------------------------------------------------
    // Métodos de carga de datos
    // -------------------------------------------------------------------------

    /**
     * Carga categorías de prueba en el sistema.
     */
    private void cargarCategorias() {
        categoriaDAO.crea(new Categoria(0, "Legumbres"));
        categoriaDAO.crea(new Categoria(0, "Cereales"));
        categoriaDAO.crea(new Categoria(0, "Conservas"));
        categoriaDAO.crea(new Categoria(0, "Lácteos"));

        log.info(">>> Categorías cargadas");
    }

    /**
     * Carga productos de prueba asociados a categorías.
     */
    private void cargarProductos() {
        productoDAO.crea(new Producto(0, "Lentejas 1kg",    1.20f, 50, 1));
        productoDAO.crea(new Producto(0, "Garbanzos 1kg",   1.35f, 40, 1));
        productoDAO.crea(new Producto(0, "Alubias blancas", 1.50f, 30, 1));

        productoDAO.crea(new Producto(0, "Arroz redondo 1kg", 0.95f, 80, 2));
        productoDAO.crea(new Producto(0, "Macarrones 500g",   0.85f, 60, 2));
        productoDAO.crea(new Producto(0, "Espaguetis 500g",   0.90f, 60, 2));

        productoDAO.crea(new Producto(0, "Tomate frito 400g", 0.75f, 100, 3));
        productoDAO.crea(new Producto(0, "Atún en aceite",    1.10f, 70,  3));

        productoDAO.crea(new Producto(0, "Leche entera 1L",   0.99f, 90, 4));
        productoDAO.crea(new Producto(0, "Yogur natural x4",  1.25f, 45, 4));

        log.info(">>> Productos cargados");
    }

    /**
     * Carga usuarios de prueba.
     */
    private void cargarUsuarios() {
        usuarioDAO.crea(new Usuario(0, new Date(0L),
                "Admin Principal", "admin@tienda.com", "admin123", "admin"));

        usuarioDAO.crea(new Usuario(0, new Date(0L),
                "Ana García", "ana@email.com", "pass1234", "cliente"));

        usuarioDAO.crea(new Usuario(0, new Date(0L),
                "Luis Martínez", "luis@email.com", "pass5678", "cliente"));

        usuarioDAO.crea(new Usuario(0, new Date(0L),
                "María López", "maria@email.com", "pass9012", "cliente"));

        log.info(">>> Usuarios cargados");
    }

    /**
     * Carga pedidos de prueba asociados a usuarios.
     */
    private void cargarPedidos() {
        Date hoy = new Date();

        pedidoDAO.crea(new Pedido(0, hoy, "entregado", 3.15f,
                "Calle Mayor 1, Madrid", 2));

        pedidoDAO.crea(new Pedido(0, hoy, "enviado", 5.40f,
                "Av. Constitución 5, Sevilla", 3));

        pedidoDAO.crea(new Pedido(0, hoy, "pendiente", 2.20f,
                "Calle Mayor 1, Madrid", 2));

        log.info(">>> Pedidos cargados");
    }

    /**
     * Carga líneas de pedido asociadas a pedidos y productos.
     */
    private void cargarLineas() {
        lineaPedidoDAO.crea(new LineaPedido(0, 2, 1, 1, 1));
        lineaPedidoDAO.crea(new LineaPedido(0, 1, 1, 1, 4));

        lineaPedidoDAO.crea(new LineaPedido(0, 3, 1, 2, 5));
        lineaPedidoDAO.crea(new LineaPedido(0, 2, 1, 2, 7));

        lineaPedidoDAO.crea(new LineaPedido(0, 1, 1, 3, 2));
        lineaPedidoDAO.crea(new LineaPedido(0, 1, 1, 3, 9));

        log.info(">>> Líneas de pedido cargadas");
    }

    /**
     * Devuelve el mensaje de bienvenida.
     */
    public String getWelcomeMessage() {
        return message;
    }
}