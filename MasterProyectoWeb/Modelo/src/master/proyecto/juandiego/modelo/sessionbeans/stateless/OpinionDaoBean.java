package master.proyecto.juandiego.modelo.sessionbeans.stateless;

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

import javax.persistence.Query;

import master.proyecto.juandiego.modelo.entitybeans.Existencia;
import master.proyecto.juandiego.modelo.entitybeans.Opinion;
import master.proyecto.juandiego.modelo.entitybeans.Usuario;
import master.proyecto.juandiego.modelo.sessionbeans.interfazlocal.OpinionDaoLocal;

@Stateless(name = "OpinionDao", mappedName = "MasterProyectoWeb-Modelo-OpinionDao")
public class OpinionDaoBean implements OpinionDaoLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "Modelo")
    private EntityManager emOpinion;

    public OpinionDaoBean() {
    }

    
    /** <code>select o from Opinion o</code> */
    public List<Opinion> getOpinionFindAll() {
        return emOpinion.createNamedQuery("Opinion.findAll").getResultList();
    }

    @Override
    public List<Opinion> mostrarOpinionesPendientesDeRevision() {
        String queryString =new String();
        queryString = "select o from Opinion o where estado='EN_REVISION'";
        Query query = emOpinion.createQuery(queryString);
        List<Opinion> listaRes = query.getResultList();
        return listaRes;
    }

    @Override
    public int anyadirOpinion(Long idUsuario, Long idExistencia, String comentario, Double puntuacion) {
        
        int res =0;
        Usuario usuario = emOpinion.find(Usuario.class, idUsuario);
        Existencia existencia = emOpinion.find(Existencia.class, idExistencia);
        
        if(usuario!=null && existencia!=null){
            //TIEMPO
            Calendar cal = new GregorianCalendar();
            
            String fecha;
            String anyo = String.valueOf(cal.get(Calendar.YEAR));
            String mes = String.valueOf(cal.get(Calendar.MONTH));
            String dia = String.valueOf(cal.get(Calendar.DATE));
            String hora = String.valueOf(cal.get(Calendar.HOUR)+":"+cal.get(Calendar.MINUTE));
            fecha = dia+"/"+mes+"/"+anyo+" | "+hora;
            
            Opinion opinion = new Opinion(comentario,fecha,puntuacion,existencia,usuario);
            usuario.addOpinion(opinion);
            
            existencia.addOpinion(opinion);
            existencia.setPuntuacion(puntuacion);
            existencia.setNpersonaVotacion(existencia.getNpersonaVotacion()+1);
            
            opinion.setUsuario(usuario);
            opinion.setExistencia(existencia);
            
            emOpinion.persist(usuario);            
            emOpinion.persist(existencia);
            emOpinion.persist(opinion);
            
            res=1;
        }
        
        
        return res;
    }

    @Override
    public void vetarOpiniones(Long idUsuario, Long idExistencia) {
        
    }
}
