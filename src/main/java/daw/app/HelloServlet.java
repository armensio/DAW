package daw.app;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

/**
 * Servlet de ejemplo que responde a peticiones HTTP GET.
 *
 * Muestra una página HTML simple con un mensaje de bienvenida
 * y registra información en el log del servidor.
 *
 * Se utiliza principalmente como prueba de funcionamiento del entorno web.
 */
@WebServlet("/hello-servlet")
public class HelloServlet extends HttpServlet {

    /**
     * Mensaje que se mostrará en la respuesta HTML.
     */
    private String message;

    /**
     * Logger para registrar eventos del servlet.
     */
    private final Logger log = Logger.getLogger(HelloServlet.class.getName());

    /**
     * Método de inicialización del servlet.
     *
     * Se ejecuta una sola vez cuando el servlet es cargado por el servidor.
     * Inicializa el mensaje y registra el evento en el log.
     */
    public void init() {
        log.info("HelloServlet initialized");
        message = "Welcome DAW!";
    }

    /**
     * Maneja las peticiones HTTP GET.
     *
     * Genera una respuesta HTML simple que muestra un mensaje de bienvenida
     * y un enlace de retorno a la página principal.
     *
     * @param request  objeto que contiene la petición del cliente
     * @param response objeto que contiene la respuesta al cliente
     * @throws IOException si ocurre un error de entrada/salida
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        String htmlContent = """
            <html>
            <body>
                <h1>Sample Servlet</h1>
                <h2>%s</h2>
                <br/>
                <a href="index.xhtml">Back</a>
            </body>
            </html>
        """.formatted(message);

        out.println(htmlContent);

        log.info("Petición GET recibida desde %s".formatted(request.getRemoteAddr()));
    }
}