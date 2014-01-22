package master.proyecto.juandiego.modelo.sessionbeans.stateless;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.Column;
import javax.persistence.EntityManager;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;

import javax.persistence.Query;

import master.proyecto.juandiego.modelo.entitybeans.Existencia;
import master.proyecto.juandiego.modelo.entitybeans.Juego;
import master.proyecto.juandiego.modelo.entitybeans.Puntosexistencias;
import master.proyecto.juandiego.modelo.entitybeans.Udsexistencia;
import master.proyecto.juandiego.modelo.entitybeans.Usuario;
import master.proyecto.juandiego.modelo.entitybeans.Video;
import master.proyecto.juandiego.modelo.entitybeans.enumerados.EnumBooleano;
import master.proyecto.juandiego.modelo.entitybeans.enumerados.EnumEstadoExistencia;
import master.proyecto.juandiego.modelo.entitybeans.enumerados.EnumEstadoUsuario;
import master.proyecto.juandiego.modelo.entitybeans.enumerados.EnumGeneroJuego;
import master.proyecto.juandiego.modelo.entitybeans.enumerados.EnumPlataformaJuego;
import master.proyecto.juandiego.modelo.entitybeans.enumerados.EnumTipoExistencia;
import master.proyecto.juandiego.modelo.sessionbeans.interfazlocal.ExistenciaDaoLocal;

@Stateless(name = "ExistenciaDao", mappedName = "MasterProyectoWeb-Modelo-ExistenciaDao")
public class ExistenciaDaoBean implements ExistenciaDaoLocal {
    @Resource
    SessionContext sessionContext;
    
    @PersistenceContext(unitName = "Modelo")
    private EntityManager emExistencia;

    public ExistenciaDaoBean() {
    }


    /** <code>select o from Existencia o</code> */
    public List<Existencia> getExistenciaFindAll() {
        return emExistencia.createNamedQuery("Existencia.findAll").getResultList();
    }
    /** <code>select o from Video o</code> */
    public List<Video> getVideoFindAll() {
        return emExistencia.createNamedQuery("Video.findAll").getResultList();
    }
    
    /** <code>select o from Juego o</code> */
    public List<Juego> getJuegoFindAll() {
        return emExistencia.createNamedQuery("Juego.findAll").getResultList();
    }


    @Override
    public int persistJuego(String nombre, EnumGeneroJuego genero, EnumPlataformaJuego plataforma, String descripcion,
                            String gameplay, String imagenGrande, String imagenPequenya,
                            EnumBooleano tieneDescuento, Integer descuento,
                            EnumBooleano esNovedad, EnumBooleano esProximamente, Integer puntosJuego,
                            Integer udsJuego) {
        
        
        Juego juego;
        juego = new Juego(descripcion,gameplay,genero,imagenGrande,imagenPequenya,nombre,plataforma,descuento,tieneDescuento,esNovedad,esProximamente);
        emExistencia.persist(juego);
        
        Puntosexistencias puntosPorJuego;
        puntosPorJuego = new Puntosexistencias (0,0,puntosJuego);
        puntosPorJuego.setExistencia(juego);
        emExistencia.persist(puntosPorJuego);
        
        Udsexistencia unidadesPorJuego;
        unidadesPorJuego = new Udsexistencia(0,0,udsJuego);
        unidadesPorJuego.setExistencia(juego);
        emExistencia.persist(unidadesPorJuego);
        
        return 0;
    }

    @Override
    public int cambiarPuntosEnExistencia(Long idExistencia,Integer puntosBlueRay,Integer puntosDvd,Integer puntosJuego) {
        Integer res =new Integer(0);
        Existencia existencia;
        existencia = emExistencia.find(Existencia.class,idExistencia); 
        
        if(existencia!=null){
            Puntosexistencias puntosExistencia = new Puntosexistencias(puntosBlueRay,puntosDvd,puntosJuego);
            existencia.setPuntosexistenciasList(puntosExistencia);
            emExistencia.persist(existencia);
            res=1;
        }
        return res;
    }

    @Override
    public int anyadirUnidadesEnExistencia(Long idExistencia,Integer unidadesBlueRay,Integer unidadesDvd,Integer unidadesJuego) {
        
        Integer res =new Integer(0);
        Existencia existencia;
        existencia = emExistencia.find(Existencia.class,idExistencia); 
        
        if(existencia!=null){
            Udsexistencia uds = new Udsexistencia(unidadesBlueRay,unidadesDvd,unidadesJuego);
            existencia.setUdsexistencia(uds);
            emExistencia.persist(existencia);
            res=1;
        }
        return res;

    }

