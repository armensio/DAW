package daw.app;

import daw.JData.*;
import daw.model.*;
import daw.DAOJData;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.event.Startup;
import jakarta.faces.annotation.FacesConfig;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

import jakarta.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import jakarta.security.enterprise.authentication.mechanism.http.LoginToContinue;
import jakarta.annotation.PostConstruct;

@FacesConfig
@Named("app")
@ApplicationScoped
@CustomFormAuthenticationMechanismDefinition(
        loginToContinue = @LoginToContinue(
                loginPage = "/login.xhtml",
                errorPage = "/login.xhtml",
                useForwardToLogin = false
        )
)
public class AppConfig {

    private final Logger log = getLogger(AppConfig.class.getName());

    private final String message = "Welcome DAW!";

    @Inject @DAOJData
    private CategoriaDAOJData categoriaDAO;

    @Inject @DAOJData
    private ProductoDAOJData productoDAO;

    @Inject @DAOJData
    private UsuarioDAOJData usuarioDAO;

    @Inject @DAOJData
    private PedidoDAOJData pedidoDAO;

    @Inject @DAOJData
    private LineaPedidoDAOJData lineaPedidoDAO;

    public AppConfig() {
        log.info(">>> Application starting...");
    }

    // 🔥 SOLO UN onStartup y DENTRO de la clase
    public void onStartup(@Observes Startup event) {

        if (usuarioDAO.numUsuarios() == 0) {

            Usuario u = new Usuario();
            u.setIdUsuario(1);
            u.setNombre("Admin");
            u.setEmail("admin");
            u.setContrasena("1234");
            u.setRol("admin");

            usuarioDAO.crea(u);

            log.info("Usuario admin creado en BD");
        }
    }

    public String getWelcomeMessage() {
        return message;
    }
}