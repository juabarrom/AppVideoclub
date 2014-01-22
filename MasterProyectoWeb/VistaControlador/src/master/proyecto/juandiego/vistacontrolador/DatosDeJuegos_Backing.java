package master.proyecto.juandiego.vistacontrolador;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;
import master.proyecto.juandiego.modelo.entitybeans.Juego;
import master.proyecto.juandiego.modelo.entitybeans.Puntosexistencias;
import master.proyecto.juandiego.modelo.entitybeans.enumerados.*;
import master.proyecto.juandiego.modelo.sessionbeans.interfazlocal.ExistenciaDaoLocal;

@ManagedBean(name="datosDeJuegosBacking")
@ViewScoped
public class DatosDeJuegos_Backing{

    private String idJuego;
    private Juego juego;
    private String nombre;
    private String unidadesDelJuego;
    private String puntosDelJuego;
    private String puntosDescuento;
    private boolean esNovedad;
    private boolean esProximamente;
    private List generos;
    private List plataformas;
    private boolean tieneDescuento;
    
    @EJB
    private ExistenciaDaoLocal eDao;
    
    public DatosDeJuegos_Backing()
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

    public void setIdJuego(String idJuego)
    {
        this.idJuego = idJuego;
        initJuego();
    }

    public String getIdJuego()
    {
        return idJuego;
    }

    public void setJuego(Juego juego)
    {
        this.juego = juego;
    }

    public Juego getJuego()
    {
        return juego;
    }

    public void initJuego()
    {
        Long id = Long.valueOf(Long.parseLong(getIdJuego()));
        Juego juego = eDao.mostrarJuegoPorId(id);
        setJuego(juego);
    }

    public void setGeneros(List generos)
    {
        this.generos = generos;
    }

    public List getGeneros()
    {
        return generos;
    }

    public void setPlataformas(List plataformas)
    {
        this.plataformas = plataformas;
    }

    public List getPlataformas()
    {
        return plataformas;
    }

    public void cambiaModoHabilitado(ValueChangeEvent event)
    {
        boolean flag = ((Boolean)event.getNewValue()).booleanValue();
        setTieneDescuento(flag);
    }

    public void setTieneDescuento(boolean tieneDescuento)
    {
        this.tieneDescuento = tieneDescuento;
    }

    public boolean isTieneDescuento()
    {
        return tieneDescuento;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setUnidadesDelJuego(String unidadesDelJuego)
    {
        this.unidadesDelJuego = unidadesDelJuego;
    }

    public String getUnidadesDelJuego()
    {
        return unidadesDelJuego;
    }

    public void setPuntosDelJuego(String puntosDelJuego)
    {
        this.puntosDelJuego = puntosDelJuego;
    }

    public String getPuntosDelJuego()
    {
        return puntosDelJuego;
    }

    public void setPuntosDescuento(String puntosDescuento)
    {
        this.puntosDescuento = puntosDescuento;
    }

    public String getPuntosDescuento()
    {
        return puntosDescuento;
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

    public String modificarJuego(){
        
        String nombre = getNombre();
        if(getNombre().equals(""))
            nombre = juego.getNombre();
        
        EnumGeneroJuego genero = juego.getGenero();
        EnumPlataformaJuego plataforma = juego.getPlataforma();
        String descripcion = juego.getDescripcion();
        
        String gameplay = "";
        String imagenGrande = "";
        String imagenPequenya = "";
        
        EnumBooleano tieneDescuento = EnumBooleano.NO;
        Integer descuento = Integer.valueOf(0);
        
        if(!isTieneDescuento())
        {
            tieneDescuento = EnumBooleano.SI;
            descuento = Integer.valueOf(Integer.parseInt(getPuntosDescuento()));
        }
        
        EnumBooleano esNovedad = EnumBooleano.NO;
        if(isEsNovedad())
            esNovedad = EnumBooleano.SI;
        
        EnumBooleano esProximamente = EnumBooleano.NO;
        if(isEsProximamente())
            esProximamente = EnumBooleano.SI;
        
        Integer puntosDelJuego = Integer.valueOf(0);
        if(getPuntosDelJuego().equals(""))
            puntosDelJuego = juego.getPuntosexistencias().getPuntosJuego();
        else
            puntosDelJuego = Integer.valueOf(Integer.parseInt(getPuntosDelJuego()));
        
        Integer udsDelJuego = Integer.valueOf(0);
        if(!getUnidadesDelJuego().equals(""))
            udsDelJuego = Integer.valueOf(Integer.parseInt(getUnidadesDelJuego()));
        
        Long id = Long.valueOf(Long.parseLong(getIdJuego()));
        eDao.modificarJuegog(id, nombre, genero, plataforma, descripcion, gameplay, imagenGrande, imagenPequenya, tieneDescuento, descuento, esNovedad, esProximamente, puntosDelJuego, udsDelJuego);
        return "productos";
    }


}
