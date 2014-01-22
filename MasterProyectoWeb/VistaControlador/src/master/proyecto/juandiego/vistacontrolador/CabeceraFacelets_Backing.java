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
import javax.faces.event.ValueChangeEvent;
import javax.faces.validator.ValidatorException;
import master.proyecto.juandiego.modelo.sessionbeans.interfazlocal.BuscadorDaoLocal;

@ManagedBean(name="cabeceraFaceletsBacking")
@RequestScoped
public class CabeceraFacelets_Backing{
    
    private String busqueda;
    private String tipoBusquedaElegida;
    private List opcionesBusqueda;
    List resultadoBusqueda;
    private boolean esConDescuento;
    @EJB
    private BuscadorDaoLocal bDao;

    public CabeceraFacelets_Backing()
    {
        opcionesBusqueda = new ArrayList();
        opcionesBusqueda.add("Nombre");
        opcionesBusqueda.add("Plataforma");
        opcionesBusqueda.add("Genero");
        opcionesBusqueda.add("Por descuento");
    }

    public void setBusqueda(String busqueda)
    {
        this.busqueda = busqueda;
    }

    public String getBusqueda()
    {
        return busqueda;
    }

    public void setTipoBusquedaElegida(String tipoBusquedaElegida)
    {
        this.tipoBusquedaElegida = tipoBusquedaElegida;
    }

    public String getTipoBusquedaElegida()
    {
        return tipoBusquedaElegida;
    }

    public void setOpcionesBusqueda(List opcionesBusqueda)
    {
        this.opcionesBusqueda = opcionesBusqueda;
    }

    public List getOpcionesBusqueda()
    {
        return opcionesBusqueda;
    }

    public void cambiaModoHabilitado(ValueChangeEvent event)
    {
        String valorTipoBusqueda = event.getNewValue().toString();
        
        if(valorTipoBusqueda.equals("Por descuento"))
        {
            setEsConDescuento(true);
            setTipoBusquedaElegida(valorTipoBusqueda);
        } else
        
        {
            setEsConDescuento(false);
            setTipoBusquedaElegida(valorTipoBusqueda);
        }
    }

    public void setEsConDescuento(boolean esConDescuento)
    {
        this.esConDescuento = esConDescuento;
    }

    public boolean isEsConDescuento()
    {
        return esConDescuento;
    }

    public void setResultadoBusqueda(List resultadoBusqueda)
    {
        this.resultadoBusqueda = resultadoBusqueda;
    }

    public List getResultadoBusqueda()
    {
        return resultadoBusqueda;
    }

    public void comprobarBusqueda(FacesContext context, UIComponent component, Object object){
        String busqueda = (String)object;
        Pattern p = Pattern.compile("[^\\W[\\s]]*");
        Matcher m = p.matcher(busqueda);
        boolean esCorrecto = m.matches();
        if(!esCorrecto){
            FacesMessage mensaje = new FacesMessage();
            mensaje.setSummary("Su bœsqyeda solo tiene que constar de letras y nœmeros, nunca simbolos");
            throw new ValidatorException(mensaje);
        } 
    }

    public String aBuscarJuegos()
    {
        if(getTipoBusquedaElegida().equals("Nombre"))
        {
            List juego = bDao.porNombre(getBusqueda());
            setResultadoBusqueda(juego);
        }
        if(getTipoBusquedaElegida().equals("Plataforma"))
        {
            List juego = bDao.porPlataforma(getBusqueda());
            setResultadoBusqueda(juego);
        }
        if(getTipoBusquedaElegida().equals("Genero"))
        {
            List juego = bDao.porGenero(getBusqueda());
            setResultadoBusqueda(juego);
        }
        if(getTipoBusquedaElegida().equals("Por descuento"))
        {
            List juego = bDao.conDescuento();
            setResultadoBusqueda(juego);
        }
        return "busqueda";
    }


}
