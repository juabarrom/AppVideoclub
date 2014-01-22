package master.proyecto.juandiego.vistacontrolador;

import javax.ejb.EJB;

import javax.faces.application.Application;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import javax.servlet.http.HttpSession;

import master.proyecto.juandiego.modelo.sessionbeans.interfazlocal.ClienteDaoLocal;

@ManagedBean(name="cambioTelefonoBacking")
@RequestScoped
public class CambioTelefono_Backing {
    
    private String telefono;
    private String movil;
    
    //EJB
    @EJB
    private ClienteDaoLocal cDao;
    
    public CambioTelefono_Backing() {
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setMovil(String movil) {
        this.movil = movil;
    }

    public String getMovil() {
        return movil;
    }
    
    public String cambiarTelefono(){
     
        Integer telefono = Integer.parseInt(getTelefono());
        Integer movil = Integer.parseInt(getMovil());
        
        FacesContext ctx = FacesContext.getCurrentInstance();
        Application app = ctx.getApplication();
        String idUsuario = (String) app.evaluateExpressionGet(ctx,"#{menuLoginBacking.usuario.idUsuario}",String.class);
        Long id = new Long(idUsuario);
        
        cDao.modificarTelefonoUsuario(id, movil, telefono);
        
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession sesion = (HttpSession) context.getExternalContext().getSession(false);
        
        sesion.setAttribute("telefono", getTelefono());
        sesion.setAttribute("movil", getMovil());
        
        
        return "correcto";
    }
}
