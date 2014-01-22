package master.proyecto.juandiego.vistacontrolador;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.EJB;

import javax.el.ELContext;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import master.proyecto.juandiego.modelo.entitybeans.Usuario;
import master.proyecto.juandiego.modelo.sessionbeans.interfazlocal.ClienteDaoLocal;

@ManagedBean(name="cambioContrasenyaBacking")
@RequestScoped
public class CambioContrasenya_Backing {
    
    private String contrasenyaActual;
    private String NuevaContrasenya;
    private String repetirNuevaContrasenya;
    
    
    private String compContrasenya;
    
    //EJB
    @EJB
    private ClienteDaoLocal cDao;
        
    public CambioContrasenya_Backing() {
    }

    public void setContrasenyaActual(String contrasenyaActual) {
        this.contrasenyaActual = contrasenyaActual;
    }

    public String getContrasenyaActual() {
        return contrasenyaActual;
    }

    public void setNuevaContrasenya(String NuevaContrasenya) {
        this.NuevaContrasenya = NuevaContrasenya;
    }

    public String getNuevaContrasenya() {
        return NuevaContrasenya;
    }

    public void setRepetirNuevaContrasenya(String repetirNuevaContrasenya) {
        this.repetirNuevaContrasenya = repetirNuevaContrasenya;
    }

    public String getRepetirNuevaContrasenya() {
        return repetirNuevaContrasenya;
    }
    
    public void setCompContrasenya(String compContrasenya) {
        this.compContrasenya = compContrasenya;
    }

    public String getCompContrasenya() {
        return compContrasenya;
    }
    
    /*
     *CONTIENE LA CONTRASE„A -->(String)object;
     *CONTIENE null --> getContrasenya()
     */
    public void comprobarContrasenyaActual(FacesContext context, UIComponent component, Object object)throws ValidatorException{
                
        String contrasenya = (String)object;
        Pattern p = Pattern.compile("[\\w]*");    
        Matcher m = p.matcher(contrasenya);
        boolean esCorrecto = m.matches();
        
        FacesContext ctx = FacesContext.getCurrentInstance();
        Application app = ctx.getApplication();
        String idUsuario = (String) app.evaluateExpressionGet(ctx,"#{menuLoginBacking.usuario.idUsuario}",String.class);
        Long id = new Long(idUsuario);
        Usuario usuario = (Usuario) cDao.mostrarDatosUsuario(id);
        String contrasenyaActual = usuario.getContrasenya();
        
        boolean sonIguales = contrasenya.equals(contrasenyaActual);
        
        if(!esCorrecto){
            FacesMessage mensaje = new FacesMessage();
            
            //ESTE MENSAJE SE MUESTRA EN EL FORMULARIO EN EL SIGUIENTE LUGAR <h:message for="contrasenya" style="color:red;"/><br/> 
            mensaje.setSummary("Su contrase–a solo tiene que constar de letras y nœmeros, nunca simbolos ni espacios");
            throw new ValidatorException(mensaje);            
            
        }else if(!sonIguales){
            FacesMessage mensaje = new FacesMessage();
            
            //ESTE MENSAJE SE MUESTRA EN EL FORMULARIO EN EL SIGUIENTE LUGAR <h:message for="contrasenya" style="color:red;"/><br/> 
            mensaje.setSummary("La contrase–a no coincide con la actual");
            throw new ValidatorException(mensaje);
        }
        
    }
    
    
    public void comprobarContrasenya(FacesContext context, UIComponent component, Object object)throws ValidatorException{
                
        String contrasenya = (String)object;
        Pattern p = Pattern.compile("[\\w]*");    
        Matcher m = p.matcher(contrasenya);
        boolean esCorrecto = m.matches();
        
        if(esCorrecto){
            setCompContrasenya(contrasenya);
        }else if(!esCorrecto){
            FacesMessage mensaje = new FacesMessage();
            
            //ESTE MENSAJE SE MUESTRA EN EL FORMULARIO EN EL SIGUIENTE LUGAR <h:message for="contrasenya" style="color:red;"/><br/> 
            mensaje.setSummary("Su contrase–a solo tiene que constar de letras y nœmeros, nunca simbolos ni espacios");
            throw new ValidatorException(mensaje);            
        }
    } 
     
    public void comprobarContrasenyaRepetida(FacesContext context, UIComponent component, Object object)throws ValidatorException{
        String contrasenyaRepetida= (String)object;
        String contrasenya = getCompContrasenya();
        boolean sonIguales = contrasenya.equals(contrasenyaRepetida);
        
        if(!sonIguales){
            FacesMessage mensaje = new FacesMessage();
            mensaje.setSummary("Las contrase–as no coinciden");
            throw new ValidatorException(mensaje);
        }
    }
    
    public String cambiarContrasenya(){
        FacesContext ctx = FacesContext.getCurrentInstance();
        Application app = ctx.getApplication();
        String idStringUsuario = (String) app.evaluateExpressionGet(ctx,"#{menuLoginBacking.usuario.idUsuario}",String.class);
        Long idUsuario = Long.parseLong(idStringUsuario);
        cDao.modificarContrasenyaUsuario(idUsuario, getNuevaContrasenya());
        
        return "correcto";
    }

    
}
