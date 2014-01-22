package master.proyecto.juandiego.vistacontrolador;

import java.io.Serializable;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;

import javax.servlet.http.HttpSession;

import master.proyecto.juandiego.modelo.entitybeans.Usuario;
import master.proyecto.juandiego.modelo.sessionbeans.interfazlocal.ClienteDaoLocal;

@ManagedBean(name="menuLoginBacking")
@SessionScoped
public class MenuLogin_Backing implements Serializable {
    
    private String correo;
    private String contrasenya;
    
    private Long idUsuario;
    
    private Usuario usuario;
    
    //GUARDA SI EL USUARIO ES ADMIN O USUARIO SIMPLE DE LA APP
    private String estadoLogin;
    
    //EJB
    @EJB
    private ClienteDaoLocal cDao;
    
    
    
    public MenuLogin_Backing() {
    }

    public MenuLogin_Backing(Usuario usuario) {
        super();
        this.usuario = usuario;
    }


    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    public String getContrasenya() {
        return contrasenya;
    }
    
    public void setCDao(ClienteDaoLocal cDAo) {
        this.cDao = cDAo;
    }

    public ClienteDaoLocal getCDao() {
        return cDao;
    }
    
    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }
    
    public void setEstadoLogin(String estadoLogin) {
        this.estadoLogin = estadoLogin;
    }

    public String getEstadoLogin() {
        return estadoLogin;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession sesion = (HttpSession) context.getExternalContext().getSession(false);
        if(sesion != null){
            if(sesion.getAttribute("telefono")!=null && sesion.getAttribute("movil")!=null){
                
                String stringTelefono = (String) sesion.getAttribute("telefono");
                String stringMovil = (String) sesion.getAttribute("movil");
                
                
                Integer telefono = Integer.parseInt(stringTelefono);
                Integer movil = Integer.parseInt(stringMovil);
                
                this.usuario.setTelefono(telefono);
                this.usuario.setTelefono(movil);
                
            }
            
            if(sesion.getAttribute("codPostal")!=null){
                
                String stringCodPostal = (String) sesion.getAttribute("codPostal");
                Integer codPostal = Integer.parseInt(stringCodPostal); 
                
                String provincia = (String) sesion.getAttribute("provincia");
                String ciudad = (String) sesion.getAttribute("ciudad");
                String direccion = (String) sesion.getAttribute("direccion");
                
                this.usuario.getDireccion().setCodPostal(codPostal);
                this.usuario.getDireccion().setProvincia(provincia);
                this.usuario.getDireccion().setCiudad(ciudad);
                this.usuario.getDireccion().setDireccion(direccion);
            }
        
        }
        
        return usuario;
        
    }
    
    public String Loguearte(){
        
        this.idUsuario(getCorreo());
        this.obtenerUsuario();
        
        int res =cDao.Login(getCorreo(), getContrasenya());
        
        System.out.println("juju"+res);
        if(res==3 || res ==4){
            //USUARIO Y CONTRASE„A CORRECTA
            //ABRIMOS SESION
            FacesContext context = FacesContext.getCurrentInstance();
            HttpSession sesion = (HttpSession) context.getExternalContext().getSession(true);
        }
        
        String sRes=new String("");
        
        if(res ==0){
            sRes="NOEXISTE";
        }
        if(res ==1){
            sRes="CONTRASE„ANOCOINCIDE";
        }
        if(res ==2){
            sRes ="NOACTIVO";
        }
        if(res ==3){
            sRes ="ACTIVO";
            setEstadoLogin("ACTIVO");
        }
        if(res ==4){
            sRes ="ADMIN";
            setEstadoLogin("ADMIN");
        }
        
        return sRes;
    }

    public String Desloguearte(){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession sesion = (HttpSession)context.getExternalContext().getSession(false);
        
           if (sesion != null)
           {
               
             sesion.invalidate();
               
           }
           
        return "deslogado";
    }

    public void idUsuario(String correo){
        Long id =cDao.idPorCorreo(correo);
        setIdUsuario(id);
    }
    
    public void obtenerUsuario(){
        Usuario usuario =cDao.mostrarDatosUsuario(getIdUsuario());
        setUsuario(usuario);
    }
}
