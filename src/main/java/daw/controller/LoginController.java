package daw.controller;

import daw.JData.UsuarioDAOJData;
import daw.model.Usuario;
import daw.DAOJData;
import daw.session.Preferencias;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.IOException;
import java.io.Serializable;

@Named("loginCtrl")
@RequestScoped
public class LoginController implements Serializable {

    private String usuario;
    private String password;

    @Inject
    private Preferencias prefs;

    @Inject
    @DAOJData
    private UsuarioDAOJData usuarioDAO;

    public String login() {

        Usuario u = usuarioDAO.buscaEmail(usuario);

        if (u != null && u.getContrasena().equals(password)) {

            prefs.setUsuario(u.getEmail());
            prefs.setRol(u.getRol());
            prefs.setLogueado(true);

            return "menu?faces-redirect=true";
        }

        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Usuario o contraseña incorrectos"));

        return null;
    }

    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String checkLogin() {
        if (!prefs.isLogueado()) {
            return "login?faces-redirect=true";
        }
        return null;
    }
    public void comprobarLogin() throws IOException {
        if (!prefs.isLogueado()) {
            FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .redirect("login.xhtml");
        }
    }
    public String logout() {
        prefs.setLogueado(false);
        prefs.setUsuario(null);
        return "login?faces-redirect=true";
    }

    public void comprobarAdmin() throws IOException {
        if (!prefs.isLogueado() || !"admin".equals(prefs.getRol())) {
            FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .redirect("login.xhtml");
        }
    }





}
