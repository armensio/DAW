package daw.JData;

import daw.daoInterfaces.LineaPedidoDAOInt;
import daw.model.LineaPedido;
import daw.DAOJData;
import daw.repositories.LineaPedidoRepository;
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
public class LineaPedidoDAOJData implements LineaPedidoDAOInt, Serializable {

    private static final Logger logger = Logger.getLogger(LineaPedidoDAOJData.class.getName());

    @Inject
    private LineaPedidoRepository lineaPedidoRepo;

    @Override
    public List<LineaPedido> buscaTodos() {
        try {
            return lineaPedidoRepo.findAll().toList();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "No se pueden recuperar las líneas", e);
            return new ArrayList<>();
        }
    }

    @Override
    public LineaPedido buscaId(int id) {
        try {
            Optional<LineaPedido> linea = lineaPedidoRepo.findById(id);
            return linea.orElse(null);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al buscar línea con id: " + id, e);
            return null;
        }
    }

    @Override
    public List<LineaPedido> buscaPorPedido(int idPedido) {
        try {
            return lineaPedidoRepo.buscaPorPedido(idPedido);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al buscar líneas por pedido", e);
            return new ArrayList<>();
        }
    }

    @Override
    public List<LineaPedido> buscaPorProducto(int idProducto) {
        try {
            return lineaPedidoRepo.buscaPorProducto(idProducto);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al buscar líneas por producto", e);
            return new ArrayList<>();
        }
    }

    @Override
    public boolean crea(LineaPedido l) {
        try {
            lineaPedidoRepo.insert(l);
            return true;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al crear línea", e);
            return false;
        }
    }

    @Override
    public boolean guarda(LineaPedido l) {
        try {
            lineaPedidoRepo.save(l);
            return true;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al actualizar línea", e);
            return false;
        }
    }

    @Override
    public boolean borra(int id) {
        try {
            lineaPedidoRepo.deleteById(id);
            return true;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al borrar línea", e);
            return false;
        }
    }

    @Override
    public void borraPorPedido(int idPedido) {
        try {
            lineaPedidoRepo.borraPorPedido(idPedido);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al borrar líneas del pedido", e);
        }
    }

    @Override
    public int numLineas() {
        try {
            return lineaPedidoRepo.findAll().toList().size();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al contar líneas", e);
            return 0;
        }
    }
}