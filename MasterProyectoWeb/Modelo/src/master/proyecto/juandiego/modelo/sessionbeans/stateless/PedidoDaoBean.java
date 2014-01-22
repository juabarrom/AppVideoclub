package master.proyecto.juandiego.modelo.sessionbeans.stateless;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import javax.persistence.Query;

import master.proyecto.juandiego.modelo.entitybeans.Detallepedido;
import master.proyecto.juandiego.modelo.entitybeans.Existencia;
import master.proyecto.juandiego.modelo.entitybeans.Juego;
import master.proyecto.juandiego.modelo.entitybeans.Notificacion;
import master.proyecto.juandiego.modelo.entitybeans.Pedido;
import master.proyecto.juandiego.modelo.entitybeans.Udsexistencia;
import master.proyecto.juandiego.modelo.entitybeans.Usuario;
import master.proyecto.juandiego.modelo.entitybeans.enumerados.EnumTipoNotificacion;
import master.proyecto.juandiego.modelo.sessionbeans.interfazlocal.PedidoDaoLocal;

@Stateless(name = "PedidoDao", mappedName = "MasterProyectoWeb-Modelo-PedidoDao")
public class PedidoDaoBean implements PedidoDaoLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "Modelo")
    private EntityManager emPedido;

    public PedidoDaoBean() {
    }

    
    /** <code>select o from Pedido o</code> */
    public List<Pedido> getPedidoFindAll() {
        return emPedido.createNamedQuery("Pedido.findAll").getResultList();
    }

    /** <code>select o from Detallepedido o</code> */
    public List<Detallepedido> getDetallepedidoFindAll() {
        return emPedido.createNamedQuery("Detallepedido.findAll").getResultList();
    }

    @Override
    public int anyadirPedido(Long idUsuario, Juego articuloCompras, int udsArticuloCompra) {
        
        int res = 0;
    
        Usuario usuario = emPedido.find(Usuario.class, idUsuario);
        Existencia existencia = emPedido.find(Existencia.class, articuloCompras.getIdExistencia());
        
        if(usuario != null){
            res = 1;
            //TIEMPO
            Calendar cal = new GregorianCalendar();
            String fecha;
            String anyo = String.valueOf(cal.get(Calendar.YEAR));
            String mes = String.valueOf(cal.get(Calendar.MONTH));
            String dia = String.valueOf(cal.get(Calendar.DATE));
            fecha = dia+"/"+mes+"/"+anyo;
            
            //CREA PEDIDO
            Pedido pedido = new Pedido(fecha,fecha);
            
            usuario.addPedido(pedido);
            
              
            Integer descuento = existencia.getDescuento();
            Integer puntosTotales= existencia.getPuntosexistencias().getPuntosJuego()*udsArticuloCompra;
            usuario.setPuntos(usuario.getPuntos() - (puntosTotales - descuento));
            emPedido.persist(usuario);
            
            
            pedido.setUsuario(usuario);
            emPedido.persist(pedido);
            
            Detallepedido detalle = new Detallepedido(udsArticuloCompra,existencia,pedido);

            emPedido.persist(detalle);
            
            //QUITAR LAS UNIDADES DEL PEDIDO EN EXISTENCIA
            existencia.getUdsexistencia().setUdsJuego(existencia.getUdsexistencia().getUdsJuego() -udsArticuloCompra);
            emPedido.persist(existencia);
            
            //NOTIFICACION
            
            Notificacion not = new Notificacion("Gracias por el pedido", fecha,"Recibo de Compra", EnumTipoNotificacion.COMPRAS,usuario); 
            usuario.addNotificacion(not);
            emPedido.persist(usuario);
            emPedido.persist(not);
            
        } 



        return res;
    }

    @Override
    public List<Pedido> mostrarPedidosUsuario(Long idUsuario) {
        List<Pedido> pedido = new ArrayList<Pedido>();
        Query query = emPedido.createQuery("select ");
        List<Pedido> resultado = (List<Pedido>) query.getResultList();
        if(!pedido.isEmpty()){
            pedido.addAll(resultado);
        }
        
        return pedido;
    }
    
}
