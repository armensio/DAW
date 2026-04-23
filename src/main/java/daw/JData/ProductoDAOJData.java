package daw.JData;

import daw.daoInterfaces.ProductoDAOInt;
import daw.model.Producto;
import daw.DAOJData;
import daw.repositories.ProductoRepository;
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
public class ProductoDAOJData implements ProductoDAOInt, Serializable {

    private static final Logger logger = Logger.getLogger(ProductoDAOJData.class.getName());

    @Inject
    private ProductoRepository productoRepo;

    @Override
    public List<Producto> buscaTodos() {
        try {
            return productoRepo.findAll().toList();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "No se pueden recuperar los productos", e);
            return new ArrayList<>();
        }
    }

    @Override
    public Producto buscaId(int id) {
        try {
            Optional<Producto> producto = productoRepo.findById(id);
            return producto.orElse(null);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al buscar producto con id: " + id, e);
            return null;
        }
    }

    @Override
    public List<Producto> buscaPorCategoria(int idCategoria) {
        try {
            return productoRepo.buscaPorCategoria(idCategoria);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al buscar productos por categoría: " + idCategoria, e);
            return new ArrayList<>();
        }
    }

    @Override
    public boolean crea(Producto p) {
        try {
            productoRepo.insert(p);
            return true;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al crear producto", e);
            return false;
        }
    }

    @Override
    public boolean guarda(Producto p) {
        try {
            productoRepo.save(p);
            return true;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al actualizar producto", e);
            return false;
        }
    }

    @Override
    public boolean borra(int id) {
        try {
            productoRepo.deleteById(id);
            return true;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al eliminar producto con id: " + id, e);
            return false;
        }
    }

    @Override
    public int numProductos() {
        try {
            return productoRepo.findAll().toList().size();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al contar productos", e);
            return 0;
        }
    }
}
