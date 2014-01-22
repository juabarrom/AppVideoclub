package master.proyecto.juandiego.vistacontrolador;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;

import javax.faces.bean.RequestScoped;

import master.proyecto.juandiego.modelo.sessionbeans.interfazlocal.ClienteDaoLocal;

@ManagedBean(name="usuariosBacking")
@RequestScoped
public class Usuarios_Backing{

    private List usuarios;
    private String elegido;
    private List menuElegido;
    
    @EJB
    private ClienteDaoLocal cDao;
    public Usuarios_Backing()
    {
        menuElegido = new ArrayList();
        menuElegido.add("Todos");
        menuElegido.add("Activos");
        menuElegido.add("Administradores");
    }

    @PostConstruct
    public void init()
    {
        setUsuarios(cDao.getUsuarioFindAll());
        setElegido((String)getMenuElegido().get(0));
    }

    public void setUsuarios(List usuarios)
    {
        this.usuarios = usuarios;
    }

    public List getUsuarios()
    {
        if(elegido.equals("Todos"))
            setUsuarios(cDao.getUsuarioFindAll());
        if(elegido.equals("Activos"))
            setUsuarios(cDao.mostrarActivos());
        if(elegido.equals("Administradores"))
            setUsuarios(cDao.mostrarAdministradores());
        return usuarios;
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
