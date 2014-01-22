package master.proyecto.juandiego.vistacontrolador;


import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;

import javax.faces.application.Application;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import master.proyecto.juandiego.modelo.entitybeans.Juego;
import master.proyecto.juandiego.modelo.sessionbeans.interfazlocal.ExistenciaDaoLocal;
import master.proyecto.juandiego.modelo.sessionbeans.interfazlocal.PedidoDaoLocal;

@ManagedBean(name="anyadirProductoCestaBacking")
@SessionScoped
public class AnyadirProductoCesta_Backing {
    
    private String idJuego;
    
    private List<Juego> juegosAComprar;
    private List<Juego>temp;
    private int numJuegosEnCesta;


    
    private String udsCompra;
    private List<Integer> ludsCompra;
    private List<Integer> ludsCompratemp;
    private int contUds;
    
    //EJB
    @EJB
    private ExistenciaDaoLocal eDao;
    
    @EJB
    private PedidoDaoLocal pDao;
    
    public AnyadirProductoCesta_Backing() {
        temp = new ArrayList<Juego>();
        this.numJuegosEnCesta = 0;
        
        ludsCompratemp = new ArrayList<Integer>();
        contUds=0;    
    }

    public void setIdJuego(String idJuego) {
        this.idJuego = idJuego;
        
        Long i = Long.parseLong(idJuego);

        Juego j = (Juego) eDao.mostrarJuegoPorId(i);
        
        if(!temp.contains(j)){
            temp.add(j);
            setJuegosAComprar(temp);
            numJuegosEnCesta++;
        }
   

    }

    public String getIdJuego() {
        return idJuego;
    }
    

    public void setJuegosAComprar(List<Juego> juegosAComprar) {
        this.juegosAComprar = juegosAComprar;
    }

    public List<Juego> getJuegosAComprar() {
        return juegosAComprar;
    }


    public void setNumJuegosEnCesta(int numJuegosEnCesta) {
        this.numJuegosEnCesta = numJuegosEnCesta;
    }

    public int getNumJuegosEnCesta() {
        return numJuegosEnCesta;
    }

    public void setUdsCompra(String udsCompra) {
        this.udsCompra = udsCompra;
        
        System.out.println("entra en uds");
        Integer uds = Integer.parseInt(udsCompra);
        ludsCompratemp.add(uds);
        contUds++;
        
        if(contUds==numJuegosEnCesta){
            setLudsCompra(ludsCompratemp);
        }
        
        
    }

    public String getUdsCompra() {
        return udsCompra;
    }

    public void setLudsCompra(List<Integer> ludsCompra) {
        this.ludsCompra = ludsCompra;
    }

    public List<Integer> getLudsCompra() {
        return ludsCompra;
    }
    
    
    public void borrarArticuloCesta(){
        
        //juegosAComprar.remove();
        //temp.remove();
        numJuegosEnCesta--;
    }
    
    
    public String confirmarCompra(){
        
        FacesContext ctx = FacesContext.getCurrentInstance();
        Application app = ctx.getApplication();
        String idStringUsuario = (String) app.evaluateExpressionGet(ctx,"#{menuLoginBacking.usuario.idUsuario}",String.class);
        Long idUsuario = Long.parseLong(idStringUsuario);
        
        for(int i=0;i<numJuegosEnCesta;i++){
            Juego juego = getJuegosAComprar().get(i);
            Integer uds = getLudsCompra().get(i);
            pDao.anyadirPedido(idUsuario, juego, uds);
        }
        
        //INICIALIZAR ATRIBUTOS DE LA CESTA
        getJuegosAComprar().clear();
        temp.clear();
        numJuegosEnCesta=0;
        udsCompra="0";
        ludsCompra.clear();
        ludsCompratemp.clear();
        contUds=0;
        
        return "confirmado";
    }
}
