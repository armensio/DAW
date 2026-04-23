package daw.dao;

import daw.model.Usuario;
import daw.daoInterfaces.UsuarioDAOInt;
import jakarta.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementación del DAO (Data Access Object) para la entidad Usuario.
 *
 * Esta clase gestiona el almacenamiento y recuperación de usuarios en memoria,
 * utilizando una estructura Map como repositorio temporal.
 *
 * Proporciona operaciones CRUD y métodos de búsqueda específicos como
 * por email o rol.
 *
 * NOTA: Esta implementación no utiliza base de datos real, por lo que los datos
 * se pierden al reiniciar la aplicación.
 */
@ApplicationScoped
public class UsuarioDAO implements UsuarioDAOInt, Serializable {

    /**
     * Mapa que almacena los usuarios, usando como clave su identificador.
     */
    private Map<Integer, Usuario> usuarios;

    /**
     * Contador interno para generar identificadores únicos de usuarios.
     */
    private int idUsuario = 1;

    /**
     * Constructor que inicializa la estructura de almacenamiento en memoria.
     */
    public UsuarioDAO() {
        usuarios = new HashMap<>();
    }

    /**
     * Recupera todos los usuarios almacenados.
     *
     * Se devuelve una copia de cada usuario para evitar modificaciones externas
     * sobre los datos internos.
     *
     * @return lista de usuarios; nunca null
     */
    @Override
    public List<Usuario> buscaTodos() {
        List<Usuario> lista = new ArrayList<>();
        for (Usuario u : usuarios.values()) {
            lista.add(new Usuario(u));
        }
        return lista;
    }

    /**
     * Busca un usuario por su identificador.
     *
     * @param id identificador del usuario
     * @return una copia del usuario si existe; null en caso contrario
     */
    @Override
    public Usuario buscaId(int id) {
        Usuario u = usuarios.get(id);
        return (u != null) ? new Usuario(u) : null;
    }

    /**
     * Busca un usuario por su dirección de correo electrónico.
     *
     * @param email email del usuario
     * @return usuario encontrado o null si no existe
     */
    @Override
    public Usuario buscaEmail(String email) {
        for (Usuario u : usuarios.values()) {
            if (u.getEmail().equalsIgnoreCase(email)) {
                return new Usuario(u);
            }
        }
        return null;
    }

    /**
     * Recupera todos los usuarios que tienen un rol específico.
     *
     * @param rol rol del usuario (por ejemplo: admin, cliente)
     * @return lista de usuarios con ese rol; nunca null
     */
    @Override
    public List<Usuario> buscaPorRol(String rol) {
        List<Usuario> lista = new ArrayList<>();
        for (Usuario u : usuarios.values()) {
            if (u.getRol().equalsIgnoreCase(rol)) {
                lista.add(new Usuario(u));
            }
        }
        return lista;
    }

    /**
     * Crea un nuevo usuario en el sistema.
     *
     * Se asigna automáticamente un identificador único antes de almacenarlo.
     *
     * @param u usuario a crear
     * @return true si la operación se realiza correctamente
     */
    @Override
    public boolean crea(Usuario u) {
        try {
            u.setIdUsuario(idUsuario++);
            usuarios.put(u.getIdUsuario(), new Usuario(u));
            return true;

        } catch (Exception e) {
            throw new DAOException("Error al crear usuario", e);
        }
    }

    /**
     * Actualiza un usuario existente.
     *
     * Solo se actualiza si el usuario ya existe en el sistema.
     *
     * @param u usuario con los datos actualizados
     * @return true si el usuario existe y se actualiza; false en caso contrario
     */
    @Override
    public boolean guarda(Usuario u) {
        try {
            if (!usuarios.containsKey(u.getIdUsuario())) {
                throw new DAOException("El usuario no existe con id: " + u.getIdUsuario());
            }

            usuarios.put(u.getIdUsuario(), new Usuario(u));
            return true;

        } catch (Exception e) {
            throw new DAOException("Error al actualizar usuario", e);
        }
    }

    /**
     * Elimina un usuario del sistema.
     *
     * @param id identificador del usuario a eliminar
     * @return true si el usuario existía y se elimina; false en caso contrario
     */
    @Override
    public boolean borra(int id) {
        try {
            if (!usuarios.containsKey(id)) {
                throw new DAOException("No existe usuario con id: " + id);
            }

            usuarios.remove(id);
            return true;

        } catch (Exception e) {
            throw new DAOException("Error al eliminar usuario", e);
        }
    }
    /**
     * Obtiene el número total de usuarios almacenados.
     *
     * @return cantidad de usuarios en el sistema
     */
    @Override
    public int numUsuarios() {
        return usuarios.size();
    }
}