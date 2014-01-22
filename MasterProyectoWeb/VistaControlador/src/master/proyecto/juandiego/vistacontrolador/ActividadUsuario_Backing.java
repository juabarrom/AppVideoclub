package master.proyecto.juandiego.vistacontrolador;

import javax.ejb.EJB;

import javax.faces.application.Application;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import master.proyecto.juandiego.modelo.sessionbeans.interfazlocal.ClienteDaoLocal;

@ManagedBean(name="actividadUsuarioBacking")
@RequestScoped
public class ActividadUsuario_Backing{
    
    @EJB
    private ClienteDaoLocal cDao;

    public ActividadUsuario_Backing()
    {
    }

    public String darDeBajaUsuario()
    {
        FacesContext ctx = FacesContext.getCurrentInstance();
        Application app = ctx.getApplication();
        String idUsuario = (String)app.evaluateExpressionGet(ctx, "#{menuLoginBacking.usuario.idUsuario}", String.class);
        
        Long id = new Long(idUsuario);
        
        cDao.inactivarCuenta(id);
        
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession sesion = (HttpSession)context.getExternalContext().getSession(false);
        if(sesion != null)
            sesion.invalidate();
        return "inactivo";
    }

    
}