    @Override
    public int ponerDescuentoEnExistencia(Long idExistencia,Integer descuento) {
        Integer res =new Integer(0);
        Existencia existencia;
        existencia = emExistencia.find(Existencia.class,idExistencia); 
        
        if(existencia!=null){
            existencia.setDescuento(descuento);
            existencia.setTieneDescuento(EnumBooleano.SI);
            emExistencia.persist(existencia);
            res=1;
        }
        return res;

    }
    
    //EnumBooleano SI NO
    @Override
    public List<Juego> mostrarNovedades() {
        List<Juego> existencia = new ArrayList<Juego>();
        Query query = emExistencia.createQuery("select j from Juego j , Existencia e where e.esNovedad=?1 and j.idExistencia=e.idExistencia");
        EnumBooleano SI = EnumBooleano.SI;
        query.setParameter(1, SI);
      
        List<Juego> novedades = (List<Juego>)query.getResultList();
        if(!novedades.isEmpty()){
            existencia.addAll(novedades);
        }
      
        return existencia;
    }
    
    //EnumBooleano SI NO
    @Override
    public List<Juego> mostrarProximamente() {
        List<Juego> existencia = new ArrayList<Juego>();
        Query query = emExistencia.createQuery("select j from Juego j , Existencia e where e.esProximamente=?1 and j.idExistencia=e.idExistencia");
        EnumBooleano SI = EnumBooleano.SI;
        query.setParameter(1, SI);
            
        List<Juego> proximamente = (List<Juego>)query.getResultList();
        if(!proximamente.isEmpty()){
            existencia.addAll(proximamente);
        }
    
        
        return existencia;
    }
    
    @Override
    public List<Juego> mostrarTodosLosProductosConDescuento() {
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
    @Override
    public int quitarExistenciaDeNovedad(Long idExistencia) {
        Integer res =new Integer(0);
        Existencia existencia;
        existencia = emExistencia.find(Existencia.class,idExistencia); 
        
        if(existencia!=null){
            existencia.setEsNovedad(EnumBooleano.NO);
            emExistencia.persist(existencia);
            res=1;
        }
        return res;
    }

    @Override
    public int quitarExistenciaDeProximamente(Long idExistencia) {
        Integer res =new Integer(0);
        Existencia existencia;
        existencia = emExistencia.find(Existencia.class,idExistencia); 
        
        if(existencia!=null){
            existencia.setEsProximamente(EnumBooleano.NO);
            existencia.setEsNovedad(EnumBooleano.SI);
            emExistencia.persist(existencia);
            res=1;
        }
        return res;
    }

    @Override
    public int descatalogarProducto(Long idExistencia) {
        Integer res =new Integer(0);
        Existencia existencia;
        existencia = emExistencia.find(Existencia.class,idExistencia); 
        
        if(existencia!=null){
            existencia.setEstado(EnumEstadoExistencia.DESCATALOGADO);
            emExistencia.persist(existencia);
            res=1;
        }
        return res;
    }

    @Override
    public int quitarDescuentoEnExistencia(Long idExistencia, Integer descuento) {
        int res =0;
        Existencia existencia = emExistencia.find(Existencia.class, idExistencia);
        if(existencia!=null){
            existencia.setTieneDescuento(EnumBooleano.NO);
            existencia.setDescuento(0);
            res=1;
            
            emExistencia.persist(existencia);
        }
        
        return res;
    }


    @Override
    public int modificarJuegog(Long idJuego, String nombre, EnumGeneroJuego genero, EnumPlataformaJuego plataforma,
                               String descripcion, String gameplay, String imagenGrande, String imagenPequenya,
                               EnumBooleano tieneDescuento, Integer descuento, EnumBooleano esNovedad,
                               EnumBooleano esProximamente, Integer puntosJuego, Integer udsJuego) {
        int res=0;
        
        Juego juego = emExistencia.find(Juego.class, idJuego);
        
        if(juego != null){
         
            juego.setNombre(nombre);
            juego.setGenero(genero);
            juego.setPlataforma(plataforma);
            juego.setDescripcion(descripcion);
            juego.setGameplay(null);
            juego.setImagenGrande(null);
            juego.setImagenPequenya(null);
            juego.setTieneDescuento(tieneDescuento);
            juego.setDescuento(descuento);
            juego.setEsNovedad(esNovedad);
            juego.setEsProximamente(esProximamente);
            
            Puntosexistencias puntos = juego.getPuntosexistencias();
            puntos.setPuntosJuego(puntosJuego);
            
            Udsexistencia uds = juego.getUdsexistencia();
            uds.setUdsJuego(udsJuego + uds.getUdsJuego());
            
            
            emExistencia.persist(juego);
            
            res=1;   

        }
        
        return res;
    }

    @Override
    public Juego mostrarJuegoPorId(Long id) {
        
        Juego juego = emExistencia.find(Juego.class, id);
        
        return juego;
    }
}
