package daw.repositories;

import daw.model.Producto;
import jakarta.data.repository.By;
import jakarta.data.repository.CrudRepository;
import jakarta.data.repository.Find;
import jakarta.data.repository.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends CrudRepository<Producto, Integer> {

    @Find
    List<Producto> buscaPorCategoria(@By("idCategoria") Integer idCategoria);
}