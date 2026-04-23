package daw.daoInterfaces;

import daw.model.Pedido;
import java.util.List;

public interface PedidoDAOInt {
    List<Pedido> buscaTodos();
    Pedido buscaId(int id);
    List<Pedido> buscaPorUsuario(int idUsuario);
    List<Pedido> buscaPorEstado(String estado);
    boolean crea(Pedido p);
    boolean guarda(Pedido p);
    boolean borra(int id);
    int numPedidos();
}
