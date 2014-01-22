package master.proyecto.juandiego.modelo.sessionbeans.stateless;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import javax.persistence.Query;

import master.proyecto.juandiego.modelo.entitybeans.Juego;
import master.proyecto.juandiego.modelo.entitybeans.enumerados.EnumBooleano;
import master.proyecto.juandiego.modelo.entitybeans.enumerados.EnumGeneroJuego;
import master.proyecto.juandiego.modelo.entitybeans.enumerados.EnumPlataformaJuego;
import master.proyecto.juandiego.modelo.sessionbeans.interfazlocal.BuscadorDaoLocal;

@Stateless(name = "BuscadorDao", mappedName = "MasterProyectoWeb-Modelo-BuscadorDao")
public class BuscadorDaoBean implements BuscadorDaoLocal {
    @Resource
    SessionContext sessionContext;
    
    @PersistenceContext(unitName = "Modelo")
    private EntityManager emExistencia;
    public BuscadorDaoBean() {
    }

        
    /*
     * CABECERA
     */
    
    @Override
    public List<Juego> porNombre(String nombre) {
        List<Juego> existencia = new ArrayList<Juego>();
        
        String nombreUpper = nombre.toUpperCase();
        Query query = emExistencia.createQuery("select j from Juego j where upper(j.nombre)='"+nombreUpper+"'");
        
        List<Juego> novedades = (List<Juego>)query.getResultList();
        if(!novedades.isEmpty()){
            existencia.addAll(novedades);
        }
        
        return existencia;
        
        
    }
   

    @Override
    public List<Juego> porPlataforma(String plataforma) {
        List<Juego> existencia = new ArrayList<Juego>();       
        Query query = emExistencia.createQuery("select j from Juego j where j.plataforma=?1");
        
        
        EnumPlataformaJuego p = extraerPlataforma(plataforma);
        if(p!=null){
            query.setParameter(1, p);
            
            List<Juego> novedades = (List<Juego>)query.getResultList();
            if(!novedades.isEmpty()){
                existencia.addAll(novedades);
            }
        }
        return existencia;
        
    }

    private EnumPlataformaJuego extraerPlataforma(String plataforma) {
        EnumPlataformaJuego res = null
            ;
        String plataformaUpper = plataforma.toUpperCase();
        
        if(plataformaUpper.equals("WII")){
            res = EnumPlataformaJuego.WII;
        }
        if(plataformaUpper.equals("XBOX360")){
            res = EnumPlataformaJuego.XBOX360;
        }
        if(plataformaUpper.equals("PLAYSTATION3")){
            res = EnumPlataformaJuego.PLAYSTATION3;
        }
        
        return res;
    }
    

    @Override
    public List<Juego> porGenero(String genero) {
        List<Juego> existencia = new ArrayList<Juego>();       
        Query query = emExistencia.createQuery("select j from Juego j where j.genero= :genero");
        
        
        EnumGeneroJuego g = extraerGenero(genero);
        if(g!=null){
            query.setParameter("genero", g);
            
            List<Juego> novedades = (List<Juego>)query.getResultList();
            if(!novedades.isEmpty()){
                existencia.addAll(novedades);
            }
        }
        return existencia;
    }

    private EnumGeneroJuego extraerGenero(String genero) {
        
        EnumGeneroJuego g = null;
        
        String generoUpper = genero.toUpperCase(); 
        if(generoUpper.equals("LUCHA")){
            g = EnumGeneroJuego.LUCHA;
        }
        if(generoUpper.equals("DISPAROS")){
            g = EnumGeneroJuego.DISPAROS;
        }
        if(generoUpper.equals("PLATAFORMA")){
            g = EnumGeneroJuego.PLATAFORMA;
        }
        if(generoUpper.equals("SIMULACION")){
            g = EnumGeneroJuego.SIMULACION;
        }
        if(generoUpper.equals("DEPORTES")){
            g = EnumGeneroJuego.DEPORTES;
        }
        if(generoUpper.equals("EDUCACION")){
            g = EnumGeneroJuego.EDUCACION;
        }
        if(generoUpper.equals("ROL")){
            g = EnumGeneroJuego.ROL;
        }
        if(generoUpper.equals("ACCION")){
            g = EnumGeneroJuego.ACCION;
        }
        
        return g;
    }
    
    

     
    @Override
    public List<Juego> conDescuento() {
        List<Juego> existencia = new ArrayList<Juego>();
        Query query = emExistencia.createQuery("select j from Juego j , Existencia e where e.tieneDescuento=?1 and j.idExistencia=e.idExistencia");
        EnumBooleano SI = EnumBooleano.SI;
        query.setParameter(1, SI);
            
        List<Juego> proximamente = (List<Juego>)query.getResultList();
        if(!proximamente.isEmpty()){
            existencia.addAll(proximamente);
        }
        
        
        return existencia;    
    }


    /*
     * PIE
     */
    
    //emExistencia.createQuery("select j from Juego j , Existencia e where e.esNovedad=?1 and j.idExistencia=e.idExistencia and rownum<=5");
    @Override
    public List<Juego> esNovedad() {
        List<Juego> existencia = new ArrayList<Juego>();
        String sql = "SELECT J.* FROM JBRF_JUEGO J , JBRF_EXISTENCIA E WHERE E.ES_NOVEDAD='SI' AND J.ID_EXISTENCIA=E.ID_EXISTENCIA AND ROWNUM<=5";
        Query query = emExistencia.createNativeQuery(sql,Juego.class);
        
        List<Juego> novedades = (List<Juego>)query.getResultList();
        if(!novedades.isEmpty()){
            existencia.addAll(novedades);
        }
        
        return existencia;    
    }

    @Override
    public List<Juego> esProximamente() {
            List<Juego> existencia = new ArrayList<Juego>();
            String sql = "SELECT J.* FROM JBRF_JUEGO J , JBRF_EXISTENCIA E WHERE E.ES_PROXIMAMENTE='SI' AND J.ID_EXISTENCIA=E.ID_EXISTENCIA AND ROWNUM<=5";
            Query query = emExistencia.createNativeQuery(sql,Juego.class);
            
            List<Juego> novedades = (List<Juego>)query.getResultList();
            if(!novedades.isEmpty()){
                existencia.addAll(novedades);
            }
            
            return existencia;    
    }
    
    @Override
    public List<Juego> topVentas() {
        return Collections.emptyList();
    }

    
}
