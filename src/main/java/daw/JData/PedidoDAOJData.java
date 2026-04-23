package daw.JData;

import daw.daoInterfaces.PedidoDAOInt;
import daw.model.Pedido;
import daw.DAOJData;
import daw.repositories.PedidoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
@DAOJData
public class PedidoDAOJData implements PedidoDAOInt, Serializable {

    private static final Logger logger = Logger.getLogger(PedidoDAOJData.class.getName());

    @Inject
    private PedidoRepository pedidoRepo;

    @Override
    public List<Pedido> buscaTodos() {
        try {
            return pedidoRepo.findAll().toList();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "No se pueden recuperar los pedidos", e);
            return new ArrayList<>();
        }
    }

    @Override
    public Pedido buscaId(int id) {
        try {
            Optional<Pedido> pedido = pedidoRepo.findById(id);
            return pedido.orElse(null);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al buscar pedido con id: " + id, e);
            return null;
        }
    }

    @Override
    public List<Pedido> buscaPorUsuario(int idUsuario) {
        try {
            return pedidoRepo.buscaPorUsuario(idUsuario);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al buscar pedidos por usuario: " + idUsuario, e);
            return new ArrayList<>();
        }
    }

    @Override
    public List<Pedido> buscaPorEstado(String estado) {
        try {
            return pedidoRepo.buscaPorEstado(estado);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al buscar pedidos por estado: " + estado, e);
            return new ArrayList<>();
        }
    }

    @Override
    public boolean crea(Pedido p) {
        try {
            pedidoRepo.insert(p);
            return true;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al crear pedido", e);
            return false;
        }
    }

    @Override
    public boolean guarda(Pedido p) {
        try {
            pedidoRepo.save(p);
            return true;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al actualizar pedido", e);
            return false;
        }
    }

    @Override
    public boolean borra(int id) {
        try {
            pedidoRepo.deleteById(id);
            return true;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al eliminar pedido con id: " + id, e);
            return false;
        }
    }

    @Override
    public int numPedidos() {
        try {
            return pedidoRepo.findAll().toList().size();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al contar pedidos", e);
            return 0;
        }
    }
}
