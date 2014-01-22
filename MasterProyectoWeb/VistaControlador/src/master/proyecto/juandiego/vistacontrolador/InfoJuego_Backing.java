package master.proyecto.juandiego.vistacontrolador;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import master.proyecto.juandiego.modelo.entitybeans.Juego;
import master.proyecto.juandiego.modelo.sessionbeans.interfazlocal.ExistenciaDaoLocal;


@ManagedBean(name="infoJuegoBacking")
@RequestScoped
public class InfoJuego_Backing{
    
    
    private String idJuego;
    
    private Juego juego;
    
    //EJB
    @EJB
    private ExistenciaDaoLocal eDao;
    
    public InfoJuego_Backing()
    {
    }

    public void setIdJuego(String id)
    {
        idJuego = id;
        
        Long i = Long.parseLong(id);
        this.juego = eDao.mostrarJuegoPorId(i);
    }

    public String getIdJuego()
    {
        return idJuego;
    }


    public void setJuego(Juego juego) {
        this.juego = juego;
    }

    public Juego getJuego() {
        return juego;
    }
    
    
}
