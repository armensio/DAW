package daw.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Clase que representa una categoría de productos.
 *
 * Se utiliza para clasificar los productos dentro del sistema.
 * Cada categoría tiene un identificador único y un nombre descriptivo.
 */
@Entity
public class Categoria {

    /**
     * Identificador único de la categoría.
     */
    @Id
    private Integer idCategoria = 0;

    /**
     * Nombre de la categoría.
     */
    private String nombre = "";

    /**
     * Constructor vacío.
     */
    public Categoria() {
    }

    /**
     * Constructor con parámetros.
     *
     * @param idCategoria identificador de la categoría
     * @param nombre nombre de la categoría
     */
    public Categoria(Integer idCategoria, String nombre) {
        this.idCategoria = idCategoria;
        this.nombre = nombre;
    }

    /**
     * Constructor de copia.
     *
     * Crea una nueva instancia a partir de otra categoría existente.
     *
     * @param c categoría a copiar
     */
    public Categoria(Categoria c) {
        this(c.idCategoria, c.nombre);
    }

    /**
     * Devuelve el identificador de la categoría.
     *
     * @return id de la categoría
     */
    public Integer getIdCategoria() {
        return idCategoria;
    }

    /**
     * Establece el identificador de la categoría.
     *
     * @param idCategoria nuevo id de la categoría
     */
    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    /**
     * Devuelve el nombre de la categoría.
     *
     * @return nombre de la categoría
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre de la categoría.
     *
     * @param nombre nuevo nombre de la categoría
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}