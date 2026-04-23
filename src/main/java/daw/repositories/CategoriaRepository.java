package daw.repositories;

import daw.model.Categoria;
import jakarta.data.repository.CrudRepository;
import jakarta.data.repository.Repository;

@Repository
public interface CategoriaRepository extends CrudRepository<Categoria, Integer> {
}
