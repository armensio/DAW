package daw.dao;

import daw.model.Pedido;
import daw.daoInterfaces.PedidoDAOInt;
import jakarta.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import daw.DAOMap;


/**
 * Implementación del DAO (Data Access Object) para la entidad Pedido.
 *
 * Esta clase gestiona el almacenamiento y recuperación de pedidos en memoria,
 * utilizando una estructura Map como repositorio temporal.
 *
 * Proporciona operaciones CRUD y métodos de búsqueda específicos
 * como por usuario o estado del pedido.
 *
 * NOTA: Esta implementación no utiliza base de datos real, por lo que los datos
 * se pierden al reiniciar la aplicación.
 */
@ApplicationScoped
@DAOMap
public class PedidoDAO implements PedidoDAOInt, Serializable {

    /**
     * Mapa que almacena los pedidos, usando como clave su identificador.
     */
    private Map<Integer, Pedido> pedidos;

    /**
     * Contador interno para generar identificadores únicos de pedidos.
     */
    private int pedidoId = 1;

    /**
     * Constructor que inicializa la estructura de almacenamiento en memoria.
     */
    public PedidoDAO() {
        pedidos = new HashMap<>();
    }

    /**
     * Recupera todos los pedidos almacenados.
     *
     * Se devuelve una copia de cada pedido para evitar modificaciones externas
     * sobre los datos internos.
     *
     * @return lista de pedidos; nunca null
     */
    @Override
    public List<Pedido> buscaTodos() {
        List<Pedido> lista = new ArrayList<>();
        for (Pedido p : pedidos.values()) {
            lista.add(new Pedido(p));
        }
        return lista;
    }

    /**
     * Busca un pedido por su identificador.
     *
     * @param id identificador del pedido
     * @return una copia del pedido si existe; null en caso contrario
     */
    @Override
    public Pedido buscaId(int id) {
        Pedido p = pedidos.get(id);
        return (p != null) ? new Pedido(p) : null;
    }

    /**
     * Recupera todos los pedidos realizados por un usuario específico.
     *
     * @param idUsuario identificador del usuario
     * @return lista de pedidos del usuario; nunca null
     */
    @Override
    public List<Pedido> buscaPorUsuario(int idUsuario) {
        List<Pedido> lista = new ArrayList<>();
        for (Pedido p : pedidos.values()) {
            if (p.getIdUsuario().equals(idUsuario)) {
                lista.add(new Pedido(p));
            }
        }
        return lista;
    }

    /**
     * Recupera todos los pedidos que tienen un estado determinado.
     *
     * @param estado estado del pedido (por ejemplo: pendiente, enviado, entregado)
     * @return lista de pedidos con ese estado; nunca null
     */
    @Override
    public List<Pedido> buscaPorEstado(String estado) {
        List<Pedido> lista = new ArrayList<>();
        for (Pedido p : pedidos.values()) {
            if (p.getEstado().equalsIgnoreCase(estado)) {
                lista.add(new Pedido(p));
            }
        }
        return lista;
    }

    /**
     * Crea un nuevo pedido en el sistema.
     *
     * Se asigna automáticamente un identificador único antes de almacenarlo.
     *
     * @param p pedido a crear
     * @return true si la operación se realiza correctamente
     */
    @Override
    public boolean crea(Pedido p) {
        try {
            p.setIdPedido(pedidoId++);
            pedidos.put(p.getIdPedido(), new Pedido(p));
            return true;

        } catch (Exception e) {
            throw new DAOException("Error al crear pedido", e);
        }
    }

    /**
     * Actualiza un pedido existente.
     *
     * Solo se actualiza si el pedido ya existe en el sistema.
     *
     * @param p pedido con los datos actualizados
     * @return true si el pedido existe y se actualiza; false en caso contrario
     */
    @Override
    public boolean guarda(Pedido p) {
        try {
            if (!pedidos.containsKey(p.getIdPedido())) {
                throw new DAOException("El pedido no existe con id: " + p.getIdPedido());
            }

            pedidos.put(p.getIdPedido(), new Pedido(p));
            return true;

        } catch (Exception e) {
            throw new DAOException("Error al actualizar pedido", e);
        }
    }

    /**
     * Elimina un pedido del sistema.
     *
     * @param id identificador del pedido a eliminar
     * @return true si el pedido existía y se elimina; false en caso contrario
     */
    @Override
    public boolean borra(int id) {
        try {
            if (!pedidos.containsKey(id)) {
                throw new DAOException("No existe pedido con id: " + id);
            }

            pedidos.remove(id);
            return true;

        } catch (Exception e) {
            throw new DAOException("Error al eliminar pedido", e);
        }
    }

    /**
     * Obtiene el número total de pedidos almacenados.
     *
     * @return cantidad de pedidos en el sistema
     */
    @Override
    public int numPedidos() {
        return pedidos.size();
    }
}