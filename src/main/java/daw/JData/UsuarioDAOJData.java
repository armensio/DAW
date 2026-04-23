package daw.JData;

import daw.daoInterfaces.UsuarioDAOInt;
import daw.model.Usuario;
import daw.DAOJData;
import daw.repositories.UsuarioRepository;
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
public class UsuarioDAOJData implements UsuarioDAOInt, Serializable {

    private static final Logger logger = Logger.getLogger(UsuarioDAOJData.class.getName());

    @Inject
    private UsuarioRepository usuarioRepo;

    @Override
    public List<Usuario> buscaTodos() {
        try {
            return usuarioRepo.findAll().toList();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "No se pueden recuperar los usuarios", e);
            return new ArrayList<>();
        }
    }

    @Override
    public Usuario buscaId(int id) {
        try {
            Optional<Usuario> usuario = usuarioRepo.findById(id);
            return usuario.orElse(null);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al buscar usuario con id: " + id, e);
            return null;
        }
    }

    @Override
    public Usuario buscaEmail(String email) {
        try {
            return usuarioRepo.buscaEmail(email);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al buscar usuario por email: " + email, e);
            return null;
        }
    }

    @Override
    public List<Usuario> buscaPorRol(String rol) {
        try {
            return usuarioRepo.buscaPorRol(rol);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al buscar usuarios por rol: " + rol, e);
            return new ArrayList<>();
        }
    }

    @Override
    public boolean crea(Usuario u) {
        try {
            usuarioRepo.insert(u);
            return true;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al crear usuario", e);
            return false;
        }
    }

    @Override
    public boolean guarda(Usuario u) {
        try {
            usuarioRepo.save(u);
            return true;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al actualizar usuario", e);
            return false;
        }
    }

    @Override
    public boolean borra(int id) {
        try {
            usuarioRepo.deleteById(id);
            return true;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al eliminar usuario con id: " + id, e);
            return false;
        }
    }

    @Override
    public int numUsuarios() {
        try {
            return usuarioRepo.findAll().toList().size();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al contar usuarios", e);
            return 0;
        }
    }
}
