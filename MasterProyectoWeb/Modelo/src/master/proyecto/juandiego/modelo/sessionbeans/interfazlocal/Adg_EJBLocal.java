package master.proyecto.juandiego.modelo.sessionbeans.interfazlocal;

import javax.ejb.Local;

import master.proyecto.juandiego.modelo.entitybeans.Adg;

@Local
public interface Adg_EJBLocal {
    public int comprobarAdg(Long idAdg, String nombreArtistico);
    public int insertarAdg(String nombreArtistico);
}
