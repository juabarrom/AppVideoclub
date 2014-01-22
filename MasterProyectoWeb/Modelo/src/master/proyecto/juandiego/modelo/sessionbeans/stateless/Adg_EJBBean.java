package master.proyecto.juandiego.modelo.sessionbeans.stateless;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import master.proyecto.juandiego.modelo.entitybeans.Adg;
import master.proyecto.juandiego.modelo.entitybeans.Produccion;
import master.proyecto.juandiego.modelo.entitybeans.enumerados.EnumTipoNotificacion;
import master.proyecto.juandiego.modelo.sessionbeans.interfazlocal.Adg_EJBLocal;

@Stateless(name = "Adg_EJB", mappedName = "MasterProyectoWeb-Modelo-Adg_EJB")
public class Adg_EJBBean implements Adg_EJBLocal {
    @Resource
    SessionContext sessionContext;

    @PersistenceContext
    private EntityManager login;
    
    public Adg_EJBBean() {
    }

    
    public int comprobarAdg(Long idAdg, String nombreArtistico) {
        int res=0;
        Adg u = login.find(Adg.class, idAdg);
        if(u!=null){
            if(u.getNombreArtistico().equals(nombreArtistico)){
                res=1;
            }
        }
        return res;
    }

    @Override
    public int insertarAdg(String nombreArtistico) {
        
        int res=0;
        
        Adg a = new Adg(nombreArtistico);
        login.persist(a);
        
        return res;
    }



}
