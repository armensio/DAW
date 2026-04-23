package daw.JData;

import daw.daoInterfaces.CategoriaDAOInt;
import daw.model.Categoria;
import daw.DAOJData;
import daw.repositories.CategoriaRepository;
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
public class CategoriaDAOJData implements CategoriaDAOInt, Serializable {

    private static final Logger logger = Logger.getLogger(CategoriaDAOJData.class.getName());

    @Inject
    private CategoriaRepository categoriaRepo;

    @Override
    public List<Categoria> buscaTodos() {
        try {
            return categoriaRepo.findAll().toList();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "No se pueden recuperar las categorías", e);
            return new ArrayList<>();
        }
    }

    @Override
    public Categoria buscaId(int id) {
        try {
            Optional<Categoria> categoria = categoriaRepo.findById(id);
            return categoria.orElse(null);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al buscar categoría con id: " + id, e);
            return null;
        }
    }

    @Override
    public boolean crea(Categoria c) {
        try {
            categoriaRepo.insert(c);
            return true;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al crear categoría", e);
            return false;
        }
    }

    @Override
    public boolean guarda(Categoria c) {
        try {
            categoriaRepo.save(c);
            return true;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al actualizar categoría", e);
            return false;
        }
    }

    @Override
    public boolean borra(int id) {
        try {
            categoriaRepo.deleteById(id);
            return true;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al eliminar categoría con id: " + id, e);
            return false;
        }
    }

    @Override
    public int numCategorias() {
        try {
            return categoriaRepo.findAll().toList().size();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al contar categorías", e);
            return 0;
        }
    }
}
