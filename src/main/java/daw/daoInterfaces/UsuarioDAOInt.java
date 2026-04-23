package daw.daoInterfaces;

import daw.model.Usuario;
import java.util.List;

public interface UsuarioDAOInt {
    List<Usuario> buscaTodos();
    Usuario buscaId(int id);
    Usuario buscaEmail(String email);
    List<Usuario> buscaPorRol(String rol);
    boolean crea(Usuario u);
    boolean guarda(Usuario u);
    boolean borra(int id);
    int numUsuarios();
}
