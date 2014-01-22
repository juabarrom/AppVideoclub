package master.proyecto.juandiego.modelo.sessionbeans.interfazlocal;

import java.util.List;

import javax.ejb.Local;

import master.proyecto.juandiego.modelo.entitybeans.Detallepedido;
import master.proyecto.juandiego.modelo.entitybeans.Juego;
import master.proyecto.juandiego.modelo.entitybeans.Pedido;

@Local
public interface PedidoDaoLocal {
    
    List<Pedido> getPedidoFindAll();

    List<Detallepedido> getDetallepedidoFindAll();
    
    int anyadirPedido(Long idUsuario,Juego articuloCompras,int udsArticuloCompra);
    
    List<Pedido> mostrarPedidosUsuario(Long idUsuario);
    
}
