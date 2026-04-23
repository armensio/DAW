package daw.dao;

import daw.daoInterfaces.CategoriaDAOInt;
import daw.model.Categoria;
import jakarta.enterprise.context.ApplicationScoped;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DAO para la entidad Categoria.
 *
 * Gestiona las categorías en memoria proporcionando operaciones CRUD básicas.
 */
@ApplicationScoped
public class CategoriaDAO implements CategoriaDAOInt, Serializable {

    private Map<Integer, Categoria> categorias;
    private int idCategoria = 1;

    public CategoriaDAO() {
        categorias = new HashMap<>();
    }

    /**
     * Obtiene todas las categorías.
     */
    @Override
    public List<Categoria> buscaTodos() {
        List<Categoria> lista = new ArrayList<>();
        for (Categoria c : categorias.values()) {
            lista.add(new Categoria(c));
        }
        return lista;
    }

    /**
     * Busca una categoría por ID.
     */
    @Override
    public Categoria buscaId(int id) {
        Categoria c = categorias.get(id);
        return (c != null) ? new Categoria(c) : null;
    }

    /**
     * Crea una nueva categoría.
     */
    @Override
    public boolean crea(Categoria c) {
        try {
            c.setIdCategoria(idCategoria++);
            categorias.put(c.getIdCategoria(), new Categoria(c));
            return true;

        } catch (Exception e) {
            throw new DAOException("Error al crear categoría", e);
        }
    }

    /**
     * Actualiza una categoría existente.
     */
    @Override
    public boolean guarda(Categoria c) {
        try {
            if (!categorias.containsKey(c.getIdCategoria())) {
                throw new DAOException("La categoría no existe con id: " + c.getIdCategoria());
            }

            categorias.put(c.getIdCategoria(), new Categoria(c));
            return true;

        } catch (Exception e) {
            throw new DAOException("Error al actualizar categoría", e);
        }
    }

    /**
     * Elimina una categoría.
     */
    @Override
    public boolean borra(int id) {
        try {
            if (!categorias.containsKey(id)) {
                throw new DAOException("No existe categoría con id: " + id);
            }

            categorias.remove(id);
            return true;

        } catch (Exception e) {
            throw new DAOException("Error al eliminar categoría", e);
        }
    }

    /**
     * Número total de categorías.
     */
    @Override
    public int numCategorias() {
        return categorias.size();
    }
}