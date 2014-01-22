package master.proyecto.juandiego.vistacontrolador;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;

import javax.faces.bean.RequestScoped;

import master.proyecto.juandiego.modelo.sessionbeans.interfazlocal.ExistenciaDaoLocal;

@ManagedBean(name="productosBacking")
@RequestScoped
public class Productos_Backing{

    private List juegos;
    private String elegido;
    private List menuElegido;
    
    @EJB
    private ExistenciaDaoLocal eDao;
    
    public Productos_Backing()
    {
        menuElegido = new ArrayList();
        menuElegido.add("Todos");
        menuElegido.add("Con descuentos");
        menuElegido.add("Novedades");
        menuElegido.add("Poximamente");
    }

    @PostConstruct
    public void init()
    {
        setJuegos(eDao.getJuegoFindAll());
        setElegido((String)getMenuElegido().get(0));
    }

    public void setJuegos(List juegos)
    {
        this.juegos = juegos;
    }

    public List getJuegos()
    {
        if(elegido.equals("Todos"))
            setJuegos(eDao.getJuegoFindAll());
        if(elegido.equals("Novedades"))
            setJuegos(eDao.mostrarNovedades());
        if(elegido.equals("Poximamente"))
            setJuegos(eDao.mostrarProximamente());
        if(elegido.equals("Con descuentos"))
            setJuegos(eDao.mostrarTodosLosProductosConDescuento());
        return juegos;
    }

    public String todosLosJuegos()
    {
        return "productos";
    }

    public void setElegido(String elegido)
    {
        this.elegido = elegido;
    }

    public String getElegido()
    {
        return elegido;
    }

    public void setMenuElegido(List menuElegido)
    {
        this.menuElegido = menuElegido;
    }

    public List getMenuElegido()
    {
        return menuElegido;
    }


}
