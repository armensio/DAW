# Changelog

Más información sobre cómo rellenar el fichero en https://keepachangelog.com/es-ES/1.1.0/

## Iteración 0 - 2026-02-20

### Added

- Página de listado de productos `index.html` (Eduardo)
- Desarrollo de README.md (Eduardo)

---

## Iteración 1 - 2026-03-03

### Added

- Transformar las vistas listado.html y alta_producto.html a xhtml (Eduardo)
- Modificación de archivos (Eduardo)
- Creación de vistas menú y pedido (Luis Carlos)

---

## Iteración 2 - 2026-03-18

### Added

- Creación de los modelos (Eduardo)
- Creación de los DAOs (Eduardo)
- Creación de los controladores (Eduardo)
- Creación vista `editar_producto.xhtml` (Eduardo)
- Creación de interfaces para DAOs (Luis Carlos)
- Creación de la clase DAOException para manejo de errores (Pablo)
- Documentación completa de los modelos (Categoria, Producto, Usuario, Pedido, LineaPedido) (Pablo)
- Documentación de DAOs y controladores mediante JavaDoc (Pablo)
- Documentación del servlet y configuración de la aplicación (Pablo)

### Changed

- Modificación de ficheros xhtml para funcionamiento dinámico (Eduardo)
- Adición de botón de edición en `listado.xhtml` (Eduardo)
- Adaptación de los DAOs para integrarlos con el modelo de interfaces (Luis Carlos)
- Implementación de manejo de excepciones en todos los DAO y controladores (Pablo)
- Refactorización de métodos CRUD para eliminar retornos booleanos y usar excepciones (Pablo)
- Despliegue de la aplicación en máquina virtual de Google Cloud
- Configuración de Payara Micro
- Generación del fichero WAR con Maven
- Acceso público mediante IP externa