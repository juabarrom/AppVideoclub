package master.proyecto.juandiego.vistacontrolador;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;

import javax.faces.application.Application;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import javax.faces.context.FacesContext;

import master.proyecto.juandiego.modelo.entitybeans.Notificacion;
import master.proyecto.juandiego.modelo.entitybeans.Pedido;
import master.proyecto.juandiego.modelo.sessionbeans.interfazlocal.NotificacionDaoLocal;
import master.proyecto.juandiego.modelo.sessionbeans.interfazlocal.PedidoDaoLocal;

@ManagedBean(name="notificacionUsuarioBacking")
@RequestScoped
public class NotificacionUsuario_Backing {
    
    private List<Pedido> listaPedido;
    private List<Notificacion> listaNotificacion;
    
    private String idUsuario;
    
    //EJB
    @EJB
    private PedidoDaoLocal pDao;
    
    @EJB
    private NotificacionDaoLocal nDao;
    
    public NotificacionUsuario_Backing() {
        
    }
    

    public void setListaPedido(List<Pedido> listaPedido) {
        this.listaPedido = listaPedido;
    }

    public List<Pedido> getListaPedido() {
        return listaPedido;
    }

    public void setListaNotificacion(List<Notificacion> listaNotificacion) {
        this.listaNotificacion = listaNotificacion;
    }

    public List<Notificacion> getListaNotificacion() {
        return listaNotificacion;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
        /*
        List<Pedido> pedidoTemp = new ArrayList<Pedido>();
        Long id = Long.parseLong(idUsuario);
        
        pedidoTemp.addAll(pDao.mostrarPedidosUsuario(id));
        setListaPedido(pedidoTemp);
        
        List<Notificacion> notTemp = new ArrayList<Notificacion>();
        notTemp.addAll(nDao.mostrarNotificacionDeUsuario(id));
        setListaNotificacion(notTemp);
        */
    }

    public String getIdUsuario() {
        return idUsuario;
    }
}
