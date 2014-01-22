package master.proyecto.juandiego.vistacontrolador;

import java.util.ArrayList;
import java.util.List;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.EJB;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import master.proyecto.juandiego.modelo.sessionbeans.interfazlocal.ClienteDaoLocal;

@ManagedBean(name="formularioUsuario")
@RequestScoped
public class FormularioUsuario{
    
    private String nombre;
    private String apellidos;
    private String correo;
    private String contrasenya;
    private String repetirContrasenya;
    private String telefono;
    private String movil;
    private String codpostal;
    
    private String ciudadElegida;
    private List<String> ciudad;
    private String ciudadInput;
    
    private String provinciaElegida;
    private List<String> provincias;
    private String provinciaInput;
    
    private String direccion;
        
    //ATRIBUTO USADO PARA COMPROBACION DE CLAVES REPETIDAS
    private String compContrasenya;
    
    //MUNICIPIOS
    private List<String> almeria;
    private List<String> cadiz;
    private List<String> cordoba;
    private List<String> granada;
    private List<String> huelva;
    private List<String> jaen;
    private List<String> malaga;
    private List<String> sevilla;
    
    //EJB
    @EJB
    private ClienteDaoLocal cDao;
    
    public FormularioUsuario() {
        
        provincias = new ArrayList<String>();
        provincias.add("Almería");
        provincias.add("Cádiz");        
        provincias.add("Córdoba");
        provincias.add("Granada");
        provincias.add("Huelva");
        provincias.add("Jaén");
        provincias.add("Málaga");
        provincias.add("Sevilla");
        
        ciudad = new ArrayList<String>();
        
        //MUNICIPIOS POR CIUDAD
        almeria = new ArrayList<String>();
        almeria.add("Almeria");
        almeria.add("El Ejido");
        almeria.add("Mojacar");
        almeria.add("Velez-Rubio");
        
        cadiz = new ArrayList<String>();
        cadiz.add("Cadiz");
        cadiz.add("Conil de la Frontera");
        cadiz.add("Chiclana");
        cadiz.add("Rota");
        
        cordoba = new ArrayList<String>();
        cordoba.add("Cordoba");
        cordoba.add("Montilla");
        cordoba.add("Belmez");
        cordoba.add("Santaella");
        
        granada = new ArrayList<String>();
        granada.add("Granada");
        granada.add("Huescar");
        granada.add("Albuñol");
        granada.add("Baza");
        
        
        huelva = new ArrayList<String>();
        huelva.add("Huelva");
        huelva.add("Isla Cristina");
        huelva.add("Almonte");
        huelva.add("Lepe");
        
        jaen = new ArrayList<String>();
        jaen.add("Jaen");
        jaen.add("Baza");
        jaen.add("Hornos");
        jaen.add("Vilches");
        
        malaga = new ArrayList<String>();
        malaga.add("Malaga");
        malaga.add("Estepona");
        malaga.add("Coin");
        malaga.add("Nerja");
        
        sevilla = new ArrayList<String>();
        sevilla.add("Sevilla");
        sevilla.add("Utrera");
        sevilla.add("Moron de la Frontera");
        sevilla.add("Pruna");
        
        
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getApellidos() {
        return apellidos;
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

    public void setCodpostal(String codpostal) {
        this.codpostal = codpostal;
    }

    public String getCodpostal() {
        return codpostal;
    }
    
    public void setCiudadElegida(String ciudadElegida) {
        System.out.println("ENTRA EN SETCIUDAD");
        this.ciudadElegida = ciudadElegida;
    }

    public String getCiudadElegida() {
        System.out.println("ENTRA EN GETCIUDAD");
        System.out.println("Provincia elegida "+this.ciudadElegida);

        return ciudadElegida;
    }
    
    public void setCiudad(List<String> ciudad) {
        this.ciudad = ciudad;
    }

    public List<String> getCiudad() {
        
     
        if(getProvinciaElegida() !=null){
            if(getProvinciaElegida().equals("Almería") ){
                setCiudad(almeria);
            }
            if(getProvinciaElegida().equals("Cádiz")){
                setCiudad(cadiz);
            }
            if(getProvinciaElegida().equals("Córdoba")){
                setCiudad(cordoba);
            }
            if(getProvinciaElegida().equals("Granada")){
                setCiudad(granada);
            }     
            if(getProvinciaElegida().equals("Huelva")){
                setCiudad(huelva);
            }
            if(getProvinciaElegida().equals("Jaén")){
                setCiudad(jaen);
            }
            if(getProvinciaElegida().equals("Málaga")){
                setCiudad(malaga);
            }
            if(getProvinciaElegida().equals("Sevilla")){
                setCiudad(sevilla);
            }
        }
        
        
        return ciudad;
    }
    
    public void setProvinciaElegida(String provinciaElegida) {
        this.provinciaElegida = provinciaElegida;
    }

    public String getProvinciaElegida() {
        System.out.println("Provincia elegida "+this.provinciaElegida);
        System.out.println("Ciudad Elegida "+getCiudadElegida());
        return provinciaElegida;
    }
    public void setProvincias(List<String> provincia) {
        this.provincias = provincia;
    }

    public List<String> getProvincias() {
        return provincias;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDireccion() {
        return direccion;
    }
    
    public void setCompContrasenya(String compContrasenya) {
        this.compContrasenya = compContrasenya;
    }

    public String getCompContrasenya() {
        return compContrasenya;
    }
    

    public void setRepetirContrasenya(String repetirContrasenya) {
        this.repetirContrasenya = repetirContrasenya;
    }

    public String getRepetirContrasenya() {
        return repetirContrasenya;
    }

    public void setAlmeria(List<String> almeria) {
        this.almeria = almeria;
    }

    public List<String> getAlmeria() {
        return almeria;
    }

    public void setCadiz(List<String> cadiz) {
        this.cadiz = cadiz;
    }

    public List<String> getCadiz() {
        return cadiz;
    }

    public void setCordoba(List<String> cordoba) {
        this.cordoba = cordoba;
    }

    public List<String> getCordoba() {
        return cordoba;
    }

    public void setGranada(List<String> granada) {
        this.granada = granada;
    }

    public List<String> getGranada() {
        return granada;
    }

    public void setHuelva(List<String> huelva) {
        this.huelva = huelva;
    }

    public List<String> getHuelva() {
        return huelva;
    }

    public void setJaen(List<String> jaen) {
        this.jaen = jaen;
    }

    public List<String> getJaen() {
        return jaen;
    }

    public void setMalaga(List<String> malaga) {
        this.malaga = malaga;
    }

    public List<String> getMalaga() {
        return malaga;
    }

    public void setSevilla(List<String> sevilla) {
        this.sevilla = sevilla;
    }

    public List<String> getSevilla() {
        return sevilla;
    }

    public void setCDao(ClienteDaoLocal cDAo) {
        this.cDao = cDAo;
    }

    public ClienteDaoLocal getCDao() {
        return cDao;
    }
    
    /*
     *CONTIENE LA CONTRASEÑA -->(String)object;
     *CONTIENE null --> getContrasenya()
     */
    public void comprobarContrasenya(FacesContext context, UIComponent component, Object object)throws ValidatorException{
                
        String contrasenya = (String)object;
        Pattern p = Pattern.compile("[\\w]*");    
        Matcher m = p.matcher(contrasenya);
        boolean esCorrecto = m.matches();
        
        if(esCorrecto){
            this.compContrasenya = contrasenya;
        }else if(!esCorrecto){
            FacesMessage mensaje = new FacesMessage();
            
            //ESTE MENSAJE SE MUESTRA EN EL FORMULARIO EN EL SIGUIENTE LUGAR <h:message for="contrasenya" style="color:red;"/><br/> 
            mensaje.setSummary("Su contraseña solo tiene que constar de letras y números, nunca simbolos ni espacios");
            throw new ValidatorException(mensaje);            
        }
    }

    public void comprobarContrasenyaRepetida(FacesContext context, UIComponent component, Object object)throws ValidatorException{
        String contrasenyaRepetida= (String)object;
        String contrasenya = getCompContrasenya();
        boolean sonIguales = contrasenya.equals(contrasenyaRepetida);
        
        if(!sonIguales){
            FacesMessage mensaje = new FacesMessage();
            mensaje.setSummary("Las contraseñas no coinciden");
            throw new ValidatorException(mensaje);
        }
    }

    public String anyadirClienteYDirrecion(){
        Integer movil = Integer.parseInt(getMovil());
        Integer telefono = Integer.parseInt(getTelefono());
        Integer codPostal = Integer.parseInt(getCodpostal());
        
        System.out.println("PROVINCIA ELEGIDA  ||||||"+getProvinciaElegida());
        System.out.println("CIUDAD ELEGIDA ||||||||"+getCiudadElegida());
        
        cDao.persistUsuario(getNombre(), getApellidos(), getContrasenya(), getCorreo(), movil, telefono, getCiudadInput(), codPostal, getDireccion(), getProvinciaInput());
        
        return "correcto";
    }


    public void setCiudadInput(String ciudadInput) {
        this.ciudadInput = ciudadInput;
    }

    public String getCiudadInput() {
        return ciudadInput;
    }

    public void setProvinciaInput(String provinciaInput) {
        this.provinciaInput = provinciaInput;
    }

    public String getProvinciaInput() {
        return provinciaInput;
    }
}
