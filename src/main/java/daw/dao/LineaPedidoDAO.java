package daw.dao;

import daw.model.LineaPedido;
import daw.daoInterfaces.LineaPedidoDAOInt;
import jakarta.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import daw.DAOMap;

/**
 * DAO para la entidad LineaPedido.
 *
 * Gestiona las líneas de pedido en memoria y permite operaciones CRUD
 * y búsquedas por pedido o producto.
 */
@ApplicationScoped
@DAOMap
public class LineaPedidoDAO implements LineaPedidoDAOInt, Serializable {

    private Map<Integer, LineaPedido> lineas;
    private int lineaId = 1;

    public LineaPedidoDAO() {
        lineas = new HashMap<>();
    }

    /**
     * Obtiene todas las líneas de pedido.
     */
    @Override
    public List<LineaPedido> buscaTodos() {
        List<LineaPedido> lista = new ArrayList<>();
        for (LineaPedido l : lineas.values()) {
            lista.add(new LineaPedido(l));
        }
        return lista;
    }

    /**
     * Busca una línea por su ID.
     */
    @Override
    public LineaPedido buscaId(int id) {
        LineaPedido l = lineas.get(id);
        return (l != null) ? new LineaPedido(l) : null;
    }

    /**
     * Obtiene todas las líneas asociadas a un pedido.
     */
    @Override
    public List<LineaPedido> buscaPorPedido(int idPedido) {
        List<LineaPedido> lista = new ArrayList<>();
        for (LineaPedido l : lineas.values()) {
            if (l.getIdPedido().equals(idPedido)) {
                lista.add(new LineaPedido(l));
            }
        }
        return lista;
    }

    /**
     * Obtiene todas las líneas asociadas a un producto.
     */
    @Override
    public List<LineaPedido> buscaPorProducto(int idProducto) {
        List<LineaPedido> lista = new ArrayList<>();
        for (LineaPedido l : lineas.values()) {
            if (l.getIdProducto().equals(idProducto)) {
                lista.add(new LineaPedido(l));
            }
        }
        return lista;
    }

    /**
     * Crea una nueva línea de pedido.
     */
    @Override
    public boolean crea(LineaPedido l) {
        try {
            l.setIdLinea(lineaId++);
            lineas.put(l.getIdLinea(), new LineaPedido(l));
            return true;

        } catch (Exception e) {
            throw new DAOException("Error al crear línea de pedido", e);
        }
    }

    /**
     * Actualiza una línea existente.
     */
    @Override
    public boolean guarda(LineaPedido l) {
        try {
            if (!lineas.containsKey(l.getIdLinea())) {
                throw new DAOException("La línea no existe con id: " + l.getIdLinea());
            }

            lineas.put(l.getIdLinea(), new LineaPedido(l));
            return true;

        } catch (Exception e) {
            throw new DAOException("Error al actualizar línea de pedido", e);
        }
    }

    /**
     * Elimina una línea por ID.
     */
    @Override
    public boolean borra(int id) {
        try {
            if (!lineas.containsKey(id)) {
                throw new DAOException("No existe línea con id: " + id);
            }

            lineas.remove(id);
            return true;

        } catch (Exception e) {
            throw new DAOException("Error al eliminar línea de pedido", e);
        }
    }

    /**
     * Elimina todas las líneas asociadas a un pedido.
     */
    @Override
    public void borraPorPedido(int idPedido) {
        try {
            lineas.entrySet().removeIf(entry ->
                    entry.getValue().getIdPedido().equals(idPedido)
            );
        } catch (Exception e) {
            throw new DAOException("Error al eliminar líneas del pedido", e);
        }
    }

    /**
     * Número total de líneas.
     */
    @Override
    public int numLineas() {
        return lineas.size();
    }
}