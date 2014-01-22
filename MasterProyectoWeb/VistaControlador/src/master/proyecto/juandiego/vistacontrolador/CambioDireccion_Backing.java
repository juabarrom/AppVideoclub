package master.proyecto.juandiego.vistacontrolador;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;

import javax.faces.application.Application;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import javax.faces.context.FacesContext;

import javax.servlet.http.HttpSession;

import master.proyecto.juandiego.modelo.sessionbeans.interfazlocal.ClienteDaoLocal;

@ManagedBean(name="cambioDirrecionBacking")
@RequestScoped
public class CambioDireccion_Backing {
    
    private String codpostal;
    
    private String ciudadElegida;
    private List<String> ciudad;
    
    private String provinciaElegida;
    private List<String> provincias;
    
    private String direccion;
    
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
    
    
    public CambioDireccion_Backing() {
        
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

    public void setCodpostal(String codpostal) {
        this.codpostal = codpostal;
    }

    public String getCodpostal() {
        return codpostal;
    }

    public void setCiudadElegida(String ciudadElegida) {
        this.ciudadElegida = ciudadElegida;
    }

    public String getCiudadElegida() {
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

        return ciudadElegida;
    }

    public void setCiudad(List<String> ciudad) {
        this.ciudad = ciudad;
    }

    public List<String> getCiudad() {
        return ciudad;
    }

    public void setProvinciaElegida(String provinciaElegida) {
        this.provinciaElegida = provinciaElegida;
    }

    public String getProvinciaElegida() {
        return provinciaElegida;
    }

    public void setProvincias(List<String> provincias) {
        this.provincias = provincias;
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
    
    public String cambiarDireccion(){
        
        FacesContext ctx = FacesContext.getCurrentInstance();
        Application app = ctx.getApplication();
        String idUsuario = (String) app.evaluateExpressionGet(ctx,"#{menuLoginBacking.usuario.idUsuario}",String.class);
        Long id = new Long(idUsuario);
        
        Integer codPostal = Integer.parseInt(getCodpostal());
    
        cDao.modificarDatosDireccion(id, getCiudadElegida(), codPostal, getDireccion(), getProvinciaElegida());
        
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession sesion = (HttpSession) context.getExternalContext().getSession(false);
        
        sesion.setAttribute("codPostal", getCodpostal());
        sesion.setAttribute("provincia", getProvinciaElegida());
        sesion.setAttribute("ciudad", getCiudadElegida());
        sesion.setAttribute("direccion", getDireccion());
        
        return "correcto";
    }
}
