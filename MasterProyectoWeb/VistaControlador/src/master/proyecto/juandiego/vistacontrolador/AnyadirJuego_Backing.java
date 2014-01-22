package master.proyecto.juandiego.vistacontrolador;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import master.proyecto.juandiego.modelo.entitybeans.enumerados.*;
import master.proyecto.juandiego.modelo.sessionbeans.interfazlocal.ExistenciaDaoLocal;

import org.primefaces.model.UploadedFile;

@ManagedBean(name="anyadirJuegoBacking")
@RequestScoped
public class AnyadirJuego_Backing{
    
    private String nombre;
    private List generos;
    private String generoElegido;
    private List plataformas;
    private String plataformaElegida;
    private String descripcion;
    private String puntosDelJuego;
    private String unidadesDelJuego;
    private boolean tieneDescuento;
    private String puntosDeDescuento;
    private boolean esNovedad;
    private boolean esProximamente;
    
    
    private UploadedFile filePequenya;
    private UploadedFile fileGrande;

    @EJB
    private ExistenciaDaoLocal eDao;
    
    public AnyadirJuego_Backing()
    {
        generos = new ArrayList();
        generos.add("ACCIÓN");
        generos.add("LUCHA");
        generos.add("DISPAROS");
        generos.add("PLATAFORMA");
        generos.add("SIMULACIÓN");
        generos.add("DEPORTES");
        generos.add("EDUCACIÓN");
        generos.add("ROL");
        plataformas = new ArrayList();
        plataformas.add("XBOX 360");
        plataformas.add("PLAYSTATION 3");
        plataformas.add("WII");
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setGeneros(List generos)
    {
        this.generos = generos;
    }

    public List getGeneros()
    {
        return generos;
    }

    public void setGeneroElegido(String generoElegido)
    {
        this.generoElegido = generoElegido;
    }

    public String getGeneroElegido()
    {
        return generoElegido;
    }

    public void setPlataformas(List plataformas)
    {
        this.plataformas = plataformas;
    }

    public List getPlataformas()
    {
        return plataformas;
    }

    public void setPlataformaElegida(String plataformaElegida)
    {
        this.plataformaElegida = plataformaElegida;
    }

    public String getPlataformaElegida()
    {
        return plataformaElegida;
    }

    public void setDescripcion(String descripcion)
    {
        this.descripcion = descripcion;
    }

    public String getDescripcion()
    {
        return descripcion;
    }

    public void setPuntosDelJuego(String puntosDelJuego)
    {
        this.puntosDelJuego = puntosDelJuego;
    }

    public String getPuntosDelJuego()
    {
        return puntosDelJuego;
    }

    public void setUnidadesDelJuego(String unidadesDelJuego)
    {
        this.unidadesDelJuego = unidadesDelJuego;
    }

    public String getUnidadesDelJuego()
    {
        return unidadesDelJuego;
    }

    public void setTieneDescuento(boolean tieneDescuento)
    {
        this.tieneDescuento = tieneDescuento;
    }

    public boolean isTieneDescuento()
    {
        return tieneDescuento;
    }

    public void cambiaModoHabilitado(ValueChangeEvent event)
    {
        boolean flag = ((Boolean)event.getNewValue()).booleanValue();
        setTieneDescuento(flag);
    }

    public void setPuntosDeDescuento(String puntosDeDescuento)
    {
        this.puntosDeDescuento = puntosDeDescuento;
    }

    public String getPuntosDeDescuento()
    {
        return puntosDeDescuento;
    }

    public void setEsNovedad(boolean esNovedad)
    {
        this.esNovedad = esNovedad;
    }

    public boolean isEsNovedad()
    {
        return esNovedad;
    }

    public void setEsProximamente(boolean esProximamente)
    {
        this.esProximamente = esProximamente;
    }

    public boolean isEsProximamente()
    {
        return esProximamente;
    }

    public String anyadirJuego(){
        String nombre = getNombre();
        EnumGeneroJuego genero = saberGenero();
        EnumPlataformaJuego plataforma = saberPlataforma();
        String descripcion = getDescripcion();
        
        String gameplay = "";
        String imagenGrande = "";
        String imagenPequenya = "";
        
        /*
        String imagenGrande = fileGrande.getFileName();
        uploadImagenGrande(filePequenya,"/Users/juandiegobarreraroman/Desktop/MASTER_WEB/MasterProyectoWeb/VistaControlador/public_html/resources/imagenGrande");

        String imagenPequenya = filePequenya.getFileName();
        uploadImagenPequenya(filePequenya,"/Users/juandiegobarreraroman/Desktop/MASTER_WEB/MasterProyectoWeb/VistaControlador/public_html/resources/imagenPequenya");

        */
        EnumBooleano tieneDescuento = EnumBooleano.NO;
        Integer descuento = Integer.valueOf(0);
        if(isTieneDescuento()){
            tieneDescuento = EnumBooleano.SI;
            descuento = Integer.valueOf(Integer.parseInt(getPuntosDeDescuento()));
        }
        
        EnumBooleano esNovedad = EnumBooleano.NO;
        if(isEsNovedad())
            esNovedad = EnumBooleano.SI;
            
        EnumBooleano esProximamente = EnumBooleano.NO;
        if(isEsProximamente())
            esProximamente = EnumBooleano.SI;
        
        Integer puntosDelJuego = Integer.valueOf(Integer.parseInt(getPuntosDelJuego()));
        Integer udsDelJuego = Integer.valueOf(Integer.parseInt(getUnidadesDelJuego()));
        eDao.persistJuego(nombre, genero, plataforma, descripcion, gameplay, imagenGrande, imagenPequenya, tieneDescuento, descuento, esNovedad, esProximamente, puntosDelJuego, udsDelJuego);
        return "correcto";
    }

    
        
    
    public EnumGeneroJuego saberGenero(){
        
        EnumGeneroJuego genero = EnumGeneroJuego.ACCION;
        
        if(getGeneroElegido().equals("ACCIÓN"))
            genero = EnumGeneroJuego.ACCION;
        
        if(getGeneroElegido().equals("LUCHA"))
            genero = EnumGeneroJuego.LUCHA;
        
        if(getGeneroElegido().equals("DISPAROS"))
            genero = EnumGeneroJuego.DISPAROS;
        
        if(getGeneroElegido().equals("PLATAFORMA"))
            genero = EnumGeneroJuego.PLATAFORMA;
        
        if(getGeneroElegido().equals("SIMULACIÓN"))
            genero = EnumGeneroJuego.SIMULACION;
        
        if(getGeneroElegido().equals("DEPORTES"))
            genero = EnumGeneroJuego.DEPORTES;
        
        if(getGeneroElegido().equals("EDUCACIÓN"))
            genero = EnumGeneroJuego.EDUCACION;
        
        if(getGeneroElegido().equals("ROL"))
            genero = EnumGeneroJuego.ROL;
        
        return genero;
    }

    public EnumPlataformaJuego saberPlataforma(){
        EnumPlataformaJuego plataforma = EnumPlataformaJuego.XBOX360;
        if(getPlataformaElegida().equals("XBOX 360"))
            plataforma = EnumPlataformaJuego.XBOX360;
        
        if(getPlataformaElegida().equals("PLAYSTATION 3"))
            plataforma = EnumPlataformaJuego.PLAYSTATION3;
        
        if(getPlataformaElegida().equals("WII"))
            plataforma = EnumPlataformaJuego.WII;
        
        return plataforma;
    }


    public void setFilePequenya(UploadedFile filePequenya) {
        this.filePequenya = filePequenya;
    }

    public UploadedFile getFilePequenya() {
        return filePequenya;
    }

    public void setFileGrande(UploadedFile fileGrande) {
        this.fileGrande = fileGrande;
    }

    public UploadedFile getFileGrande() {
        return fileGrande;
    }

    private void uploadImagenPequenya(UploadedFile uploadedFile, String ruta) {
        int linea = 0;
        byte bytes[] = new byte[1024];
        InputStream in = null;
        OutputStream out = null;
        /*
        //PATH DONDE DEBERIA IR UNA VEZ SUBIDA INDEFINIDAMENTE AL SERVIDOR
        String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/imagen");
        setPath(path);
        */
        
        try
        {
            
            in = uploadedFile.getInputstream();
            out = new FileOutputStream(new File(ruta + File.separator + filePequenya.getFileName().toString()));
            while((linea = in.read(bytes)) != -1) 
                out.write(bytes, 0, linea);
            
            in.close();
            out.flush();
            out.close();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "El archivo ", (new StringBuilder()).append(uploadedFile.getFileName()).append(" fue cargado de manera exitosa!!").toString());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        catch(IOException e)
        {
            e.printStackTrace();
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Error al subir el archivo");
            FacesContext.getCurrentInstance().addMessage("", mensaje);
        }
    }
    
    private void uploadImagenGrande(UploadedFile uploadedFile, String ruta) {
        int linea = 0;
        byte bytes[] = new byte[1024];
        InputStream in = null;
        OutputStream out = null;
        /*
        //PATH DONDE DEBERIA IR UNA VEZ SUBIDA INDEFINIDAMENTE AL SERVIDOR
        String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/imagen");
        setPath(path);
        */
        
        try
        {
            
            in = uploadedFile.getInputstream();
            out = new FileOutputStream(new File(ruta + File.separator + fileGrande.getFileName().toString()));
            while((linea = in.read(bytes)) != -1) 
                out.write(bytes, 0, linea);
            
            in.close();
            out.flush();
            out.close();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "El archivo ", (new StringBuilder()).append(uploadedFile.getFileName()).append(" fue cargado de manera exitosa!!").toString());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        catch(IOException e)
        {
            e.printStackTrace();
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Error al subir el archivo");
            FacesContext.getCurrentInstance().addMessage("", mensaje);
        }
    }

}
