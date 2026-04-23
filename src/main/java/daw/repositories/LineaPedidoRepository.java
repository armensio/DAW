package daw.repositories;

import daw.model.LineaPedido;
import jakarta.data.repository.By;
import jakarta.data.repository.CrudRepository;
import jakarta.data.repository.Delete;
import jakarta.data.repository.Find;
import jakarta.data.repository.Repository;

import java.util.List;

@Repository
public interface LineaPedidoRepository extends CrudRepository<LineaPedido, Integer> {

    @Find
    List<LineaPedido> buscaPorPedido(@By("idPedido") Integer idPedido);

    @Find
    List<LineaPedido> buscaPorProducto(@By("idProducto") Integer idProducto);

    @Delete
    void borraPorPedido(@By("idPedido") Integer idPedido);
}
