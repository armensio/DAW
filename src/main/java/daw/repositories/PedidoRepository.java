package daw.repositories;

import daw.model.Pedido;
import jakarta.data.repository.By;
import jakarta.data.repository.CrudRepository;
import jakarta.data.repository.Find;
import jakarta.data.repository.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends CrudRepository<Pedido, Integer> {

    @Find
    List<Pedido> buscaPorUsuario(@By("idUsuario") Integer idUsuario);

    @Find
    List<Pedido> buscaPorEstado(@By("estado") String estado);
}
