package daw.daoInterfaces;

import daw.model.LineaPedido;
import java.util.List;

public interface LineaPedidoDAOInt {
    List<LineaPedido> buscaTodos();
    LineaPedido buscaId(int id);
    List<LineaPedido> buscaPorPedido(int idPedido);
    List<LineaPedido> buscaPorProducto(int idProducto);
    boolean crea(LineaPedido l);
    boolean guarda(LineaPedido l);
    boolean borra(int id);
    void borraPorPedido(int idPedido);
    int numLineas();
}
