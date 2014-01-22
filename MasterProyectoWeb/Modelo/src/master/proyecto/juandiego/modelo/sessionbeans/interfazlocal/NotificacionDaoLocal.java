package master.proyecto.juandiego.modelo.sessionbeans.interfazlocal;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import master.proyecto.juandiego.modelo.entitybeans.Notificacion;
import master.proyecto.juandiego.modelo.entitybeans.enumerados.EnumEstadoNotificacion;
import master.proyecto.juandiego.modelo.entitybeans.enumerados.EnumTipoNotificacion;

@Local
public interface NotificacionDaoLocal {
    
    
    Notificacion persistNotificacion(Notificacion notificacion);

    List<Notificacion> getNotificacionFindAll();
    
    int anyadirNotificacion(Long idUsuario,String descripcion, String nombre,EnumTipoNotificacion TipoNotificacion );
    
    List<Notificacion> mostrarNotificacionDeUsuario(Long idUsuario);    
    
    int estadoNotificacionBorrado(Long idNotificacion);
    
    int estadoNotificacionLeida(Long idNotificacion);
}
