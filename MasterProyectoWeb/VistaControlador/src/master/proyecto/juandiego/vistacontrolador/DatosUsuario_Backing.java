package master.proyecto.juandiego.vistacontrolador;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;

import javax.faces.bean.ViewScoped;

import master.proyecto.juandiego.modelo.entitybeans.Usuario;
import master.proyecto.juandiego.modelo.sessionbeans.interfazlocal.ClienteDaoLocal;

@ManagedBean(name="datosUsuarioBacking")
@ViewScoped
public class DatosUsuario_Backing{
    
    private String idUsuario;
    private Usuario usuario;
    private String puntos;
    @EJB
    private ClienteDaoLocal cDao;
    
    public DatosUsuario_Backing()
    {
    }

    public void setIdUsuario(String idJuego)
    {
        idUsuario = idJuego;
        initUsuario();
    }

    public String getIdUsuario()
    {
        return idUsuario;
    }

    public void setUsuario(Usuario usuario)
    {
        this.usuario = usuario;
    }

    public Usuario getUsuario()
    {
        return usuario;
    }

    private void initUsuario()
    {
        Long id = Long.valueOf(Long.parseLong(getIdUsuario()));
        Usuario usuario = cDao.mostrarDatosUsuario(id);
        setUsuario(usuario);
    }

    public void setPuntos(String puntos)
    {
        this.puntos = puntos;
    }

    public String getPuntos()
    {
        return puntos;
    }

    public String convertirEnAdmin()
    {
        Long id = Long.valueOf(Long.parseLong(getIdUsuario()));
        cDao.convertirEnAdmin(id);
        return "usuario";
    }

    public String activarCuenta()
    {
        Long id = Long.valueOf(Long.parseLong(getIdUsuario()));
        cDao.activarCuenta(id);
        return "usuario";
    }

    public String inactivarCuenta()
    {
        Long id = Long.valueOf(Long.parseLong(getIdUsuario()));
        cDao.inactivarCuenta(id);
        return "usuario";
    }

    public String anyadirPuntos()
    {
        Long id = Long.valueOf(Long.parseLong(getIdUsuario()));
        Integer puntos = Integer.valueOf(Integer.parseInt(getPuntos()));
        cDao.anyadirPuntos(id, puntos);
        return "usuario";
    }


}
