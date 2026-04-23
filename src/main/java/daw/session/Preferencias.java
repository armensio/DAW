package daw.session;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;

@Named("prefs")
@SessionScoped
public class Preferencias implements Serializable {

    private String usuario;
    private boolean logueado;

    private String rol;

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public boolean isLogueado() {
        return logueado;
    }

    public void setLogueado(boolean logueado) {
        this.logueado = logueado;
    }
}