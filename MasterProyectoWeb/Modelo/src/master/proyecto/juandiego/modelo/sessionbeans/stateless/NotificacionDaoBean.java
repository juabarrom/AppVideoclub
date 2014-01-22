package master.proyecto.juandiego.modelo.sessionbeans.stateless;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import master.proyecto.juandiego.modelo.entitybeans.Notificacion;
import master.proyecto.juandiego.modelo.entitybeans.Usuario;
import master.proyecto.juandiego.modelo.entitybeans.enumerados.EnumEstadoNotificacion;
import master.proyecto.juandiego.modelo.entitybeans.enumerados.EnumTipoNotificacion;
import master.proyecto.juandiego.modelo.sessionbeans.interfazlocal.NotificacionDaoLocal;

@Stateless(name = "NotificacionDao", mappedName = "MasterProyectoWeb-Modelo-NotificacionDao")
public class NotificacionDaoBean implements NotificacionDaoLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "Modelo")
    private EntityManager emNotificacion;

    public NotificacionDaoBean() {
    }

    public Notificacion persistNotificacion(Notificacion notificacion) {
        emNotificacion.persist(notificacion);
        return notificacion;
    }

    /** <code>select o from Notificacion o</code> */
    public List<Notificacion> getNotificacionFindAll() {
        return emNotificacion.createNamedQuery("Notificacion.findAll").getResultList();

    }


    @Override
    public int anyadirNotificacion(Long idUsuario, String descripcion, String nombre,
                                   EnumTipoNotificacion TipoNotificacion) {
        int res = 0;
        Usuario usuario = emNotificacion.find(Usuario.class, idUsuario);
        if(usuario != null){
            res=1;
            
            //TIEMPO
            Calendar cal = new GregorianCalendar();
            
            String fecha;
            String anyo = String.valueOf(cal.get(Calendar.YEAR));
            String mes = String.valueOf(cal.get(Calendar.MONTH));
            String dia = String.valueOf(cal.get(Calendar.DATE));
            String hora = String.valueOf(cal.get(Calendar.HOUR)+":"+cal.get(Calendar.MINUTE));
            fecha = dia+"/"+mes+"/"+anyo+" | "+hora;

            
            Notificacion not = new Notificacion(descripcion,fecha,nombre,TipoNotificacion,usuario); 
            usuario.addNotificacion(not);
            emNotificacion.persist(usuario);
            
            
            not.setUsuario(usuario);
            emNotificacion.persist(not);
        }
        
        return res;
    }

    @Override
    public List<Notificacion> mostrarNotificacionDeUsuario(Long idUsuario) {
        List<Notificacion> res = new ArrayList<Notificacion>();
        Usuario usuario = emNotificacion.find(Usuario.class, idUsuario);
        if(usuario != null){
            res.addAll(usuario.getNotificacionList());
        }
        
        return res;
    }

    @Override
    public int estadoNotificacionBorrado(Long idNotificacion) {
        int res = 0;
        Notificacion not = emNotificacion.find(Notificacion.class, idNotificacion);
        if(not != null){
            res=1;
            EnumEstadoNotificacion borrada = EnumEstadoNotificacion.BORRADA;
            not.setEstado(borrada);
            emNotificacion.persist(not);
        }
        return res;
    }

    @Override
    public int estadoNotificacionLeida(Long idNotificacion) {
        int res = 0;
        Notificacion not = emNotificacion.find(Notificacion.class, idNotificacion);
        if(not != null){
            res=1;
            EnumEstadoNotificacion leida = EnumEstadoNotificacion.LEIDA;
            not.setEstado(leida);
            emNotificacion.persist(not);
        }
        return res;
    }
}
