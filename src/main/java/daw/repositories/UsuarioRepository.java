package daw.repositories;

import daw.model.Usuario;
import jakarta.data.repository.By;
import jakarta.data.repository.CrudRepository;
import jakarta.data.repository.Find;
import jakarta.data.repository.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {

    @Find
    Usuario buscaEmail(@By("email") String email);

    @Find
    List<Usuario> buscaPorRol(@By("rol") String rol);
}