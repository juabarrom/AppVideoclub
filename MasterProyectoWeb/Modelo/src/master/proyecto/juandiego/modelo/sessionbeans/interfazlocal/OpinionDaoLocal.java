package master.proyecto.juandiego.modelo.sessionbeans.interfazlocal;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import master.proyecto.juandiego.modelo.entitybeans.Opinion;

@Local
public interface OpinionDaoLocal {
    
    List<Opinion> getOpinionFindAll();
    
    List<Opinion> mostrarOpinionesPendientesDeRevision();
    
    void vetarOpiniones(Long idUsuario,Long idExistencia);
    
    int anyadirOpinion(Long idUsuario,Long idExistencia, String comentario,Double puntuacion);
}
