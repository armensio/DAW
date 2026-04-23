package daw.model;

import java.util.Date;

/**
 * Clase que representa un usuario del sistema.
 *
 * Contiene información personal, credenciales de acceso y el rol del usuario.
 *
 * Se utiliza para gestionar autenticación, perfiles y relación con pedidos.
 *
 * Se relaciona con:
 * - Pedido (un usuario puede tener varios pedidos)
 */
public class Usuario {

    /**
     * Identificador único del usuario.
     */
    private Integer idUsuario = 0;

    /**
     * Fecha de nacimiento del usuario.
     */
    private Date fechaNacimiento;

    /**
     * Nombre completo del usuario.
     */
    private String nombre = "";

    /**
     * Dirección de correo electrónico del usuario.
     */
    private String email = "";

    /**
     * Contraseña del usuario.
     */
    private String contrasena = "";

    /**
     * Rol del usuario dentro del sistema (ej: admin, cliente).
     */
    private String rol = "";

    /**
     * Constructor vacío.
     */
    public Usuario() {
    }

    /**
     * Constructor con parámetros.
     *
     * @param idUsuario identificador del usuario
     * @param fechaNacimiento fecha de nacimiento
     * @param nombre nombre completo
     * @param email correo electrónico
     * @param contrasena contraseña
     * @param rol rol del usuario
     */
    public Usuario(Integer idUsuario, Date fechaNacimiento, String nombre,
                   String email, String contrasena, String rol) {
        this.idUsuario = idUsuario;
        this.fechaNacimiento = fechaNacimiento;
        this.nombre = nombre;
        this.email = email;
        this.contrasena = contrasena;
        this.rol = rol;
    }

    /**
     * Constructor de copia.
     *
     * Crea una nueva instancia a partir de otro usuario.
     *
     * @param u usuario a copiar
     */
    public Usuario(Usuario u) {
        this(u.idUsuario, u.fechaNacimiento, u.nombre, u.email, u.contrasena, u.rol);
    }

    /**
     * Devuelve el identificador del usuario.
     */
    public Integer getIdUsuario() {
        return idUsuario;
    }

    /**
     * Establece el identificador del usuario.
     */
    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * Devuelve la fecha de nacimiento.
     */
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Establece la fecha de nacimiento.
     */
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Devuelve el nombre del usuario.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del usuario.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve el email del usuario.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el email del usuario.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Devuelve la contraseña del usuario.
     *
     * NOTA: En un sistema real, la contraseña debería almacenarse cifrada.
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * Establece la contraseña del usuario.
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    /**
     * Devuelve el rol del usuario.
     */
    public String getRol() {
        return rol;
    }

    /**
     * Establece el rol del usuario.
     */
    public void setRol(String rol) {
        this.rol = rol;
    }
}