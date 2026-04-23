package daw.daoInterfaces;

import daw.model.Categoria;
import java.util.List;

public interface CategoriaDAOInt {
    List<Categoria> buscaTodos();
    Categoria buscaId(int id);
    boolean crea(Categoria c);
    boolean guarda(Categoria c);
    boolean borra(int id);
    int numCategorias();
}
