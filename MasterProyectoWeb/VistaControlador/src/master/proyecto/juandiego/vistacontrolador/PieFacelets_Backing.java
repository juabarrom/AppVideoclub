package master.proyecto.juandiego.vistacontrolador;

import java.util.List;

import javax.annotation.PostConstruct;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;

import javax.faces.bean.ViewScoped;

import master.proyecto.juandiego.modelo.sessionbeans.interfazlocal.BuscadorDaoLocal;

@ManagedBean(name="pieFaceletsBacking")
@ViewScoped
public class PieFacelets_Backing{

    private List juegosNovedades;
    private List juegosProximamente;
    @EJB
    private BuscadorDaoLocal bDao;
    
    public PieFacelets_Backing()
    {
    }

    @PostConstruct
    public void init()
    {
        setJuegosNovedades(bDao.esNovedad());
        setJuegosProximamente(bDao.esProximamente());
    }

    public void setJuegosNovedades(List juegosNovedades)
    {
        this.juegosNovedades = juegosNovedades;
    }

    public List getJuegosNovedades()
    {
        return juegosNovedades;
    }

    public void setJuegosProximamente(List juegosProximamente)
    {
        this.juegosProximamente = juegosProximamente;
    }

    public List getJuegosProximamente()
    {
        return juegosProximamente;
    }

    
}
