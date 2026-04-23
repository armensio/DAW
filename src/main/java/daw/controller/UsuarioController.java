package daw.controller;

import daw.dao.UsuarioDAO;
import daw.dao.DAOException;
import daw.model.Usuario;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;

/**
 * Controlador para la gestión de usuarios.
 *
 * Se encarga de manejar las operaciones relacionadas con el perfil del usuario,
 * como la carga de datos y la actualización de información.
 *
 * Actúa como intermediario entre la vista (JSF) y la capa DAO.
 */
@Named("usuarioCtrl")
@ViewScoped
public class UsuarioController implements Serializable {

    /**
     * DAO de usuarios para acceso a datos.
     */
    @Inject private UsuarioDAO usuarioDAO;

    /**
     * Contexto de JSF para mostrar mensajes en la interfaz.
     */
    @Inject private FacesContext facesContext;

    /**
     * Usuario actual en sesión (perfil).
     */
    private Usuario usuario;

    public UsuarioController() { }

    /**
     * Método de inicialización del controlador.
     *
     * Actualmente carga un usuario con ID fijo (2).
     * NOTA: Esto debe cambiar cuando se implemente un sistema de login real.
     */
    @PostConstruct
    public void init() {
        usuario = usuarioDAO.buscaId(2);

        if (usuario == null) {
            usuario = new Usuario();
        }
    }

    /**
     * Guarda los cambios del perfil del usuario.
     *
     * @return redirección a la vista de perfil si tiene éxito;
     *         permanece en la vista actual si ocurre un error
     */
    public String guarda() {
        try {
            usuarioDAO.guarda(usuario);

            facesContext.addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_INFO,
                    "Éxito", "Perfil actualizado correctamente"));

            return "perfil?faces-redirect=true";

        } catch (DAOException e) {
            facesContext.addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    "Error", e.getMessage()));
            return "";
        }
    }

    /**
     * Devuelve el usuario actual.
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Establece el usuario actual.
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}